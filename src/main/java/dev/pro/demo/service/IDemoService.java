package dev.pro.demo.service;

import java.util.List;

import dev.pro.demo.dto.Demo;

/**
 * @author ...
 */

public interface IDemoService {

    /**
     * 查询.
     * @param demo 实体
     * @return 列表
     */
    List<Demo> select(Demo demo);

    /**
     * 保存.
     * @param demos 列表
     * @return 列表
     */
    List<Demo> save(List<Demo> demos);

    /**
     * 删除.
     * @param demos 列表
     */
    void remove(List<Demo> demos);

    /**
     * AOP 类测试.
     * @return string
     */
    List<String> aopClass();

    /**
     * AOP 注解测试.
     * @return string
     */
    String aopAnno();

}
