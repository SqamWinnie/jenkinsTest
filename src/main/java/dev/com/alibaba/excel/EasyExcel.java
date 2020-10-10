package dev.com.alibaba.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 重写 EasyExcel.read()
 */
public class EasyExcel extends EasyExcelFactory {

    /**
     * 重载 request 参数进行解析
     *
     * @param request      页面请求参数
     * @param head         Excel 实体类
     * @param readListener 监听器
     * @return ExcelReaderBuilder
     */
    public static ExcelReaderBuilder read(HttpServletRequest request, Class head, ReadListener readListener) {
        ExcelReaderBuilder excelReaderBuilder = new ExcelReaderBuilder();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List items = upload.parseRequest(request);
            if (!items.isEmpty()) {
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        InputStream in = item.getInputStream();
                        excelReaderBuilder.file(in);
                        if (head != null) {
                            excelReaderBuilder.head(head);
                        }
                        if (readListener != null) {
                            excelReaderBuilder.registerReadListener(readListener);
                        }
                    }
                }
                return excelReaderBuilder;
            }
        } catch (FileUploadException | IOException e) {
            e.printStackTrace();
        }
        return excelReaderBuilder;
    }
}
