package com.shoppingmall.entity;

public class Order {
    private String id;
    private Double sumPrice;
    private String state;
    private String accountNumber;

    public Order() {
    }

    public Order(String id, Double sumPrice, String state, String accountNumber) {
        this.id = id;
        this.sumPrice = sumPrice;
        this.state = state;
        this.accountNumber = accountNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getId() {
        return id;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public String getState() {
        return state;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
