package com.shoppingmall.service;


import com.shoppingmall.entity.Commodity;

import java.util.List;

public interface CommodityService {

    /**
     * 获取按销量排行的前topNumber个商品
     * @param topNumber 指定的topNumber
     * @return 结果列表
     */
    List<Commodity> getSalesVolumeTopCommodities(int topNumber);
}
