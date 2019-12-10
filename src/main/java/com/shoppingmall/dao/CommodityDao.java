package com.shoppingmall.dao;

import com.shoppingmall.entity.Commodity;

import java.util.List;

public interface CommodityDao {

    /**
     * 获取部分商品信息
     * @param startPos 开始坐标
     * @param endPos  结束坐标
     * @param sortKey  排序关键字
     * @param srotType  排序类型（ASC/DESC）
     * @return 结果列表
     */
    List<Commodity> getCommoditiesByPos(int startPos,int endPos,String sortKey,String srotType);
}
