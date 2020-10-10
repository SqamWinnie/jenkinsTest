package javabase.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 11861 on 2018/4/9.
 */
public class lambda_map {
    public static void main(String[] args){

       /* List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        // 不使用lambda表达式为每个订单加上12%的税
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }
        // 使用lambda表达式
        costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
        //返回类型 List<Double>
        List<Double> costBeforeTaxs = costBeforeTax.stream().map((cost) -> cost + .12*cost).collect(Collectors.toList());
        costBeforeTaxs.forEach(System.out::println);

        // 为每个订单加上12%的税
        // 老方法：
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        // 新方法：
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);


        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);

        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,%nSquare Without duplicates : %s", numbers, distinct);

        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());*/

        Integer[] a = new Integer[]{2,5,3,7};
        List<Integer> primes = Arrays.asList(a);
        int 整形 = 10;
        primes.stream().map(x-> x+10).forEach(System.out::println);

        primes.forEach(x -> System.out.println(x));

    }

}
