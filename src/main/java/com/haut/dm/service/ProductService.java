package com.haut.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.dm.domain.DTO.ProductDto;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.domain.entity.Product;
import com.haut.dm.domain.entity.ProductDetail;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService extends IService<Product> {
    MyRes uploadProImage(MultipartFile multipartFile);

    MyRes delProImage(String imageUrl);

    MyRes addProduct(ProductDto productDto);

    MyRes getProductsByPage(Integer pageNum, Integer pageSize, String keyword);

    MyRes delProduct(Integer productId);

    MyRes editProduct(ProductDetail productDetail);
}
