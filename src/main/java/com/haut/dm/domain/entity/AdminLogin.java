package com.haut.dm.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 管理员登录表，管理员注册表，
 * id既是主键，也是管理员真实id
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLogin {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String adminName;
    private String password;
}
