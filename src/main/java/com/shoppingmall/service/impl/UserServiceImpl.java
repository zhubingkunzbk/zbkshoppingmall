package com.shoppingmall.service.impl;

import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyService;
import com.shoppingmall.dao.UserDao;
import com.shoppingmall.entity.User;
import com.shoppingmall.service.UserService;
import com.shoppingmall.util.BeanFactory;
import com.shoppingmall.util.MD5Util;

@MyService
public class UserServiceImpl implements UserService {

    @MyAutowired
    private UserDao userDao;

    @Override
    public int registerUser(User user) {
        User u = userDao.selectUserByAccountNumber(user.getAccountNumber());
        if(u != null){
            return 1;
        }
        int res = userDao.addUser(user);
        if(res == 1)
            return 0;
        else
            return 2;
    }

    @Override
    public int login(String accountNumber, String password) {
        User user = userDao.selectUserByAccountNumber(accountNumber);
        if(user == null)
            return 1;
        String truePassword = user.getPassword();
        String enCodePassword = MD5Util.encode(password);
        if(truePassword.equals(enCodePassword)){
            return 0;
        }
        return 1;
    }
}
