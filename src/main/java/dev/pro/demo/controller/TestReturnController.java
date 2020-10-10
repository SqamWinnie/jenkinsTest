package dev.pro.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.com.Result;
import dev.pro.demo.dto.Demo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 返回数据类型测试.
 *
 * @author ...
 */
@Controller
public class TestReturnController {
    private static Logger logger = Logger.getLogger(TestReturnController.class);

    /*======================================================================================================*/
    /*===========================================返回基本数据类型.===========================================*/
    /*======================================================================================================*/

    /**
     * 1. 返回 Integer.
     */
    @RequestMapping(value = "/return/int")
    @ResponseBody
    public Integer integeR() {
        return 10;
    }

    /**
     * 2. 返回 Short.
     */
    @RequestMapping(value = "/return/short")
    @ResponseBody
    public Short shorT() {
        return 20;
    }

    /**
     * 3. 返回 Long.
     */
    @RequestMapping(value = "/return/long")
    @ResponseBody
    public Long lonG() {
        return 30L;
    }

    /**
     * 4. 返回 Char.
     */
    @RequestMapping(value = "/return/char")
    @ResponseBody
    public Character chaR() {
        return 40;
    }

    /**
     * 5. 返回 Byte.
     */
    @RequestMapping(value = "/return/byte")
    @ResponseBody
    public Byte bytE() {
        return 50;
    }

    /**
     * 6. 返回 Boolean.
     */
    @RequestMapping(value = "/return/boolean")
    @ResponseBody
    public Boolean booleaN() {
        return true;
    }

    /**
     * 7. 返回 Double.
     */
    @RequestMapping(value = "/return/double")
    @ResponseBody
    public Double doublE() {
        return 70.0;
    }

    /**
     * 8. 返回 Float.
     */
    @RequestMapping(value = "/return/float")
    @ResponseBody
    public Float floaT() {
        return 80.0f;
    }

    /*======================================================================================================*/
    /*===========================================返回 string 类型.===========================================*/
    /*======================================================================================================*/

    /**
     * 1. 返回 string.
     */
    @RequestMapping(value = "/return/string")
    @ResponseBody
    public String strinG() {
        return "返回字符串类型";
    }

    /*======================================================================================================*/
    /*===========================================返回 list,map 类型.=========================================*/
    /*======================================================================================================*/

    /**
     * 1. 返回 list.
     */
    @RequestMapping(value = "/return/list")
    @ResponseBody
    public List lisT() {
        List<String> list = new ArrayList<>();
        list.add("字符串1");
        list.add("字符串2");
        list.add("字符串3");
        return list;
    }

    /**
     * 2. 返回 list T.
     */
    @RequestMapping(value = "/return/listDemo")
    @ResponseBody
    public List<Demo> queryList() {
        List<Demo> list = new ArrayList<>();
        Demo d1 = new Demo();
        d1.setId(1L);
        d1.setUserName("jackson");
        list.add(d1);
        Demo d2 = new Demo();
        d2.setId(2L);
        d2.setUserName("mack");
        list.add(d2);
        Demo d3 = new Demo();
        d3.setId(3L);
        d3.setUserName("scarf");
        list.add(d3);
        return list;
    }

    /**
     * 3. 返回 Map.
     */
    @RequestMapping(value = "/return/map")
    @ResponseBody
    public Map maP() {
        Map map = new HashMap();
        map.put("1","字符串1");
        map.put("2","字符串2");
        map.put("3","字符串3");
        return map;
    }

    /*======================================================================================================*/
    /*=============================================返回自定义类型.===========================================*/
    /*======================================================================================================*/

    /**
     * 1. 返回 Result.
     */
    @RequestMapping(value = "/return/result")
    @ResponseBody
    public Result result() {
        List<Demo> list = new ArrayList<>();
        Demo d1 = new Demo();
        d1.setId(1L);
        d1.setUserName("jackson");
        list.add(d1);
        Demo d2 = new Demo();
        d2.setId(2L);
        d2.setUserName("mack");
        list.add(d2);
        Demo d3 = new Demo();
        d3.setId(3L);
        d3.setUserName("adage");
        list.add(d3);
        return new Result(list);
    }

    /**
     * 2. 返回 Demo.
     */
    @RequestMapping(value = "/return/demo")
    @ResponseBody
    public Demo demo() {
        Demo d = new Demo();
        d.setId(1L);
        d.setUserName("jackson");
        return d;
    }
}
