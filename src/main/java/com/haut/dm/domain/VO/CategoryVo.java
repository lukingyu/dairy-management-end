package com.haut.dm.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {
    private Integer id;
    private String categoryName;
    private Integer brandId;
    private String brandName;
}
