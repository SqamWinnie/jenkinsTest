package dev.pro.excel.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ...
 */

public interface IPartyInfoService {

    /**
     * 解析 Excel
     * @param file excel
     */
    void resolveExcel(MultipartFile file);

    /**
     * 批量保存
     * @param requestMap 数据
     */
    void save(Map requestMap);


    /**
     * 批量保存 - 多线程
     * @param requestMap 数据
     */
    void saveThread(Map requestMap);

    /**
     * easyExcel 导出 Excel
     * @param response 响应参数
     */
    void export(HttpServletResponse response);
}
