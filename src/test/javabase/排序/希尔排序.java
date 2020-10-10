package javabase.排序;


import java.util.Scanner;

/**
 * @author 11861
 * @version 1.0
 * @date 2018/7/20
 */
public class 希尔排序 {
    public static void main(String[] args){

        int[] a ={10,9,8,7,6,5,4,3,2,1};
        sort(a);

        System.out.print("输入三个整数,以空格隔开：");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        while(read.indexOf(" ")==0) {
            read = read.substring(read.indexOf(" ")+1);
        }
        int[] b = new int[3];
        b[0]= Integer.parseInt(read.substring(0,read.indexOf(" ")));
        read = read.substring(read.indexOf(" ")+1);
        b[1]= Integer.parseInt(read.substring(0,read.indexOf(" ")));
        read = read.substring(read.indexOf(" ")+1);
        if(read.indexOf(" ")>=0){
            read = read.substring(0,read.indexOf(" "));
        }
        b[2] = Integer.parseInt(read);
        if(b[0]==b[1]&&b[0]==b[2]&&(b[1]==b[2])){
           System.out.println("equal");
        }else{
            System.out.println("not equal");
        }


    }

    public static void sort(int[] a)
    { // 将a[]按升序排列
        int N = a.length;
        int h = 1;
        int temp = 0;
        while (h < N/3) {
            // 1, 4, 13, 40, 121, 364, 1093, ...
            h = 3 * h + 1;
        }
        while (h >= 1)
        { // 将数组变为h有序
            for (int i = h; i < N; i++)
            { // 将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]... 之中
                for (int j = i; j >= h && (a[j]<a[(j-h)]); j -= h) {
                    temp = a[j];
                    a[j] = a[j-h];
                    a[j-h] = temp;
                }
            }
            h = h/3;
        }
    }
}
