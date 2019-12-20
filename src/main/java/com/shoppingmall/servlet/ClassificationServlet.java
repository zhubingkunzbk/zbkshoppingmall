package com.shoppingmall.servlet;

import com.alibaba.fastjson.JSON;
import com.shoppingmall.common.Entry;
import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyRequestMapping;
import com.shoppingmall.common.RequestType;
import com.shoppingmall.common.response.responseImpl.ExpandDataResponseObject;
import com.shoppingmall.entity.Classifications;
import com.shoppingmall.entity.User;
import com.shoppingmall.service.ClassificationService;
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

@WebServlet(urlPatterns = "/classifications/*",loadOnStartup = 1)
public class ClassificationServlet extends HttpServlet {

    @MyAutowired
    private ClassificationService classificationService;

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

    @MyRequestMapping(value = "/classifications/getParentClassification", type = RequestType.GET)
    private void getParentClassification(HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> map = classificationService.getParentClassification();
        try {
            response.getWriter().write(JSON.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @MyRequestMapping(value = "/classifications/getClassificationByParent", type = RequestType.GET)
    private void getClassificationByParent(HttpServletRequest request, HttpServletResponse response) {
        String requestParentId = request.getParameter("parentId");
        if(!CommonUtils.isNotNull(requestParentId)){
            try {
                response.sendError(400,"参数不全");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        int parentId = 0;
        try{
            parentId = Integer.parseInt(requestParentId);
        }catch (NumberFormatException e){
            try {
                response.sendError(400,"参数错误");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        Map<String,String> list = classificationService.getClassificationByParent(parentId);
        try {
            response.getWriter().write(JSON.toJSONString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
