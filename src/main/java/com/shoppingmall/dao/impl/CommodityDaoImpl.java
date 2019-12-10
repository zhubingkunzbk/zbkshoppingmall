package com.shoppingmall.dao.impl;

import com.shoppingmall.common.MyRepository;
import com.shoppingmall.dao.CommodityDao;
import com.shoppingmall.entity.Commodity;
import com.shoppingmall.util.SqlUtil;

import java.util.List;


@MyRepository
public class CommodityDaoImpl implements CommodityDao {

    @Override
    public List<Commodity> getCommoditiesByPos(int startPos, int endPos, String sortKey,String sortType) {
        String sql = "SELECT * FROM commodities ORDER BY " + sortKey + " " + sortType + " LIMIT ?,?";
        List list = null;
        try {
            list = SqlUtil.query(sql,Commodity.class,startPos,endPos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
