package com.shoppingmall.service;

import com.shoppingmall.entity.Order;
import com.shoppingmall.entity.OrderDetail;

public interface OrderService {
    /**
     * 创建订单
     * @param order
     * @return null-创建失败 创建成功的订单的ID
     */
    String createOrder(Order order);

    /**
     * 创建订单详情
     * @param orderDetail
     * @return
     */
    String createOrderDetail(OrderDetail orderDetail);

    /**
     * 支付
     * @param orderId
     */
    String pay(String orderId);

    /**
     * 更新订单状态
     * @param valueOf
     */
    void updateById(String valueOf);
}
