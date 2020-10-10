package javadev.线程.ThreadPool.Runnable;

/**
 * Created by 11861 on 2018/3/28.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        //创建线程池对象  参数5，代表有5个线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(5);

        //创建Runnable线程任务对象
        TaskRunnable task1 = new TaskRunnable();
        TaskRunnable2 task2 = new TaskRunnable2();
        TaskRunnable3 task3 = new TaskRunnable3();
        TaskRunnable4 task4 = new TaskRunnable4();
        TaskRunnable5 task5 = new TaskRunnable5();
        TaskRunnable6 task6 = new TaskRunnable6();
        //从线程池中获取线程对象
        service.submit(task1);
        service.submit(task2);
        service.submit(task3);
        service.submit(task4);
        service.submit(task5);
        service.submit(task6);
        //关闭线程池
        service.shutdown();
    }


}
