package com.shoppingmall.util.sql;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class SqlConnectionFactory {
    protected Properties properties;
    protected DataSource dataSource;
    protected InputStream inputStream;
    private static volatile SqlConnectionFactory instance;

    /**
     * 只允许同包下的类调用该方法
     * @param propertiesFilePath
     * @return
     */
    protected static SqlConnectionFactory getInstance(String propertiesFilePath){
        if(instance == null){
            synchronized (SqlConnectionFactory.class){
                if(instance == null){
                    instance = new SqlConnectionFactory(propertiesFilePath);
                }
            }
        }
        return instance;
    }
    private SqlConnectionFactory(String propertiesFilePath){
        this.inputStream = SqlConnectionFactory.class.getResourceAsStream(propertiesFilePath);
    }
    public Connection getConnection(){
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("获取连接失败" + e.getMessage());
        }
    }
}
