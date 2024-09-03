package com.haut.dm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haut.dm.config.QiniuConfig;
import com.haut.dm.domain.DTO.BrandDto;
import com.haut.dm.domain.entity.Brand;
import com.haut.dm.domain.entity.BrandIntroduction;
import com.haut.dm.domain.entity.Category;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.enums.ResEnum;
import com.haut.dm.mapper.BrandIntroductionMapper;
import com.haut.dm.mapper.BrandMapper;
import com.haut.dm.mapper.CategoryMapper;
import com.haut.dm.service.BrandService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private QiniuConfig qiniuConfig;
    @Autowired
    private BrandIntroductionMapper brandIntroductionMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public MyRes uploadLogo(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("文件是空的");
        }
        // 创建上传token
        Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
        String upToken = auth.uploadToken(qiniuConfig.getBucket());

        // 设置上传配置，Region要与存储空间所属的存储区域保持一致
        Configuration cfg = new Configuration(Region.huabei());

        // 创建上传管理器
        UploadManager uploadManager = new UploadManager(cfg);

        String originalFilename = file.getOriginalFilename();
        // 构造文件目录和文件名
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileKey = qiniuConfig.getDirectory() + UUID.randomUUID() + suffix;

        // 上传文件
        try {
            Response response = uploadManager.put(file.getInputStream(), fileKey, upToken, null, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 返回文件url
        return MyRes.success(ResEnum.UPLOAD_SUC, qiniuConfig.getDomain() + fileKey);
    }

    @Override
    public MyRes delLogo(String logoUrl) {
        System.out.println("哈哈哈哈"+logoUrl);
        String key = logoUrl.substring(qiniuConfig.getDomain().length()+1, logoUrl.length()-1);
        String[] splitArr = logoUrl.split("/");
        //String key = splitArr[3];
        System.out.println("=============================");
        System.out.println(key);
        System.out.println("=============================");
        Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
        Configuration configuration = new Configuration(Region.huabei());
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            bucketManager.delete(qiniuConfig.getBucket(), key);
        } catch (QiniuException e) {
            throw new RuntimeException(e);
        }
        return MyRes.success(ResEnum.DEL_SUC);
    }

    @Override
    public MyRes addBrand(BrandDto brandDto) {
        Brand brand = new Brand(null, brandDto.getBrandName());
        save(brand);

        BrandIntroduction brandIntroduction = new BrandIntroduction(null, brand.getId(), brandDto.getBrandName(), brandDto.getLogoUrl(), brandDto.getIntroduction(), null);
        brandIntroductionMapper.insert(brandIntroduction);

        return MyRes.success(ResEnum.ANYTHING_SUC);
    }

    @Override
    public MyRes getBrandsByPage(Integer pageNum, Integer pageSize) {
        Page<BrandIntroduction> page = new Page<>(pageNum, pageSize);

        brandIntroductionMapper.selectPage(page,null);
        List<BrandIntroduction> brands = page.getRecords();
        //暂时先直接返回page对象
        //records 本页的数据
        //total 所有数据的总数，不只是分页
        //size 一页显示多少，固定的
        //current 当前页码
        //pages 一共多少页
        return MyRes.success(ResEnum.ANYTHING_SUC, page);
    }

    @Override
    public MyRes editBrand(BrandIntroduction brandIntroduction) {
        //先修改商品介绍表
        UpdateWrapper<BrandIntroduction> wrapper = new UpdateWrapper<>();
        wrapper.eq("brand_id", brandIntroduction.getBrandId());
        brandIntroductionMapper.update(brandIntroduction, wrapper);
        //再修改商品表
        Brand brand = new Brand(brandIntroduction.getBrandId(), brandIntroduction.getBrandName());
        updateById(brand);
        return MyRes.success(ResEnum.ANYTHING_SUC);
    }

    @Override
    public MyRes delBrand(Integer brandId) {
        //先删除七牛云上的logo图片
        String logoUrl = brandIntroductionMapper.getLogoUrl(brandId);
        if (StringUtils.hasText(logoUrl)){
            delLogoByLogoUrl(logoUrl);
        }

        QueryWrapper<BrandIntroduction> wrapper = new QueryWrapper<>();
        wrapper.eq("brand_id", brandId);
        brandIntroductionMapper.delete(wrapper);

        removeById(brandId);
        return MyRes.success(ResEnum.ANYTHING_SUC);
    }

    @Override
    public MyRes getCategoriesByBrandId(Integer brandId) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("brand_id", brandId);
        List<Category> categories = categoryMapper.selectList(wrapper);
        return MyRes.success(ResEnum.QUERY_SUCCESS, categories);
    }

    @Override
    public MyRes getAllBrands() {
        List<Brand> brands = list();
        return MyRes.success(ResEnum.QUERY_SUCCESS, brands);
    }

    //前提是你logoUrl字符串前后没有双引号
    public boolean delLogoByLogoUrl(String logoUrl){
        String key = logoUrl.substring(qiniuConfig.getDomain().length(), logoUrl.length());
        String[] splitArr = logoUrl.split("/");
        //String key = splitArr[3];
        System.out.println("=============================");
        System.out.println(key);
        System.out.println("=============================");
        Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
        Configuration configuration = new Configuration(Region.huabei());
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            bucketManager.delete(qiniuConfig.getBucket(), key);
        } catch (QiniuException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
