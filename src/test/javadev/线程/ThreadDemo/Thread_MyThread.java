package javadev.线程.ThreadDemo;

/**
 * Created by 11861 on 2018/3/30.
 */
public class Thread_MyThread {
    public static void main(String[] args){
        //启动线程 MyThread
        MyThread R1 = new MyThread();    //调用构造函数
        R1.start();                      //调用继承的父类的start()方法
    }

}
