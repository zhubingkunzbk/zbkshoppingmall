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

    /**
     * 根据商品标题模糊查询商品信息
     * @param searchKey 关键字
     * @param startPos 起始位置
     * @param endPos 结束位置
     * @return
     */
    List<Commodity> searchCommoditiesByTitle(String searchKey, int startPos, int endPos);

    /**
     *
     * @param commodity
     * @return
     */
    int insertCommodity(Commodity commodity);

    /**
     * 根据商品编号查询商品简要信息
     * @param commodityId
     * @return
     */
    Commodity selectSimpleCommodityById(Integer commodityId);

    /**
     * 根据商品ID获取商品详细信息
     * @param commodityId 商品ID
     * @return
     */
    Commodity selectCommodityById(Integer commodityId);

    /**
     * 根据分类ID获取商品
     * @param classificationId 分类ID
     * @param pageNumber 页数
     * @return
     */
    List<Commodity> getCommoditiesByClassification(int classificationId, int pageNumber);

    /**
     * 根据商品商家用户的账号查找商品
     * @param accountNumber
     * @return
     */
    List<Commodity> getCommoditiesByAccountNumber(String accountNumber);
}
