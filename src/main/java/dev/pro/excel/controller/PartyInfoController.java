package dev.pro.excel.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.com.Result;
import dev.com.alibaba.excel.EasyExcel;
import dev.pro.excel.dto.PartyInfoExcel;
import dev.pro.excel.service.IPartyInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ...
 */
@Controller
@RequestMapping(value = "/party/info")
public class PartyInfoController {

    @Autowired
    IPartyInfoService partyInfoService;
    private static Logger logger = Logger.getLogger(PartyInfoController.class);

    /**
     * 导入 Excel.
     * @param request 页面请求参数
     * @param multipartFile 参数 partyInfo 是 input 的 name 属性
     */
    @RequestMapping(value = "/importXls", method = {RequestMethod.POST})
    @ResponseBody
    public Result importXls(HttpServletRequest request, @RequestParam("partyInfo") MultipartFile multipartFile) {
        // 开始时间
        Long begin = System.currentTimeMillis();
        Result rs = new Result(true);
        try {
            logger.info("导入 Excel 开始 " + begin);
            // 方法一：使用 request 接收并转换成文件类型
            // MultipartHttpServletRequest multipartRequest= (MultipartHttpServletRequest)request;
            //MultipartFile requestFile = multipartRequest.getFile("partyInfo");
            // 方法二：直接使用 MultipartFile 接收文件
            partyInfoService.resolveExcel(multipartFile);
            // 耗时
            Long end = System.currentTimeMillis();
            logger.info("导入 Excel 结束 " + end);
            logger.info("导入 Excel 总时间 " + (end - begin) / 1000 + " s");
            rs.setMessage("导入 Excel 开始时间 " + begin
                    + "\n导入 Excel 结束时间 " + end
                    + "\n导入 Excel 花费时间 : " + (end - begin) / 1000 + " s");
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            rs.setDatas(Arrays.asList(e.getStackTrace()));
            rs.setSuccess(false);
        }
        return rs;
    }

    /**
     * easy - 导入 Excel.
     * @param request 页面请求参数
     * @param multipartFile 参数 partyInfo 是 input 的 name 属性
     */
    @RequestMapping(value = "/importXls/easy", method = {RequestMethod.POST})
    @ResponseBody
    public Result importXlsEasy(HttpServletRequest request, @RequestParam("partyInfo") MultipartFile multipartFile) {
        // 开始时间
        Long begin = System.currentTimeMillis();
        Result rs = new Result(true);
        try {
            logger.info("导入 Excel 开始 " + begin);
            EasyExcel.read(multipartFile.getInputStream(),
                            PartyInfoExcel.class,
                            new PartyInfoListener(partyInfoService, request))
                    .sheet("Sheet2").doRead();
            // 耗时
            Long end = System.currentTimeMillis();
            logger.info("导入 Excel 结束 " + end);
            logger.info("导入 Excel 总时间 " + (end - begin) / 1000 + " s");
            rs.setMessage("导入 Excel 花费时间 : " + (end - begin) / 1000 + " s");
        } catch (Exception e) {
            e.printStackTrace();
            rs.setSuccess(false);
            rs.setMessage(e.getMessage());
        }
        return rs;
    }

    /**
     * easy - 导出 Excel.
     * @param response 响应参数
     */
    @RequestMapping(value = "/exportXls/easy")
    @ResponseBody
    public Result exportXlsEasy(HttpServletResponse response) {
        // 开始时间
        Long begin = System.currentTimeMillis();
        Result rs = new Result(true);
        try {
            logger.info("导出 Excel 开始 " + begin);
            partyInfoService.export(response);
            // 耗时
            Long end = System.currentTimeMillis();
            logger.info("导出  Excel 结束 " + end);
            logger.info("导出  Excel 总时间 " + (end - begin) / 1000 + " s");
            rs.setMessage("导出 Excel 花费时间 : " + (end - begin) / 1000 + " s");
        } catch (Exception e) {
            e.printStackTrace();
            rs.setSuccess(false);
            rs.setMessage(e.getMessage());
        }
        return rs;
    }

}
