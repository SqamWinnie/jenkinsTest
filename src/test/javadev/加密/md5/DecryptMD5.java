package javadev.加密.md5;

import java.security.MessageDigest;

/**
 * MD5 加密不可逆.
 * 明文通过 MD5 变成 MD5 文，再加密成密文保存
 * 密文可以解密成 MD5 文
 */
public class DecryptMD5 {

    // MD5加码。32位
    public static String md5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    // 可逆的加密算法
    public static String KL(String inStr) {
        // String s = new String(inStr);
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

    // 加密后解密
    public static String JM(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

    // 测试主函数
    public static void main(String args[]) {
        String s = "sa";
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + md5(s));
        System.out.println("MD5后再加密：" + KL(md5(s)));
        System.out.println("解密为MD5后的：" + JM(KL(md5(s))));
    }
}