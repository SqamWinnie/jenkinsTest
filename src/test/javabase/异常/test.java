package javabase.异常;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @date 2019/4/25
 */
public class test {
    public static void main(String[] args) {
        Throwable thr = get(new MyException("报错！"));
        Throwable thr1 = get(new Exception("报错！"));
        System.out.println(thr.toString());
        System.out.println(thr1.toString());
    }

    private static Throwable get(Throwable throwable){
        while(throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        return throwable;
    }
}
