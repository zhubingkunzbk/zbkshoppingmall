package com.shoppingmall.util;

import com.shoppingmall.util.sql.SqlConnectionFactory;
import com.shoppingmall.util.sql.SqlConnectionFactoryBuilder;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUtil {
    private static Logger logger = (Logger) BeanFactory.getInstance().getBean("Logger");
    private static final SqlConnectionFactory sqlConnectionFactory;
    private static SqlConnectionFactoryBuilder sqlConnectionFactoryBuilder = new SqlConnectionFactoryBuilder("/druid.properties");
    static{
        sqlConnectionFactory = sqlConnectionFactoryBuilder.build();
    }

    public static String transformationFieldName(String name) {
        StringBuffer stringBuffer = new StringBuffer(name);
        for(int i = 0;i < stringBuffer.length();i++){
            char c = stringBuffer.charAt(i);
            if(c >= 'A' && c <= 'Z'){
                c = (char)(c + 32);
                stringBuffer.deleteCharAt(i);
                stringBuffer.insert(i,"_" + c);
            }
        }
        return stringBuffer.toString();
    }

    public static int update(String sql,Object...args) throws Exception{
        Connection connection = null;
        PreparedStatement pstmt = null;
        synchronized (SqlUtil.class) {
            connection = sqlConnectionFactory.getConnection();
        }
        pstmt = connection.prepareStatement(sql);
        int i = 1;
        for (Object obj : args) {
            pstmt.setObject(i,obj);
            i++;
        }
        int res = pstmt.executeUpdate();
        synchronized (SqlUtil.class) {
            logger.debug(pstmt.toString());
            CommonUtils.close(pstmt, connection);
        }
        return res;
    }

    public static List<Object> query(String sql,Class clazz,Object...args) throws Exception {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        synchronized (SqlUtil.class) {
            connection = sqlConnectionFactory.getConnection();
        }
        pstmt = connection.prepareStatement(sql);
        int i = 1;
        for (Object obj : args) {
            //handleArgsType(pstmt, i, obj);
            pstmt.setObject(i,obj);
            i++;
        }
        rs = pstmt.executeQuery();
        synchronized (SqlUtil.class) {
            logger.debug(pstmt.toString());
        }
        if (rs == null) {
            return null;
        }
        List<Object> list = new ArrayList<>();
        while (rs.next()) {
            Object obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                name = transformationFieldName(name);
                Object fieldValue = rs.getObject(name);
                field.setAccessible(true);
                field.set(obj, fieldValue);
            }
            list.add(obj);
        }
        synchronized (SqlUtil.class) {
            CommonUtils.close(rs, pstmt, connection);
        }
        return list;
    }
//    private static void handleArgsType(PreparedStatement pstmt, int i, Object obj) throws SQLException {
//        pstmt.setObject(i,obj);
//        if(obj instanceof String){
//            pstmt.setString(i,(String)obj);
//            return;
//        }
//        if(obj instanceof Boolean){
//            pstmt.setBoolean(i,(Boolean)obj);
//            return;
//        }
//        if(obj instanceof Byte){
//            pstmt.setByte(i,(Byte)obj);
//            return;
//        }
//        if(obj instanceof Short){
//            pstmt.setShort(i,(Short)obj);
//            return;
//        }
//        if(obj instanceof Integer){
//            pstmt.setInt(i,(Integer)obj);
//            return;
//        }
//        if(obj instanceof Long){
//            pstmt.setLong(i,(Long)obj);
//            return;
//        }
//        if(obj instanceof Float){
//            pstmt.setFloat(i,(Float)obj);
//            return;
//        }
//        if(obj instanceof Double){
//            pstmt.setDouble(i,(Double)obj);
//            return;
//        }
//        if(obj instanceof BigDecimal){
//            pstmt.setBigDecimal(i,(BigDecimal)obj);
//            return;
//        }
//        if(obj instanceof Date){
//            pstmt.setDate(i,(Date)obj);
//            return;
//        }
//    }

}
