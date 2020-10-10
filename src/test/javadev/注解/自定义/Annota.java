package javadev.注解.自定义;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javabase.类型.Enum.ColorEnum;

/**
 * 元注解 @Target, @Retention, @Documented, @Inherited.
 * <p>
 * 1. @Target 表示该注解用于什么地方，可能的 ElemenetType 参数包括：
 * ElemenetType.CONSTRUCTOR 构造器声明
 * ElemenetType.FIELD 域声明（包括 enum 实例）
 * ElemenetType.LOCAL_VARIABLE 局部变量声明
 * ElemenetType.METHOD 方法声明
 * ElemenetType.PACKAGE 包声明
 * ElemenetType.PARAMETER 参数声明
 * ElemenetType.TYPE 类，接口（包括注解类型）或enum声明
 * <p>
 * 2. @Retention 表示在什么级别保存该注解信息。可选的 RetentionPolicy 参数包括：
 * RetentionPolicy.SOURCE 注解将被编译器丢弃
 * RetentionPolicy.CLASS 注解在class文件中可用，但会被VM丢弃
 * RetentionPolicy.RUNTIME VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。
 * <p>
 * 3. @Documented 将此注解包含在 javadoc 中
 * <p>
 * 4. @Inherited 允许子类继承父类中的注解
 *
 * @date 2019/5/9
 */

/**
 * 自定义注解 Anno.
 * 注解中只定义方法，
 * 这些方法是注解的元素，
 * 方法的返回类型是元素的类型，
 * 元素可以设置默认值 default value
 * @date 2019/5/9
 * @version 1.0
 */
@Target(ElementType.METHOD)           //该注解用于方法声明
@Retention(RetentionPolicy.RUNTIME)   //VM 将在运行期也保留注释，因此可以通过反射机制读取注解的信息
@Documented                           //将此注解包含在javadoc中
@Inherited                            //允许子类继承父类中的注解
public @interface Annota {
    /**
     * 1. 定义属性 id
     * @return int
     */
    public int id()  default 0;

    /**
     * 2. 定义属性 description
     * @return String
     */
    public String description() default "no description";

    /**
     * 3. 定义属性 value( 不注明参数名且只有一个参数，其他参数都有默认值时，默认使用 value 属性接收 )
     * @return String
     */
    public String value();

    /**
     * 4. 定义数组类型的属性
     * 应用数组类型的属性：@DefineAnnotation(arrayAttr={2,4,5})
     * 如果数组属性只有一个值，这时候属性值部分可以省略大括号，如：@MyAnnotation(arrayAttr=2)
     */
    int[] arrayAttr() default {1,2,4};

    /**
     * 5. 定义枚举类型的属性
     *
     * 应用枚举类型的属性：@MyAnnotation(lamp=EumTrafficLamp.GREEN)
     */
    ColorEnum color() default ColorEnum.RED;

    /**
     * 6. 定义注解类型的属性,并指定注解属性的缺省值
     */
    AnnoSimple as() default @AnnoSimple("value");;
}
