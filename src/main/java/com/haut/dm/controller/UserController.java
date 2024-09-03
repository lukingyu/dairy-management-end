package com.haut.dm.controller;

import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.domain.entity.UserInfo;
import com.haut.dm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserInfos")
    public MyRes getUserInfos(){
        return userInfoService.getUserInfos();
    }

    @PostMapping("/modifyUserInfo")
    public MyRes modifyUserInfo(@RequestBody UserInfo userInfo){
        return userInfoService.modifyUserInfo(userInfo);
    }

    @DeleteMapping("/deleteUserInfo/{userId}")
    public MyRes deleteUserInfo(@PathVariable("userId") Integer userId){
        return userInfoService.deleteUserInfo(userId);
    }
}
