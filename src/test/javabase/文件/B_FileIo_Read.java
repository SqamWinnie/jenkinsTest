package javabase.文件;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

/**
 *
 * 练习IO操作 往文件中写入内容，读取文件中的内容！<br/>
 * @author 11861
 *
 */
public class B_FileIo_Read {

    public static void main(String[] args) throws IOException {
        //写文件 (字节流)
        writeByteFile();
        //读文件 (字节流，限定读文件大小)
        readByteFile();
        //读文件（字节流，不限文件大小）
        changeReadByteFile();
        //写文件 (字符流)
        writeStringFile();
        //读文件 (字符流)
         readStringFile();
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
        String path = "D:"+ File.separator +"/zz_upload/111.txt";
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
        // \n 表示换行
        String appendStr = "\nappend new words!";
        byte[] b1 = appendStr.getBytes();
        //输出 Hello World! append new words!
        os.write(b1);
        os.flush();
        os.close();
    }
    /**
     * 字节流
     * 1:读取内容
     * @throws IOException
     */
    public static void readByteFile() throws IOException{
        String path = "D:"+ File.separator +"/zz_upload/111.txt";
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        byte b[] = new byte[1024]; //设置最多存1024个字节
        int len = is.read(b);
        is.close();
        System.out.println(new String(b));
        System.out.println("文件读入长度： " + len);
    }
    /**
     * 修改上面那种方式
     * 字节流
     * 上面那种方式   预先申请了一个指定大小的空间，但是有时候这个空间可能太小，有时候可能太大，我们需要准确的大小，这样子可以更好的利用空间
     * @throws IOException
     */
    public static void changeReadByteFile() throws IOException{
        String path =  "D:"+ File.separator +"/zz_upload/111.txt";
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        byte b[] = new byte[(int) file.length()]; //设置读取的该文件的长度，这样子可以很好的设置使用的空间
        //附1:一个字节一个字节的读
        /*for (int i = 0; i < b.length; i++) {
            b[i] = (byte) is.read();
        }*/
        //附2:判断文件是否读完
        int tmp = 0;
        int count = 0;
        while((tmp = is.read())!= -1){
            b[count++] = (byte) tmp;
        }
        is.close();
        System.out.println(new String(b));
        System.out.println(count);

    }
    /**
     * 字符流
     * 文件写人字符数据
     * @throws IOException
     */
    public static void  writeStringFile() throws IOException{
        String path =  "D:"+ File.separator +"/zz_upload/111.txt";
        File file = new File(path);
        Writer out = new FileWriter(file);
        String str = "你好，世界！";
        out.write(str);
        out.close();
    }
    /**
     * 字符流
     * 文件读出
     * @throws IOException
     */
    public static void readStringFile() throws IOException{
        String path = "D:"+ File.separator +"/zz_upload/111.txt";
        File file = new File(path);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String str = "";
        while((str = bf.readLine()) != null){ //按行读
            System.out.println(str);
        }
        bf.close();
    }

    /**
     * 总结 字节流和字符流的区别
     *  字节流在操作的时候本身是不会用到缓冲区的，是文件本身的直接操作的，但是字符流在操作的时候是会用到缓冲区的，是通过缓冲区来操作文件的。
     *  试着将上面的字节流和字符流的程序的最后一行关闭文件的代码注释掉，然后运行程序看看。你就会发现使用字节流的话，文件中已经存在内容，但是使用字符流的时候，文件中还是没有内容的，这个时候就要刷新缓冲区。
     *  那到底那个好一些呢？
     *  答案是字节流。首先因为硬盘上的所有文件都是以字节的形式进行传输或者保存的，包括图片等内容。但是字符只是在内存中才会形成的，所以在开发中，字节流使用广泛。
     */
}
