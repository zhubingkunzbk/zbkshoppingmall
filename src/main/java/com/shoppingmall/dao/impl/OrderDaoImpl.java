package com.shoppingmall.dao.impl;

import com.shoppingmall.common.MyRepository;
import com.shoppingmall.dao.OrderDao;
import com.shoppingmall.entity.Order;
import com.shoppingmall.entity.OrderDetail;
import com.shoppingmall.util.SqlUtil;

@MyRepository
public class OrderDaoImpl implements OrderDao {
    @Override
    public void insertOrder(Order order) throws Exception{
        String sql = "INSERT INTO orders VALUES (?,?,?,?)";
        int res = SqlUtil.update(sql,order.getId(),order.getSumPrice(),order.getState(),order.getAccountNumber());

    }

    @Override
    public void insertOrderDetail(OrderDetail orderDetail) throws Exception {
        String sql = "INSERT INTO order_detail VALUES (?,?,?,?)";
        int res = SqlUtil.update(sql,orderDetail.getId(),orderDetail.getOrderId(),orderDetail.getCommodityId(),orderDetail.getNumber());
    }

    @Override
    public Order selectOrderById(String orderId) {
        String sql = "SELECT * FROM orders WHERE id=?";
        Order order = null;
        try {
            order = (Order) SqlUtil.query(sql,Order.class,orderId).get(0);
        } catch (Exception e) {
            return null;
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "UPDATE orders SET state=? WHERE id=?";
        try {
            SqlUtil.update(sql,order.getState(),order.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
