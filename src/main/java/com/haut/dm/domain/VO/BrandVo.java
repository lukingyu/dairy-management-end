package com.haut.dm.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandVo {
    private Integer brandId;
    private String brandName;
    private String logoUrl;
    private String introduction;
}
