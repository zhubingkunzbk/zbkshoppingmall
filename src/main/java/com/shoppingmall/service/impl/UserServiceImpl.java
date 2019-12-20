package com.shoppingmall.service.impl;

import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyService;
import com.shoppingmall.dao.UserDao;
import com.shoppingmall.entity.User;
import com.shoppingmall.service.UserService;
import com.shoppingmall.util.BeanFactory;
import com.shoppingmall.util.CommonUtils;
import com.shoppingmall.util.MD5Util;
import com.sun.javafx.iio.ImageStorage;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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
    public User login(String accountNumber, String password) {
        User user = userDao.selectUserByAccountNumber(accountNumber);
        if(user == null)
            return null;
        String truePassword = user.getPassword();
        String enCodePassword = MD5Util.encode(password);
        if(truePassword.equals(enCodePassword)){
            user.setPassword("");
            return user;
        }
        return null;
    }

    @Override
    public BufferedImage getIdentifyingCode(HttpSession session) {
        int height = 50;
        int width = 120;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();//获取画笔
        graphics.setColor(new Color(255,255,255));//设置白色
        graphics.fillRect(0,0,width,height);//填充整个图片为白色背景
        String identifyingCode = "";//定义生成的验证码
        int r,g,b,x1,x2,y1,y2;
        char[] charCode = CommonUtils.getCharCode();//获取52个大小写英文字母和10个数字
        Random random = new Random();
        graphics.setFont(new Font("Times New Roman",Font.BOLD,height - 5));//设置字体
        graphics.setStroke(new BasicStroke(1.5f));//设置线条宽度
        //随机四个字符
        for(int i = 0;i < 4;i++){
            int randomPos = random.nextInt(62);
            char c = charCode[randomPos];
            identifyingCode += c;//添加到真实验证码中
            //随机颜色
            r = random.nextInt(255);
            g = random.nextInt(255);
            b = random.nextInt(255);
            //设置随机颜色
            graphics.setColor(new Color(r,g,b));
            //画出生成的字符
            graphics.drawString(String.valueOf(c),i * (width / 4),height / 2 + height / 4);
        }
        //将真实验证码存入session
        session.setAttribute("identifyingCode",identifyingCode);
        r = random.nextInt(255);
        g = random.nextInt(255);
        b = random.nextInt(255);
        graphics.setColor(new Color(r,g,b));
        //随机两点生成15条干扰线
        for(int i = 0;i < 15;i++){
            x1 = random.nextInt(width);
            x2 = random.nextInt(width);
            y1 = random.nextInt(height);
            y2 = random.nextInt(height);
            graphics.drawLine(x1,y1,x2,y2);
        }
        //释放资源
        graphics.dispose();
        return image;
    }

    @Override
    public User getUserByAccountNumber(String accountNumber) {
        User user = userDao.selectUserByAccountNumber(accountNumber);
        return user;
    }

    @Override
    public int changeToSeller(String accountNumber) {
        User user = userDao.selectUserByAccountNumber(accountNumber);
        user.setIsBusiness(true);
        int res = userDao.updateUser(user);
        if(res == 1){
            return 0;
        }else{
            return 1;
        }
    }
}
