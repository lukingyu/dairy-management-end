package com.haut.dm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.dm.domain.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
