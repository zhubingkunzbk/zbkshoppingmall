package com.shoppingmall.service.impl;

import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyService;
import com.shoppingmall.dao.CommodityDao;
import com.shoppingmall.entity.Commodity;
import com.shoppingmall.service.CommodityService;

import java.util.List;

@MyService
public class CommodityServiceImpl implements CommodityService {

    @MyAutowired
    private CommodityDao commodityDao;


    @Override
    public List<Commodity> getSalesVolumeTopCommodities(int topNumber) {
        List list = null;
        list = commodityDao.getCommoditiesByPos(0,topNumber,"sales_volume","DESC");
        return list;
    }
}
