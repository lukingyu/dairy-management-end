package com.haut.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.dm.domain.DTO.BrandDto;
import com.haut.dm.domain.entity.Brand;
import com.haut.dm.domain.entity.BrandIntroduction;
import com.haut.dm.domain.entity.MyRes;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface BrandService extends IService<Brand> {
    MyRes uploadLogo(MultipartFile multipartFile);

    MyRes delLogo(String logoUrl);

    MyRes addBrand(BrandDto brandDto);

    MyRes getBrandsByPage(Integer pageNum, Integer pageSize);

    MyRes editBrand(BrandIntroduction brandIntroduction);

    MyRes delBrand(Integer brandId);

    MyRes getCategoriesByBrandId(Integer brandId);

    MyRes getAllBrands();

}
