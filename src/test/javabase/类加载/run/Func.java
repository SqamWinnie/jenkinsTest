package javabase.类加载.run;

public class Func {
    static {
        System.out.println(Func.class + " 静态初始化块执行了！");
    }
    public Func(){
        System.out.println(Func.class + " 构造方法执行了！");
    }
}
