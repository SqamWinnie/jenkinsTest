package javadev.线程.ThreadPool.Runnable;

/**
 * Created by 11861 on 2018/3/28.
 */
public class TaskRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程1--任务在执行"+i);
        }
    }
}

class TaskRunnable2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程2--任务在执行"+i);
        }
    }
}

class TaskRunnable3 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程3--任务在执行"+i);
        }
    }
}

class TaskRunnable4 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程4--任务在执行"+i);
        }
    }
}

class TaskRunnable5 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程5--任务在执行"+i);
        }
    }
}

class TaskRunnable6 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程6--任务在执行"+i);
        }
    }
}