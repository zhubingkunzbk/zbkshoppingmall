package com.shoppingmall.servlet;

import com.alibaba.fastjson.JSON;
import com.shoppingmall.common.Entry;
import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyRequestMapping;
import com.shoppingmall.common.RequestType;
import com.shoppingmall.common.response.responseImpl.ExpandDataResponseObject;
import com.shoppingmall.common.response.responseImpl.SimpleResponseObject;
import com.shoppingmall.entity.Commodity;
import com.shoppingmall.entity.User;
import com.shoppingmall.service.ShoppingCartService;
import com.shoppingmall.service.UserService;
import com.shoppingmall.servlet.responseObject.LoginResponse;
import com.shoppingmall.servlet.responseObject.RegisterResponse;
import com.shoppingmall.util.BeanFactory;
import com.shoppingmall.util.CommonUtils;
import com.shoppingmall.util.MD5Util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(urlPatterns = "/shoppingCart/*",loadOnStartup = 1)
public class ShoppingCartServlet extends HttpServlet {

    @MyAutowired
    private ShoppingCartService shoppingCartService;

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



    @MyRequestMapping(value = "/shoppingCart/getSoppingCart", type = RequestType.GET)
    private void getSoppingCart(HttpServletRequest request, HttpServletResponse response) {
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        Map<Commodity,Integer> map = shoppingCartService.getShoppingCart(accountNumber);
//        try {
//            response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<Map<Commodity,Integer>>(0,"成功",map)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        request.setAttribute("shoppingCart",map);
        try {
            request.getRequestDispatcher("/shoppingCart.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @MyRequestMapping(value = "/shoppingCart/insertIntoShoppingCart", type = RequestType.POST)
    private void insertIntoShoppingCart(HttpServletRequest request, HttpServletResponse response) {
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        String requestCommodityId = request.getParameter("commodityId");
        String requestNumber = request.getParameter("number");
        int commodityId = 0;
        int number = 0;
        try {
            commodityId = Integer.parseInt(requestCommodityId);
            number = Integer.parseInt(requestNumber);
        }catch (Exception e){
            try {
                response.sendError(400,"参数错误");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        int res = shoppingCartService.insertIntoShoppingCart(accountNumber,commodityId,number);
        if(res == 0){
            try {
                response.getWriter().write("SUCCESS");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("FAILED");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @MyRequestMapping(value = "/shoppingCart/incShoppingCartNumber", type = RequestType.POST)
    private void incShoppingCartNumber(HttpServletRequest request, HttpServletResponse response) {
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        String requestCommodityId = request.getParameter("commodityId");
        String requestNumber = request.getParameter("number");
        int commodityId = 0;
        int number = 0;
        try {
            commodityId = Integer.parseInt(requestCommodityId);
            number = Integer.parseInt(requestNumber);
        }catch (Exception e){
            try {
                response.sendError(400,"参数错误");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        shoppingCartService.incShoppingCartNumber(accountNumber,commodityId,number);
    }
}
