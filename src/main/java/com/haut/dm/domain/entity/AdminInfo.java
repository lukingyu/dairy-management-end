package com.haut.dm.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员详细信息表。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminInfo {
    // 主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 管理员真实id
    private Integer adminId;
    private String adminName;
    private Integer age;
    private String phone;
}
