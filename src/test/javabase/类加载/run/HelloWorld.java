package javabase.类加载.run;

public class HelloWorld {
    static {
        System.out.println(HelloWorld.class + " 静态初始化块执行了！");
    }
    Func f = new Func();
}
