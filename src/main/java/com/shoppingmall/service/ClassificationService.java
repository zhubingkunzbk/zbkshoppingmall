package com.shoppingmall.service;


import com.shoppingmall.entity.Classifications;

import java.util.List;
import java.util.Map;

public interface ClassificationService {

    /**
     * 获取所有父分类
     * @return
     */
    Map<String,String> getParentClassification();

    /**
     * 根据父ID获取分类
     * @param parentId
     * @return
     */
    Map<String,String> getClassificationByParent(int parentId);
}
