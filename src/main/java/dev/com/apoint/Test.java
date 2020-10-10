package dev.com.apoint;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @date 2019/5/23
 */
@Component
public class Test implements Itest{

    private static Logger logger = Logger.getLogger(Test.class);

    @Override
    public String t(){
        logger.info("这是 Test 类的 t()");
        return "t() 方法的返回值";
    }
}
