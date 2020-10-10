package javadev.线程.线程同步;

/**
 * @author --
 * @description 使用 volatile 关键字 保证线程同步
 * 用法： 在需要线程同步的变量前加上关键字 volatile
 */
public class VolatileThread extends Thread {
    private static volatile int count_vola = 0;

    @Override
    public void run() {
        for (int i = 0; i < EnumNum.NUM.getValue(); i++) {
            count_vola++;
        }
        System.out.println("vola count:"+count_vola);
    }

    public int getCount(){
        return count_vola;
    }
}
