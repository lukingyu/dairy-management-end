package com.haut.dm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.dm.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
