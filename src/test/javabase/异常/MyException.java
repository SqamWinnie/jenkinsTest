package javabase.异常;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @description MyException
 * @date 2019/4/25
 */
public class MyException extends Exception {
    static final long serialVersionUID = -7034897190745766939L;

    /** 自定义异常.
     *
     * @param message 异常错误信息
     */
    MyException(String message) {
        super(message);
    }

    /**
     * 返回异常类描述，spring 框架将此描述信息返回到前台.
     *
     * @return 异常类描述字符串.
     */
    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? message : s;
    }
}

