package com.haut.dm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.haut.dm.domain.entity.ProductDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailMapper extends MPJBaseMapper<ProductDetail> {
}
