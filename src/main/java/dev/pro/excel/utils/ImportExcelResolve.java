package dev.pro.excel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ImportExcelResolve {

    public static final String EXCEL_SUFFIX_XLS = ".xls";
    public static final String EXCEL_SUFFIX_XLSX = ".xlsx";

    /**
     * 将 excel 数据多 sheet 页解析成 List
     * 适用单表导入
     *
     * @param file 导入的 excel 文件
     * @return List < Map < String, String>>
     * List < Map < 字段名, 字段值>>>
     * @throws Exception e
     */
    public static List<Map<String, String>> resolveExcelToList(MultipartFile file) throws Exception {
        Map<String, List<Map<String, String>>> map = resolveExcelFile(file, 1);
        List<Map<String, String>> list = new ArrayList<>();
        for (String key : map.keySet()) {
            list.addAll(map.get(key));
        }
        return list;
    }

    /**
     * 将 excel 数据按照不同的 sheet 页解析进 Map
     *
     * @param file     导入的 excel 文件
     * @param titleNum 表头有几行
     * @return Map < String, List < Map < String, String>>>
     * Map< Sheet 名, List < Map < 字段名, 字段值>>>
     * @throws Exception e
     */
    public static Map<String, List<Map<String, String>>> resolveExcelFile(MultipartFile file, int titleNum) throws Exception {
        //sheetMap：key 为 sheet名，value 为该页中所有行的集合
        Map<String, List<Map<String, String>>> sheetMap = new HashMap<>();
        //一个sheet页中所有行的集合，第一条数据为表头
        List<Map<String, String>> lineList;
        //LineMap：key为字段名，value为cell值
        Map<String, String> LineMap;
        Workbook workbook;
        try (InputStream inputStream = file.getInputStream()) {
            String originalFileName = file.getOriginalFilename();
            if (originalFileName.endsWith(EXCEL_SUFFIX_XLSX)) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (originalFileName.endsWith(EXCEL_SUFFIX_XLS)) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IOException("文件格式不对");
            }
        } catch (IOException e) {
            throw new IOException("EXCEL 打开错误");
        }

        int sheetNum = workbook.getNumberOfSheets();
        //循环所有 sheet 页
        for (int i = 0; i < sheetNum; i++) {
            boolean isFirstRow = true;
            Map<Integer, String> titleMap = new HashMap<>();
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet.getPhysicalNumberOfRows() == 0) {
                continue;
            }
            lineList = new ArrayList<>();
            //循环遍历sheet页中所有的行
            for (int numRow = sheet.getFirstRowNum(); numRow <= sheet.getLastRowNum(); numRow++) {
                LineMap = new HashMap<>();
                Row row = sheet.getRow(numRow);
                if (null == row) {
                    continue;
                }
                //获取行中所有的第一列
                int numbCell = row.getFirstCellNum();
                //获取行中所有的最后一列
                int lastNum = row.getLastCellNum();
                for (; numbCell < lastNum; numbCell++) {
                    Cell cell = row.getCell(numbCell);
                    String value = handleCellDataType(cell);
                    if (row.getRowNum() < titleNum) {
                        if (value != null && !value.isEmpty()) {
                            titleMap.put(numbCell, value);
                        }
                    } else {
                        LineMap.put(titleMap.get(numbCell), value);
                    }
                }
                if (row.getRowNum() >= titleNum) {
                    lineList.add(LineMap);
                }
                isFirstRow = false;
            }
            sheetMap.put(sheet.getSheetName(), lineList);
        }
        return sheetMap;
    }

    /**
     * 单元格不同数据类型数值处理
     *
     * @param cell Cell
     * @return 单元格值的字符串格式
     */
    private static String handleCellDataType(Cell cell) {
        if (null == cell) {
            return "";
        }
        String value = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                value = numberResolve(cell);
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_FORMULA:
                try {
                    value = String.valueOf(cell.getStringCellValue());
                } catch (IllegalStateException e) {
                    value = numberResolve(cell);
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 数字格式处理
     *
     * @param cell 单元格
     * @return 值
     */
    private static String numberResolve(Cell cell) {
        String format = cell.getCellStyle().getDataFormatString();
        String value = "";
        // 格式化 number
        DecimalFormat df = new DecimalFormat("#.#########");
        // 日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 格式化数字
        DecimalFormat df2 = new DecimalFormat("0.00");
        //TODO 数字处理完善
        if ("General".equals(format)) {
            value = df.format(cell.getNumericCellValue());
        } else if ("m/d/yy".equals(format)) {
            value = sdf.format(cell.getDateCellValue());
        } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
            value = sdf.format(cell.getDateCellValue());
        } else {
            value = df2.format(cell.getNumericCellValue());
        }
        return value;
    }

}

