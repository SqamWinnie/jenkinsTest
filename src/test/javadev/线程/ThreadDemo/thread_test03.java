package javadev.线程.ThreadDemo;

/**
 * Created by 11861 on 2018/3/29.
 */
class thread_test03 extends Thread{


    public void run(){
        //这里写上主线程的内容
        System.out.println("run()");
    }

    public static void main(String[] args){
        //启动线程 MyThread
        thread_test03 R1 = new thread_test03();  //调用构造函数
        R1.start();                    //调用继承的父类的start()方法
    }

}

