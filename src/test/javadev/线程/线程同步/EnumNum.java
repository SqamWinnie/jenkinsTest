package javadev.线程.线程同步;

/**
 * @author --
 * @description 枚举类型
 */
public enum EnumNum {
    /*
     * NUM
     */
    NUM(10000);

    private Integer value;

    EnumNum(Integer value){
        this.value = value;
    }

    public Integer getValue(){return this.value;}
}
