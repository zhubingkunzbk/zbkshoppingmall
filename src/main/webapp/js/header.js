$(function () {
    getHotKey();
    initLogin();
    topFixedSearch();
    bindHeaderAction();
    associationSearch();
});
var selectType = "商品";
var fixedSelectType = "商品";
var inUlFlag = false;
var domainName = "http://localhost/";
function initLogin() {
    $.ajax({
        url: domainName + "user/isLogin",
        type: "GET",
        dataType: "json",
        success: function (response) {
            if(response == "NO_LOGIN"){
                return;
            }
            $("body > div.core-div > div > ul > li:nth-child(2) > div > div.right > div.user-info > div.user-name > span").text(response.data.userName);
            $("body > div.core-div > div > ul > li:nth-child(2) > div > div.right > div.user-info > button:nth-child(3)").remove();
            $("#username").text(response.data.userName);
            if(response.data.isBusiness == true){
                $("body > div.core-div > div > ul > li:nth-child(2) > div > div.right > div.user-info > button:last-child").text("我的店铺");
            }else{
                $("body > div.core-div > div > ul > li:nth-child(2) > div > div.right > div.user-info > button:last-child").text("我想开店");

            }
        }
    });
}
function getHotKey() {
    $.ajax({
        type: "GET",
        url: domainName + "commodities/getHotKey",
        success: function (res) {
            var aList = $(".center > .search-div > .hot-search > a");
            if(aList == null || aList.length < 1){
                return;
            }
            var response = JSON.parse(res);
            for(var i = 0;i <response.data.length;i++){
                aList[i].text = response.data[i];
            }
        }
    });
}
function topFixedSearch() {
    $(window).scroll(function(){
        if($(window).scrollTop() > 200){
            $(".fixed-top").css("display","block");
        }else{
            $(".fixed-top").css("display","none");
        }
    });
    $(".fixed-top > .center > .search-div > .select-option > ul").hover(function () {
        $(".fixed-top > .center > .search-div > .select-option > ul > li").css("display","block");
    },function () {
        $(".fixed-top > .center > .search-div > .select-option > ul > li").css("display","none");
    });
    $(".fixed-top > .center > .search-div > .select-option > ul > li").click(function () {
        var text = $(this).text();
        fixedSelectType = text;
        $(".fixed-top > .center > .search-div > .select-option > ul > span").text(text);
        $(".fixed-top > .center > .search-div > .select-option > ul > li").css("display","none");
    });
}
function bindHeaderAction() {

    $("body > header > div > div.left > ul > li:nth-child(2)").hover(function () {
        if($("#username").text() == "请登录"){
            $(this).children(".level-2-menu").css("display","none");
        }else{
            $(this).children(".level-2-menu").css("display","block");
        }
    },function () {
        $(this).children(".level-2-menu").css("display","none");
    });

    //点击退出登陆时
    $("#personal-info > div.operator > div > a:nth-child(3)").click(function () {
        $.ajax({
            url: domainName + "user/logout",
            type: "GET",
            success: function () {
                window.location.href = "/";
            }
        });
    });
    //点击热搜词时
    $(".center > .search-div > .hot-search > a").click(function () {
        var text = $(this).text();
        $("body > div.top-div > div > div.search-div > form > div > input[type=text]:nth-child(1)").val(text);
    });
    //切换商品/店铺
    $(".top-div > .center > .search-div > ul > li > button").click(function () {
        $(this).addClass("selection");
        $(this).parent().siblings("li").children("button").removeClass("selection");
        selectType = $(this).text();
    });

    //搜索框中文本发生变化时
    $("body > div.top-div > div > div.search-div > form > div > input[type=text]:nth-child(1)").bind("input propertychange",associationSearch);


    //对于动态生成的联想词绑定事件
    $("#associationSearch > ul").on("click",".resultText",function () {
        var text = $(this).text();
        $("body > div.top-div > div > div.search-div > form > div > input[type=text]:nth-child(1)").val(text);
        $("#associationSearch").css("display","none");
    });

    //搜索框中有内容又重新获得焦点时
    $("body > div.top-div > div > div.search-div > form > div > input[type=text]:nth-child(1)").focus(function () {
        if($(this).val().length >= 1){
            associationSearch();
        }
    });

    //搜索框失去焦点时
    $("body > div.top-div > div > div.search-div > form > div > input[type=text]:nth-child(1)").blur(function () {
        //如果当前鼠标不在联想词框中
        if(!inUlFlag) {
            $("#associationSearch > ul > li").remove();
            $("#associationSearch").css("display", "none");
        }
    });
    //鼠标进入联想词框
    $("#associationSearch > ul").mouseenter(function () {inUlFlag = true;});
    //鼠标离开联想词框
    $("#associationSearch > ul").mouseleave(function () {inUlFlag = false;});

    //搜索按钮被点击时
    $(".center > .search-div > form > div > input[type='submit']").click(function () {
        // var searchKey = $(this).siblings("input[type='text']").val();
        // $.ajax({
        //     type: "GET",
        //     url: domainName + "commodities/searchCommodity",
        //     dataType: "json",
        //     data: {
        //         "key": searchKey
        //     }
        // });
    });
}
//请求联想词
function associationSearch() {
    var text = $("body > div.top-div > div > div.search-div > form > div > input[type=text]:nth-child(1)").val();
    $.ajax({
        type: "GET",
        url: domainName + "commodities/getSearchText",
        dataType: "json",
        data: {
            "text": text
        },
        success: function (res) {
            if (text && text.length >= 1) {
                $("#associationSearch").css("display", "block");
            } else {
                return;
            }
            $("#associationSearch > ul > li").remove();
            for (var i = 0; i < res.data.length; i++) {
                var text1 = res.data[i];
                $("#associationSearch > ul").append("<li class='resultText'>" + text1 + "</li>");
            }
        }
    });
}