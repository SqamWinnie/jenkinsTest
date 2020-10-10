package dev.com.apoint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解.
 * 打印日志信息
 *
 * @author yachen.li@hand-china.com
 * @date 2019/5/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anno {
    public String value() default "apoint";
}
