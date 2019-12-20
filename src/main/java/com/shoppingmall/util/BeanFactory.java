package com.shoppingmall.util;

import com.shoppingmall.common.Entry;
import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyRepository;
import com.shoppingmall.common.MyService;
import com.shoppingmall.configure.AliPayConf;
import org.apache.http.HttpHost;
import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import javax.servlet.Servlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public class BeanFactory {
    private Map<String,Object> factoryMap;
    private static volatile BeanFactory instance;

    public Map<String, Object> getFactoryMap() {
        return factoryMap;
    }


    private BeanFactory(){
        this.factoryMap = new HashMap<String,Object>();
    }
    public static BeanFactory getInstance(){
        if(instance == null){
            synchronized (BeanFactory.class){
                if(instance == null){
                    instance = new BeanFactory();
                }
                return instance;
            }
        }
        return instance;
    }

    public Object getBean(String id){
        if(factoryMap.get(id) == null){
            throw new RuntimeException("class not loaded");
        }
        return factoryMap.get(id);
    }

    private void loadClassByPath(String path,ServletContextEvent sce){
        String packageAndClass = path.substring(path.indexOf(File.separator + "classes" + File.separator) + 9,path.length() - 6).replace(File.separator,".");
        try {
            Class clazz = Class.forName(packageAndClass);
            if(clazz.isAnnotationPresent(MyService.class) || clazz.isAnnotationPresent(MyRepository.class)){
                Object obj = clazz.newInstance();//创建实例
                factoryMap.put(clazz.getSimpleName(),obj);
            }
//            else if(clazz.isAnnotationPresent(WebServlet.class)){
//                Servlet servlet = (Servlet)clazz.newInstance();
//                WebServlet webServlet = (WebServlet) clazz.getAnnotation(WebServlet.class);
//                String servletName = webServlet.name();
//                String url = webServlet.urlPatterns()[0];
//                ServletRegistration.Dynamic dynamic = sce.getServletContext().addServlet(servletName,servlet);
//                factoryMap.put(clazz.getSimpleName(),servlet);
//
//            }
        } catch (Exception e) {
            throw new RuntimeException("bean加载失败" + e.getMessage());
        }
    }
    public void scanClassByBasePackage(String basePackge, ServletContextEvent sce){
        initLogger(sce);
        initJedis(sce);
        initElasticSearch();
        initAliPay();
        File rootPath = new File(basePackge);
        File[] childrenFileList = rootPath.listFiles();
        for(File file:childrenFileList){
            //如果文件是目录
            if(file.isDirectory()){
                scanClassByBasePackage(file.getAbsolutePath(),sce);
            }else{
                if(file.getName().endsWith(".class"))
                    loadClassByPath(file.getAbsolutePath(),sce);
            }
        }
    }

    private void initElasticSearch() {
        HttpHost httpHost = HttpHost.create("localhost:9200");
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
        factoryMap.put(client.getClass().getSimpleName(),client);
    }

    private void initJedis(ServletContextEvent sce) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Properties properties = new Properties();
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("jedis.properties");
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setMaxWaitMillis(1500);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"106.54.42.85",6379,2000,"zhubingkun0715..");
        factoryMap.put(jedisPool.getClass().getSimpleName(),jedisPool);
    }

    private void initAliPay(){
        AliPayConf aliPayConf = AliPayConf.getInstance();
        factoryMap.put(AliPayConf.class.getSimpleName(),aliPayConf);
    }

    private void initLogger(ServletContextEvent sce) {
        Logger logger = Logger.getLogger(sce.getServletContext().getClass());
        factoryMap.put(logger.getClass().getSimpleName(),logger);
    }

    public void dependencyInjection() {
        for(Map.Entry<String,Object> entry:factoryMap.entrySet()){
            Class clazz = entry.getValue().getClass();
            Field[] fields = clazz.getDeclaredFields();
            for(Field field:fields){
                //如果该字段被@MyAutowired修饰
                if(field.isAnnotationPresent(MyAutowired.class)){
                    Class fieldClass = field.getType();
                    Collection<Object> objList = factoryMap.values();
                    for(Object obj:objList){
                        if(obj.getClass() == fieldClass || fieldClass.isAssignableFrom(obj.getClass())){
                            try {
                                field.setAccessible(true);
                                field.set(entry.getValue(),obj);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void addServlet(Servlet servlet){
        Class clazz = servlet.getClass();
        synchronized (this) {
            factoryMap.put(clazz.getSimpleName(), servlet);
        }
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(MyAutowired.class)){
                Class fieldClass = field.getType();
                Collection<Object> objList = factoryMap.values();
                for(Object obj:objList){
                    if(obj.getClass() == fieldClass || fieldClass.isAssignableFrom(obj.getClass())){
                        try {
                            field.setAccessible(true);
                            field.set(servlet,obj);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void destroyed(ServletContextEvent sce) {
        for(Map.Entry<String, Object> entry:factoryMap.entrySet()){
            Object obj = entry.getValue();
            if(obj instanceof AutoCloseable){
                System.out.println(obj.toString());
                AutoCloseable closeable = (AutoCloseable) obj;
                CommonUtils.close(closeable);
            }
        }
    }
}
