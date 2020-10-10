package javabase.文件;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 11861
 * @version 1.0
 * @date 2018/7/9
 */
public class FileIoTest01 {
    public static void main(String[] args) throws IOException {
        writeByteFile();
    }
    /**
     * 字节流
     * 1:向文件中写入字符串
     * 2:向文件中一个字节一个字节的写入字符串
     * 3:向文件中追加新内容
     * @throws IOException
     */
    public static void writeByteFile() throws IOException{
        /**若文件不存在则会自动创建文件，若存在原文件被覆盖(不创建文件夹)*/
        String path = "D:"+ File.separator +"/zz_upload/111.xlsx";
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        String str = "Hello World!";
        byte[] b = str.getBytes();
        //1:向文件中写入字符串 （方法1写入字符串）
        os.write(b);
        //2:向文件中一个字节一个字节的写入字符串（方法2写入字符串）
        for (int i = 0; i < b.length; i++) {
            os.write(b[i]);
        }
        //3:向文件中追加新内容
        String appendStr = "\nappend new words!"; // \n 表示换行
        byte[] b1 = appendStr.getBytes();
        os.write(b1);//输出 Hello World! append new words!
        os.flush();
        os.close();
    }
}
