package javabase.文件;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * 练习IO操作 javaIo文件的复制 <br/>  *
 */
public class C_FileIo_Copy {
    /**
     * 文件复制 
     * 基本思路还是从一个文件中读入内容，边读边写入另一个文件，就是这么简单 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String sou =  "D:"+ File.separator +"/zz_upload/111.txt";
        String dir =  "D:"+ File.separator +"/zz_upload/javaCreateFDir1/112.txt";
        File file1=new File(sou);
        File file2=new File(dir);
        if(!file1.exists()){
            System.out.println("被复制的文件不存在");
            System.exit(1);
        }
        InputStream input=new FileInputStream(file1);
        OutputStream output=new FileOutputStream(file2);
        if((input!=null)&&(output!=null)){
            int temp=0;
            while((temp=input.read())!=(-1)){
                output.write(temp);
            }
        }
        input.close();
        output.close();
    }
}  
