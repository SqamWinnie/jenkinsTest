package dev.com.aspect;

import java.lang.reflect.Method;

import dev.com.apoint.Anno;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 */
@Component
@Aspect
public class AnnoAspect{

    private static Logger logger = Logger.getLogger(AnnoAspect.class);

    AnnoAspect() {
        logger.info("====================AnnoAspect 方面组件初始化==========================");
    }

    @Around("@annotation(dev.com.apoint.Anno)")
    public Object annoAspect(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("-----注解 @Anno 触发方面组件：---");
        //获取要执行的目标组件类名
        String className = pjp.getTarget().getClass().getName();
        //获取要执行的方法名
        String math = pjp.getSignature().getName();
        //获取要执行的方法
        Method method = pjp.getTarget().getClass().getMethod(math);
        boolean hasAnnotation = method.isAnnotationPresent(Anno.class);
        if (hasAnnotation) {
            //根据注解类型返回方法的指定类型注解
            Anno annotation = method.getAnnotation(Anno.class);
            logger.info(className + "." + math + "() 方法的注解 value = " + annotation.value());
        }
        long beginTime = System.currentTimeMillis();
        //执行目标方法
        Object result = pjp.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        logger.info("-----目标方法执行时长: "+ time +" ms.---");
        logger.info("-----注解 @Anno 触发方面组件结束---");
        return result;
    }
}
