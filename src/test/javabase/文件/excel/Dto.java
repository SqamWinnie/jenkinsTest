package javabase.文件.excel;

import java.util.Date;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @description dto
 * @date 2019/1/31
 */
public class Dto {
    private Long id;
    private String name;
    private int age;
    private String property;
    private Date birthday;
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthdayPattern() {
        return "YYYY-MM-DD HH:mm:ss";
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getTime() {
        return time;
    }
    public String getTimePattern() {
        return "HH:mm:ss";
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
