package dev.pro.excel.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mysql.jdbc.StringUtils;
import dev.com.alibaba.excel.EasyExcel;
import dev.pro.excel.dto.PartyInfo;
import dev.pro.excel.dto.PartyInfoDown;
import dev.pro.excel.dto.PartyInfoExcel;
import dev.pro.excel.mapper.PartyInfoMapper;
import dev.pro.excel.service.IPartyInfoService;
import dev.pro.excel.utils.ImportExcelResolve;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ...
 */

@Service
public class PartyInfoServiceImpl implements IPartyInfoService {

    private static Logger logger = Logger.getLogger(PartyInfoServiceImpl.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String NOT_NULL = "字段不能为空";
    private static final String NOT_RIGHT = "字段填写不正确";

    @Autowired
    PartyInfoMapper partyInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void resolveExcel(MultipartFile file) {
        Long begin = System.currentTimeMillis();
        logger.info("解析 Excel-------开始 " + begin);
        // Excel 转换成 list
        List<Map<String, String>> list = new ArrayList<>();
        try {
            list = ImportExcelResolve.resolveExcelFile(file, 3).get("Sheet2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        logger.info("解析 Excel-------结束 " + end);
        logger.info("解析 Excel 时间 " + (end - begin) / 1000 + " s");

        try {
            // 转换成对象 list
            Long begin1 = System.currentTimeMillis();
            logger.info("转换对象-------开始 " + begin1);
            List<PartyInfo> infoList = excelToPartyInfoList(list);
            Long end1 = System.currentTimeMillis();
            logger.info("转换对象-------结束 " + end1);
            logger.info("转换对象时间 " + (end1 - begin1) / 1000 + " s");

            // 方法一：分批次保存到数据表
            //batchInsert(infoList);
            // 方法二：多线程分批次保存
            multiThreadBatchInsert(infoList);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            throw e;
        }

    }

    @Override
    public void save(Map requestMap) {
        List<PartyInfoExcel> data = (List<PartyInfoExcel>) requestMap.get("list");
        List<PartyInfo> list = new ArrayList<>();
        data.forEach(dto ->{
            PartyInfo pid = new PartyInfo();
            BeanUtils.copyProperties(dto, pid);
            list.add(pid);
        });
        batchInsert(list);
    }

    @Override
    public void saveThread(Map requestMap) {
        List<PartyInfoExcel> data = (List<PartyInfoExcel>) requestMap.get("list");
        List<PartyInfo> list = new ArrayList<>();
        data.forEach(dto ->{
            PartyInfo pid = new PartyInfo();
            BeanUtils.copyProperties(dto, pid);
            list.add(pid);
        });
        multiThreadBatchInsert(list);
    }

    @Override
    public void export(HttpServletResponse response) {
        List<PartyInfo> list = partyInfoMapper.selectAll();
        List<PartyInfoDown> data = new ArrayList<>();
        list.forEach(dto ->{
            PartyInfoDown pid = new PartyInfoDown();
            BeanUtils.copyProperties(dto, pid);
            data.add(pid);
        });
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里 URLEncoder.encode 可以防止中文乱码 当然和 easyexcel 没有关系
            String fileName = null;
            fileName = URLEncoder.encode("文件_导出", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), PartyInfoDown.class).sheet("模板").doWrite(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 excel 数据转化成 PartyInfo 对象列表
     *
     * @param list Excel 数据
     * @return PartyInfo 列表
     */
    private List<PartyInfo> excelToPartyInfoList(List<Map<String, String>> list) {
        List<PartyInfo> piList = new ArrayList<>();
        for (Map<String, String> map : list) {
            PartyInfo partyInfo = new PartyInfo();
            for (String key : map.keySet()) {
                // 列名为空时跳过
                if (StringUtils.isNullOrEmpty(key)) {
                    continue;
                }
                switch (key) {
                    case PartyInfo.PARTY_NUMBER:
                        if (StringUtils.isNullOrEmpty(map.get(key))) {
                            throw new RuntimeException(PartyInfo.PARTY_NUMBER + NOT_NULL);
                        }
                        partyInfo.setPartyNumber(map.get(key).trim());
                        break;
                    case PartyInfo.PARTY_NAME:
                        partyInfo.setPartyName(map.get(key).trim());
                        break;
                    case PartyInfo.PARTY_CODE:
                        try {
                            partyInfo.setPartyCode(Long.valueOf(map.get(key).trim()));
                        } catch (Exception e) {
                            logger.error("excel 数据转化 PartyInfo 对象失败");
                            logger.error(PartyInfo.PARTY_CODE + NOT_RIGHT);
                            logger.error(e.getStackTrace());
                        }
                        break;
                    case PartyInfo.ADDRESS:
                        partyInfo.setAddress(map.get(key).trim());
                        break;
                    case PartyInfo.PHONE_NUMBER:
                        partyInfo.setPhoneNumber(map.get(key).trim());
                        break;
                    case PartyInfo.START_DATE:
                        if (StringUtils.isNullOrEmpty(map.get(key))) {
                            continue;
                        }
                        Date startDate = null;
                        try {
                            startDate = sdf.parse(map.get(key).trim());
                        } catch (ParseException e) {
                            logger.error("excel 数据转化 PartyInfo 对象失败");
                            logger.error(PartyInfo.START_DATE + NOT_RIGHT);
                            logger.error(e.getStackTrace());
                        }
                        partyInfo.setStartDate(startDate);
                        break;
                    case PartyInfo.END_DATE:
                        if (StringUtils.isNullOrEmpty(map.get(key))) {
                            continue;
                        }
                        Date endDate = null;
                        try {
                            endDate = sdf.parse(map.get(key).trim());
                        } catch (ParseException e) {
                            logger.error("excel 数据转化 PartyInfo 对象失败");
                            logger.error(PartyInfo.END_DATE + NOT_RIGHT);
                            logger.error(e.getStackTrace());
                        }
                        partyInfo.setEndDate(endDate);
                        break;
                    case PartyInfo.PARTY_AMOUNT:
                        if (StringUtils.isNullOrEmpty(map.get(key))) {
                            continue;
                        }
                        try {
                            BigDecimal partyAmount = new BigDecimal(map.get(key).trim());
                            partyInfo.setPartyAmount(partyAmount);
                        } catch (Exception e) {
                            logger.error("excel 数据转化 PartyInfo 对象失败");
                            logger.error(PartyInfo.PARTY_AMOUNT + NOT_RIGHT);
                            logger.error(e.getStackTrace());
                        }
                        break;
                    default:
                        break;
                }
            }
            // 限制开始日期小于等于结束日期
            Date start = partyInfo.getStartDate();
            Date end = partyInfo.getEndDate();
            if (null != start && null != end) {
                if (start.after(end)) {
                    throw new RuntimeException("项目开始时间应大于等于项目结束时间！");
                }
            } else if (null != start || null != end) {
                throw new RuntimeException("请完善项目时间！");
            }
            piList.add(partyInfo);
        }
        return piList;
    }

    /**
     * 批量保存到数据表（分批次保存）
     *
     * @param list 数据
     */
    private void batchInsert(List<PartyInfo> list) {
        // 开始时间
        Long begin = System.currentTimeMillis();
        logger.info("保存--------开始 " + begin);
        int pageSize = 500;
        int totalSize = list.size();
        int totalPage = totalSize / pageSize;
        if (totalSize % pageSize != 0) {
            totalPage += 1;
            if (totalSize < pageSize) {
                pageSize = list.size();
            }
        }
        for (int pageNum = 1; pageNum < totalPage + 1; pageNum++) {
            int starNum = (pageNum - 1) * pageSize;
            int endNum = pageNum * pageSize > totalSize ? (totalSize) : pageNum * pageSize;
            partyInfoMapper.batchInsert(list.subList(starNum, endNum));
        }

        // 耗时
        Long end = System.currentTimeMillis();
        logger.info("保存--------结束 ");
        logger.info("保存时间 : " + (end - begin) / 1000 + " s");
    }

    /**
     * 批量保存到数据表（多线程 + 分批次）
     *
     * @param partyInfoList 数据
     */
    private void multiThreadBatchInsert(List<PartyInfo> partyInfoList) {
        Long begin = System.currentTimeMillis();
        logger.info("多线程保存-------开始 " + begin);
        // 创建线程池
        ThreadFactory tf = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(10,
                15, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), tf,
                new ThreadPoolExecutor.AbortPolicy());
        BatchInsertThread bi = new BatchInsertThread(partyInfoMapper, partyInfoList, 500);
        pool.execute(bi);
        pool.shutdown();
        try {
            //可以让while循环每2s执行一次, 而不是一直循环消耗性能
            while(!pool.awaitTermination(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        logger.info("多线程保存-------结束 " + end);
        logger.info("多线程保存时间 " + (end - begin) / 1000 + " s");
    }
}
