package javabase.类型.Enum;

/**
 * Created by 11861 on 2018/4/3.
 */
public enum ColorEnum {
    RED(1),YELLOW(2),BLUE(3);

    ColorEnum(int i) {
        switch (i){
            case 1:System.out.println(1);
                break;
            case 2:System.out.println(2);
                break;
            case 3:System.out.println(3);
                break;
        }
    }
}
