package javabase.lambda;

/**
 * Created by 11861 on 2018/4/4.
 */
public class lambda {
    public static void main(String[] args) {
        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        //Java 8方式：
        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
        /*(params) -> expression
          (params) -> statement
          (params) -> { statements }*/

        accept(new Inter() {
            @Override
            public void ccc(int even, int odd) {
                System.out.println("People");
            }
        },12);
       accept ((int even, int odd) -> System.out.println("111"),12);
    }

    public static void accept(Inter inter,int i){
        inter.ccc(1,2);
    }
    }

interface Inter{

    public void ccc(int even, int odd);
}
