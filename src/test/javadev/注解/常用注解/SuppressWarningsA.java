package javadev.注解.常用注解;

import java.util.ArrayList;
import java.util.List;

/**
 * __ @SuppressWarnings("all").
 *
 * @author lyc
 * @date 2019/5/14
 */
public class SuppressWarningsA {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        String s = "";
    }

    /**
     * 多参数注解.
     * @param item  字符串参数
     */
    @SuppressWarnings(value = {"unused", "MismatchedQueryAndUpdateOfCollection", "unchecked"})
    public void addItems(String item) {
        List items = new ArrayList();
        items.add(item);
    }

    /**
     * 使用 all 注解.
     * @param item  字符串参数
     */
    @SuppressWarnings("all")
    public void addItems1(String item) {
        List items = new ArrayList();
        items.add(item);
    }

}
