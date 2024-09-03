package com.haut.dm.service.impl;

import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.domain.other.*;
import com.haut.dm.enums.ResEnum;
import com.haut.dm.mapper.ShowMapper;
import com.haut.dm.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowMapper showMapper;

    @Override
    public MyRes getAllIncome() {
        Double allIncome = showMapper.getAllIncome();
        if (null == allIncome){
            return MyRes.success(ResEnum.QUERY_SUCCESS, 0);
        }
        return MyRes.success(ResEnum.QUERY_SUCCESS, allIncome);
    }

    @Override
    public MyRes getAllOrderNum() {
        Integer allOrderNum = showMapper.getAllOrderNum();
        if (null == allOrderNum){
            return MyRes.success(ResEnum.QUERY_SUCCESS, 0);
        }
        return MyRes.success(ResEnum.QUERY_SUCCESS, allOrderNum);
    }

    @Override
    public MyRes getAllUserNum() {
        Integer allUserNum = showMapper.getAllUserNum();
        if (null == allUserNum){
            return MyRes.success(ResEnum.QUERY_SUCCESS, 0);
        }
        return MyRes.success(ResEnum.QUERY_SUCCESS, allUserNum);
    }

    @Override
    public MyRes getProductNameAndSumList() {
        List<ProductNameAndSum> productNameAndSumList = showMapper.getProductNameAndSumList();
        return MyRes.success(ResEnum.QUERY_SUCCESS, productNameAndSumList);
    }

    @Override
    public MyRes getProvinceSumList() {
        List<ProvinceSum> provinceSumList = showMapper.getProvinceSumList();
        return MyRes.success(ResEnum.QUERY_SUCCESS, provinceSumList);
    }

    @Override
    public MyRes getIncomeAndTimeList() {
        List<IncomeAndTime> incomeAndTimeList = showMapper.getIncomeAndTimeList();
        return MyRes.success(ResEnum.QUERY_SUCCESS, incomeAndTimeList);
    }

    @Override
    public MyRes getProductAvgGradeList() {
        List<ProductAvgGrade> productAvgGradeList = showMapper.getProductAvgGradeList();
        return MyRes.success(ResEnum.QUERY_SUCCESS, productAvgGradeList);
    }

    @Override
    public MyRes getUserOrderSumList() {
        List<UserOrderSum> userOrderSumList = showMapper.getUserOrderSumList();
        return MyRes.success(ResEnum.QUERY_SUCCESS, userOrderSumList);
    }

    @Override
    public MyRes getGoodUserList() {
        List<GoodUser> goodUserList = showMapper.getGoodUserList();
        return MyRes.success(ResEnum.QUERY_SUCCESS, goodUserList);
    }
}
