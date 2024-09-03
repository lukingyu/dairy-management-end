package com.haut.dm.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer brandId;
    private Integer categoryId;
    private String productName;
    private Double price;
    private String introduction;
    private String ingredient;
    private String specification;
    private String shelfLife;
    private String imageUrl;
//    brandId:computed(()=>brandId.value),
//    categoryId:computed(()=>categoryId.value),
//    productName:'',
//    price: NaN,
//    introduction:'',
//    ingredient:'',
//    specification:'',
//    shelfLife:'',
//    imageUrl:''
}
