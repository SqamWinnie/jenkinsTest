package javabase.类型.Class;

/**
 * <i>单例模式</i>示例
 * @author 11861
 * @version 1.0
 */
public class Singleton {
    private final static Singleton instance = new Singleton();
    private static boolean initialized = false;
    private Singleton(){
        super();
    }
    /**
     * 对象初始化的方法
     */
    private void init(){
        System.out.println("Singleton init().");
    }
    /**
     * 使用静态方法初始化类对象
     * @return 一个Singleton对象
     */
    public static synchronized Singleton getInstance(){
        if(initialized) {
            return instance;
        }
        instance.init();
        initialized = true;
        return instance;
    }
    public static void main(String[] args){
        System.out.println("run");
    }
}
