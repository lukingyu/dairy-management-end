package com.haut.dm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.haut.dm.domain.VO.CategoryVo;
import com.haut.dm.domain.entity.Brand;
import com.haut.dm.domain.entity.Category;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.enums.ResEnum;
import com.haut.dm.mapper.CategoryMapper;
import com.haut.dm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public MyRes categoryExist(Integer brandId, String categoryName) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("category_name", categoryName)
                        .eq("brand_id", brandId);
        Category category = categoryMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(category)){
            //该分类不存在
            return MyRes.success(ResEnum.ANYTHING_SUC, false);
        }
        return MyRes.success(ResEnum.ANYTHING_SUC, true);
    }

    @Override
    public MyRes addCategory(Category category) {
        categoryMapper.insert(category);
        return MyRes.success(ResEnum.ANYTHING_SUC);
    }

    @Override
    public MyRes getCategoriesByPage(Integer brandId, String categoryName, Integer pageNum, Integer pageSize) {
        Page<CategoryVo> page = categoryMapper.selectJoinPage(new Page<>(pageNum, pageSize), CategoryVo.class,
                new MPJLambdaWrapper<Category>().selectAll(Category.class).select(Brand::getBrandName)
                        .leftJoin(Brand.class, Brand::getId, Category::getBrandId)
                        .eq(!ObjectUtils.isEmpty(brandId), Category::getBrandId, brandId)
                        .like(StringUtils.hasText(categoryName), Category::getCategoryName, categoryName)
        );
        return MyRes.success(ResEnum.QUERY_SUCCESS, page);
    }

    @Override
    public MyRes delCategory(Integer id) {
        categoryMapper.deleteById(id);
        return MyRes.success(ResEnum.DELETE_SUCCESS);
    }


}
