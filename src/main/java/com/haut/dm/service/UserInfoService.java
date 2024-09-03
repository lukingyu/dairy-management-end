package com.haut.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.domain.entity.UserInfo;

public interface UserInfoService extends IService<UserInfo> {
    MyRes getUserInfos();

    MyRes modifyUserInfo(UserInfo userInfo);

    MyRes deleteUserInfo(Integer userId);
}
