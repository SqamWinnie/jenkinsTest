package javadev.线程.线程同步;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author --
 * @description 使用锁 Lock 保证线程同步
 * 用法： 在 run 方法中先获取锁，执行完成后释放锁
 */
public class LockThread extends Thread {
    private static int count_lock = 0;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() { //获取锁
        lock.lock();
        try {
            for (int i = 0; i < EnumNum.NUM.getValue(); i++) {
                count_lock++;
            }
            System.out.println("lock count:"+count_lock);
        } finally {   //释放锁
            lock.unlock();
        }
    }

    public int getCount() {
        return count_lock;
    }

}

