package com.shoppingmall.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyService;
import com.shoppingmall.common.OrderState;
import com.shoppingmall.configure.AliPayConf;
import com.shoppingmall.dao.OrderDao;
import com.shoppingmall.entity.Order;
import com.shoppingmall.entity.OrderDetail;
import com.shoppingmall.service.OrderService;

@MyService
public class OrderServiceImpl implements OrderService {

    @MyAutowired
    private OrderDao orderDao;

    @MyAutowired
    private AliPayConf aliPayConf;

    @Override
    public String createOrder(Order order) {
        try {
            orderDao.insertOrder(order);
            return order.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String createOrderDetail(OrderDetail orderDetail) {
        try {
            orderDao.insertOrderDetail(orderDetail);
            return orderDetail.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String pay(String orderId) {
        Order order = orderDao.selectOrderById(orderId);
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConf.getGatewayUrl(),aliPayConf.getApp_id(),
                aliPayConf.getMerchant_private_key(),"json",aliPayConf.getCharset(),aliPayConf.getAlipay_public_key(),
                aliPayConf.getSign_type());
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setReturnUrl(aliPayConf.getReturn_url());
        alipayTradePagePayRequest.setNotifyUrl(aliPayConf.getNotify_url());
        String out_trade_no = order.getId();
        //付款金额，必填
        String total_amount = order.getSumPrice().toString();
        //订单名称，必填
        String subject = "www.zhubingkun.com";
        //商品描述，可空
        String body = "";
        alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
                + total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        AlipayTradePagePayResponse alipayResponse = null;
        try {
            alipayResponse=alipayClient.pageExecute(alipayTradePagePayRequest);
            System.out.println(alipayResponse.getBody());
            System.out.println(alipayResponse.getMsg());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return alipayResponse.getBody();
    }

    @Override
    public void updateById(String id) {
        Order order = orderDao.selectOrderById(id);
        order.setState(OrderState.PAY_SUCCESS.getMsg());
        orderDao.updateOrder(order);
    }
}
