package javadev.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @date 2019/6/14
 */
public class Test {

    private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
//        getClazz();
        getConstructor();
//        getFields();
//        getMethod();
    }

    /**
     * 1. 获取 Class 对象.
     */
    private static void getClazz() throws ClassNotFoundException {
        // 三种方法获取 Class 对象
        Reflect r = new Reflect();
        Class c1 = r.getClass();
        Class c2 = Reflect.class;
        Class c3 = Class.forName("javadev.反射.Reflect");
        // Class 对象唯一性
        if (c1.equals(c2) && c2.equals(c3)) {
            logger.debug("对象是同一个");
        }
    }

    /**
     * 2. 获取构造函数.
     */
    private static void getConstructor() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Class.forName("javadev.反射.Reflect");
        // 1. 构造无参对象（如果类没有无参构造函数, 就不能这样创建）
        Reflect r1 = (Reflect) clazz.newInstance();
        // 2. 构造有参对象( 该构造函数必须 public )
        Constructor constructor = clazz.getConstructor(int.class, String.class);
        Reflect r2 = (Reflect) constructor.newInstance(1, "名字");
        // 3. 获取所有构造函数
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor con : constructors) {
            // 4. 获取构造函数中的参数类型
            Class[] pTypes = con.getParameterTypes();
            // 打印这些参数的类型
            logger.info("=========构造函数==========");
            String str = "";
            for (int i = 0; i < pTypes.length; i++) {
                logger.info(pTypes[i].getName());
            }
        }
    }

    /**
     * 3. 获取属性.
     */
    private static void getFields() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class clazz = Class.forName("javadev.反射.Reflect");
        Reflect r = (Reflect) clazz.newInstance();
        getReflect(r);

        // 1. 获取指定公有变量
        Field publicVar1 = clazz.getField("enable");

        // 2. 获取指定私有变量
        Field privateVar1 = clazz.getDeclaredField("id");
        Field privateVar2 = clazz.getDeclaredField("name");
        // 私有变量打开可见权限
        privateVar1.setAccessible(true);
        privateVar2.setAccessible(true);
        // 对变量赋值操作
        privateVar1.set(r, 8);
        privateVar2.set(r, "姓名");
        // 获取私有变量的值
        Integer id = (Integer) privateVar1.get(r);
        String name = (String) privateVar2.get(r);
        logger.info("id: " + id);
        logger.info("name: " + name);
        logger.info("============================================");

        // 3. 获取全部公有变量
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            logger.info(f.getName()+ ": " + f.get(r));
        }
        logger.info("============================================");

        // 4. 获取全部变量
        Field[] allFields = clazz.getDeclaredFields();
        for (Field f : allFields) {
            f.setAccessible(true);
            logger.info(f.getName()+ ": " + f.get(r));
        }
    }

    /**
     * 4. 获得方法.
     */
    private static void getMethod() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Class.forName("javadev.反射.Reflect");
        Reflect r = (Reflect) clazz.newInstance();
        getReflect(r);

        // 1. 获取指定公有方法并执行( 如果方法有参数，则 getMethod(name,param1.class,param2.class...) 获取方法 )
        Method m1 = clazz.getMethod("toString");
        Method m2 = clazz.getMethod("setId", Integer.class);
        // 调用方法( 如果方法有参数，则 invoke(r,param1,param2...) 执行方法 )
        logger.info(m1.invoke(r));
        m2.invoke(r, 1);
        logger.info(r.toString());

        // 2. 获取指定私有方法并执行
        Method m3 = clazz.getDeclaredMethod("getReflectName");
        m3.setAccessible(true);
        logger.info(m3.invoke(r));

        // 3. 获取所有方法集合
//        Method[] methods = clazz.getMethods();  // 获取公有方法集合
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            m.setAccessible(true);
            logger.info("method: " + m.getName());
            Class<?>[] paramTypes = m.getParameterTypes();
            for (Class c : paramTypes) {
                logger.info(c.getName());
            }
        }
    }

    private static void getReflect(Reflect r){
        r.setId(6);
        r.setCode("scoff");
        r.setName("ashram");
        r.setAge(20);
        r.setEnable(true);
    }
}
