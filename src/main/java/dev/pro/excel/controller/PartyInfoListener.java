package dev.pro.excel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import dev.pro.excel.dto.PartyInfoExcel;
import dev.pro.excel.service.IPartyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @date 2019/12/13
 */
public class PartyInfoListener extends AnalysisEventListener<PartyInfoExcel> {

    private static final Logger logger = LoggerFactory.getLogger(PartyInfoListener.class);

    private static final int BATCH_COUNT = 10000;

    private List<PartyInfoExcel> list = new ArrayList<>();

    private IPartyInfoService service;

    private Map requestMap = new HashMap();

    PartyInfoListener(IPartyInfoService service, HttpServletRequest request){
        this.service = service;
        // this.requestMap.put("request", request);
    }

    @Override
    public void invoke(PartyInfoExcel data, AnalysisContext context) {
        // 1. 校验
        checkParams(data, context);
        // 2. 批量保存
        /*if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }*/
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        logger.info("Excel 数据解析完成");
        if (list.size() > 0) {
            saveData();
        }
    }

    private void saveData() {
        try {
            requestMap.put("list", list);
            service.saveThread(requestMap);
            logger.info("保存到数据库完成");
        } catch (Exception e) {
            throw new RuntimeException("保存到数据库失败");
        }
    }

    private void checkParams(PartyInfoExcel data, AnalysisContext context){
        Integer rowIndex = context.readRowHolder().getRowIndex();
        if(StringUtils.isEmpty(data.getPartyNumber())){
            throw new RuntimeException("公司编码不能为空");
        }
        list.add(data);
    }


}
