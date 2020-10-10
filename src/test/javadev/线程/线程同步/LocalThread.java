package javadev.线程.线程同步;

/**
 * @author --
 * @description ThreadLocal 与同步机制都是为了解决多线程中相同变量的访问冲突问题
 * 用法：ThreadLocal 修饰变量
 */
public class LocalThread extends Thread{

    private static ThreadLocal<Integer> count_local = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 0;
        }
    };

    @Override
    public void run(){
        for(int i=0;i< EnumNum.NUM.getValue();i++){
            count_local.set(count_local.get()+1);
        }
        System.out.println(count_local.get());
    }

    public int getCount(){
        return count_local.get();
    }

    public void setCount(){
        count_local.set(count_local.get());
    }
}

/**
 * @author --
 * @description 此类没有使用线程同步
 */
class LocalThread_normal extends Thread{

    private static Integer count_local_ = 0;

    @Override
    public void run(){
        for(int i=0;i< EnumNum.NUM.getValue();i++){
            count_local_++;
        }
    }

    public int getCount(){
        return count_local_;
    }

}
