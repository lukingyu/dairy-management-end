package com.haut.dm.service;

import com.haut.dm.domain.entity.Category;
import com.haut.dm.domain.entity.MyRes;

public interface CategoryService {
    MyRes categoryExist(Integer brandId, String categoryName);

    MyRes addCategory(Category category);

    MyRes getCategoriesByPage(Integer brandId, String categoryName, Integer pageNum, Integer pageSize);

    MyRes delCategory(Integer id);
}
