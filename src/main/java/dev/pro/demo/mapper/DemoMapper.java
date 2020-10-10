package dev.pro.demo.mapper;

import java.util.List;

import dev.com.apoint.Anno;
import dev.pro.demo.dto.Demo;

/**
 * @author ...
 */

public interface DemoMapper {

    /**
     * 查询.
     * @param demo 实体
     * @return 列表
     */
    @Anno("inter")
    List<Demo> select(Demo demo);

    /**
     * 根据 id 查询.
     * @param demo 实体
     * @return 列表
     */
    Demo selectById(Demo demo);

    /**
     * 新增.
     * @param demo 实体
     */
    void insert(Demo demo);

    /**
     * 更新.
     * @param demo 实体
     */
    void update(Demo demo);

    /**
     * 删除.
     * @param demo 实体
     */
    void remove(Demo demo);
}
