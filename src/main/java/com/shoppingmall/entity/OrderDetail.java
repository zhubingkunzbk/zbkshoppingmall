package com.shoppingmall.entity;

public class OrderDetail {
    private String id;
    private String orderId;
    private Integer commodityId;
    private Integer number;

    public OrderDetail() {
    }

    public OrderDetail(String id, String orderId, Integer commodityId, Integer number) {
        this.id = id;
        this.orderId = orderId;
        this.commodityId = commodityId;
        this.number = number;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public Integer getNumber() {
        return number;
    }
}
