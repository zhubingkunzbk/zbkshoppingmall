package com.shoppingmall.servlet.responseObject;

import com.shoppingmall.common.response.ResponseObject;

public enum LoginResponse implements ResponseObject {
    LOGIN_SUCCESS(0,"登陆成功"),
    ACCOUNT_NUMBER_OR_PASSWORD_ERROR(1,"账号或密码错"),
    NO_LOGIN(2,"未登录");
    private int code;
    private String msg;
    LoginResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
