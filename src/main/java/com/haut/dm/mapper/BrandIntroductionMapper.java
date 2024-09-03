package com.haut.dm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.dm.domain.entity.BrandIntroduction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BrandIntroductionMapper extends BaseMapper<BrandIntroduction> {
    @Select("select logo_url from brand_introduction where brand_id = #{brandId};")
    String getLogoUrl(@Param("brandId") Integer brandId);

}
