package com.haut.dm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haut.dm.domain.VO.LoginVo;
import com.haut.dm.domain.entity.AdminInfo;
import com.haut.dm.domain.entity.AdminLogin;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.enums.ResEnum;
import com.haut.dm.mapper.AdminInfoMapper;
import com.haut.dm.mapper.AdminLoginMapper;
import com.haut.dm.service.AdminLoginService;
import com.haut.dm.utils.JwtUtil;
import com.haut.dm.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AdminLoginServiceImpl extends ServiceImpl<AdminLoginMapper, AdminLogin> implements AdminLoginService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    public MyRes login(AdminLogin adminLogin) {
        LambdaQueryWrapper<AdminLogin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminLogin::getAdminName, adminLogin.getAdminName())
                .eq(AdminLogin::getPassword, adminLogin.getPassword());
        AdminLogin one = getOne(wrapper);
        if (!ObjectUtils.isEmpty(one)){
            //登录成功
            String jwtStr = JwtUtil.generateJwtStr(adminLogin.getAdminName());
            //存取一个键值对到redis中，证明该管理员已经在登录状态。消失代表该用户下线了。
            redisUtil.set("adminName",adminLogin.getAdminName(),60*60*24*7);
            LoginVo loginVo = new LoginVo(one.getId(),jwtStr);
            return MyRes.success(ResEnum.LOGIN_SUCCESS,loginVo);
        }else{
            //登录失败
            redisUtil.del("adminName");
            return MyRes.error(ResEnum.LOGIN_ERROR,null);
        }
    }

    @Override
    public MyRes register(AdminLogin adminLogin) {
        try {
            save(adminLogin);
            Integer adminId = getOne(new QueryWrapper<AdminLogin>().eq("admin_name",adminLogin.getAdminName())).getId();
            AdminInfo adminInfo = new AdminInfo();
            adminInfo.setAdminId(adminId);
            adminInfo.setAdminName(adminLogin.getAdminName());
            adminInfoMapper.insert(adminInfo);
        } catch (Exception e) {
            return MyRes.error(ResEnum.REGISTER_ERROR);
        }
        return MyRes.success(ResEnum.REGISTER_SUCCESS);
    }

    @Override
    public MyRes getAdminNameById(Integer adminId) {
        QueryWrapper<AdminLogin> wrapper = new QueryWrapper<>();
        wrapper.eq("id",adminId);
        AdminLogin one = getOne(wrapper);
        return MyRes.success(ResEnum.QUERY_SUCCESS, one.getAdminName());
    }

    @Override
    public MyRes logout(Integer adminId) {
        AdminLogin adminLogin = getById(adminId);
        if (redisUtil.get("adminName").toString().equals(adminLogin.getAdminName())){
            //才进行退出操作
            redisUtil.del("adminName");
            return MyRes.success(ResEnum.LOGOUT_SUCCESS);
        }
        return MyRes.error(ResEnum.LOGOUT_ERROR);
    }
}
