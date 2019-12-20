package com.shoppingmall.service;

import com.shoppingmall.entity.User;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

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
    User login(String accountNumber, String password);


    /**
     * 获取验证码
     * @param session
     * @return 绘制好的验证码
     */
    BufferedImage getIdentifyingCode(HttpSession session);

    /**
     * 通过账号获取用户信息
     * @param accountNumber
     * @return
     */
    User getUserByAccountNumber(String accountNumber);

    /**
     * 将指定账号的用户设置为卖家
     * @param accountNumber 指定的帐号
     * @return 0-成功 1-失败
     */
    int changeToSeller(String accountNumber);
}
