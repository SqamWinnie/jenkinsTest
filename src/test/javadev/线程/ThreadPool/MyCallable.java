package javadev.线程.ThreadPool;

import java.util.concurrent.Callable;

/**
 * @author --
 * @description 实现 Callable 接口的线程对象
 */
public class MyCallable implements Callable<Integer> {
    /**成员变量*/
    private int x = 5;
    private int y = 3;

    /**构造方法*/
    public MyCallable() {}

    public MyCallable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer call() throws Exception {
        return x + y;
    }
}
