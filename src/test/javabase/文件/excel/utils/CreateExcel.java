package javabase.文件.excel.utils;

import static java.util.regex.Pattern.compile;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导出 Excel - 模拟 controller .
 */
public class CreateExcel<T> {

    /**
     * 多 sheet 创建 Excel （传入模拟 ExportConfig 属性）
     *
     * @param sheetIndex sheet页序列
     * @param sheetName  sheet页名字
     * @param columns      标题数据
     * @param dtoList    内容数据
     * @param wb         excel 工作簿
     * @return excel 工作簿
     */
    public XSSFWorkbook getXSSFSheet(int sheetIndex, String sheetName, List<ColumnInfo> columns, List<T> dtoList, XSSFWorkbook wb) {
        // 1. 创建 workbook
        if (wb == null) {
            wb = new XSSFWorkbook();
        }
        // 2. 创建 sheet 页
        XSSFSheet sheet = wb.createSheet();
        wb.setSheetName(sheetIndex, sheetName);
        // 设置工作表默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 设置筛选框
        CellRangeAddress c = rangeSelect(columns.size());
        sheet.setAutoFilter(c);
        // 3. 添加行(表头)
        XSSFRow row = sheet.createRow(0);
        /* 设置标题行高：row.setHeight((short) (25 * 14)); */
        // 4. 创建单元格，声明列对象
        XSSFCell cell = null;
        // 5. 创建样式
        XSSFCellStyle styleTitle = ExportExcel.getStyleTitle(wb);
        XSSFCellStyle styleContent = ExportExcel.getStyleContent(wb, false);
        // 6. 设置标题
        for (int i = 0; i < columns.size(); i++) {
            cell = row.createCell(i + 1);
            cell.setCellValue(columns.get(i).getTitle());
            cell.setCellStyle(styleTitle);
        }
        // 7. 设置内容
        for (int i = 0; i < dtoList.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < columns.size(); j++) {
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j + 1);
                formatValue(cell,dtoList.get(i),columns.get(j).getName());
                cell.setCellStyle(styleContent);
            }
        }
        // 8. 设置序号
        autoIndex(sheet, dtoList.size(),styleTitle,styleContent);
        // 9. 设置列宽
        autoWeight(sheet, columns.size());
        return wb;
    }

    /**
     * 设置列宽
     *
     * @param sheet  页
     */
    private static void autoWeight(XSSFSheet sheet, int columnNum) {
        //让列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < columnNum; colNum++) {
            // 系统自动列宽
            sheet.autoSizeColumn(colNum);
            int columnWidth = sheet.getColumnWidth(colNum)/256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    // 固定长度
                    // XSSFCell currentCell = currentRow.getCell(colNum);
                    // int length = currentCell.getStringCellValue().getBytes().length;
                    int length = 14;
                    if (columnWidth < length) {
                        columnWidth = length;
                    }
                }
            }
            columnWidth = columnWidth - 1;
            sheet.setColumnWidth(colNum, columnWidth * 256);
        }
    }

    /**
     * 设置筛选框
     * @param num  标题列数
     * @return  筛选框
     */
    private static CellRangeAddress rangeSelect(int num){
        char characters = (char) ('A' + num);
        return CellRangeAddress.valueOf("A1:"+characters+"1");
    }

    /**
     * 设置序号
     * @param sheet 页
     * @param size 内容行数
     */
    private static void autoIndex(XSSFSheet sheet, int size, XSSFCellStyle styleTitle, XSSFCellStyle styleContent){
        XSSFCell cell = null;
        cell = sheet.getRow(0).createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(styleTitle);
        for (int i = 1; i < size + 1; i++) {
            //将内容按顺序赋给对应的列对象
            cell = sheet.getRow(i).createCell(0);
            cell.setCellValue(i);
            cell.setCellStyle(styleContent);
        }
    }

    /**
     * 取单元格的值
     * @param cell 单元格
     * @param t  实体类
     * @param name  属性名
     */
    private  void formatValue(XSSFCell cell, T t,String name){
        String method = getMethodName(name,false);
        Object value = runMethod(t,method);
        // 如果是时间类型,按照格式转换
        String textValue = null;
        if (value instanceof Date) {
            Date date = (Date) value;
            String methodName = getMethodName(name,true);
            String pattern = runMethod(t,methodName).toString();
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            textValue = sdf.format(date);
        } else if (value != null){
            // 其它数据类型都当作字符串简单处理
            textValue = value.toString();
        }

        // 利用正则表达式判断textValue是否全部由数字组成
        if (textValue != null) {
            Pattern p = compile("^\\d+(\\.\\d+)?$");
            Matcher matcher = p.matcher(textValue);
            if (matcher.matches()) {
                // 是数字当作double处理
                cell.setCellValue(Double.parseDouble(textValue));
            } else {
                // 不是数字做普通处理
                cell.setCellValue(textValue);
            }
        }
    }

    /**
     * 通过属性名转换成方法名.
     *
     * @param fieldName 属性名
     * @param isDate    是否获取时间格式
     * @return 方法的返回值
     */
    private static String getMethodName(String fieldName, Boolean isDate) {
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        if (null != isDate && isDate) {
            methodName = methodName + "Pattern";
        }
        return methodName;
    }

    /**
     * 运行 get 方法.
     *
     * @param t          实体类
     * @param methodName 方法名
     * @return 方法的返回值
     */
    private Object runMethod(T t, String methodName) {
        Class dto;
        dto = t.getClass();
        Method getMethod = null;
        try {
            getMethod = dto.getMethod(methodName);
            Object value = getMethod.invoke(t);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
