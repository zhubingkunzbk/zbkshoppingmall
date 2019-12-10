package com.shoppingmall.servlet;

import com.alibaba.fastjson.JSON;
import com.shoppingmall.common.Entry;
import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyRequestMapping;
import com.shoppingmall.common.RequestType;
import com.shoppingmall.common.response.responseImpl.ExpandDataResponseObject;
import com.shoppingmall.entity.Commodity;
import com.shoppingmall.entity.User;
import com.shoppingmall.service.CommodityService;
import com.shoppingmall.service.UserService;
import com.shoppingmall.servlet.responseObject.LoginResponse;
import com.shoppingmall.servlet.responseObject.RegisterResponse;
import com.shoppingmall.util.BeanFactory;
import com.shoppingmall.util.CommonUtils;
import com.shoppingmall.util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/commodities/*",loadOnStartup = 1)
public class CommodityServlet extends HttpServlet {

    @MyAutowired
    private CommodityService commodityService;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        Entry<RequestType, Method> entry = uriMapping.get(uri);
        if (entry.getKey() != RequestType.POST) {
            response.sendError(503, "请求类型不匹配");
            return;
        }
        request.setAttribute("fromDoPost", true);
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        Entry<RequestType, Method> entry = uriMapping.get(uri);
        Boolean isFromDoPost = (Boolean) request.getAttribute("fromDoPost");
        if ((isFromDoPost == null || !isFromDoPost) && entry.getKey() != RequestType.GET) {
            response.sendError(503, "请求类型不匹配");
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

    @MyRequestMapping(value = "/commodities/getSalesVolumeTopCommodities", type = RequestType.GET)
    private void getSalesVolumeTopCommodities(HttpServletRequest request, HttpServletResponse response) {
        String requestTopNumber = request.getParameter("topNumber");
        if(requestTopNumber == null || requestTopNumber.length() <= 0){
            requestTopNumber = "10";
        }
        int topNumber = 0;
        try {
            topNumber = Integer.parseInt(requestTopNumber);
        }catch (NumberFormatException e){
            try {
                response.sendError(400,"参数错误" + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        List<Commodity> list = commodityService.getSalesVolumeTopCommodities(topNumber);
        try {
            response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<List<Commodity>>(0,"获取成功",list)));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
