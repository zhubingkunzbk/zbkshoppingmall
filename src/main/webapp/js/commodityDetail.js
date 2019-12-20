$(function () {
   bindCommodityDetailAction();
});
var domainName = "http://localhost/";
function bindCommodityDetailAction() {

    $("#buy").click(function () {
        $.ajax({
            url: domainName + "user/isLogin",
            type: "GET",
            dataType: "json",
            success: function (response) {
                if(response == "NO_LOGIN"){
                    alert("请先登录");
                    window.location.href="/";
                    return;
                }
                //购买
                var commodityId = $("#id > label").text();
                var userAccountNumber = response.data.accountNumber;
                var price = Number($("body > div.core-div > div > div.commodity-describe > div.right > div.price > span:nth-child(2)").text());
                var num = Number($("body > div.core-div > div > div.commodity-describe > div.right > div.operate-number > input[type=text]").val());
                var sumPrice = price * num;
                //开始创建订单
                $.ajax({
                    url: domainName + "order/createOrder",
                    type: "POST",
                    dataType: "json",
                    data: {
                        sumPrice: sumPrice
                    },
                    success: function (res1) {
                        //在创建订单成功后开始填充订单详情
                        if(res1.code == 0) {
                            $.ajax({
                                url: domainName + "order/createOrderDetail",
                                type: "POST",
                                dataType: "json",
                                data: {
                                    orderId: res1.data,
                                    number: num,
                                    commodityId: commodityId
                                },
                                success: function (res2) {
                                    //订单详情创建成功后开始支付
                                    console.log(res2);
                                    $.ajax({
                                        url: domainName + "order/pay",
                                        type: "POST",
                                        data: {
                                            orderId: res1.data
                                        },
                                        success: function (responseHTML) {
                                            $("body").empty();
                                            $("body").append(responseHTML);
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
            }
        });
    });

    $("#add-shopping-cart").click(function () {
        $.ajax({
            url: domainName + "user/isLogin",
            type: "GET",
            dataType: "json",
            success: function (response) {
                if(response == "NO_LOGIN"){
                    alert("请先登录");
                    window.location.href="/";
                    return;
                }
                //添加购物车
                var commodityId = $("#id > label").text();
                var number = $("body > div.core-div > div > div.commodity-describe > div.right > div.operate-number > input[type=text]").val();
                $.ajax({
                    url: domainName + "shoppingCart/insertIntoShoppingCart",
                    type: "POST",
                    data: {
                        commodityId: commodityId,
                        number: number
                    },
                    success: function (res) {
                        if(res == "SUCCESS"){
                            alert("添加成功");
                        }else{
                            alert("服务器繁忙，请稍后再试");
                        }

                    }
                });
            }
        });
    });

    //数量输入框的内容改变时
    $("body > div.core-div > div > div.commodity-describe > div.right > div.operate-number > input[type=text]").bind("input propertychange",function () {
        if(!isRealNum($(this).val())){
            $(this).val(1);
        }
    });
    //点击+号
    $("body > div.core-div > div > div.commodity-describe > div.right > div.operate-number > div > input[type=button]:nth-child(1)").click(function () {
        var num = $("body > div.core-div > div > div.commodity-describe > div.right > div.operate-number > input[type=text]").val();
        num++;
        $("body > div.core-div > div > div.commodity-describe > div.right > div.operate-number > input[type=text]").val(num);
    });
    //点击-号
    $("body > div.core-div > div > div.commodity-describe > div.right > div.operate-number > div > input[type=button]:nth-child(2)").click(function () {
        var num = $("body > div.core-div > div > div.commodity-describe > div.right > div.operate-number > input[type=text]").val();
        num--;
        if(num <= 0){
            num = 1;
        }
        $("body > div.core-div > div > div.commodity-describe > div.right > div.operate-number > input[type=text]").val(num);
    });
}
function isRealNum(val){
    if(val === "" || val ==null){
        return false;
    }
    if(!isNaN(val)){
        return true;
    }
    else{
        return false;
    }
}