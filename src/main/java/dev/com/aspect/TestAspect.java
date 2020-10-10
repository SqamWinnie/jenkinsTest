package dev.com.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @date 2019/5/23
 */
@Component
@Aspect
public class TestAspect {

    private static Logger logger = Logger.getLogger(TestAspect.class);

    TestAspect(){
        logger.info("=================TestAspect 方面组件初始化=====================");
    }

    @Before("execution(* dev.com.apoint.Test.t(..))")
    public void before(){
        logger.info("run()---before");
    }

    @After("execution(* dev.com.apoint.Test.t(..))")
    public void after(){
        logger.info("run()---after");
    }
}
