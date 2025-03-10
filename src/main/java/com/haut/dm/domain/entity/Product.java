package com.haut.dm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String productName;
    private Integer categoryId;
    private Integer brandId;
    private Double price;
    private String imageUrl;
}
