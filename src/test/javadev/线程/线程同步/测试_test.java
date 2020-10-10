package javadev.线程.线程同步;

/**
 * @author --
 * @description 测试_test
 */
public class 测试_test {
    /**
     * 测试 使用锁  线程同步机制
     * @throws InterruptedException
     */
    public static void f1() throws InterruptedException {
        LockThread l1 = new LockThread();
        LockThread l2 = new LockThread();

        System.out.println("lock t1.count:" + l1.getCount());
        System.out.println("lock t2.count:" + l2.getCount());
    }

    /**
     * 测试 使用 synchronized 关键字  线程同步机制
     * @throws InterruptedException
     */
    public static void f2() throws InterruptedException {
        SynchronizedThread t1 = new SynchronizedThread();
        SynchronizedThread t2 = new SynchronizedThread();

        System.out.println("sync t1.count:" + t1.getCount());
        System.out.println("sync t2.count:" + t2.getCount());
    }

    /**
     * 测试 使用 volatile 关键字  线程同步机制
     * @throws InterruptedException
     */
    public static void f3() throws InterruptedException {
        VolatileThread v1 = new VolatileThread();
        VolatileThread v2 = new VolatileThread();
        v1.start();
        v2.start();
        v1.join();
        v2.join();
        System.out.println("vola v1.count: " + v1.getCount());
        System.out.println("vola v2.count: " + v2.getCount());
    }

    /**
     * 测试 使用 ThreadLocal 关键字
     * @throws InterruptedException
     */
    public static void f4() throws InterruptedException {
        LocalThread l1 = new LocalThread();
        LocalThread l2 = new LocalThread();
        /*LocalThread_normal l1 = new LocalThread_normal();
        LocalThread_normal l2 = new LocalThread_normal();*/
        l1.start();
        l2.start();
        l1.join();
        l2.join();
        System.out.println("local l1.count: " + l1.getCount() + "\n" + "local l2.count: " + l2.getCount());
    }

    public static void main(String[] args) throws InterruptedException {
        f4();
    }
}
