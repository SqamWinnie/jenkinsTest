package javabase.类型.collection;

import java.util.Arrays;

/**
 * @author 11861
 * @version 1.0
 * @date 2018/7/19
 */
public class array {
    public static void main(String[] args) {
        int[] a = new int[4];
        a[1] = 1234;
        int[] b = new int[4];
        for(int i=0;i<4;i++){
            b[i] = a[i];
        }
        b[1] = 5678;

        System.out.println(Arrays.asList(a));
        System.out.println(b[1]);

    }
}
