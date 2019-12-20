package com.shoppingmall.servlet;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.shoppingmall.common.*;
import com.shoppingmall.common.response.responseImpl.ExpandDataResponseObject;
import com.shoppingmall.common.response.responseImpl.SimpleResponseObject;
import com.shoppingmall.configure.AliPayConf;
import com.shoppingmall.entity.Order;
import com.shoppingmall.entity.OrderDetail;
import com.shoppingmall.entity.User;
import com.shoppingmall.service.OrderService;
import com.shoppingmall.service.UserService;
import com.shoppingmall.servlet.responseObject.LoginResponse;
import com.shoppingmall.servlet.responseObject.RegisterResponse;
import com.shoppingmall.util.BeanFactory;
import com.shoppingmall.util.CommonUtils;
import com.shoppingmall.util.MD5Util;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import static com.alipay.api.AlipayConstants.CHARSET;
import static com.alipay.api.AlipayConstants.SIGN_TYPE;

@WebServlet(urlPatterns = "/order/*",loadOnStartup = 1)
public class OrderServlet extends HttpServlet {

    @MyAutowired
    private Logger logger;
    @MyAutowired
    private OrderService orderService;

    private Map<String, Entry<RequestType, Method>> uriMapping;

    @Override
    public void init() throws ServletException {
        BeanFactory.getInstance().addServlet(this);
        uriMapping = new HashMap();
        Class clazz = this.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping myRequestMapping = (MyRequestMapping) method.getAnnotation(MyRequestMapping.class);
                String uri = myRequestMapping.value();
                RequestType requestType = myRequestMapping.type();
                uriMapping.put(uri, new Entry<>(requestType, method));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        Entry<RequestType, Method> entry = uriMapping.get(uri);
        if (entry.getKey() != RequestType.POST) {
            try {
                response.sendError(503, "请求类型不匹配");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        request.setAttribute("fromDoPost", true);
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String uri = request.getRequestURI();
        Entry<RequestType, Method> entry = uriMapping.get(uri);
        if(entry == null){
            try {
                response.sendError(404);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Boolean isFromDoPost = (Boolean) request.getAttribute("fromDoPost");
        if ((isFromDoPost == null || !isFromDoPost) && entry.getKey() != RequestType.GET) {
            try {
                response.sendError(503, "请求类型不匹配");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        Method method = entry.getValue();
        try {
            method.invoke(this, request, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @MyRequestMapping(value = "/order/notify", type = RequestType.POST)
    private void notify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        logger.info("支付宝异步通知");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConf.getInstance().getAlipay_public_key(), AliPayConf.getInstance().getCharset(), AliPayConf.getInstance().getSign_type()); // 调用SDK验证签名
        //验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            logger.info("商户订单号="+out_trade_no);
            logger.info("支付宝交易号="+trade_no);
            logger.info("付款金额="+total_amount);
            orderService.updateById(out_trade_no);
        }else{
            return;
        }

    }

    @MyRequestMapping(value = "/order/createOrder", type = RequestType.POST)
    private void createOrder(HttpServletRequest request, HttpServletResponse response) {
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        String requestSumPrice = request.getParameter("sumPrice");
        Double sumPrice = null;
        try {
            sumPrice = Double.parseDouble(requestSumPrice);
        }catch (Exception e){
            try {
                response.sendError(400,"参数错误");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setAccountNumber(accountNumber);
        order.setSumPrice(sumPrice);
        order.setState(OrderState.WAIT_PAY.getMsg());
        String id = orderService.createOrder(order);
        if(id == null){
            try {
                response.getWriter().write(JSON.toJSONString(new SimpleResponseObject(1,"创建订单失败")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<String>(0,"订单创建成功",id)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @MyRequestMapping(value = "/order/pay", type = RequestType.POST)
    private void pay(HttpServletRequest request,HttpServletResponse response){
        String orderId = request.getParameter("orderId");
        String body = orderService.pay(orderId);
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping(value = "/order/createOrderDetail", type = RequestType.POST)
    private void createOrderDetail(HttpServletRequest request, HttpServletResponse response) {
        String requestNumber = request.getParameter("number");
        String orderId = request.getParameter("orderId");
        String requestCommodityId = request.getParameter("commodityId");
        Integer number = null;
        Integer commodityId = null;
        try {
            number = Integer.parseInt(requestNumber);
            commodityId = Integer.parseInt(requestCommodityId);
        }catch (Exception e){
            try {
                response.sendError(400,"参数错误");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(UUID.randomUUID().toString());
        orderDetail.setOrderId(orderId);
        orderDetail.setCommodityId(commodityId);
        orderDetail.setNumber(number);
        String id = orderService.createOrderDetail(orderDetail);
        if(id == null){
            try {
                response.getWriter().write(JSON.toJSONString(new SimpleResponseObject(1,"创建订单失败")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<String>(0,"订单创建成功",id)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
