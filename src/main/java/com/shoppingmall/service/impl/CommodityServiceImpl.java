package com.shoppingmall.service.impl;

import com.alibaba.fastjson.JSON;
import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyService;
import com.shoppingmall.dao.CommodityDao;
import com.shoppingmall.entity.Commodity;
import com.shoppingmall.service.CommodityService;
import org.apache.log4j.Logger;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.*;

@MyService
public class CommodityServiceImpl implements CommodityService {

    @MyAutowired
    private CommodityDao commodityDao;

    @MyAutowired
    private JedisPool jedisPool;

    @MyAutowired
    private RestHighLevelClient client;

    @MyAutowired
    private Logger logger;


    @Override
    public List<Commodity> getSalesVolumeTopCommodities(int topNumber) {
        List list = null;
        list = commodityDao.getCommoditiesByPos(0,topNumber,"sales_volume","DESC");
        return list;
    }

    @Override
    public List<Commodity> searchCommoditiesByTitle(String searchKey, int pageNumber) {
        Jedis jedis = jedisPool.getResource();
        jedis.zincrby("search:commodity:title",1,searchKey);
        jedis.close();
        saveToElasticSearchTitle(searchKey);
        int startPos = pageNumber * 10;
        int endPos = startPos + 10;
        List<Commodity> list = commodityDao.searchCommoditiesByTitle(searchKey,startPos,endPos);
        return list;
    }
    private void saveToElasticSearchTitle(String title){
        List<String> list = getSearchText(title);
        for(String ele:list){
            if(ele.equals(title)){
                return;
            }
        }
        Map<String,String> map = new HashMap<>();
        map.put("title",title);
        IndexRequest request = new IndexRequest("shoppingmall");
        request.source(map, XContentType.JSON);
        try {
            IndexResponse response = client.index(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<String> getSearchText(String text) {
        if(text == null){
            return null;
        }
        MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery(text, "title");
        SearchRequest searchRequest = new SearchRequest("shoppingmall");
        searchRequest.source().query(query);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        if(searchResponse != null){
            List<String> list = new ArrayList<>();
            SearchHits searchHits = searchResponse.getHits();
            for(SearchHit hit : searchHits){
                String source = hit.getSourceAsString();
                String res = source.substring(10,source.length() - 2);
                list.add(res);
            }
            return list;
        }
        return null;
    }

    @Override
    public Set<String> getHotKey() {
        Jedis jedis = jedisPool.getResource();
        Set<String> set = jedis.zrange("search:commodity:title",-6,-1);
        jedis.close();
        return set;
    }

    @Override
    public int addCommodity(Commodity commodity) {
        int res = commodityDao.insertCommodity(commodity);
        if(res == 1){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public Commodity getCommodityDetail(int commodityId) {
        Commodity commodity = null;
        commodity = commodityDao.selectCommodityById(commodityId);
        return commodity;
    }

    @Override
    public List<Commodity> getCommoditiesByClassification(int classificationId, int pageNumber) {
        List<Commodity> list = null;
        list = commodityDao.getCommoditiesByClassification(classificationId,pageNumber);
        return list;
    }

    @Override
    public List<Commodity> getCommoditiesByAccountNumber(String accountNumber) {
        List<Commodity> list = commodityDao.getCommoditiesByAccountNumber(accountNumber);
        return list;
    }
}
