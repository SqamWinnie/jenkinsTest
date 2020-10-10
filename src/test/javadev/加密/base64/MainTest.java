package javadev.加密.base64;

import java.io.IOException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 使用 base64 编码格式.
 * 加码之后可以解码
 * @author 画一
 *
 */
public class MainTest {

    public static void main(String[] args) {
        //编码
        encode("编码");
        //解码
       decode("eyJhbGciOiJIUzI1NiJ9");
    }

    /**
     * 编码
     * @param str 字符
     */
    private static String encode(String str){
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(str.getBytes());
        System.out.println("编码后  " + base64);
        return base64;
    }

    /**
     * 解码
     * @param str 字符
     */
    private static String decode(String str){
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] d = decoder.decodeBuffer(str);
            System.out.println("解码后  " + new String(d));
            return new String(d);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}

