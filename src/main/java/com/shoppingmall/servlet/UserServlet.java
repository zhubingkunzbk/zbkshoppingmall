package com.shoppingmall.servlet;

import com.alibaba.fastjson.JSON;
import com.shoppingmall.common.Entry;
import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyRequestMapping;
import com.shoppingmall.common.RequestType;
import com.shoppingmall.entity.User;
import com.shoppingmall.service.UserService;
import com.shoppingmall.servlet.responseObject.LoginResponse;
import com.shoppingmall.servlet.responseObject.RegisterResponse;
import com.shoppingmall.util.BeanFactory;
import com.shoppingmall.util.CommonUtils;
import com.shoppingmall.util.MD5Util;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/user/*",loadOnStartup = 1)
public class UserServlet extends HttpServlet {

    @MyAutowired
    private UserService userService;

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

    @MyRequestMapping(value = "/user/register", type = RequestType.POST)
    private void register(HttpServletRequest request, HttpServletResponse response) {
        String accountNumber = request.getParameter("accountNumber");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String eMail = request.getParameter("eMail");
        String phoneNumber = request.getParameter("phoneNumber");
        if (!CommonUtils.isNotNull(accountNumber, userName, password)) {
            try {
                response.sendError(403, "参数不全");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        password = MD5Util.encode(password);
        User user = new User();
        user.setAccountNumber(accountNumber);
        user.setUserName(userName);
        user.setPassword(password);
        user.setGender(gender);
        user.seteMail(eMail);
        user.setPhoneNumber(phoneNumber);
        user.setIsBusiness(false);
        int result = userService.registerUser(user);
        try {
            if (result == 0) {
                response.getWriter().write(JSON.toJSONString(RegisterResponse.REGISTER_SUCCESS));
            } else if (result == 1) {
                response.getWriter().write(JSON.toJSONString(RegisterResponse.ACCOUNT_NUMBER_EXIST));
            } else {
                response.getWriter().write(JSON.toJSONString(RegisterResponse.ANOTHER_ERROR));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @MyRequestMapping(value = "/user/login", type = RequestType.POST)
    private void login(HttpServletRequest request, HttpServletResponse response) {
        String accountNumber = request.getParameter("accountNumber");
        String password = request.getParameter("password");
        if (!CommonUtils.isNotNull(accountNumber, password)) {
            try {
                response.sendError(403, "参数不全");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        int result = userService.login(accountNumber, password);
        try {
            if (result == 0) {
                request.getSession().setAttribute("accountNumber",accountNumber);
                response.getWriter().write(JSON.toJSONString(LoginResponse.LOGIN_SUCCESS));
            } else {
                response.getWriter().write(JSON.toJSONString(LoginResponse.ACCOUNT_NUMBER_OR_PASSWORD_ERROR));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping(value = "/user/aaa", type = RequestType.POST)
    private void aaa(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("register");
    }
}
