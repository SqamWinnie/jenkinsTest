package javabase.文件.excel.utils;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * 导出 excel
 * @author --
 * @date 2019/5/10
 */

/**
 * 导出 excel.
 */
public class ExportExcel {

    private ExportExcel() {
    }

    /**
     * 多 sheet 创建 Excel （传入数组）.
     *
     * @param sheetIndex sheet页序列
     * @param sheetName  sheet页名字
     * @param title      标题数组
     * @param content    内容数组
     * @param wb         excel 工作簿
     */
    public static void getXssfSheet(int sheetIndex, String sheetName, String[] title, String[][] content, XSSFWorkbook wb) {
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
        CellRangeAddress c = rangeSelect(title.length);
        sheet.setAutoFilter(c);
        // 3. 添加行(表头)
        XSSFRow row = sheet.createRow(0);
        // 4. 创建单元格，声明列对象
        XSSFCell cell = null;
        // 5. 创建样式
        XSSFCellStyle styleTitle = getStyleTitle(wb);
        XSSFCellStyle styleContent = getStyleContent(wb, false);
        XSSFCellStyle styleSum = getStyleContent(wb, true);
        XSSFCellStyle styleText;
        // 6. 设置标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(styleTitle);
        }
        // 7. 设置内容
        for (int i = 0; i < content.length; i++) {
            row = sheet.createRow(i + 1);
            if (null != content[i][0] && content[i][0].contains("合计")) {
                styleText = styleSum;
            } else {
                styleText = styleContent;
            }
            for (int j = 0; j < content[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(content[i][j]);
                cell.setCellStyle(styleText);
            }
        }
        // 8. 设置列宽
        autoWeight(sheet, title.length);
    }

    /**
     * 设置筛选框.
     *
     * @param num 标题列数
     * @return 筛选框
     */
    private static CellRangeAddress rangeSelect(int num) {
        char characters = (char) ('A' + num - 1);
        return CellRangeAddress.valueOf("A1:" + characters + "1");
    }

    /**
     * 设置标题样式.
     *
     * @param wb 工作簿
     * @return 表格样式
     */
    protected static XSSFCellStyle getStyleTitle(XSSFWorkbook wb) {
        //设置字体大小，不加粗，字体类型，颜色
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setBold(false);
        font.setFontName("宋体");
        font.setColor(IndexedColors.WHITE.getIndex());

        // 创建样式
        XSSFCellStyle style = wb.createCellStyle();
        // 样式应用字体
        style.setFont(font);
        // 设置自动换行
        style.setWrapText(false);
        // 设置水平:左对齐；垂直：居中
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置单元格背景颜色：灰色
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    /**
     * 设置表格样式.
     *
     * @param wb 工作簿
     * @return 表格样式
     */
    protected static XSSFCellStyle getStyleContent(XSSFWorkbook wb, Boolean isSum) {
        //设置字体大小，不加粗，字体类型，颜色
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setBold(false);
        font.setFontName("宋体");
        font.setColor(IndexedColors.BLACK.getIndex());

        // 创建样式
        XSSFCellStyle style = wb.createCellStyle();
        // 样式应用字体
        style.setFont(font);
        // 设置自动换行
        style.setWrapText(false);
        // 设置水平:左对齐；垂直：居中
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        if (isSum) {
            // 设置单元格背景颜色：灰色
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        return style;
    }

    /**
     * 设置列宽.
     *
     * @param sheet 页
     */
    private static void autoWeight(XSSFSheet sheet, int columnNum) {
        //让列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < columnNum; colNum++) {
            // 系统自动列宽
            sheet.autoSizeColumn(colNum);
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
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
                    int length = 20;
                    if (columnWidth < length) {
                        columnWidth = length;
                    }
                }
            }
            columnWidth = columnWidth - 1;
            sheet.setColumnWidth(colNum, columnWidth * 256);
        }
    }
}

