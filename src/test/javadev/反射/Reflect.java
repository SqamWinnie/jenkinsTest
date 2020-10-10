package javadev.反射;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @date 2019/6/14
 */
public class Reflect {

    Reflect(){}

    public Reflect(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Reflect(int id, String code, String name, int age, boolean enable){
        this.setId(id);
        this.setCode(code);
        this.setName(name);
        this.setAge(age);
        this.setEnable(enable);
    }

    private Integer id;

    private String code;

    private String name;

    private Integer age;

    public Boolean enable;

    @Override
    public String toString(){
        return Reflect.class.getName() + "\nid: " + id + ", code: " + code
                + ", name: " + name + ", age: " + age + ", enable: " + enable;
    }

    private String getReflectName(){
        return Reflect.class.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        if (null == enable) {
            enable = true;
        }
        this.enable = enable;
    }
}
