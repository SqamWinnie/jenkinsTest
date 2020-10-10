package javadev.线程.ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author --
 * @description 线程池练习：返回两个数相加的结果
 *              使用 MyCallable 类
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
         //创建线程池对象
         ExecutorService threadPool = Executors.newFixedThreadPool(3);
         
         //创建一个Callable接口子类对象
         //MyCallable c = new MyCallable();
         MyCallable c = new MyCallable(100, 200);
         MyCallable c2 = new MyCallable(10, 20);
         
         //获取线程池中的线程，调用Callable接口子类对象中的call()方法, 完成求和操作
         //<Integer> Future<Integer> submit(Callable<Integer> task)
         // Future 结果对象
         Future<Integer> result = threadPool.submit(c);
         //此 Future 的 get 方法所返回的结果类型
         Integer sum = result.get();
         System.out.println("sum=" + sum);
         
         //再演示
         result = threadPool.submit(c2);
         sum = result.get();
         System.out.println("sum=" + sum);

        result = threadPool.submit(c);
        //此 Future 的 get 方法所返回的结果类型
        sum = result.get();
        System.out.println("sum=" + sum);

        //再演示
        result = threadPool.submit(c2);
        sum = result.get();
        System.out.println("sum=" + sum);

        result = threadPool.submit(c);
        //此 Future 的 get 方法所返回的结果类型
        sum = result.get();
        System.out.println("sum=" + sum);

        //再演示
        result = threadPool.submit(c2);
        sum = result.get();
        System.out.println("sum=" + sum);


         //关闭线程池(可以不关闭)
         threadPool.shutdown();
    }
}

