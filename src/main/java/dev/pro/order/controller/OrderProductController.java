package dev.pro.order.controller;

import java.util.List;

import dev.com.Result;
import dev.pro.order.dto.OrderProduct;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ...
 */
@Controller
public class OrderProductController {

    private static Logger logger = Logger.getLogger(OrderProductController.class);

    /**
     * 查询.
     */
    @RequestMapping(value = "/orderProduct/query")
    @ResponseBody
    public Result query(@RequestBody OrderProduct dto) {
        return new Result();
    }

    /**
     * 保存.
     */
    @RequestMapping(value = "/orderProduct/submit")
    @ResponseBody
    public Result save(@RequestBody List<OrderProduct> dto) {
        return new Result();
    }

    /**
     * 删除.
     */
    @RequestMapping(value = "/orderProduct/remove")
    @ResponseBody
    public Result del(@RequestBody List<OrderProduct> dto) {
        return new Result();
    }
}
