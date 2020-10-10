package dev.pro.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dev.com.Result;
import dev.com.apoint.Anno;
import dev.com.apoint.Itest;
import dev.pro.demo.dto.Demo;
import dev.pro.demo.service.IDemoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ...
 */
@Controller
public class DemoController {

    @Autowired
    IDemoService demoService;
    @Autowired
    Itest itest;
    private static Logger logger = Logger.getLogger(DemoController.class);

    /**
     * 查询.
     */
    @RequestMapping(value = "/demo/query")
    @ResponseBody
    public Result query(@RequestBody Demo demo) {
        return new Result(demoService.select(demo));
    }

    /**
     * 保存.
     */
    @RequestMapping(value = "/demo/save")
    @ResponseBody
    public Result save(@RequestBody List<Demo> demos) {
        return new Result(demoService.save(demos));
    }

    /**
     * 删除.
     */
    @RequestMapping(value = "/demo/del")
    @ResponseBody
    public Result del(@RequestBody List<Demo> demos) {
        demoService.remove(demos);
        return new Result();
    }

    /**
     * AOP 类测试.
     */
    @RequestMapping(value = "/aop/class")
    @ResponseBody
    public Result aopClass() {
        logger.info("---------controller--/aop/class-----------");
        List<String> list = new ArrayList<>();
        list.add("AOP 切入点：类");
        list.add("注入 类的继承接口");
        list.add(itest.t());
        return new Result(list);
    }

    /**
     * AOP 自定义注解测试.
     */
    @Anno("这是 demoController")
    @RequestMapping(value = "/aop/anno")
    @ResponseBody
    public Result aopAnno() {
        logger.info("---------controller---/aop/anno----------");
        List<String> list = new ArrayList<>();
        list.add("使用自定义注解 AOP");
        list.add("获取自定义注解参数");
        return new Result(list);
    }

    /**
     * AOP service 层 测试.
     */
    @RequestMapping(value = "/aop/service")
    @ResponseBody
    public Result aopService() {
        logger.info("---------controller---/aop/service----------");
        List<String> list = new ArrayList<>();
        list.add("使用自定义注解 AOP");
        list.add("获取自定义注解参数");
        list.addAll(demoService.aopClass());
        list.add(demoService.aopAnno());
        return new Result(list);
    }

    /**
     * 登录.
     */
    @RequestMapping(value = "/_login")
    public ModelAndView login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request) {
        String path = request.getContextPath();

        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        HttpSession session = request.getSession();
        session.setAttribute("basePath", basePath);
        Demo demo = new Demo();
        demo.setUserName(userName);
        demo.setPassword(password);
        List<Demo> demos = demoService.select(demo);
        Map<String, Object> map = new HashMap<String, Object>();
        if (demos.isEmpty()) {
            map.put("error", "用户名和密码不匹配");
            ModelAndView mv = new ModelAndView("view/login.html", map);
            mv.setViewName("redirect:view/login.html");
            return mv;
        }
        map.put("user", demos.get(0).getUserName());
        ModelAndView mv = new ModelAndView("view/index.html", map);
        mv.setViewName("redirect:view/index.html");
        return mv;
    }
}
