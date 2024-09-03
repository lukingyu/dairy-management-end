package com.haut.dm.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

    private String brandName;
    private String logoUrl;
    private String introduction;
}
