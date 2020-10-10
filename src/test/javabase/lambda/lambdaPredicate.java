package javabase.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class lambdaPredicate {

    public static void main(String[] args) {
            /*List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            lambdaPredicate predicateTest = new lambdaPredicate();
            //输出大于5的数字
            List<Integer> result = predicateTest.conditionFilter(intList, integer -> integer > 5);
            result.forEach(System.out::print);
            System.out.println("\n-------");
            //输出大于等于5的数字
            result = predicateTest.conditionFilter(intList, integer -> integer <= 5);
            result.forEach(System.out::print);
            System.out.println("\n-------");
            //输出所有数字
            result = predicateTest.conditionFilter(intList, integer -> true);
            result.forEach(System.out::print);
            System.out.println("\n-------");


            List<String> strlist = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
            lambdaPredicate lambdaPredicate = new lambdaPredicate();
            //输出以J开始的字符串
            List<String> strs = lambdaPredicate.conditionFilters(strlist, str -> str.startsWith("J"));
            strs.forEach(System.out::println);
            System.out.println("-------");
            //输出以a结尾的字符串
            strs = lambdaPredicate.conditionFilters(strlist, str -> str.endsWith("a"));
            strs.forEach(System.out::println);
            System.out.println("-------");
            //输出所有字符串
            strs = lambdaPredicate.conditionFilters(strlist, str -> true);
            strs.forEach(System.out::println);
            System.out.println("-------");
            //输出字母长度大于3的字符串
            strs = lambdaPredicate.conditionFilters(strlist, str -> str.length() > 3);
            strs.forEach(System.out::println);
            System.out.println("-------");



            List<String> names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp","JQuery");
            // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
            // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
            Predicate<String> startsWithJ = (n) -> n.startsWith("J");
            Predicate<String> fourLetterLong = (n) -> n.length() > 4;
            names.stream()
                    .filter(startsWithJ.and(fourLetterLong))
                    .forEach((n) -> System.out.print("names, which starts with 'J' and more than four letter is : " + n+"\n"));
            System.out.println("_____________________");
            names.stream()
                    .filter(startsWithJ.or(fourLetterLong))
                    .forEach((n) -> System.out.print("names, which starts with 'J' or more than four letter is : " + n+"\n"));*/


        List<String> strlist = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        // 创建一个字符串列表，每个字符串长度大于2
        List<String> filtered = strlist.stream().filter(x -> x.length()> 4).collect(Collectors.toList());
        System.out.printf("Original List: %s, %nfiltered List: %s", strlist, filtered);


    }

    public List<Integer> conditionFilter(List<Integer> intList, Predicate<Integer> predicate){
        return intList.stream().filter(predicate).collect(Collectors.toList());
    }
    public  List<String> conditionFilters(List<String> strList, Predicate<String> predicate){
        return strList.stream().filter(predicate).collect(Collectors.toList());
    }









}
