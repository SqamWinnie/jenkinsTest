package javabase.父类;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 11861 on 2018/4/3.
 */
public class T_test {
    public static void main(String[] args){
        pet pet = new pet();
        cat cat = new cat();
        aaa aaa = new aaa();
        List<? super pet> su = new ArrayList<>();
        Iterator<? super pet> sui = su.iterator();
        List<? extends pet> ex = new ArrayList<>();
        for(aaa p:ex){

        }
        Iterator<? extends pet> exi = ex.iterator();
        while(sui.hasNext())         //判断下一个元素之后有值
        {
            while(exi.hasNext()){
                Object os = new Object();
                Object oe = new Object();
                pet =  exi.next();
                aaa =  exi.next();
                cat = (cat) exi.next();
                oe = exi.next();
                os = sui.next();
                os = oe;
                oe = os;
            }
            Object o = new Object();
            pet = (pet) sui.next();
            aaa = (aaa) sui.next();
            cat = (cat) sui.next();
            o = sui.next();
        }

        List<? super pet> pets2 = new ArrayList<>();





    }
}

class aaa{

}

class pet extends aaa{
    String name = "pet";
    int age = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class cat extends pet{
    String name = "cat";
    int age = 5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class extend<T> {

}