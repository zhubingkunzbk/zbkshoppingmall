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
    <script src="./lib/ckeditor5-build-classic/ckeditor.js"></script>
    <link rel="shortcut icon" href="./css/image/favicon.ico" />
    <link rel="stylesheet" href="./lib/bootstrap-3.4.1-dist/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="./assert/font_3pzoh1xs3x9/iconfont.css" type="text/css">
    <link rel="stylesheet" href="./css/header.css" type="text/css">
    <link rel="stylesheet" href="./css/storeManagement.css" type="text/css">
    <script src="./js/header.js"></script>
    <script src="./js/storeManagement.js"></script>
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
        <div><a href="/"><img src="./css/image/index_icon.png" height="160px" width="230px"></a></div>
        <div>StoreManagement</div>
    </div>
</div>
<div class="core-div">
    <div class="center">
        <nav class="left-nav">
            <ul>
                <li class="can-not-select">店铺管理</li>
                <li class="checked can-not-select">我的商品</li>
                <li class="can-not-select">上架商品</li>
                <li class="can-not-select">我的交易订单</li>
            </ul>
        </nav>
        <div class="area-div">
            <div id="myCommodities">
                <ul>
                    <li>
                        <div><img src="./css/image/default_show_img.png" alt=""></div>
                        <div><a href="">我是一个闲置物品啦啦啦啦啦啦</a></div>
                        <div>
                            <button class="btn">删除</button>
                            <button class="btn">修改</button>
                        </div>
                    </li>
                    <li>
                        <div></div>
                    </li>
                    <li>
                        <div></div>
                    </li>
                    <li>
                        <div></div>
                    </li>
                    <li>
                        <div></div>
                    </li>
                    <li>
                        <div></div>
                    </li>
                    <li>
                        <div></div>
                    </li>
                    <li>
                        <div></div>
                    </li>
                    <li>
                        <div></div>
                    </li>
                    <div style="clear: both"></div>
                </ul>
            </div>
            <div id="addCommodity" style="display: none">
                <form action="">
                    <div><label>商品标题：</label><input type="text"></div>
                    <div><label>商品描述：</label><input type="text"></div>
                    <div><label>库存：</label><input type="text"></div>
                    <div><label>价格：</label><input type="text"></div>
                    <div>
                        <lable>请选择分类：</lable>
                        <select name="classificationSelect" id="classificationSelect"></select>
                    </div>
                    <div>
                        <lable>请选择二级分类：</lable>
                        <select name="classificationSecondSelect" id="classificationSecondSelect">
                            <option value="-1">不选择二级分类</option>
                        </select>
                    </div>
                    <div><label>展示图：</label><input type="file" style="display: inline" accept="*.jpg, *.png"></div>
                    <div style="clear: both"></div>
                </form>
                <textarea name="content" id="editor" cols="30" rows="10"></textarea>

                <div>
                    <button class="btn">预览</button>
                    <button class="btn">上架</button>
                </div>
            </div>
            <div id="myOrder" style="display: none"></div>
        </div>
        <div style="clear: both"></div>
    </div>
</div>

</body>
<div class="modal fade" id="preview" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">

    </div>
</div>
</html>