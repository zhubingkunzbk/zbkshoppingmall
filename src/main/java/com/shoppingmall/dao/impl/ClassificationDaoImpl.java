package com.shoppingmall.dao.impl;

import com.shoppingmall.common.MyRepository;
import com.shoppingmall.dao.ClassificationDao;
import com.shoppingmall.entity.Classifications;
import com.shoppingmall.util.SqlUtil;

import java.util.List;

@MyRepository
public class ClassificationDaoImpl implements ClassificationDao {
    @Override
    public List<Classifications> getAllClassifications() {
        String sql = "SELECT * FROM classifications";
        List list = null;
        try {
            list = SqlUtil.query(sql,Classifications.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
