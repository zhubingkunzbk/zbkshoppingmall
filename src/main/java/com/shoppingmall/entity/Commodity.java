package com.shoppingmall.entity;

public class Commodity {
    private Integer id;
    private String content;
    private Double price;
    private Integer stock;
    private Integer salesVolume;
    private String describe;
    private String title;
    private Integer classificationId;
    private String userAccountNumber;
    private String showImgSrc;

    public Commodity() {
    }

    public Commodity(Integer id, String content, Double price, Integer stock, Integer salesVolume, String describe, String title, Integer classificationId, String userAccountNumber,String showImgSrc) {
        this.id = id;
        this.content = content;
        this.price = price;
        this.stock = stock;
        this.salesVolume = salesVolume;
        this.describe = describe;
        this.title = title;
        this.classificationId = classificationId;
        this.userAccountNumber = userAccountNumber;
        this.showImgSrc = showImgSrc;
    }

    public void setShowImgSrc(String showImgSrc) {
        this.showImgSrc = showImgSrc;
    }

    public String getShowImgSrc() {
        return showImgSrc;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }

    public void setUserAccountNumber(String userAccountNumber) {
        this.userAccountNumber = userAccountNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public String getDescribe() {
        return describe;
    }

    public String getTitle() {
        return title;
    }

    public Integer getClassificationId() {
        return classificationId;
    }

    public String getUserAccountNumber() {
        return userAccountNumber;
    }
}
