<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>学生闲置物品信息</title>
    <script src="./js/jquery-3.4.1.min.js"></script>
    <script src="./lib/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="./css/image/favicon.ico" />
    <link rel="stylesheet" href="./lib/bootstrap-3.4.1-dist/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="./assert/font_3pzoh1xs3x9/iconfont.css" type="text/css">
    <link rel="stylesheet" href="./css/header.css" type="text/css">
    <link rel="stylesheet" href="./css/index.css" type="text/css">
    <script src="./js/header.js"></script>
    <script src="./js/index.js"></script>


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
                            <div class="portrait"><img src="./css/image/portrait.png" style="width: 80%;height: 80%;"></div><!--
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
            <div><a href="#"><img src="./css/image/index_icon.png" height="160px" width="230px"></a></div>
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
    <div class="mid-div">
        <div class="carousel center">
            <button>&lt;</button>
            <img src="./css/image/carousel1.jpg">
            <img src="./css/image/carousel2.jpg" style="display: none">
            <img src="./css/image/carousel3.jpg" style="display: none">
            <div class="bottom-circle">
            </div>
            <button>&gt;</button>
        </div>
    </div>
    <div class="core-div">
        <div class="center">
        	<ul>
        		<li>
        			<div class="classification-div">
		                <ul>
		                    <li class="title">商品分类</li>
		                    <li ><a href="./commodities.html"></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>
                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
		                    <li ><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
		                    <li ><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                </div>
                            </li>
                            <li><a href=""></a> / <a href=""></a> / <a href=""></a><i class="iconfont">&#xe6a2;</i>
                                <div class="classification-detail">
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>
                                        
                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>

                                    </div>
                                    <div>
                                        <p><span></span><span><a href="">更多<i class="iconfont">&#xe6a2;</i></a></span></p>
                                    </div>
                                </div>
                            </li>

		                </ul>
            		</div>
        		</li>
        		<li>
        			<nav class="nav">
        				<ul>
        					<li><a href="">天猫</a></li>
        					<li><a href="">聚划算</a></li>
        					<li><a href="">抢购</a></li>
        					<li><a href="">电器城</a></li>
        					<li><a href="">拍卖</a></li>
        					<li><a href="">用心选择</a></li>
        					<li><a href="">飞猪旅行</a></li>
        					<li><a href="">苏宁易购</a></li>
        					<div style="clear: both;"></div>
        				</ul>
        			</nav>
        			<div>
                        <div class="left">
                            <div class="top-commodity-image-div">
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                                <div class="top-commodity-image"><img src="" alt=""></div>
                            </div>
                            <div class="top-commodity">
                                <div>
                                    <img src="./css/image/topCommodity1.jpg" alt="">
                                </div>
                                <div>
                                    <img src="./css/image/topCommodity2.jpg" alt="">
                                </div>
                            </div>
                        </div>

                        <div class="right">
                            <div class="user-info">
                                <div class="portrait"><img src="./css/image/portrait.png" alt=""></div>
                                <div class="user-name">您好：<span>请登录</span></div>
                                <button class="btn" data-toggle="modal" data-target="#loginModal">登 录</button>
                                <button class="btn" data-toggle="modal" data-target="#registerModal">注 册</button>
                                <button class="btn">开 店</button>
                            </div>
                            <div><a href="#">网络有害信息举报专区</a></div>
                            <div class="other-information">
                                <div class="other-information-nav">
                                    <span class="select">公告</span>
                                    <span>规则</span>
                                    <span>安全</span>
                                    <span>公益</span>
                                    <span>微博头条</span>
                                </div>
                                <div class="other-information-title">
                                    <a href="">95公益周阿里、腾讯等六家公司同台联合做公益</a>
                                    <a href="">魔豆妈妈公益项目</a>
                                </div>
                            </div>
                        </div>
        			</div>
        		</li>
                <div style="clear: both"></div>
            </ul>
            <div class="bottom">
                <div class="bottom-top">
                    <div class="left">
                        <div class="title">
                            <div><img src="./css/image/good-commodity-title.png" alt=""></div>
                            <span>与品质生活不期而遇</span>
                        </div>
                        <div class="content">
                            <div class="commodity">
                                <div><img src="./css/image/commodity1.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">max破产都要买的口红</p>
                                <p class="description">《破产姐妹》max最爱</p>
                                <p class="fabulous iconfont">&#xe6a0;5960人说好</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity2.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">碎脂机，超快瘦身</p>
                                <p class="description">这个精美的碎脂机，不仅采用最新技术</p>
                                <p class="fabulous iconfont">&#xe6a0;130人说好</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity3.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">老铁，这是电吸门</p>
                                <p class="description">电吸门可以避免车门未关</p>
                                <p class="fabulous iconfont">&#xe6a0;1675人说好</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity4.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">窗台整理架，利用家中所有位置</p>
                                <p class="description">简单易操作的设计</p>
                                <p class="fabulous iconfont">&#xe6a0;338人说好</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity5.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">北岸多功能上下床</p>
                                <p class="description">充分的利用了房子的上下</p>
                                <p class="fabulous iconfont">&#xe6a0;18203人说好</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity6.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">SUSISU 幻境流沙眼影</p>
                                <p class="description">幻境流沙，超级美腻外包</p>
                                <p class="fabulous iconfont">&#xe6a0;287人说好</p>
                            </div>
                            <div style="clear: both"></div>
                        </div>
                    </div>
                    <div class="right">
                        <div class="title">
                            <div><img src="./css/image/shopping-title.png" alt=""></div>
                            <span>献给聪明可爱的你</span>
                        </div>
                        <div class="content">
                            <div class="commodity">
                                <div><img src="./css/image/commodity7.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">纯棉布艺爬爬垫</p>
                                <p class="description">评论用户：</p>
                                <p class="fabulous iconfont">妙***社</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity8.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">简约日式小清新 独特品味</p>
                                <p class="description">评论用户：</p>
                                <p class="fabulous iconfont">室***师</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity9.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">你的好友个性卫衣上线，温暖又时髦</p>
                                <p class="description">评论用户：</p>
                                <p class="fabulous iconfont">A***U</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity10.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">百褶小立领</p>
                                <p class="description">评论用户：</p>
                                <p class="fabulous iconfont">日***装</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity11.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">复古娃娃领格子衬衫</p>
                                <p class="description">评论用户：</p>
                                <p class="fabulous iconfont">日***装</p>
                            </div>
                            <div class="commodity">
                                <div><img src="./css/image/commodity12.png" alt="" class="image">
                                    <div></div>
                                </div>
                                <p class="commodity-title">冬季个性范儿必备的邮票衬衫</p>
                                <p class="description">评论用户：</p>
                                <p class="fabulous iconfont">做***建</p>
                            </div>
                            <div style="clear: both"></div>
                        </div>
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
                    <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=43080202000405" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="./css/image/keep_on_record_icon.png" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">湘公网安备 43080202000405号</p></a>
                </div>
            </footer>
        </div>


    </div>

    <div class="fixed-top">
        <div class="center">
            <div><img src="./css/image/index_icon.png" alt="" style="height: 50px"></div>

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
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title" id="myModalLabel">登 陆</h3>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="input-group">
                            <span class="input-group-addon iconfont">&#xe6a0;</span>
                            <input type="text" class="form-control" placeholder="账 号" aria-describedby="sizing-addon2">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon iconfont">&#xe69e;</span>
                            <input type="password" class="form-control" placeholder="密 码" aria-describedby="sizing-addon2">
                        </div>
                        <div id="loginMessage"><span> </span></div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn" data-dismiss="modal">取 消</button>
                    <button type="button" class="btn" id="loginButton">登 陆</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title" id="registerModalLabel">注 册</h3>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="input-group">
                            <span class="input-group-addon iconfont">&#xe6a0;</span>
                            <input type="text" class="form-control" placeholder="账 号" aria-describedby="sizing-addon2">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon iconfont">&#xe69e;</span>
                            <input type="password" class="form-control" placeholder="密 码" aria-describedby="sizing-addon2">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon iconfont">&#xe6a0;</span>
                            <input type="text" class="form-control" placeholder="用 户 名 / 昵 称" aria-describedby="sizing-addon2">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon iconfont">&#xe631;</span>
                            <input type="email" class="form-control" placeholder="邮 箱" aria-describedby="sizing-addon2">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon iconfont">&#xe8ad;</span>
                            <input type="text" class="form-control" placeholder="手 机 号" aria-describedby="sizing-addon2">
                        </div>
                        <div class="input-group">
                            <span><input type="radio" name="gender" value="female" checked>男</span>
                            <span><input type="radio" name="gender" value="male">女</span>
                        </div>
                        <div id="identifyingCode">
                            <div></div>
                            <input type="text" class="form-control">
                        </div>
                        <div id="registerMessage"><span> </span></div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn" data-dismiss="modal">取 消</button>
                    <button type="button" class="btn" id="registerButton">注 册</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <div class="modal fade" id="agreementModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title">开 店 协 议</h3>
                </div>
                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn" data-dismiss="modal">滚</button>
                    <button type="button" class="btn" id="agree" style="width: 200px">我已阅读并同意</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</body>
</html>