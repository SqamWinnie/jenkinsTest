package javadev.安全.b_字节码校验;

/**
 * 当类加载器将新加载的 Java 平台类的字节码传递给虚拟机时，这些字节码首先要接受校验器(verifier)的校验。
 * 除了系统类外，所有的类都要被校验，不过，可以使用非正式的 noverify 选项来钝化校验。
 * 使用命令行： java -noverify Hello
 * 校验器负责检查那些指令无法执行的明显有破坏性的操作，有则不予加载。以下检查的内容：
 * 1. 变量要在使用之前进行初始化。
 * 2. 方法调用与对象引用类型之间要匹配。
 * 3. 访问私有数据和方法的规则没有被违反。
 * 4．对本地变量的访问都在运行在堆栈内。
 * 5. 运行时堆栈没有溢出。
 * @author ...
 * @date 2019/6/27
 */
public class Verifier {
    public static void main(String[] args) {
       fun1();
    }

    static int fun1() {
        int m;
        int n;
        m = 1;
        n = 2;
        int r = m + n;
        return r;
    }

}
