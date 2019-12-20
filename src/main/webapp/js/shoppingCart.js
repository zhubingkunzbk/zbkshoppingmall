$(function () {
    var sumPrice = 0;
    var list = $(".core-div > div:nth-child(1) > ul > li > div:nth-child(4) > p");
    for(var i = 0;i < list.length;i++)
    {
        var price = Number($(list[i]).text().substring(1));
        sumPrice += price;
    }

    //点击结算
    $("body > div.core-div > .center:nth-child(2) > div:nth-child(1) > input[type='button']").click(function () {
        $.ajax({
            url: domainName + "order/createOrder",
            type: "POST",
            dataType: "json",
            data: {
                sumPrice: sumPrice
            },
            success: function (createOrderResponse) {
                if(createOrderResponse.code == 0) {
                    var aList = $(".core-div > .center > ul > li > div:nth-child(2) > a");
                    for(var i = 0;i < aList.length;i++) {
                        (function (i) {
                            var num = $(aList[i]).parent().next().children("input[type='text']").val();
                            var commodityId = $(aList[i]).prop("href").split("=")[1];
                            console.log(num);
                            console.log(commodityId);
                            $.ajax({
                                url: domainName + "order/createOrderDetail",
                                type: "POST",
                                dataType: "json",
                                data: {
                                    orderId: createOrderResponse.data,
                                    number: num,
                                    commodityId: commodityId
                                },
                                success: function (res2) {
                                }
                            });
                        })(i);
                    }
                    $.ajax({
                        url: domainName + "order/pay",
                        type: "POST",
                        data: {
                            orderId: createOrderResponse.data
                        },
                        success: function (responseHTML) {
                            $("body").empty();
                            $("body").append(responseHTML);
                        }
                    });
                }
            }
        });
    });





    $("body > div.core-div > div:nth-child(2) > div:nth-child(2) > p").append("<span>共计：￥</span>");
    $("body > div.core-div > div:nth-child(2) > div:nth-child(2) > p").append(sumPrice);
    $("body > div.core-div > div:nth-child(1) > ul > li > div > input[type=text]").bind("input propertychange",function () {
        if(!isRealNum($(this).val())){
            $(this).val(1);
        }
    });

    $("body > div.core-div > div:nth-child(1) > ul > li > div > input[type=button]:nth-child(4)").click(function () {
        $(this).prev().val(Number($(this).prev().val()) + 1);
        sumPrice = 0;
        var list = $(".core-div > div:nth-child(1) > ul > li > div:nth-child(4) > p");
        for(var i = 0;i < list.length;i++)
        {
            var price = Number($(list[i]).text().substring(1));
            var num = Number($(list[i]).parent().prev().children("input[type='text']").val());
            console.log(num);
            sumPrice += (price * num);
        }
        $("body > div.core-div > div:nth-child(2) > div:nth-child(2) > p").empty();
        $("body > div.core-div > div:nth-child(2) > div:nth-child(2) > p").append("<span>共计：￥</span>");
        $("body > div.core-div > div:nth-child(2) > div:nth-child(2) > p").append(sumPrice);
    });

    $("body > div.core-div > div:nth-child(1) > ul > li > div > input[type=button]:nth-child(2)").click(function () {
        if(Number($(this).next().val()) <= 1){
            return;
        }
        $(this).next().val(Number($(this).next().val()) - 1);
        sumPrice = 0;
        var list = $(".core-div > div:nth-child(1) > ul > li > div:nth-child(4) > p");
        for(var i = 0;i < list.length;i++)
        {
            var price = Number($(list[i]).text().substring(1));
            var num = Number($(list[i]).parent().prev().children("input[type='text']").val());
            console.log(num);
            sumPrice += (price * num);
        }
        $("body > div.core-div > div:nth-child(2) > div:nth-child(2) > p").empty();
        $("body > div.core-div > div:nth-child(2) > div:nth-child(2) > p").append("<span>共计：￥</span>");
        $("body > div.core-div > div:nth-child(2) > div:nth-child(2) > p").append(sumPrice);

    });

});
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