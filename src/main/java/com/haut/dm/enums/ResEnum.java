package com.haut.dm.enums;

public enum ResEnum {
    LOGIN_SUCCESS(200,"登录成功"),
    LOGIN_ERROR(401,"登录失败"),
    TOKEN_CHECK_SUCCESS(200,"token验证成功"),
    REGISTER_SUCCESS(200,"用户注册成功"),
    REGISTER_ERROR(501,"注册失败"),
    QUERY_SUCCESS(200,"查询成功"),
    MODIFY_SUCCESS(200, "修改成功"),
    MODIFY_ERROR(501, "修改失败"),
    DELETE_SUCCESS(200, "删除成功"),
    DELETE_ERROR(501, "删除失败"),
    UPLOAD_SUC(200, "上传成功"),
    DEL_SUC(200, "删除成功"),
    ANYTHING_SUC(200, "成功了"),
    LOGOUT_SUCCESS(200, "退出成功"),
    LOGOUT_ERROR(501,"退出失败"),
    ;


    Integer code;
    String msg;

    ResEnum() {
    }

    ResEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
