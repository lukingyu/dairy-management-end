package com.haut.dm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.domain.entity.UserInfo;
import com.haut.dm.enums.ResEnum;
import com.haut.dm.mapper.UserInfoMapper;
import com.haut.dm.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public MyRes getUserInfos() {
        List<UserInfo> userInfos = list();
        return MyRes.success(ResEnum.QUERY_SUCCESS, userInfos);
    }

    @Override
    public MyRes modifyUserInfo(UserInfo userInfo) {
        try {
            updateById(userInfo);
        } catch (Exception e) {
            return MyRes.error(ResEnum.MODIFY_ERROR);
        }
        return MyRes.success(ResEnum.MODIFY_SUCCESS);
    }

    @Override
    public MyRes deleteUserInfo(Integer userId) {
        try {
            removeById(userId);
        } catch (Exception e) {
            return MyRes.error(ResEnum.DELETE_ERROR);
        }
        return MyRes.success(ResEnum.DELETE_SUCCESS);
    }
}
