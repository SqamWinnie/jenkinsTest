package javabase.类型.collection;

import java.util.*;

/**
 * Created by 11861 on 2017/11/23.
 */
public class collection {
    public static void main(String [] args) {
        set();
    }

    public static void map() {
       /* Map<String, String> map =new HashMap<String,String>();
        map.put( "key1" , "value1");
        String s= map.get("key1");                          //获得 "key1" 的value值
        Boolean key = map.containsKey( "key1");        //key值中是否存在 "key1"
        Boolean value = map.containsValue("value");   //value值中是否存在 "value"
        //输出所有的 key
        Set<String> sets= map.keySet();
        Iterator<String> i =  sets.iterator();
        while (i.hasNext()){
            System. out. println(i.next());
        }
        //输出所有的 value
        Collection<String> c= map.values() ;
        Iterator<String> il =  c. iterator();
        while(il.hasNext()) {
            System. out. println(il.next());
        }*/


        List<String> str1 = new ArrayList<>();
        List<String> str2 = new ArrayList<>();
        /*for (String a : str1) {
            System.out.println(a);
        }*/
        str2.add("4");
        str2.add("5");
        str2.add("6");
        int x = 9;
        System.out.println((x--));
        System.out.println(x);


    }

    public static void set(){
        // 去重 + 排序
        Set<String> set = new HashSet<>();
        set.add("2");
        set.add("2");
        set.add("1");
        set.add("3");
        System.out.println(set.toString());
        set.removeIf(s -> s.equals("2"));
        System.out.println(set.contains("2"));
        System.out.println(set.toString());

    }

    public static void list(){
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        System.out.println("第"+integerList.toString()+";;");
        integerList.clear();
        System.out.println("第"+integerList.toString()+";;");
    }
}

