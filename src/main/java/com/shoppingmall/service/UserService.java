package com.shoppingmall.service;

import com.shoppingmall.entity.User;

public interface UserService {

    /**
     * 注册用户
     * @param user 传入需要注册的用户信息封装好的对象
     * @return 0-注册成功 1-账号已存在
     */
    int registerUser(User user);

    /**
     * 登陆
     * @param accountNumber 账号
     * @param password 密码
     * @return 0-登陆成功 1-用户名或密码错
     */
    int login(String accountNumber, String password);
}
