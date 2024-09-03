package com.haut.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.dm.domain.entity.AdminLogin;
import com.haut.dm.domain.entity.MyRes;

public interface AdminLoginService extends IService<AdminLogin> {
    MyRes login(AdminLogin adminLogin);

    MyRes register(AdminLogin adminLogin);

    MyRes getAdminNameById(Integer adminId);

    MyRes logout(Integer adminId);
}
