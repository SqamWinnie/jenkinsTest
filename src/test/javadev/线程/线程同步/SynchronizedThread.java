package javadev.线程.线程同步;

/**
 * @author --
 * @description 使用 synchronized 关键字保证线程同步
 * 用法: 在方法或代码块前加上关键字 synchronized
 * @date 2018/12/5
 */
public class SynchronizedThread extends Thread {
    private static int count_sync = 0;

    public synchronized void increment() {
        count_sync++;
    }

    @Override
    public void run() {
        for (int i = 0; i < EnumNum.NUM.getValue(); i++) {
            this.increment();
        }
        System.out.println("sync count:"+count_sync);
    }

    public int getCount() {
        return count_sync;
    }
}
