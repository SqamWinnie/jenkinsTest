package javabase.文件.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javabase.文件.excel.utils.ColumnInfo;
import javabase.文件.excel.utils.CreateExcel;
import javabase.文件.excel.utils.ExportConfig;
import javabase.文件.excel.utils.ExportExcel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {

    private static final Logger logger = Logger.getLogger("Test");

    public static void main(String[] args) {
        testData();
    }


    /**
     * 导出测试1--excel 2007.
     * 数据：事先写好的数据
     */
    private static void testData() {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        String sheetName1 = "页名称1";
        String sheetName2 = "页名称2";
        String[] title1 = {"到场", "车位", "卸货标准时长（分钟）", "业务板块", "组织", "部门", "启用", "更新时间"};
        String[] title2 = {"标题2", "标题", "标题长度", "标题长度测试标题长度测试", "标题长度测试标题长度测试测试测试测试1"};
        // 根据内容得到行数，此处设置为固定值
        int rowLength = 10;
        String[][] content1 = new String[rowLength][title1.length];
        for (int i = 0; i < rowLength; i++) {
            content1[i][0] = String.valueOf("标题长度测试标题长度测试测试测试测试");
            content1[i][1] = String.valueOf("99");
            content1[i][2] = String.valueOf("99");
            content1[i][3] = String.valueOf("东风日产");
            content1[i][4] = String.valueOf("厂内物流事业部");
            content1[i][5] = String.valueOf("厂内综合管理科");
            content1[i][6] = String.valueOf("Y");
            content1[i][7] = String.valueOf("2018/12/29");
        }
        String[][] content2 = new String[rowLength][title2.length];
        for (int i = 0; i < rowLength; i++) {
            content2[i][0] = String.valueOf("标题长度测试标题长度测试测试测试测试");
            content2[i][1] = String.valueOf("99");
            content2[i][2] = String.valueOf("99");
            content2[i][3] = String.valueOf("东风日产");
            content2[i][4] = String.valueOf("厂内物流事业部");
        }
        /*XSSFWorkbook wb = new XSSFWorkbook();
        CreateExcel c = new CreateExcel();
        wb = c.getXSSFSheet(0, sheetName1, title1, content1, wb);
        wb = c.getXSSFSheet(1, sheetName2, title2, content2, wb);*/
        content2[rowLength-1][0] = String.valueOf("--合计--");
        XSSFWorkbook wb = new XSSFWorkbook();
        ExportExcel.getXssfSheet(0, sheetName1, title1, content1, wb);
        ExportExcel.getXssfSheet(1, sheetName2, title2, content2, wb);
        try {
            FileOutputStream out = new FileOutputStream("D:/zz_upload/测试" + fileName);
            wb.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            logger.info("文件夹不存在");
        } catch (IOException e) {
            logger.info("输出错误");
        }
    }

   /**
     * 导出测试2--excel 2007(1).
     * 数据：模拟传入的数据 Controller 层
     */
    private static void testSimulation() {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";

        List<ColumnInfo> columns1 = new ArrayList<>();
        ColumnInfo info1 = new ColumnInfo();
        info1.setTitle("编号");
        info1.setName("id");
        info1.setType("Long");
        info1.setWidth(120);
        info1.setAlign("center");

        columns1.add(info1);
        ColumnInfo info2 = new ColumnInfo();
        info2.setTitle("名称");
        info2.setName("name");
        info2.setType("String");
        info2.setWidth(120);
        info2.setAlign("left");

        columns1.add(info2);
        ColumnInfo info3 = new ColumnInfo();
        info3.setTitle("年龄");
        info3.setName("age");
        info3.setType("int");
        info3.setWidth(120);
        info3.setAlign("left");
        columns1.add(info3);

        ColumnInfo info4 = new ColumnInfo();
        info4.setTitle("属性");
        info4.setName("property");
        info4.setType("String");
        info4.setWidth(120);
        info4.setAlign("left");
        columns1.add(info4);

        ColumnInfo info5 = new ColumnInfo();
        info5.setTitle("生日");
        info5.setName("birthday");
        info5.setType("Date");
        info5.setWidth(120);
        info5.setAlign("left");
        columns1.add(info5);

        ColumnInfo info6 = new ColumnInfo();
        info6.setTitle("时间");
        info6.setName("time");
        info6.setType("Date");
        info6.setWidth(120);
        info6.setAlign("left");
        columns1.add(info6);

        List<ColumnInfo> columns2 = new ArrayList<>();
        columns2.add(info1);
        columns2.add(info2);
        columns2.add(info3);
        columns2.add(info4);
        columns2.add(info5);
        columns2.add(info6);

        List<String> sheetNames = new ArrayList<>();
        sheetNames.add("页1");
        sheetNames.add("页2");

        List<ExportConfig> configList = new ArrayList<>();
        ExportConfig<Dto,ColumnInfo> exportConfig1 = new ExportConfig<>();
        ExportConfig<Dto,ColumnInfo> exportConfig2 = new ExportConfig<>();
        exportConfig1.setColumnsInfo(columns1);
        exportConfig1.setFileName(fileName);
        exportConfig2.setColumnsInfo(columns2);
        exportConfig2.setFileName(fileName);
        configList.add(exportConfig1);
        configList.add(exportConfig2);

        exportExcel(configList);
    }

   /**
     * 导出测试2--excel 2007(2).
     * 数据：模拟传入的数据 Service 层
     */
    private static void exportExcel(List<ExportConfig> exportConfigs) {
        // 获取 Config 类中的参数
        String fileName = exportConfigs.get(0).getFileName();
        Dto param0 = (Dto) exportConfigs.get(0).getParam();
        Dto param1 = (Dto) exportConfigs.get(1).getParam();

        // 根据 param 查询出来的内容 list<Dto>
        List<Dto> dto1 = new ArrayList<>();
        List<Dto> dto2 = new ArrayList<>();
        int sum1 = 0,sum2 = 0;
        for (int i = 1; i < 16; i++) {
            Dto dto = new Dto();
            dto.setId((long) i);
            dto.setName("名字1-" + i);
            dto.setAge((int) (10 + i));
            dto.setBirthday(new Date());
            dto.setTime(new Date());
            dto.setProperty("属性1-" + i);
            sum1 = sum1 + i;
            dto1.add(dto);
        }
        for(int i = 0; i < 9; i++){
            Dto dto = new Dto();
            dto.setId((long) i);
            dto.setName("名字2-"+i);
            dto.setAge((int) (20+i));
            dto.setBirthday(new Date());
            dto.setTime(new Date());
            dto.setProperty("属性2-"+i);
            sum2 = sum2 + i;
            dto2.add(dto);
        }
        XSSFWorkbook wb = new XSSFWorkbook();
        CreateExcel c = new CreateExcel();
        wb = c.getXSSFSheet(0, "ye1", exportConfigs.get(0).getColumnsInfo(), dto1, wb);
        wb = c.getXSSFSheet(1, "ye2", exportConfigs.get(1).getColumnsInfo(), dto2, wb);

        // 合计列的内容

        try {
            FileOutputStream out = new FileOutputStream("D:/zz_upload/测试" + fileName);
            wb.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            logger.info("文件夹不存在1");
        } catch (IOException e) {
            logger.info("输出错误1");
        }
    }

}
