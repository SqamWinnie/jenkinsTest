package javadev.加密;

import javadev.加密.md5.DecryptMD5;
import javadev.加密.md5.SecurityUtil;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @date 2019/5/10
 */
public class 加密测试 {
    public static void main(String[] args) {
        String s = SecurityUtil.strToMD5("123456");
        System.out.println(s);

        s = "123456";
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + DecryptMD5.md5(s));
        System.out.println("MD5后再加密：" + DecryptMD5.KL(DecryptMD5.md5(s)));
        System.out.println("解密为MD5后的：" + DecryptMD5.JM(DecryptMD5.KL(DecryptMD5.md5(s))));
    }
}
