package com.haut.dm.controller;

import com.haut.dm.domain.entity.AdminLogin;
import com.haut.dm.domain.entity.MyRes;
import com.haut.dm.enums.ResEnum;
import com.haut.dm.service.AdminLoginService;
import com.haut.dm.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminLoginService adminLoginService;


    @PostMapping("/login")
    public MyRes login(@RequestBody AdminLogin adminLogin){
        return adminLoginService.login(adminLogin);
    }

    @PostMapping("/checkToken")
    public MyRes checkToken(@RequestBody String tokenStr){
        boolean flag = JwtUtil.checkToken(tokenStr);
        return MyRes.error(ResEnum.TOKEN_CHECK_SUCCESS, flag);
    }

    @PostMapping("/logout/{adminId}")
    public MyRes logout(@PathVariable("adminId") Integer adminId){
        return adminLoginService.logout(adminId);
    }

    @PostMapping("/register")
    public MyRes register(@RequestBody AdminLogin adminLogin){
        return adminLoginService.register(adminLogin);
    }

    @GetMapping("/getAdminNameById/{adminId}")
    public MyRes getAdminNameById(@PathVariable("adminId") Integer adminId){
        return adminLoginService.getAdminNameById(adminId);
    }
}
