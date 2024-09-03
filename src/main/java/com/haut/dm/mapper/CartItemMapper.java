package com.haut.dm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.dm.domain.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {
}
