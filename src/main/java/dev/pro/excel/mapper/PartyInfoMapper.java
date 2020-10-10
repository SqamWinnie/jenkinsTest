package dev.pro.excel.mapper;

import java.util.List;

import dev.pro.excel.dto.PartyInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author 11861
 */
public interface PartyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PartyInfo record);

    int insertSelective(PartyInfo record);

    PartyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PartyInfo record);

    int updateByPrimaryKey(PartyInfo record);

    /**
     * 批量插入数据
     * @param list 数据
     * @return int
     */
    int batchInsert(@Param("list") List<PartyInfo> list);


    /**
     * 查询数据
     * @return 数据
     */
    List<PartyInfo> selectAll();
}


