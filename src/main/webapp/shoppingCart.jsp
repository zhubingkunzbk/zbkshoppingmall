<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>学生闲置物品信息</title>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/lib/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="/css/image/favicon.ico" />
    <link rel="stylesheet" href="/lib/bootstrap-3.4.1-dist/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/assert/font_3pzoh1xs3x9/iconfont.css" type="text/css">
    <link rel="stylesheet" href="/css/header.css" type="text/css">
    <link rel="stylesheet" href="/css/shoppingCart.css" type="text/css">
    <script src="/js/header.js"></script>
    <script src="/js/shoppingCart.js"></script>
</head>
<body>
<header>
    <div class="center">
        <div class="left">
            <ul>
                <li class="header-list-item">
                    <span id="provinces">吉首大学张家界校区</span>
                    <ul class="level-2-menu">
                        <li><a href="#">吉首大学沙子坳校区</a></li>
                        <li><a href="#">吉首大学雷公井校区</a></li>
                        <li><a href="#">吉首大学张家界校区</a></li>
                    </ul>
                </li>
                <li class="header-list-item">
                    <span id="username">请登录</span>
                    <div class="level-2-menu" id="personal-info">
                        <div class="portrait"><img src="/css/image/portrait.png" style="width: 80%;height: 80%;"></div><!--
                            --><div class="operator">
                        <div style="height: 40px">
                            <a href="">账号管理</a>
                            <span>|</span>
                            <a href="">退出登录</a>
                        </div>

                        <p>普通用户</p>
                    </div>
                        <div class="privilege"><a href="">查看您的特权</a></div>
                    </div>
                </li>
                <li class="header-list-item">
                    <span id="message">消息</span>
                    <ul class="level-2-menu" id="message-info">
                        <a href="#">来自撒币的消息</a>
                        <a href="#">来自制杖的消息</a>
                        <a href="#" id="more-message">查看更多</a>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="right">
            <ul>
                <li class="header-list-item">
                    <span>我的交易</span>
                    <ul class="level-2-menu">
                        <li><a href="#">已买到的商品</a></li>
                        <li><a href="#">我的足迹</a></li>
                    </ul>
                </li>
                <li class="header-list-item">
                    <span id="shopping-cart"><a href="/shoppingCart/getSoppingCart">购物车</a></span>
                </li>
                <li class="header-list-item">
                    <span><a href="#">★收藏夹</a></span>
                    <ul class="level-2-menu">
                        <li><a href="#">收藏的商品</a></li>
                        <li><a href="#">收藏的店铺</a></li>
                    </ul>
                </li>
                <li class="header-list-item">
                    <span id="classification"><a href="#">商品分类</a></span>
                </li>
                <li class="header-list-item">
                    <span>联系客服</span>
                    <ul class="level-2-menu">
                        <li><a href="#">消费者客服</a></li>
                        <li><a href="#">商家客服</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</header>
<div class="top-div">
    <div class="center">
        <div><a href="/"><img src="/css/image/index_icon.png" height="160px" width="230px"></a></div>
    </div>
</div>
<div class="core-div">
    <div class="center">
        <ul>
            <li>
                <div style="width: 520px">
                    商品
                </div>
                <div style="width: 370px">
                    数量
                </div>
                <div style="width: 150px">
                    小结
                </div>
                <div style="width: 150px">操作</div>
            </li>
            <c:forEach items="${requestScope.shoppingCart}" var="commodity">
            <li>
                <div><img src="${commodity.key.showImgSrc}" alt=""></div>
                <div>
                    <a href="/commodities/getCommodityDetail?commodityId=${commodity.key.id}">${commodity.key.title}</a>
                    <p>${commodity.key.describe}</p>
                </div>
                <div>
                    <span>现在购物车中有</span>
                    <input type="button" value="-">
                    <input type="text" value="${commodity.value}">
                    <input type="button" value="+">
                    <span>件</span>
                </div>
                <div>
                    <p><span>￥</span>${commodity.key.price}</p>
                </div>
                <div><button class="btn">移 除</button></div>
            </li>
            </c:forEach>
            <div style="clear: both"></div>
        </ul>
    </div>
    <div class="center">
        <div><input type="button" value="去结算"></div>
        <div><p></p></div>
        <div style="clear: both"></div>
    </div>
</div>
</body>
</html>