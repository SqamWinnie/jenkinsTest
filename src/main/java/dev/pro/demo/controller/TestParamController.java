package dev.pro.demo.controller;

import java.util.Arrays;
import java.util.List;

import dev.pro.demo.dto.Demo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 传参类型测试.
 *
 * @author ...
 */
@Controller
public class TestParamController {
    private static Logger logger = Logger.getLogger(TestParamController.class);
    /**
     * 1. 传参：字符串.
     * 防止中文乱码_ , produces = {"text/html;charset=UTF-8;", "application/json;"}
     */
    @RequestMapping(value = "/param/string")
    @ResponseBody
    public String string(@RequestParam("str") String str) {
        logger.info(str);
        return "传参为：" + str;
    }

    /**
     * 2. 传参：数组.
     * 接收：数组
     * 防止中文乱码_ , produces = {"text/html;charset=UTF-8;", "application/json;"}
     */
    @RequestMapping(value = "/param/array")
    @ResponseBody
    public String array(@RequestBody String[] arr) {
        logger.info(Arrays.toString(arr));
        return "传参为：" + Arrays.toString(arr);
    }

    /**
     * 3. 传参：数组.
     * 接收：list
     * 防止中文乱码_ , produces = {"text/html;charset=UTF-8;", "application/json;"}
     */
    @RequestMapping(value = "/param/arrayList")
    @ResponseBody
    public String array(@RequestBody List<String> arr) {
        logger.info(arr.toString());
        return "传参为：" + arr.toString();
    }

    /**
     * 4. 传参：对象.
     * 接收：dto
     * 防止中文乱码_ , produces = {"text/html;charset=UTF-8;", "application/json;"}
     */
    @RequestMapping(value = "/param/demo")
    @ResponseBody
    public String demo(@RequestBody Demo demo) {
        logger.info(demo.toString());
        return "传参为：" + demo.toString();
    }

    /**
     * 5. 传参：对象数组.
     * 接收：list
     * 防止中文乱码_ , produces = {"text/html;charset=UTF-8;", "application/json;"}
     */
    @RequestMapping(value = "/param/demoList")
    @ResponseBody
    public String demos(@RequestBody List<Demo> demos) {
        logger.info(demos.toString());
        return "传参为：" + demos.toString();
    }
}
