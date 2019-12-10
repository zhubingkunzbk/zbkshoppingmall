package com.shoppingmall.servlet;

import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.entity.User;
import com.shoppingmall.service.UserService;
import com.shoppingmall.util.BeanFactory;
import com.shoppingmall.util.SqlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

@WebServlet(urlPatterns="/registerServlet")
public class RegisterServlet extends HttpServlet{

    @MyAutowired
    private UserService userService;

    @Override
    public void init() throws ServletException {
        BeanFactory.getInstance().addServlet(this);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String accountNumber = request.getParameter("accountNumber");
        String eMail = request.getParameter("eMail");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        User user = new User();
        user.setAccountNumber(accountNumber);
        user.setUserName(userName);
        user.setPassword(password);
        user.seteMail(eMail);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        int result = userService.registerUser(user);
        if(result == 0){

        }else{

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List list = SqlUtil.query("SELECT * FROM users WHERE account_number=?",User.class,"462036369");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
