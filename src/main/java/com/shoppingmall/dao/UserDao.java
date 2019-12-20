package com.shoppingmall.dao;

import com.shoppingmall.entity.User;

public interface UserDao {
    User selectUserByAccountNumber(String accountNumber);

    int addUser(User user);

    int updateUser(User user);
}
