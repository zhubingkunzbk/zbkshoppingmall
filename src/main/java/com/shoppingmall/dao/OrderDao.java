package com.shoppingmall.dao;

import com.shoppingmall.entity.Order;
import com.shoppingmall.entity.OrderDetail;

public interface OrderDao {
    /**
     * 插入订单
     * @param order
     */
    void insertOrder(Order order) throws Exception;

    /**
     * 插入订单详情
     * @param orderDetail
     */
    void insertOrderDetail(OrderDetail orderDetail) throws Exception;

    /**
     * 根据订单编号查找订单
     * @param orderId
     * @return
     */
    Order selectOrderById(String orderId);

    void updateOrder(Order order);
}
