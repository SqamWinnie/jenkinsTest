package javabase.正则;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 11861 on 2017/12/13.
 */
public class Patterns {
    public static void main(String[] args) {

       /* test1(2);
        test2(-3);*/
       /* try {
            factorial(-1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("2345678");
            System.out.println(e);
        }*/
       /* try {
            homePageSize("www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*delete();*/
//        equals_("a",null);
        f5();
//        System.out.println(f6("12-3456-123-1234-123",1));
    }

    public static String f6(String string, int number) {
        String counts = string.replaceAll("-", "");
        if (string.length() - counts.length() >= number) {
            try {
                //这里是获取"-"符号的位置
                Matcher slashMatcher = Pattern.compile("-").matcher(string);
                int mIdx = 0;

                while (slashMatcher.find()) {
                    /*System.out.println(slashMatcher.group());*/
                    //当"-"符号第三次出现的位置
                    if (mIdx == number - 1) {
                        break;
                    }
                    mIdx++;
                }
                /*System.out.println(slashMatcher.start());*/
                return string.substring(0, slashMatcher.start());
            } catch (Exception e) {
                return string;
            }
        } else {
            return string;
        }
    }

    public static void f5() {
        TreeSet<String> set = new TreeSet<String>();

        set.add("0");
        set.add(null);
        set.add("A");
        set.add("00000");
        set.add("000");
        set.add("_");
        Iterator i = set.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

    public static void equals_(String a, String b) {
        if (a.equals(b)) {
            System.out.println("equals");
        }
    }

    public static void delete() {
        File f = new File("D:/image/1/2.jpg");
        if (f.exists()) {
            f.delete();
        }
    }

    /*获取年月日*/
    public static void test3(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(date);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(d));
        } catch (ParseException e) {
            System.out.println(e);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        System.out.printf("year=%04d, month=%02d, day=%02d\n", year, month, day);
    }

    public static void homePageSize(String host) throws IOException {
        URL url = new URL("http://" + host + "/");
        try (InputStream in = url.openStream()) {
            System.out.println(in.available());
            System.out.println(in.read());
            System.out.println(in.markSupported());
            System.out.println(in.toString());
            System.out.println(in.hashCode());
            System.out.println(in);
        }
    }

    //九九乘法表
    public static void table() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " * " + i + " = " + i * j + "   " + "\t");
            }
            System.out.println();
        }
    }

    public static double factorial(int x) {
        if (x < 0) throw new IllegalArgumentException("x must be >=0");
        double fact;
        for (fact = 1.0; x > 1; fact *= x, x--) ;
        return fact;
    }

    public static void test1(int a) {
        assert a > 0;
        System.out.println(a);
    }

    public static void test2(int a) {
        assert a > 0 : "something goes wrong here, a cannot be less than 0";
        System.out.println(a);
    }

    public static void f1() {
        Pattern p = Pattern.compile("honou?r");
        String s1 = "For Brutus is an honourable man";
        String s2 = "For Brutus is an honorable man";
        Matcher m1 = p.matcher(s1);
        Matcher m2 = p.matcher(s2);

        System.out.println(m1.find());
        System.out.println(m2.find());
    }

    public static void f2() {
//        . 代表任意一个字符
        Pattern p = Pattern.compile("a..zA..Z");
        String s1 = "aqwzAqwZ";
        Matcher m1 = p.matcher(s1);
        System.out.println(m1.find());
        System.out.println(m1.group());
    }

    public static void f3() {
//        \d 代表一个数字   \D 代表不是数字的字符  \w 代表数字，字母和_  \W 不能组成单词的字符  \s 空白字符  \S 一个不是空白的字符
//        \n 换行符  \t 制表符  [] 方括号中任意一个字符
        Pattern p = Pattern.compile("\\w");
        String s1 = "aqwzAqwZ3";
        Matcher m1 = p.matcher(s1);
        System.out.println(m1.find());
        System.out.println(m1.group());
    }

    public static void f4() {

        int flags = 2;
        int f = 1;
        flags |= f;
        System.out.println(flags);
        flags = 2;
        flags &= ~f;
        System.out.println(flags);

    }
}
