package com.shoppingmall.service.impl;

import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyService;
import com.shoppingmall.dao.ClassificationDao;
import com.shoppingmall.entity.Classifications;
import com.shoppingmall.service.ClassificationService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MyService
public class ClassificationServiceImpl implements ClassificationService {

    @MyAutowired
    private ClassificationDao classificationDao;

    @MyAutowired
    private JedisPool jedisPool;

    @Override
    public Map<String,String> getParentClassification() {
        Jedis jedis = jedisPool.getResource();
        Map<String,String> jedisMap = jedis.hgetAll("classification:parentClassification");
        if(jedisMap == null || jedisMap.size() < 1){
            if(jedisMap == null)
                jedisMap = new HashMap<>();
            List<Classifications> list = classificationDao.getAllClassifications();
            for(Classifications ele:list){
                if(ele.getId().compareTo(ele.getParentId()) == 0){
                    jedisMap.put(ele.getId().toString(),ele.getName());
                    jedis.hset("classification:parentClassification",ele.getId().toString(),ele.getName());
                }
            }
            jedis.close();
            return jedisMap;
        }else{
            jedis.close();
            return jedisMap;
        }

    }

    @Override
    public Map<String,String> getClassificationByParent(int parentId) {
        Jedis jedis = jedisPool.getResource();
        Map<String,String> res = jedis.hgetAll("classification:childClassification:" + parentId);
        if(res == null || res.size() <= 0){
            List<Classifications> list = classificationDao.getAllClassifications();
            for(Classifications ele:list){
                if(ele.getParentId().compareTo(parentId) == 0 && ele.getId().compareTo(ele.getParentId()) != 0) {
                    res.put(ele.getId().toString(),ele.getName());
                    jedis.hset("classification:childClassification:" + parentId,ele.getId().toString(),ele.getName());
                }
            }
            jedis.close();
            return res;
        }else{
            jedis.close();
            return res;
        }
    }
}
