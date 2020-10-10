package dev.com.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;

import dev.com.apoint.Anno;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.log4j.Logger;

/**
 * sql 修改执行.
 *
 * @date 2019/5/31
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class ModifySqlInterceptor implements Interceptor {

    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //通过MetaObject访问对象的属性，这里是访问statementHandler的属性;
        // MetaObject是Mybatis提供的一个用于方便访问对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常，同时它支持对JavaBean、Collection、Map三种类型对象的操作。
        MetaObject metaObject = MetaObject
            .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                    SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //id为执行的mapper方法的全路径名，如 dev.pro.demo.mapper.select
        String methodFullName = mappedStatement.getId();
        Logger logger = Logger.getLogger(methodFullName);
        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
//        logger.info(sqlCommandType);
        //获取到原始sql语句
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        // 修改的 Sql 语句
        String mSql = sql;

        //注解逻辑判断  添加注解了才拦截
        Class<?> classType = Class.forName(methodFullName.substring(0, methodFullName.lastIndexOf(".")));
        String methodName = methodFullName.substring(methodFullName.lastIndexOf(".") + 1, methodFullName.length());
        for (Method method : classType.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Anno.class) && methodName.equals(method.getName())) {
                Anno anno = method.getAnnotation(Anno.class);
                if ("inter".equals(anno.value())) {
                    mSql = "select * from (" + sql + ")auth where auth.dept_code = 'A'";
//                    logger.info("==>sql 权限: " + mSql);
                }
            }
        }

        //通过反射修改sql语句
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, mSql);
        try {
            Object obj = invocation.proceed();
            return obj;
        } catch (Exception e) {
            StackTraceElement[] els = e.getStackTrace();
            for (StackTraceElement el : els) {
                logger.error(el);
            }
            return null;
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
        Logger logger = Logger.getLogger(ModifySqlInterceptor.class);
        //此处可以接收到配置文件的property参数
        logger.info(properties.getProperty("name"));
    }

}
