$(function () {
    carousel();
    topCommodityImageShow();
    commodityImage();
    bindAction();
    initClassification();
});
var domainName = "http://localhost/";
function getCookie(NameOfCookie) {
    if (document.cookie.length > 0) {
        begin = document.cookie.indexOf(NameOfCookie + "=");
        if (begin !== -1) {
            begin += NameOfCookie.length + 1;
            end = document.cookie.indexOf(";", begin);
            if (end === -1) end = document.cookie.length;
            return unescape(document.cookie.substring(begin, end));
        }
    }
    return null;
}
function initClassification() {
    $.ajax({
        url: domainName + "classifications/getParentClassification",
        type: "GET",
        dataType: "json",
        success: function (response) {
            var keys = Object.keys(response);
            var list = $(".classification-div > ul > li > a").toArray();
            var detailList = $(".classification-div > ul > li > .classification-detail > div");
            for(var i = 0;i < list.length;i++){
                    $(list[i]).text(response[keys[i]]);
                    $(list[i]).prop("href",domainName + "commodities/getCommoditiesByClassification?classificationId=" + keys[i] + "&pageNumber=0");
                    $(detailList[i]).children("p").children("span:first-child").text(response[keys[i]]);
                (function (i) {
                    $.ajax({
                        url: domainName + "classifications/getClassificationByParent",
                        type: "GET",
                        dataType: "json",
                        async: true,
                        data: {
                            parentId: keys[i]
                        },
                        success: function (res) {
                            var childKeys = Object.keys(res);
                            $(detailList[i]).children("a").remove();
                            for(var j = 0;j < childKeys.length;j++){
                                $(detailList[i]).append('<a href="' + domainName + "commodities/getCommoditiesByClassification?classificationId=" + childKeys[j] + "&pageNumber=0" + '">'+ res[childKeys[j]] +'</a>');
                            }
                        }
                    });
                })(i);

            }
        }
    });
}
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
function getIdentifyingCode() {
    $("#identifyingCode > div > img").remove();
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET",domainName + "user/identifyingCode",true);
    xmlhttp.responseType = "blob";
    xmlhttp.onload = function(){
        if (this.status == 200) {
            var blob = this.response;
            var img = document.createElement("img");
            img.onload = function(e) {
                window.URL.revokeObjectURL(img.src);
            };
            img.src = window.URL.createObjectURL(blob);
            $("#identifyingCode > div").append(img);
        }
    };
    xmlhttp.send();
}
function bindAction(){
    //注册窗口被打开时
    $("body > div.core-div > div > ul > li:nth-child(2) > div > div.right > div.user-info > button:nth-child(4)").click(function () {
        $("#registerModal input[type='text'],#registerModal input[type='password']").val("");
        getIdentifyingCode();
    });

    //验证码图片被点击
    $("#identifyingCode > div").click(getIdentifyingCode);

    //登录框中的任何按钮被点击都清空
    $("#loginModal button").click(function () {
        $("#loginMessage > span").text(" ");
    });

    //注册窗口中的任何按钮被点击都清空
    $("#registerModal button").click(function () {
        $("#registerMessage > span").text(" ");
    });


    //点击登陆按钮时
    $("#loginButton").click(function () {
        var accountNumber = $("#loginModal > .modal-dialog > .modal-content > .modal-body input[type='text']").val();
        var password = $("#loginModal > .modal-dialog > .modal-content > .modal-body input[type='password']").val();
        if(accountNumber.trim().length <= 0 || password.trim().length <= 0){
            $("#loginMessage span").text("请输入用户名和密码");
            return
        }
        $.ajax({
            type: "POST",
            url: domainName + "user/login",
            dataType: "json",
            data: {
                "accountNumber":accountNumber,
                "password":password
            },
            success: function (data) {
                if(data == "ACCOUNT_NUMBER_OR_PASSWORD_ERROR"){
                    $("#loginMessage > span").text("用户名或密码错，请重新登录");
                }else{
                    $("#loginMessage > span").text("登陆成功");
                    initLogin();
                    //两秒后关闭模态窗
                    var time = setInterval(function () {
                        $('#loginModal').modal('hide');
                        clearInterval(time);
                    },2000);
                }
            }
        });

    });

    //登录窗口其中任意一个input对焦时清空信息
    $("#loginModal input").focus(function () {
        $("#loginMessage > span").text(" ");
    });
    //注册窗口其中任意一个input对焦时清空信息
    $("#registerModal input").focus(function () {
        $("#registerMessage > span").text(" ");
    });

    //点击注册按钮时
    $("#registerButton").click(function () {
        var accountNumber = $("#registerModal > div > div > div.modal-body > form > div:nth-child(1) > input").val();
        var password = $("#registerModal > div > div > div.modal-body > form > div:nth-child(2) > input").val();
        var userName = $("#registerModal > div > div > div.modal-body > form > div:nth-child(3) > input").val();
        var eMail = $("#registerModal > div > div > div.modal-body > form > div:nth-child(4) > input").val();
        var phoneNumber = $("#registerModal > div > div > div.modal-body > form > div:nth-child(5) > input").val();
        var gender = $("#registerModal > div > div > div.modal-body > form > div:nth-child(6) input[name='gender']:checked").val();
        var code = $("#registerModal > div > div > div.modal-body > form > div:nth-child(7) > input").val();
        if(accountNumber == "" || password == "" || userName == ""){
            $("#registerMessage > span").text("账号/用户名/密码 不可省略");
            return;
        }
        if(code.trim() == ""){
            $("#registerMessage > span").text("请输入验证码");
            return;
        }
        console.log(gender);
        $.ajax({
            type: "POST",
            url: domainName + "user/register",
            dataType: "json",
            data: {
                "accountNumber":accountNumber,
                "password":password,
                "userName": userName,
                "eMail": eMail,
                "phoneNumber": phoneNumber,
                "gender": gender,
                "code": code
            },
            success: function (data) {
                if(data == "ACCOUNT_NUMBER_EXIST"){
                    $("#registerMessage > span").text("账号已存在");
                }else if(data == "REGISTER_SUCCESS"){
                    $("#registerMessage > span").text("注册成功");
                    var time = setInterval(function () {
                        $('#registerModal').modal('hide');
                        $('#loginModal').modal('show');
                        clearInterval(time);
                    },2000);
                }else if(data == "ANOTHER_ERROR"){
                    $("#registerMessage > span").text("服务器繁忙");
                }else if(data == "IDENTIFYING_CODE_ERROR"){
                    $("#registerMessage > span").text("验证码错误");
                    getIdentifyingCode();
                }
            }
        });

    });

    $("body > div.core-div > div > ul > li:nth-child(2) > div > div.right > div.user-info > button:last-child").click(function () {
        var text = $(this).text();
        console.log(text);
        if(text == "我的店铺"){
            window.location.href=domainName + "storeManagement.jsp";
        }else if(text == "开 店"){
            alert("请先登录");
        }else{
            $("#agreementModal").modal('show');
        }
    });

    $("#agree").click(function () {
        $.ajax({
            url: "user/changeToSeller",
            dataType: "json",
            type: "GET",
            success: function (res) {
                console.log(res.msg);
                if(res.msg == "SUCCESS"){
                    $("#agreementModal").modal('hide');
                    $("body > div.core-div > div > ul > li:nth-child(2) > div > div.right > div.user-info > button:last-child").text("我的店铺");
                }else{
                    alert("服务器繁忙，请稍后再试");
                }
            }
        });
    });
}

