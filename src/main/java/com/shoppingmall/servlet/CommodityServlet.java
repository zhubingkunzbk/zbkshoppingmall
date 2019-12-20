package com.shoppingmall.servlet;

import com.alibaba.fastjson.JSON;
import com.shoppingmall.common.Entry;
import com.shoppingmall.common.MyAutowired;
import com.shoppingmall.common.MyRequestMapping;
import com.shoppingmall.common.RequestType;
import com.shoppingmall.common.response.responseImpl.ExpandDataResponseObject;
import com.shoppingmall.entity.Commodity;
import com.shoppingmall.entity.User;
import com.shoppingmall.service.CommodityService;
import com.shoppingmall.service.UserService;
import com.shoppingmall.servlet.responseObject.LoginResponse;
import com.shoppingmall.servlet.responseObject.RegisterResponse;
import com.shoppingmall.util.BeanFactory;
import com.shoppingmall.util.CommonUtils;
import com.shoppingmall.util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = "/commodities/*",loadOnStartup = 1)
public class CommodityServlet extends HttpServlet {

    @MyAutowired
    private CommodityService commodityService;

    private Map<String, Entry<RequestType, Method>> uriMapping;

    @Override
    public void init() throws ServletException {
        BeanFactory.getInstance().addServlet(this);
        uriMapping = new HashMap();
        Class clazz = this.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping myRequestMapping = (MyRequestMapping) method.getAnnotation(MyRequestMapping.class);
                String uri = myRequestMapping.value();
                RequestType requestType = myRequestMapping.type();
                uriMapping.put(uri, new Entry<>(requestType, method));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        Entry<RequestType, Method> entry = uriMapping.get(uri);
        if (entry.getKey() != RequestType.POST) {
            try {
                response.sendError(503, "请求类型不匹配");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        request.setAttribute("fromDoPost", true);
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        Entry<RequestType, Method> entry = uriMapping.get(uri);
        if (entry == null) {
            try {
                response.sendError(404);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Boolean isFromDoPost = (Boolean) request.getAttribute("fromDoPost");
        if ((isFromDoPost == null || !isFromDoPost) && entry.getKey() != RequestType.GET) {
            try {
                response.sendError(503, "请求类型不匹配");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        Method method = entry.getValue();
        try {
            method.invoke(this, request, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping(value = "/commodities/getSalesVolumeTopCommodities", type = RequestType.GET)
    private void getSalesVolumeTopCommodities(HttpServletRequest request, HttpServletResponse response) {
        String requestTopNumber = request.getParameter("topNumber");
        if (requestTopNumber == null || requestTopNumber.length() <= 0) {
            requestTopNumber = "10";
        }
        int topNumber = 0;
        try {
            topNumber = Integer.parseInt(requestTopNumber);
        } catch (NumberFormatException e) {
            try {
                response.sendError(400, "参数错误" + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        List<Commodity> list = commodityService.getSalesVolumeTopCommodities(topNumber);
        try {
            response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<List<Commodity>>(0, "获取成功", list)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping(value = "/commodities/getCommodityDetail", type = RequestType.GET)
    private void getCommodityDetail(HttpServletRequest request,HttpServletResponse response){
        String requestCommodityId = request.getParameter("commodityId");
        int commodityId = 0;
        try{
            commodityId = Integer.parseInt(requestCommodityId);
        }catch (Exception e){
            try {
                response.sendError(400,"参数错误");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        Commodity commodity = commodityService.getCommodityDetail(commodityId);
        request.setAttribute("commodity",commodity);
        try {
            //response.sendRedirect("/commodityDetail.jsp");
            request.getRequestDispatcher("/commodityDetail.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @MyRequestMapping(value = "/commodities/searchCommodity", type = RequestType.GET)
    private void searchCommodity(HttpServletRequest request, HttpServletResponse response){
        String searchKey = request.getParameter("key");
        String requestPageNumber = request.getParameter("pageNumber");
        if(!CommonUtils.isNotNull(requestPageNumber)){
            requestPageNumber = "0";
        }
        int pageNumber = 0;
        try {
            pageNumber = Integer.parseInt(requestPageNumber);
        }catch (Exception e){
            try {
                response.sendError(400,"参数错误");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        List<Commodity> list = commodityService.searchCommoditiesByTitle(searchKey,pageNumber);
        request.setAttribute("commoditiesList",list);
        response.setContentType("text/html; charset=utf-8");
        try {
            if(list == null || list.size() == 0){
                request.getRequestDispatcher("/noCommodities.jsp").forward(request,response);
                return;
            }
            request.getRequestDispatcher("/commodities.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<List<Commodity>>(0,"查询成功",list)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    @MyRequestMapping(value = "/commodities/getCommoditiesByClassification", type = RequestType.GET)
    private void getCommoditiesByClassification(HttpServletRequest request, HttpServletResponse response){
        String requestClassificationId = request.getParameter("classificationId");
        String requestPageNumber = request.getParameter("pageNumber");
        if(!CommonUtils.isNotNull(requestPageNumber)){
            requestPageNumber = "0";
        }
        int pageNumber = 0;
        int classificationId = 0;
        try {
            pageNumber = Integer.parseInt(requestPageNumber);
            classificationId = Integer.parseInt(requestClassificationId);
        }catch (Exception e){
            try {
                response.sendError(400,"参数错误");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        List<Commodity> list = commodityService.getCommoditiesByClassification(classificationId,pageNumber);
        request.setAttribute("commoditiesList",list);
        response.setContentType("text/html; charset=utf-8");
        try {
            if(list == null || list.size() == 0){
                request.getRequestDispatcher("/noCommodities.jsp").forward(request,response);
                return;
            }
            request.getRequestDispatcher("/commodities.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<List<Commodity>>(0,"查询成功",list)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @MyRequestMapping(value = "/commodities/getCommoditiesByAccountNumber", type = RequestType.GET)
    private void getCommoditiesByAccountNumber(HttpServletRequest request, HttpServletResponse response){
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        if(accountNumber == null){
            try {
                response.sendError(400,"未登录");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<Commodity> list = commodityService.getCommoditiesByAccountNumber(accountNumber);
        try {
            response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<List<Commodity>>(0,"查询成功",list)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping(value = "/commodities/getSearchText", type = RequestType.GET)
    private void getSearchText(HttpServletRequest request, HttpServletResponse response){
        String text = request.getParameter("text");
        List<String> list = commodityService.getSearchText(text);
        try {
            response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<List<String>>(0,"查询成功",list)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping(value = "/commodities/getHotKey", type = RequestType.GET)
    private void getHotKey(HttpServletRequest request, HttpServletResponse response){
        Set<String> set = commodityService.getHotKey();
        try {
            response.getWriter().write(JSON.toJSONString(new ExpandDataResponseObject<Set<String>>(0,"查询成功",set)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping(value = "/commodities/addCommodity", type = RequestType.POST)
    private void addCommodity(HttpServletRequest request, HttpServletResponse response){
        String title = request.getParameter("title");
        String describe = request.getParameter("describe");
        String requestStock = request.getParameter("stock");
        String requestPrice = request.getParameter("price");
        String content = request.getParameter("content");
        String showImgSrc = request.getParameter("showImgSrc");
        String requestClassificationId = request.getParameter("classificationId");
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        if(accountNumber == null)
            accountNumber = "46203636";
        int stock = 0;
        int classificationId = -1;
        double price = 0;
        if(!CommonUtils.isNotNull(title,describe,requestStock,requestPrice,requestClassificationId)){
            try {
                response.sendError(400,"参数不全");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            stock = Integer.parseInt(requestStock);
            price = Double.parseDouble(requestPrice);
            classificationId = Integer.parseInt(requestClassificationId);
        }catch (NumberFormatException e){
            try {
                response.sendError(400,"参数错误");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Commodity commodity = new Commodity();
        commodity.setClassificationId(classificationId);
        commodity.setContent(content);
        commodity.setDescribe(describe);
        commodity.setPrice(price);
        commodity.setSalesVolume(0);
        commodity.setStock(stock);
        commodity.setTitle(title);
        commodity.setShowImgSrc(showImgSrc);
        commodity.setUserAccountNumber(accountNumber);
        int result = commodityService.addCommodity(commodity);
    }

    @MyRequestMapping(value = "/commodities/uploadCommodityImg",type = RequestType.POST)
    private void uploadCommodityImg(HttpServletRequest request,HttpServletResponse response){
        if(!ServletFileUpload.isMultipartContent(request)){
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        List<FileItem> list = null;
        try {
            list = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator<FileItem> it = list.iterator();
        String uuid = null;
        String suffix = null;
        while(it.hasNext()){
            FileItem fileItem = it.next();
            if(fileItem == null || fileItem.getName() == null || fileItem.getName().equals("null")){
                continue;
            }
            uuid = UUID.randomUUID().toString();
            suffix = fileItem.getName().substring(fileItem.getName().lastIndexOf('.'));
            String localFilePath = request.getRealPath("/images/upload/");
            try {
                File dir = new File(localFilePath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                File file = new File( localFilePath + uuid+suffix);
                if(!file.exists()){
                    file.createNewFile();
                }
                fileItem.write(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String,String> res = new HashMap<>();
        res.put("uploaded","1");
        res.put("url","/images/upload/" + uuid + suffix);
        try {
            response.getWriter().write(JSON.toJSONString(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
