package javadev.线程.ThreadDemo;


/**
 * Created by 11861 on 2018/3/7.
 */
public class thread_test01 extends Thread{
        private static int count = 0;

        public static synchronized void increment() {
            count++;
        }

        public void run() {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
            System.out.println(count);
        }

        public static void main(String[] args) throws InterruptedException {
            thread_test01 t1 = new thread_test01();
            thread_test01 t2 = new thread_test01();
            thread_test01 t3 = new thread_test01();
            thread_test01 t4 = new thread_test01();
            t1.start();
            t2.start();
            t3.start();
            t4.start();
        }
    }

