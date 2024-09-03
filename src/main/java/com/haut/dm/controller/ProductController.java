package com.haut.dm.controller;

import com.haut.dm.domain.DTO.ProductDto;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.domain.entity.ProductDetail;
import com.haut.dm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/uploadProImage")
    public MyRes uploadProImage(@RequestParam("file") MultipartFile multipartFile){
        return productService.uploadProImage(multipartFile);
    }

    @PostMapping("/delProImage")
    public MyRes delProImage(@RequestBody String imageUrl){
//        System.out.println("=============================");
//        System.out.println(imageUrl);
//        System.out.println("=============================");
        return productService.delProImage(imageUrl);
    }

    @PostMapping("/addProduct")
    public MyRes addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @PostMapping("/getProductsByPage/{pageNum}/{pageSize}")
    public MyRes getProductsByPage(@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize,
                                   @RequestParam(value = "keyword", required = false) @DefaultValue("") String keyword){
        return productService.getProductsByPage(pageNum, pageSize, keyword);
    }

    @PostMapping("/editProduct")
    public MyRes editProduct(@RequestBody ProductDetail productDetail){
        return productService.editProduct(productDetail);
    }

    @PostMapping("/delProduct/{productId}")
    public MyRes delProduct(@PathVariable("productId") Integer productId){
        return productService.delProduct(productId);
    }
}
