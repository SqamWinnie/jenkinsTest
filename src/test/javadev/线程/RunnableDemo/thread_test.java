package javadev.线程.RunnableDemo;

/**
 * Created by 11861 on 2018/3/7.
 */
public class thread_test {

    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo( "线程-1");

//        (new 线程(R1)).start();

        RunnableDemo R2 = new RunnableDemo( "线程-2");

        Runnable2 runnable2 = new Runnable2();
        new Thread(runnable2).start();

    }
}