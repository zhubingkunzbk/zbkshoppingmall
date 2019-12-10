package com.shoppingmall.common.response.responseImpl;

import com.shoppingmall.common.response.ResponseObject;

public class SimpleResponseObject implements ResponseObject {

    private int code;
    private String msg;
    public SimpleResponseObject(int code) {
        this.code = code;
    }

    public SimpleResponseObject(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
