package javabase.类加载.自定义类加载器;

import java.io.FileInputStream;

/**
 * @author ...
 * @date 2019/6/27
 */

public class DivClassLoader extends ClassLoader {
    // 被加载的 class 文件存放目录
    private String classPath;

    public DivClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 先去加载器里面看看已经加载到的类中是否有这个类
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            //先用本类加载器查询
            try {
                c = this.findClass(name);
            } catch (Exception e) {
            }
            if (c == null) {
                //本类加载不到再用父类的方法进行双亲委派机制查
                c = super.loadClass(name);
            }
        }

        return c;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            throw new ClassNotFoundException();
        }
    }

    /**
     * 获取加载的class文件的字节流数据
     */
    private byte[] loadByte(String name) throws Exception {
        //通过类的全路径名称生成路径
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }
}
