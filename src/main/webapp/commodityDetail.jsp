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
    <link rel="stylesheet" href="/css/commodityDetail.css" type="text/css">
    <script src="/js/header.js"></script>
    <script src="/js/commodityDetail.js"></script>
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
        <div class="search-div">
            <ul>
                <li><button class="selection">商品</button></li>
                <li><button>店铺</button></li>
                <div style="clear: both"></div>
            </ul>
            <form action="http://localhost/commodities/searchCommodity" method="get">
                <div><input type="text" name="key"><input type="submit" value="搜    索">
                    <div id="associationSearch">
                        <ul>

                        </ul>
                    </div>
                </div>
            </form>
            <div class="hot-search">
                <a href="#"></a>
                <a href="#"></a>
                <a href="#"></a>
                <a href="#"></a>
                <a href="#"></a>
                <a href="#"></a>
            </div>
        </div>
    </div>
</div>
<div id="id">
    <label style="display: none;">${requestScope.commodity.id}</label>
</div>
<div class="core-div">
    <div class="center">
        <div class="commodity-describe">
            <div class="left">
                <div>
                    <img src="${requestScope.commodity.showImgSrc}" alt="">
                </div>
            </div>
            <div class="right">
                <div class="title">
                    <h3>${requestScope.commodity.title}</h3>
                </div>
                <div class="describe">
                    <h5>${requestScope.commodity.describe}</h5>
                </div>
                <div class="price">
                    <span>价格：&nbsp;&nbsp;&nbsp;&nbsp;￥</span><span>${requestScope.commodity.price}</span>
                </div>
                <div class="sale-info">
                    <p>累计销量：${requestScope.commodity.salesVolume}件</p>
                </div>
                <div class="operate-number">
                    <input type="text" value="1">
                    <div>
                        <input type="button" value="+"><input type="button" value="-">
                    </div>
                    <span>件&nbsp;&nbsp;&nbsp;&nbsp;库存${requestScope.commodity.stock}件</span>
                </div>
                <div class="operate-shopping">
                    <button id="buy">立即购买</button>
                    <button id="add-shopping-cart">加入购物车</button>
                </div>
            </div>
            <div style="clear: both"></div>
        </div>
        <div class="commodity-content">
            ${requestScope.commodity.content}
<%--            <figure class="image"><img src="/images/upload/d0cfc97c-8c45-4cf6-a872-6b5ddb07d077.jpg"></figure>--%>
        </div>
    </div>
</div>
<footer>
    <div class="footer-top">

    </div>
    <p>©2019 ZhuBingkun 版权所有
        <a href="http://beian.miit.gov.cn">湘ICP备19020362号</a>
    </p>
    <div style="width:300px;margin:0 auto; padding:20px 0;">
        <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=43080202000405" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="/css/image/keep_on_record_icon.png" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">湘公网安备 43080202000405号</p></a>
    </div>
</footer>
</body>
</html>