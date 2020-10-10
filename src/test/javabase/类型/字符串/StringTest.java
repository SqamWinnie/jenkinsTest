package javabase.类型.字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by 11861 on 2018/3/12.
 */
public class StringTest {
    private static final Logger logger = Logger.getLogger("StringTest");

    public static void main(String[] args) {
        try {
            String s = "smafn";
            int i = Integer.valueOf(s);
            System.out.println(i);
        } catch (NumberFormatException e) {
            logger.info("String 转换成 int 失败");
        }
    }

    /** string 去除首尾空格. */
    private static void f1(){
        String a = "";
        String b= a.trim();
        logger.info(a);
        logger.info(b);
    }

    /**
     * 根据字符截取字符串.
     */
    public static void f2(){
        String str = "BZGL20190424018";
        // 从第四位开始截取   "20190424018"
        String number = str.substring(4);
        // 截取第几位到第几位  "20190424018"
        String num = str.substring(str.indexOf("L") + 1, str.length());
        // 截取第几位到第几位  "BZGL"
        String letter = str.substring(0, str.indexOf("L") + 1);
        logger.info(number);
        logger.info(num);
        logger.info(letter);
    }

    @SuppressWarnings("all")
    public static void f3() {
        String str = "1;1;1";
        List<String> list = new ArrayList();
        if (str != null && !"".equals(str)) {
            String[] receivers = str.split(";");
            list.addAll(Arrays.asList(receivers));
        }
    }


    /**
     * string 转 Integer.
     * @param str String
     * @return Integer
     */
    private static Integer toInteger(String str) {
        Integer i = 0;
        try {
            return Integer.valueOf(str);
        } catch (RuntimeException e) {
            return i;
        }
    }
}
