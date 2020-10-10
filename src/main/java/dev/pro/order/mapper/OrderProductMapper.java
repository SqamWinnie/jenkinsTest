package dev.pro.order.mapper;

import dev.pro.order.dto.OrderProduct;

/**
 * @author ...
 */
public interface OrderProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);
}