$(function () {
    carousel();
    topCommodityImageShow();
    topFixedSearch();
    commodityImage();
    bindAction();
});
var selectType = "商品";
function commodityImage() {
    $(".core-div > .center > .bottom > .bottom-top .content > .commodity").hover(function () {
        $(this).children(".commodity-title").css("color","#2BACA9");
        $(this).children().children("div").stop(false,true);
        $(this).children().children("div").fadeIn(200);
        },function () {
        $(this).children(".commodity-title").css("color","#000");
        $(this).children().children("div").stop(false,true);
        $(this).children().children("div").fadeOut(200);
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
        $(".fixed-top > .center > .search-div > .select-option > ul > span").text(text);
        $(".fixed-top > .center > .search-div > .select-option > ul > li").css("display","none");
    });
}
function carousel() {
    var nowIndex = 0;
    var nextIndex = 1;
    var imageListLength = $(".mid-div > .carousel").children("img").length;
    for(var i = 0;i < imageListLength;i++){
        $(".mid-div > .carousel > .bottom-circle").append("<button></button>");
    }
    $(".mid-div > .carousel > .bottom-circle :first-child").addClass("now-carousel-circle");
    var fun = function () {
        var $now = $(".mid-div > .carousel").children("img").eq(nowIndex);
        var $next = $(".mid-div > .carousel").children("img").eq(nextIndex);
        $now.fadeOut(1000);
        $next.fadeIn(1000);
        //已经变换完成，更换变量内容
        nowIndex = nextIndex;
        $(".mid-div > .carousel > .bottom-circle").children("button").eq(nowIndex).addClass("now-carousel-circle");
        $(".mid-div > .carousel > .bottom-circle").children("button").eq(nowIndex).siblings().removeClass("now-carousel-circle");
        nextIndex = (nowIndex + 1) % (imageListLength);

    };
    var time = setInterval(fun,7000);
    $(".mid-div > .carousel > :first-child").click(function () {
        // $(".mid-div > .carousel").children("img").eq(nowIndex).stop(false,true);
        // $(".mid-div > .carousel").children("img").eq(nextIndex).stop(false,true);
        $(".mid-div > .carousel").children("img").stop(false,true);
        nextIndex = nowIndex - 1;
        if(nextIndex < 0){
            nextIndex = imageListLength - 1;
        }
        window.clearInterval(time);
        fun();
        time = setInterval(fun,7000);
    });
    $(".mid-div > .carousel > :last-child").click(function () {
        // $(".mid-div > .carousel").children("img").eq(nowIndex).stop(false,true);
        // $(".mid-div > .carousel").children("img").eq(nextIndex).stop(false,true);
        $(".mid-div > .carousel").children("img").stop(false,true);
        nextIndex = (nowIndex + 1) % imageListLength;
        window.clearInterval(time);
        fun();
        time = setInterval(fun,7000);
    });
    $(".mid-div > .carousel > .bottom-circle > button").hover(function (event) {
        $(this).addClass("now-carousel-circle");
        $(this).siblings().removeClass("now-carousel-circle");
        var index = $(".mid-div > .carousel > .bottom-circle > button").index(this);
        if(index == nowIndex){
            return;
        }
        $(".mid-div > .carousel").children("img").stop(false,true);
        nextIndex = index;
        window.clearInterval(time);
        fun();
        time = setInterval(fun,7000);
    },function () {

    });
    $(".mid-div > .carousel > img").hover(function () {
        $(this).css("cursor","pointer");
        window.clearInterval(time);
    },function () {
        $(this).css("cursor","default");
        time = setInterval(fun,7000);
    });
}
function topCommodityImageShow() {
    $topCommodityImageList = $(".core-div > .center > ul > :nth-child(2) > div > .left > .top-commodity-image-div > .top-commodity-image").children("img");
    for(var i = 1;i <= $topCommodityImageList.length;i++){
        $topCommodityImageList[i - 1].src = "./css/image/top-commodity-"+i+".png";
    }
}
function bindAction(){
    $(".top-div > .center > .search-div > ul > li > button").click(function () {
        $(this).addClass("selection");
        $(this).parent().siblings("li").children("button").removeClass("selection");
        selectType = $(this).text();
    });

}