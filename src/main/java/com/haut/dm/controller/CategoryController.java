package com.haut.dm.controller;

import com.haut.dm.domain.entity.Category;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categoryExist")
    public MyRes categoryExist(@RequestParam("brandId") Integer brandId,
                               @RequestParam("categoryName") String categoryName){
        return categoryService.categoryExist(brandId, categoryName);
    }

    @PostMapping("/addCategory")
    public MyRes addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PostMapping("/getCategoriesByPage") //虽然表单数据名和上面一样，但是提交的是完全不同的数据
    public MyRes getCategoriesByPage(@RequestParam(value = "brandId", required = false) Integer brandId,
                                     @RequestParam(value = "categoryName", required = false) String categoryName,
                                     @RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("pageSize") Integer pageSize){
        return categoryService.getCategoriesByPage(brandId, categoryName, pageNum, pageSize);
    }

    @PostMapping("/delCategory/{id}")
    public MyRes delCategory(@PathVariable("id") Integer id){
        return categoryService.delCategory(id);
    }
}
