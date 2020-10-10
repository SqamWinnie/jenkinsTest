package javadev.线程.RunnableDemo;

/**
 * Created by 11861 on 2018/3/7.
 */
public class Runnable2 implements Runnable {

    @Override
    public void run() {
        System.out.println("线程 Runnable2 的 run 方法");
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Runnable2: " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Runnable2 " + " interrupted.");
        }
        System.out.println("线程 Runnable2 的 run 方法 exiting.");
    }

}

