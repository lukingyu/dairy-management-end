package com.haut.dm.mapper;

import com.haut.dm.domain.other.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShowMapper {

    @Select("select income from all_income")
    Double getAllIncome();

    @Select("select num from all_order_num")
    Integer getAllOrderNum();

    @Select("select count(*) as num from user_info")
    Integer getAllUserNum();

    @Select("select * from product_name_and_sum")
    List<ProductNameAndSum> getProductNameAndSumList();

    @Select("select * from province_sum")
    List<ProvinceSum> getProvinceSumList();

    @Select("select * from income_and_time")
    List<IncomeAndTime> getIncomeAndTimeList();

    @Select("SELECT x2.product_name,x1.avg_grade FROM product_avg_grade x1 LEFT JOIN product x2 on x1.product_id=x2.id")
    List<ProductAvgGrade> getProductAvgGradeList();

    @Select("SELECT x2.username,x1.order_sum FROM user_order_sum x1 LEFT JOIN user_login x2 on x1.user_id=x2.id")
    List<UserOrderSum> getUserOrderSumList();

    @Select("select * from good_user")
    List<GoodUser> getGoodUserList();
}
