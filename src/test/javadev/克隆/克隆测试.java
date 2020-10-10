package javadev.克隆;

import java.util.Date;

import dev.pro.demo.controller.DemoController;
import org.apache.log4j.Logger;

/**
 * @author ...
 * @date 2019/6/26
 */
public class 克隆测试 {

    private static Logger logger = Logger.getLogger(DemoController.class);

    public static void main(String[] args) throws CloneNotSupportedException {
        Product p = new Product();
        p.product();
        Info i = new Info();
        i.info();
        p.setInfo(i);

        // 1. 浅克隆
        /*logger.info("==================浅克隆====================");
        logger.info("p 的初始值  ： " + p.toString());
        Product p1 = p;
        p1.setProductId(1);
        p1.setProductName("产品1");
        p1.getInfo().setProductId(1);
        p1.getInfo().setProductName("产品1");
        logger.info("浅克隆后的 p1: " + p1.toString());
        logger.info("浅克隆后的 p : " + p.toString());

        p.product();
        i.info();
        // 2. 深克隆(有属性为对象时，该属性是浅克隆)
        logger.info("==================深克隆（对象属性浅克隆）====================");
        logger.info("p 的初始值  ： " + p.toString());
        Product p2 = p.clone1();
        p2.setProductId(2);
        p2.setProductName("产品2");
        p2.getInfo().setProductId(2);
        p2.getInfo().setProductName("产品2");
        logger.info("深克隆后的 p2: " + p2.toString());
        logger.info("深克隆后的 p : " + p.toString());*/

        p.product();
        i.info();
        // 3. 深克隆
        logger.info("==================深克隆（对象属性深克隆）====================");
        logger.info("p 的初始值  ： " + p.toString());
        Product p3 = p.clone2();
        p3.setProductId(3);
        p3.setProductName("产品3");
        p3.setWorkDate(new Date());
        p3.getInfo().setProductId(3);
        p3.getInfo().setProductName("产品3");
        logger.info("深克隆后的 p3: " + p3.toString());
        logger.info("深克隆后的 p : " + p.toString());
    }
}
