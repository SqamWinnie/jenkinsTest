package javadev.java8.方法引用;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @date 2019/8/29
 */
public class Test {
    public static void main(String[] args) {
        // 构造器引用：它的语法是 Class::new，或者更一般的 Class< T >::new 实例如下：
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);

        // 静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach(Car::collide);

        // 特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach(Car::repair);

        // 特定对象的方法引用：它的语法是instance::method实例如下：
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }
}
