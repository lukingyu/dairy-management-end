package com.haut.dm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.haut.dm.config.QiniuConfig;
import com.haut.dm.domain.DTO.ProductDto;
import com.haut.dm.domain.VO.ProductVo;
import com.haut.dm.domain.entity.*;
import com.haut.dm.enums.ResEnum;
import com.haut.dm.mapper.ProductDetailMapper;
import com.haut.dm.mapper.ProductEvaluationMapper;
import com.haut.dm.mapper.ProductMapper;
import com.haut.dm.service.BrandService;
import com.haut.dm.service.ProductService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private QiniuConfig qiniuConfig;
    @Autowired
    private ProductDetailMapper productDetailMapper;
    @Autowired
    private ProductEvaluationMapper productEvaluationMapper;
    @Autowired
    private BrandServiceImpl brandService;

    @Override
    public MyRes uploadProImage(MultipartFile file) {
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
    public MyRes delProImage(String imageUrl) {
        //System.out.println(logoUrl);
        String key = imageUrl.substring(qiniuConfig.getDomain().length()+1, imageUrl.length()-1);
        String[] splitArr = imageUrl.split("/");
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
    @Transactional
    public MyRes addProduct(ProductDto productDto) {
        //先增加product表
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setCategoryId(productDto.getCategoryId());
        product.setBrandId(productDto.getBrandId());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        save(product);

        //再增加product_detail表
        ProductDetail productDetail = new ProductDetail(null,product.getId(), productDto.getProductName(),
                productDto.getBrandId(), productDto.getCategoryId(), productDto.getPrice(), productDto.getImageUrl(),
                productDto.getIntroduction(), productDto.getIngredient(), productDto.getSpecification(),
                productDto.getShelfLife(),
                null);
        productDetailMapper.insert(productDetail);
        return MyRes.success(ResEnum.ANYTHING_SUC);
    }

    @Override
    public MyRes getProductsByPage(Integer pageNum, Integer pageSize, String keyword) {
        Page<ProductVo> page = productDetailMapper.selectJoinPage(new Page<>(pageNum, pageSize), ProductVo.class,
                new MPJLambdaWrapper<ProductDetail>().selectAll(ProductDetail.class).select(Brand::getBrandName).select(Category::getCategoryName)
                        .leftJoin(Brand.class, Brand::getId, ProductDetail::getBrandId)
                        .leftJoin(Category.class, Category::getId, ProductDetail::getCategoryId)
                        .like(ProductDetail::getProductName, keyword));

        return MyRes.success(ResEnum.QUERY_SUCCESS, page);
    }

    @Override
    @Transactional
    public MyRes delProduct(Integer productId) {
        //和product有关系的有三张表，都得删除。记得删除云服器上的图片
        brandService.delLogoByLogoUrl(getById(productId).getImageUrl());

        removeById(productId); //一张表

        QueryWrapper<ProductDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);
        productDetailMapper.delete(wrapper); //一张表

        QueryWrapper<ProductEvaluation> wrapperEva = new QueryWrapper<>();
        wrapperEva.eq("product_id", productId);  //特别注意，wrapper一定要传对了。之前没有传对，导致删除了整张表。
        productEvaluationMapper.delete(wrapperEva); //一张表

        return MyRes.success(ResEnum.ANYTHING_SUC);
    }

    @Override
    @Transactional
    public MyRes editProduct(ProductDetail productDetail) {
        //修改两张表

        Product product = new Product(productDetail.getProductId(), productDetail.getProductName(),
                null, null, productDetail.getPrice(), productDetail.getImageUrl());
        updateById(product); //一张表

        productDetail.setId(null);
        productDetail.setBrandId(null);
        productDetail.setCategoryId(null);
        UpdateWrapper<ProductDetail> wrapper = new UpdateWrapper<>();
        wrapper.eq("product_id", productDetail.getProductId());
        productDetailMapper.update(productDetail, wrapper); //一张表

        return MyRes.success(ResEnum.ANYTHING_SUC);
    }
}
