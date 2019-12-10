package com.shoppingmall.dao.impl;

import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyRepository;
import com.shoppingmall.dao.UserDao;
import com.shoppingmall.entity.User;
import com.shoppingmall.util.SqlUtil;
import org.apache.log4j.Logger;

import java.util.List;

@MyRepository
public class UserDaoImpl implements UserDao {

    @MyAutowired
    private Logger logger;

    @Override
    public User selectUserByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM users WHERE account_number=?";
        User user = null;
        try {
            List<Object> list = SqlUtil.query(sql,User.class,accountNumber);
            if(list == null || list.size() != 1){
                return null;
            }
            user = (User)list.get(0);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";
        int res = 0;
        try {
            res = SqlUtil.update(sql,user.getAccountNumber(),
                                user.getUserName(),
                                user.getPassword(),
                                user.getGender(),
                                user.geteMail(),
                                user.getPhoneNumber(),
                                user.getIsBusiness());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return res;
    }
}
