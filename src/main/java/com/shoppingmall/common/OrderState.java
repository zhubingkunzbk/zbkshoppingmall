package com.shoppingmall.common;

public enum OrderState {
    WAIT_PAY("待支付"),CANCEL("订单取消"),PAY_SUCCESS("支付成功");
    private String msg;

    OrderState(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
