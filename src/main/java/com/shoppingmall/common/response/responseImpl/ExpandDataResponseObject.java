package com.shoppingmall.common.response.responseImpl;


import com.shoppingmall.common.response.ExpandResponseObject;

public class ExpandDataResponseObject<T> extends SimpleResponseObject implements ExpandResponseObject {

    private T data;

    public ExpandDataResponseObject(int code) {
        super(code);
    }

    public ExpandDataResponseObject(int code, String msg) {
        super(code, msg);
    }
    public ExpandDataResponseObject(int code, String msg,T data) {
        super(code, msg);
        this.data = data;
    }


    @Override
    public T getData() {
        return this.data;
    }
}
