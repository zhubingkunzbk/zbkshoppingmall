package com.shoppingmall.service.impl;

import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyService;
import com.shoppingmall.dao.CommodityDao;
import com.shoppingmall.entity.Commodity;
import com.shoppingmall.service.ShoppingCartService;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@MyService
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @MyAutowired
    private Logger logger;

    @MyAutowired
    private CommodityDao commodityDao;

    @MyAutowired
    private JedisPool jedisPool;

    @Override
    public Map<Commodity, Integer> getShoppingCart(String accountNumber) {
        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = jedis.hgetAll("shoppingCart:" + accountNumber);
        if(map == null){
            return null;
        }
        Map<Commodity,Integer> res = new HashMap<>();
        for(Map.Entry<String,String> entry : map.entrySet()){
            Integer commodityId = Integer.parseInt(entry.getKey());
            Integer number = Integer.parseInt(entry.getValue());
            Commodity commodity = commodityDao.selectSimpleCommodityById(commodityId);
            res.put(commodity,number);
        }
        jedis.close();
        return res;
    }
    @Override
    public int insertIntoShoppingCart(String accountNumber,Integer commodityId,Integer number) {
        Jedis jedis = jedisPool.getResource();
        long res = -1;
        try {
            res = jedis.hset("shoppingCart:" + accountNumber, commodityId.toString(), number.toString());
        }catch (Exception e){
            return 1;
        }
        jedis.close();
        if((res == 1) || (res == 0)){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public void incShoppingCartNumber(String accountNumber, Integer commodityId, Integer number) {
        Jedis jedis = jedisPool.getResource();
        jedis.hincrBy("shoppingCart:" + accountNumber,commodityId.toString(),number);
        jedis.close();
    }


}
