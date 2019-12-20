package com.shoppingmall.service;


import com.shoppingmall.entity.Commodity;

import java.util.List;
import java.util.Set;

public interface CommodityService {

    /**
     * 获取按销量排行的前topNumber个商品
     * @param topNumber 指定的topNumber
     * @return 结果列表
     */
    List<Commodity> getSalesVolumeTopCommodities(int topNumber);

    /**
     * 根据商品标题查询商品
     * @param searchKey 关键字
     * @param pageNumber 页数
     * @return
     */
    List<Commodity> searchCommoditiesByTitle(String searchKey, int pageNumber);


    /**
     * 获取搜索词联想列表
     * @param text
     * @return
     */
    List<String> getSearchText(String text);

    /**
     * 获取热搜词
     * @return
     */
    Set<String> getHotKey();

    /**
     * 添加商品
     * @param commodity 商品
     * @return 0 添加成功 1 添加失败
     */
    int addCommodity(Commodity commodity);

    /**
     * 根据商品ID获取商品详细信息
     * @param commodityId 商品ID
     * @return 商品详细信息
     */
    Commodity getCommodityDetail(int commodityId);

    /**
     * 根据商品分类获取商品
     * @param classificationId 分类ID
     * @param pageNumber 页数
     * @return
     */
    List<Commodity> getCommoditiesByClassification(int classificationId, int pageNumber);

    /**
     * 根据商家用户的账号查找商品
     * @param accountNumber
     * @return
     */
    List<Commodity> getCommoditiesByAccountNumber(String accountNumber);
}
