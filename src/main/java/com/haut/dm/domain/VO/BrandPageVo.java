package com.haut.dm.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandPageVo {

    private long total; //总记录条数，不只是分页的数据，是所有的数据
    private List<BrandVo> brands; //数据

}
