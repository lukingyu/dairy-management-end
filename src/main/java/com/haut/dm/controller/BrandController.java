package com.haut.dm.controller;

import com.haut.dm.domain.DTO.BrandDto;
import com.haut.dm.domain.entity.BrandIntroduction;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/uploadLogo")
    public MyRes uploadLogo(@RequestParam("file") MultipartFile multipartFile){
        return brandService.uploadLogo(multipartFile);
    }

    @PostMapping("/delLogo")
    public MyRes delLogo(@RequestBody String logoUrl){
//        System.out.println("=============================");
//        System.out.println(logoUrl);
//        System.out.println("=============================");
        return brandService.delLogo(logoUrl);
    }

    @PostMapping("/addBrand")
    public MyRes addBrand(@RequestBody BrandDto brandDto){
        return brandService.addBrand(brandDto);
    }

//    @GetMapping("/getBrandsFirstPage")
//    public MyRes getBrandsFirstPage(){
//        return brandService.getBrandsFirstPage();
//    }

    @GetMapping("/getBrandsByPage/{pageNum}/{pageSize}")
    public MyRes getBrandsByPage(@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize){
        return brandService.getBrandsByPage(pageNum, pageSize);
    }

    @PostMapping("/editBrand")
    public MyRes editBrand(@RequestBody BrandIntroduction brandIntroduction){
        return brandService.editBrand(brandIntroduction);
    }

    @PostMapping("/delBrand/{brandId}")
    public MyRes delBrand(@PathVariable("brandId") Integer brandId){
        return brandService.delBrand(brandId);
    }

    @GetMapping("/getCategoriesByBrandId/{brandId}")
    public MyRes getCategoriesByBrandId(@PathVariable("brandId") Integer brandId){
        return brandService.getCategoriesByBrandId(brandId);
    }

    @GetMapping("/getAllBrands")
    public MyRes getAllBrands(){
        return brandService.getAllBrands();
    }

}
