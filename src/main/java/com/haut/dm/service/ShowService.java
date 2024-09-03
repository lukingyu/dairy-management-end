package com.haut.dm.service;

import com.haut.dm.domain.entity.MyRes;

public interface ShowService {
    MyRes getAllIncome();

    MyRes getAllOrderNum();

    MyRes getAllUserNum();

    MyRes getProductNameAndSumList();

    MyRes getProvinceSumList();

    MyRes getIncomeAndTimeList();

    MyRes getProductAvgGradeList();

    MyRes getUserOrderSumList();

    MyRes getGoodUserList();
}
