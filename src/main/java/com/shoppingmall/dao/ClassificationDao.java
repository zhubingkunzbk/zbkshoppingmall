package com.shoppingmall.dao;

import com.shoppingmall.entity.Classifications;

import java.util.List;

public interface ClassificationDao {
    /**
     * 获取所有分类
     * @return
     */
    List<Classifications> getAllClassifications();
}
