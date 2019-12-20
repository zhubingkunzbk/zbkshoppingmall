<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%--<%@page import="com.shoppingmall.entity.Commodity" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="shortcut icon" href="/css/image/favicon.ico" />
    <link rel="stylesheet" href="/css/header.css" type="text/css">
    <link rel="stylesheet" href="/css/commodities.css" type="text/css">
    <link rel="stylesheet" href="/assert/font_3pzoh1xs3x9/iconfont.css" type="text/css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/header.js"></script>
    <script src="/js/commodities.js"></script>
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
    <div class="fixed-top">
        <div class="center">
            <div><img src="/css/image/index_icon.png" alt="" style="height: 50px"></div>

            <div class="search-div">
                <div class="select-option">
                    <ul>
                        <span>商品</span><i class="iconfont">&#xe69b;</i>
                        <li>商品</li>
                        <li>店铺</li>
                        <div style="clear: both"></div>
                    </ul>
                </div>
                <form action="http://localhost/commodities/searchCommodity" method="get">
                    <div><input type="text" name="key"><input type="submit" value="搜    索"></div>
                </form>
            </div>
        </div>
    </div>
    <div class="center core-div">
            <img src="/css/image/no_commodities.png" alt="">
<%--            <div><img src="/css/image/ware1.png" alt="">--%>
<%--                <div><span class="price">￥485.00</span><span>76人付款</span></div>--%>
<%--                <div class="title"><a href="">新款CDG川久保玲PLAY长袖男女红心T袖情侣宽松休闲爱心纯棉打底衫</a></div>--%>
<%--                <div class="seller-info"><span class="seller-name">小吉利小屋</span><span class="seller-address">日本</span><div style="clear: both"></div></div>--%>
<%--            </div>--%>
<%--            <div><img src="/css/image/ware2.png" alt="">--%>
<%--                <div><span class="price">￥49.00</span><span>1.5万+人付款</span></div>--%>
<%--                <div class="title"><a href="">基础款磨毛加厚纯色半高领打底衫内搭上衣女秋冬修身纯色长袖T恤</a></div>--%>
<%--                <div class="seller-info"><span class="seller-name">3_wenjing2007</span><span class="seller-address">浙江 宁波</span><div style="clear: both"></div></div>--%>
<%--            </div><div><img src="/css/image/ware3.png" alt="">--%>
<%--            <div><span class="price">￥158.00</span><span>1036人付款</span></div>--%>
<%--            <div class="title"><a href="">黑色打底衫女秋冬修身百搭高领加绒加厚体恤上衣女秋纯棉长袖t恤</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">欧韩街区</span><span class="seller-address">广东 东莞</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware4.png" alt="">--%>
<%--            <div><span class="price">￥99.00</span><span>8000+人付款</span></div>--%>
<%--            <div class="title"><a href="">钱夫人 修身破洞长袖打底衫女秋冬装2019新款洋气内搭t恤紧身上衣</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">钱夫人209</span><span class="seller-address">浙江 杭州</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware5.png" alt="">--%>
<%--            <div><span class="price">￥368.00</span><span>316人付款</span></div>--%>
<%--            <div class="title"><a href="">显胸大长袖T恤女秋季2019新款韩版内搭纯色打底衫性感紧身上衣薄</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">金牌码头</span><span class="seller-address">福建 福州</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware6.png" alt="">--%>
<%--            <div><span class="price">￥108</span><span>1290人付款</span></div>--%>
<%--            <div class="title"><a href="">XINN t恤女小可爱简约气质2019秋季破洞oversize长袖t恤薄中长款</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">鹿儿thedeer</span><span class="seller-address">浙江 杭州</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware7.png" alt="">--%>
<%--            <div><span class="price">￥59.00</span><span>5000+人付款</span></div>--%>
<%--            <div class="title"><a href="">EKOOL黑色高领打底衫女秋冬新款修身百搭长袖t恤内搭紧身显瘦上衣</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">郎沈剑</span><span class="seller-address">浙江 宁波</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware8.png" alt="">--%>
<%--            <div><span class="price">￥185.00</span><span>100人付款</span></div>--%>
<%--            <div class="title"><a href="">韩国2019秋冬季新款半高领加厚打底衫女 韩版内搭T恤修身长袖上衣</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">chenminxiang</span><span class="seller-address">上海</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware9.png" alt="">--%>
<%--            <div><span class="price">￥69.00</span><span>3236人付款</span></div>--%>
<%--            <div class="title"><a href="">欧货加厚打底衫女高领内搭修身上衣2019秋冬新款洋气羊绒长袖T恤</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">吉品铺</span><span class="seller-address">浙江 杭州</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware10.png" alt="">--%>
<%--            <div><span class="price">￥94.05</span><span>5000+人付款</span></div>--%>
<%--            <div class="title"><a href="">林珊珊 高领羊毛内搭打底衫女秋冬圆领长袖t恤2019网红春装上衣潮</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">珊珊宝贝0332008</span><span class="seller-address">浙江 杭州</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware11.png" alt="">--%>
<%--            <div><span class="price">￥69.00</span><span>2316人付款</span></div>--%>
<%--            <div class="title"><a href="">JU好穿舒服自留推荐 修身显瘦百搭磨毛堆堆领长袖T恤纯色打底衫女</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">zhuwenjuan1991</span><span class="seller-address">江苏 南京</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware12.png" alt="">--%>
<%--            <div><span class="price">￥89.00</span><span>964人付款</span></div>--%>
<%--            <div class="title"><a href="">打底衫女2019洋气白色内搭设计感破洞长袖T恤宽松薄款打底体恤潮</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">笨笨的店店</span><span class="seller-address">浙江 杭州</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware13.png" alt="">--%>
<%--            <div><span class="price">￥378.00</span><span>119人付款</span></div>--%>
<%--            <div class="title"><a href="">半高领打底衫女秋冬2019新款紧身上衣修身内搭ins超火百搭长袖t恤</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">金牌码头</span><span class="seller-address">福建 福州</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware14.png" alt="">--%>
<%--            <div><span class="price">￥49.80</span><span>1502人付款</span></div>--%>
<%--            <div class="title"><a href="">网纱半高领加绒打底衫长袖t恤女2019秋冬新洋气显瘦内搭条纹上衣</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">morgy01</span><span class="seller-address">陕西 西安</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware15.png" alt="">--%>
<%--            <div><span class="price">￥289.00</span><span>330人付款</span></div>--%>
<%--            <div class="title"><a href="">长袖t恤女秋冬2019韩版纯色半高领上衣显瘦莫代尔加厚加绒打底衫</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">hb13692057687</span><span class="seller-address">广东 中山</span><div style="clear: both"></div></div>--%>
<%--        </div><div><img src="/css/image/ware16.png" alt="">--%>
<%--            <div><span class="price">￥79.90</span><span>3578人付款</span></div>--%>
<%--            <div class="title"><a href="">李婉君修身长袖T恤女撞色拼接内搭打底衫2019早秋港味显瘦上衣</a></div>--%>
<%--            <div class="seller-info"><span class="seller-name">153321450lxj</span><span class="seller-address">广东 广州</span><div style="clear: both"></div></div>--%>
<%--        </div>--%>
    </div>
    <footer>
        <div class="footer-top">

        </div>
        <p>©2019 Zhubingkun 版权所有</p>
    </footer>
</body>
</html>