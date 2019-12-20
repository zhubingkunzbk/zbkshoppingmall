package com.shoppingmall.dao.impl;

import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyRepository;
import com.shoppingmall.dao.CommodityDao;
import com.shoppingmall.entity.Commodity;
import com.shoppingmall.util.SqlUtil;
import org.apache.log4j.Logger;

import java.util.List;


@MyRepository
public class CommodityDaoImpl implements CommodityDao {

    @MyAutowired
    Logger logger;

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

    @Override
    public List<Commodity> searchCommoditiesByTitle(String searchKey, int startPos, int endPos) {
        searchKey = "%" + searchKey + "%";
        String sql = "SELECT id,price,stock,sales_volume,`describe`,title,classification_id,user_account_number,show_img_src FROM commodities " +
                "WHERE title LIKE ? LIMIT ?,?";
        List list = null;
        try {
            list = SqlUtil.query(sql,Commodity.class,searchKey,startPos,endPos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insertCommodity(Commodity commodity) {
        String sql = "INSERT INTO commodities (content,price,stock,sales_volume,`describe`,title,classification_id,user_account_number,show_img_src) VALUES (?,?,?,?,?,?,?,?,?)";
        int res = 0;
        try {
            res = SqlUtil.update(sql,commodity.getContent(),
                    commodity.getPrice(),
                    commodity.getStock(),
                    commodity.getSalesVolume(),
                    commodity.getDescribe(),
                    commodity.getTitle(),
                    commodity.getClassificationId(),
                    commodity.getUserAccountNumber(),
                    commodity.getShowImgSrc());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return 0;
        }
        return res;
    }

    @Override
    public Commodity selectSimpleCommodityById(Integer commodityId) {
        String sql = "SELECT id,price,stock,sales_volume,`describe`,title,classification_id,user_account_number,show_img_src FROM commodities WHERE id=?";
        Commodity commodity = null;
        try {
            commodity = (Commodity) SqlUtil.query(sql,Commodity.class,commodityId).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commodity;
    }

    @Override
    public Commodity selectCommodityById(Integer commodityId) {
        String sql = "SELECT * FROM commodities WHERE id=?";
        Commodity commodity = null;
        try {
            commodity = (Commodity) SqlUtil.query(sql,Commodity.class,commodityId).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commodity;
    }

    @Override
    public List<Commodity> getCommoditiesByClassification(int classificationId, int pageNumber) {
        String sql = "SELECT id,price,stock,sales_volume,`describe`,title,classification_id,user_account_number,show_img_src FROM commodities " +
                "WHERE classification_id=? LIMIT ?,?";
        List list = null;
        try {
            list = SqlUtil.query(sql,Commodity.class,classificationId,pageNumber * 10,pageNumber * 10 + 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Commodity> getCommoditiesByAccountNumber(String accountNumber) {
        String sql = "SELECT id,price,stock,sales_volume,`describe`,title,classification_id,user_account_number,show_img_src FROM commodities " +
                "WHERE user_account_number=?";
        List list = null;
        try {
            list = SqlUtil.query(sql,Commodity.class,accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
//Error Code: 1064. You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'describe,title,classification_id,user_account_number FROM commodities WHERE titl' at line 1	0.016 sec