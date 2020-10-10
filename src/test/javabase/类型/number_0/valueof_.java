package javabase.类型.number_0;

import static java.lang.Math.rint;

/**
 * Created by 11861 on 2018/3/9.
 */
public class valueof_ {
    public static void main(String args[]){
        Integer x =Integer.valueOf(9);
        Double c = Double.valueOf(5);
        Float a = Float.valueOf("80");

        Integer b = Integer.valueOf("32",16);   // 使用 16 进制

        double n = rint(4.51);
        System.out.println(n);
       /* System.out.println(x);
        System.out.println(c);
        System.out.println(a);
        System.out.println(b);*/
    }
}
