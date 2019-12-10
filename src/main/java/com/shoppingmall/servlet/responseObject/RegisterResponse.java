package com.shoppingmall.servlet.responseObject;

import com.shoppingmall.common.response.ResponseObject;

public enum RegisterResponse implements ResponseObject {
    REGISTER_SUCCESS(0,"注册成功"),
    ACCOUNT_NUMBER_EXIST(1,"账号已存在"),
    ANOTHER_ERROR(2,"未知错误");
    private int code;
    private String msg;
    RegisterResponse(int code, String msg) {
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
