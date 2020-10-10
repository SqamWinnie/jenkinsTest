package dev.pro.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import dev.com.apoint.Anno;
import dev.com.apoint.Itest;
import dev.pro.demo.dto.Demo;
import dev.pro.demo.mapper.DemoMapper;
import dev.pro.demo.service.IDemoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ...
 */

@Transactional(rollbackFor = Exception.class)
@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    DemoMapper demoMapper;
    @Autowired
    Itest itest;
    private static Logger logger = Logger.getLogger(DemoServiceImpl.class);

    @Override
    public List<Demo>   select(Demo demo) {
        List<Demo> demos = new ArrayList<>();
        if (null != demo.getId()) {
            demo = demoMapper.selectById(demo);
            if (demo != null){
                demos.add(demo);
            }
        } else {
            demos.addAll(demoMapper.select(demo));
        }
        return demos;
    }

    @Override
    public List<Demo> save(List<Demo> demos) {
        for (Demo demo : demos) {
            if (demo.getId() != null) {
                demoMapper.update(demo);
            } else {
                demoMapper.insert(demo);
            }
//            throw new RuntimeException("创建异常");
        }
        return demos;
    }

    @Override
    public void remove(List<Demo> demos) {
        for (Demo demo : demos) {
            demoMapper.remove(demo);
        }
    }

    @Override
    public List<String> aopClass() {
        logger.info("---------service--aopClass-----------");
        List<String> list = new ArrayList<>();
        list.add(itest.t());
        list.add("aopClass()的返回值");
        return  list;
    }

    @Override
    @Anno("这是 demoService")
    public String aopAnno() {
        logger.info("---------service--aopAnno-------------");
        return "aopAnno()的返回值";
    }
}
