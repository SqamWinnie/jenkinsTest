package javadev.线程.RunnableDemo;

/**
 * Created by 11861 on 2018/3/7.
 */
class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;


    RunnableDemo( String name) {
        threadName = name;
    }

    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("线程: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("线程 " +  threadName + " interrupted.");
        }
        System.out.println("线程 " +  threadName + " exiting.");
    }

    /*public void start () {
       System.out.println("Starting " +  threadName );
         if (t == null) {
            t = new 线程 (this, threadName);
            t.start ();
        }
    }*/
}

