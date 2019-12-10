package com.shoppingmall.util.sql;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.util.Properties;

public class SqlConnectionFactoryBuilder {
    private String propertiesFilePath;

    public SqlConnectionFactoryBuilder(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
    }
    public SqlConnectionFactory build(){
        SqlConnectionFactory sqlConnectionFactory = SqlConnectionFactory.getInstance(this.propertiesFilePath);
        sqlConnectionFactory.properties = new Properties();
        try {
            sqlConnectionFactory.properties.load(sqlConnectionFactory.inputStream);
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件出错" + e.getMessage());
        }
        try {
            sqlConnectionFactory.dataSource = DruidDataSourceFactory.createDataSource(sqlConnectionFactory.properties);
        } catch (Exception e) {
            throw new RuntimeException("创建连接池失败" + e.getMessage());
        }
        return sqlConnectionFactory;
    }
}
