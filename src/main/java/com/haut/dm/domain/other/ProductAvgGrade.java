package com.haut.dm.domain.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAvgGrade {
    private String productName;
    private Double avgGrade;
}
