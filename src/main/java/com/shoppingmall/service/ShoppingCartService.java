package com.shoppingmall.service;

import com.shoppingmall.entity.Commodity;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {

    /**
     * 根据账号查询所有购物车信息
     * @param accountNumber 指定的帐号
     * @return 查询出的购物车中的商品信息
     */
    Map<Commodity,Integer> getShoppingCart(String accountNumber);

    /**
     * 为指定账户购物车添加商品
     * @param accountNumber 用户账号
     * @param commodityId 商品ID
     * @param number 数量
     * @return 0-添加成功 1-添加失败
     */
    int insertIntoShoppingCart(String accountNumber,Integer commodityId,Integer number);

    /**
     * 增/减用户购物车中商品数量
     * @param accountNumber 账号
     * @param commodityId 商品ID
     * @param number 增/减值
     */
    void incShoppingCartNumber(String accountNumber,Integer commodityId,Integer number);
}
