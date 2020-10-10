package javabase.类加载;

import java.lang.reflect.InvocationTargetException;

import javabase.类加载.run.HelloWorld;
import javabase.类加载.自定义类加载器.DivClassLoader;

public class LoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        divClassLoader();
    }

    /**
     * 1. 寻找类加载器
     */
    private static void findClassLoader(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }

    /**
     * 2. 类的加载方式（三种）
     */
    private static void classLoader() throws ClassNotFoundException{
        ClassLoader loader = HelloWorld.class.getClassLoader();
        System.out.println(loader);
        System.out.println("=========ClassLoader.loadClass()=========");

        //1. 使用 ClassLoader.loadClass()来加载类，不会执行初始化块
        loader.loadClass("javabase.类加载.run.Func");
        System.out.println("=========Class.forName()=========");

        //2. 使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("javabase.类加载.run.Func");
        System.out.println("=========Class.forName() 并指定初始化时不执行静态块========");

        //3. 使用Class.forName()来加载类，并指定初始化时不执行静态块
        Class.forName("javabase.类加载.run.Func", false, loader);
    }

    /**
     * 3. 使用自定义类加载器
     */
    private static void divClassLoader() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        DivClassLoader loader = new DivClassLoader("D:/demo/testDemo/springmvc/src/test");
        Class<?> findClass = loader.loadClass("javabase.类加载.run.Func");
        Object o = findClass.newInstance();
        System.out.println(o.getClass().getClassLoader());
    }
}







