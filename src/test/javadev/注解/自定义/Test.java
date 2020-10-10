package javadev.注解.自定义;

import java.lang.reflect.Method;

/**
 * 将 使用测试类 所有被注解方法的信息打印出来.
 *
 * @date 2019/5/9
 */
public class Test {
    /**
     * 方法1.
     */
    @Annota(id = 1, value = "1v")
    public void method1() {
    }

    /**
     * 方法2.
     */
    @Annota(id = 2, value = "2v")
    public void method2() {
    }

    /**
     * 主方法.
     */
    @Annota("mv")
    public static void main(String[] args) {
        Method[] methods = Test.class.getDeclaredMethods();
        for (Method method : methods) {
            //判断方法中是否有指定注解类型的注解
            boolean hasAnnotation = method.isAnnotationPresent(Annota.class);
            if (hasAnnotation) {
                //根据注解类型返回方法的指定类型注解
                Annota annotation = method.getAnnotation(Annota.class);
                System.out.println("Test(method=" + method.getName() + ", id=" + annotation.id()
                        + ", value=" + annotation.value() + ")");
            }
        }
    }
}
