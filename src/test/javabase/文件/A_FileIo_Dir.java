package javabase.文件;

import java.io.File;

/**
 *
 * 练习IO操作 基本创建，删除文件/文件夹
 * @author 11861
 */
public class A_FileIo_Dir {

    public static void main(String[] args) {
       //1. 文件夹分隔符
       printConstant();
        //2. 创建文件夹
        createDirs();
        //3. 创建文件
        createFile();
         //4. 删除文件夹
        deleteDirs();
        //5. 删除文件
        deleteFile();
        //6. 列出目录下的文件和文件夹名称（包括隐藏文件，不包括子层文件或文件夹）
         list();
         //7. 列出目录下的文件和文件夹路径及名称（包括隐藏文件，不包括子层文件或文件夹）
         listFiles();
         //8. 判断是文件还是文件夹
        isDirectory();
        //9. 列出目录路径和目录下的所有文件夹路径和文件名称（包括所有子层文件或文件夹）
        String pathName = "D:"+ File.separator+"zz_upload";
        File file = new File(pathName);
        findAllFf(file);
    }
    /** File类的两个常量 */
    public static void printConstant() {
        System.out.println(File.separator); //打印结果  \
        System.out.println(File.pathSeparator); //打印结果  ;
        //注：在使用中为了我们程序的可移植性和健壮性，建议使用 这两个常量
    }
    /** 创建文件夹 */
    public static void createDirs() {
        String dir1 = "D:"+ File.separator+"zz_upload/javaCreateFDir1";
        String dir2 = "D:"+ File.separator+"zz_upload/javaCreateFDir2/javaCreateFDir21";
        File file1 = new File(dir1);
        if(!file1.exists()){
            file1.mkdir(); //boolean mkdir()   只建立一级文件夹，即只有最后一个文件夹是不存在的。
            System.out.println("目录 "+dir1+" 创建成功！");
        }
        File file2 = new File(dir2);
        if(!file2.exists()){
            file2.mkdirs(); //boolean mkdirs()  建立多级文件夹。
            System.out.println("目录 "+dir2+" 创建成功！");
        }
    }
    /** 创建文件 */
    public static void createFile() {
        String fileName = "D:"+ File.separator+"zz_upload/firstFile.txt";
        File file = new File(fileName);
        try {//使用try catch 捕获异常
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            //createTempFile(prefix, suffix); 如 createTempFile("tmp", ".txt"); 创建临时文件，默认在c:下
            //createTempFile(prefix, suffix, directory); 指定的目录创建临时文件
            System.out.println("文件 "+fileName+" 创建成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** 删除一个文件夹 */
    public static void deleteDirs() {
        String dir1 = "D:"+ File.separator+"zz_upload/javaCreateFDir1";
        File file = new File(dir1);
        if(file.exists()){
            file.delete();
            System.out.println("删除文件夹 "+dir1);
        }else{
            System.out.println("文件夹不存在！");
        }
    }
    /** 删除一个文件 */
    public static void deleteFile() {
        String fileName = "D:"+ File.separator+"zz_upload/firstFile.txt";
        File file = new File(fileName);
        if(file.exists()){
            file.delete();
            System.out.println("删除文件 "+fileName);
        }else{
            System.out.println("文件不存在！");
        }
    }
    /** 列出目录下的文件和文件夹名称（包括隐藏文件） */
    public static void list() {
        File file = new File("D:"+ File.separator+"zz_upload");
        String[] list = file.list();
        for (String string : list) {   //使用file.list()列出的不是完整路径,返回的是目录下文件和文件夹名称
            System.out.println(string);
        }
    }
    /**列出目录下的文件和文件夹路径及名称（包括隐藏文件）*/
    public static void listFiles() {
        File file = new File("D:"+ File.separator+"zz_upload");
        File[] listFiles = file.listFiles();
        for (File file2 : listFiles) {
            System.out.println(file2);
        }
    }
    /** 判断是文件夹还是文件 */
    public static void isDirectory() {
        String dirName = "D:"+ File.separator+"zz_upload/javaCreateFDir1";
        String fileName = "D:"+ File.separator+"zz_upload/firstFile.txt";
        File f1 = new File(dirName);
        File f2 = new File(fileName);
        if(f1.isDirectory()){
            System.out.println(dirName+" is a Directory");
        }
        if(f2.isFile()){
            System.out.println(fileName+" is a File");
        }
    }
    /** 列出目录路径和目录下的所有文件夹路径和文件名称（包括所有子层文件或文件夹） */
    public static void findAllFf(File f) {
        if(f != null){
            if(f.isDirectory()){
                System.out.println(f);
                File[] listFiles = f.listFiles();
                for (File file : listFiles) {
                    findAllFf(file);    //递归本方法
                }
            }else{
                System.out.println(f);
            }
        }
    }
}
