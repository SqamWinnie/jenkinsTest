package javabase.文件;

import java.io.File;

/**
 * 删除目录下所有文件夹和文件
 */
public class D_FileIo_Delete {
    public static void main(String[] args){
       /* delete("D:/zz_upload/javaCreateFDir1");
        File 文件 = new File("D:"+File.separator+"zz_upload"+File.separator+"javaCreateFDir1");
        deleteDirs(文件);*/
        String 反斜杠 = File.separator;
        System.out.println("D:"+反斜杠+"zz_upload"+反斜杠+"javaCreateFDir1");
    }

    public static void delete(String filePath){
        File file = new File(filePath);
        if((file.isDirectory())){
            File[] listFiles = file.listFiles();
            if(listFiles.length!=0&&listFiles!=null){
                for (File file2 : listFiles) {
                    delete(file2.getAbsolutePath());
                }
                file.delete();
                System.out.println("删除文件夹 "+file.getAbsolutePath());
            }else{
                file.delete();
                System.out.println("删除文件夹 "+file.getAbsolutePath());
            }
        }else if(file.isFile()){
            file.delete();
            System.out.println("删除文件 "+file.getAbsolutePath());
        }else{
            System.out.println("文件目录不存在");
        }
    }

    public static void deleteDirs(File file){
        if((file.isDirectory())){
            File[] listFiles = file.listFiles();
            if(listFiles.length!=0 && listFiles!=null){
                for (File file2 : listFiles) {
                    deleteDirs(file2);
                }
                file.delete();
                System.out.println("删除文件夹 "+file.getAbsolutePath());
            }else{
                file.delete();
                System.out.println("删除文件夹 "+file.getAbsolutePath());
            }
        }else if(file.isFile()){
            file.delete();
            System.out.println("删除文件 "+file.getAbsolutePath());
        }else{
            System.out.println("文件目录不存在");
        }
    }
}
