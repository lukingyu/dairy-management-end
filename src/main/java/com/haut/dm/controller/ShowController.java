package com.haut.dm.controller;

import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理系统，历史数据可视化，专用Controller
 */
@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping("/getAllIncome")
    public MyRes getAllIncome(){
        return showService.getAllIncome();
    }

    @GetMapping("/getAllOrderNum")
    public MyRes getAllOrderNum(){
        return showService.getAllOrderNum();
    }

    @GetMapping("/getAllUserNum")
    public MyRes getAllUserNum(){
        return showService.getAllUserNum();
    }

    @GetMapping("/getProductNameAndSumList")
    public MyRes getProductNameAndSumList(){
        return showService.getProductNameAndSumList();
    }

    @GetMapping("/getProvinceSumList")
    public MyRes getProvinceSumList(){
        return showService.getProvinceSumList();
    }

    @GetMapping("/getIncomeAndTimeList")
    public MyRes getIncomeAndTimeList(){
        return showService.getIncomeAndTimeList();
    }

    @GetMapping("/getProductAvgGradeList")
    public MyRes getProductAvgGradeList(){
        return showService.getProductAvgGradeList();
    }

    @GetMapping("/getUserOrderSumList")
    public MyRes getUserOrderSumList(){
        return showService.getUserOrderSumList();
    }

    @GetMapping("/getGoodUserList")
    public MyRes getGoodUserList(){
        return showService.getGoodUserList();
    }
}
