var myEditor;
var domainName = "http://localhost/";
var showImgPath;
var classificationId;
$(function () {
    $.ajax({
        url: domainName + "user/isLogin",
        type: "GET",
        dataType: "json",
        success: function (response) {
            if(response == "NO_LOGIN"){
                window.location.href = "/";
                return;
            }
            if(response.data.isBusiness == true){
                initMyCommodities();
            }else{
                window.location.href = "/";
            }
        }
    });
   bindStoreManagementActive();
   ckEditor();
});

function initMyCommodities() {
    $.ajax({
        url: domainName + "commodities/getCommoditiesByAccountNumber",
        type: "GET",
        dataType: "json",
        success: function (res) {
            $("#myCommodities > ul").empty();
            for(var i = 0;i < res.data.length;i++){
                var ele = '<li>\n' +
                    '                        <div><img src="' + res.data[i].showImgSrc + '" alt=""></div>\n' +
                    '                        <div><a href="' + domainName + "commodities/getCommodityDetail?commodityId=" + res.data[i].id + '">' + res.data[i].title + '</a></div>\n' +
                    '                        <div>\n' +
                    '                            <button class="btn">删除</button>\n' +
                    '                            <button class="btn">修改</button>\n' +
                    '                        </div>\n' +
                    '                    </li>';
                $("#myCommodities > ul").append(ele);
            }
            $("#myCommodities > ul").append('<div style="clear: both"></div>');
        }
    });
}
function bindStoreManagementActive() {
    $("#classificationSecondSelect").change(function () {
        var val = $(this).val();
        if(val == -1){
            classificationId = $("#classificationSelect").val();
            return;
        }
        classificationId = val;

    });
    //一级分类改变时
    $("#classificationSelect").change(function () {
        var val = $("#classificationSelect").val();
        classificationId = val;
        $.ajax({
            url: domainName + "classifications/getClassificationByParent",
            type: "GET",
            dataType: "json",
            data: {
                parentId: val
            },
            success: function (res) {
                console.log(res);
                var keys = Object.keys(res);
                $("#classificationSecondSelect").empty();
                $("#classificationSecondSelect").append('<option value="-1">不选择二级分类</option>');
                for(var i = 0;i < keys.length;i++){
                    var id = keys[i];
                    var name = res[keys[i]];
                    $("#classificationSecondSelect").append('<option value =' + id + '>' + name +'</option>');
                }
            }
        });
    });

    //点击我的商品列表
    $(".core-div > div > nav > ul > li:nth-child(2)").click(function () {
        $("#myCommodities").css("display", "block");
        $("#myCommodities").siblings().css("display", "none");
        $(this).addClass("checked");
        $(this).siblings().removeClass("checked");
    });
    //点击上架商品
    $(".core-div > div > nav > ul > li:nth-child(3)").click(function () {
        $("#addCommodity").css("display","block");
        $("#addCommodity").siblings().css("display","none");
        $(this).addClass("checked");
        $(this).siblings().removeClass("checked");

        $.ajax({
            url: domainName + "classifications/getParentClassification",
            type: "GET",
            dataType: "json",
            success: function (response) {
                $("#classificationSelect").empty();
                var keys = Object.keys(response);
                console.log(keys);
                for(var i = 0;i < keys.length;i++) {
                    var id = keys[i];
                    var name = response[keys[i]];
                    $("#classificationSelect").append('<option value =' + id + '>' + name +'</option>');
                    if(i == 0){
                        var val = id;
                        classificationId = id;
                        $.ajax({
                            url: domainName + "classifications/getClassificationByParent",
                            type: "GET",
                            dataType: "json",
                            data: {
                                parentId: val
                            },
                            success: function (res) {
                                var keys = Object.keys(res);
                                $("#classificationSecondSelect").empty();
                                $("#classificationSecondSelect").append('<option value="-1">不选择二级分类</option>');
                                for(var i = 0;i < keys.length;i++){
                                    var id = keys[i];
                                    var name = res[keys[i]];
                                    $("#classificationSecondSelect").append('<option value =' + id + '>' + name +'</option>');
                                }
                            }
                        });
                    }
                }
                /*
                $("#classificationSelect").empty();
                console.log(typeof res);
                for(var i = 0;i < res.length;i++){
                    var id = res[i].id;
                    var name = res[i].name;
                    $("#classificationSelect").append('<option value =' + id + '>' + name +'</option>');
                    if(i == 0){
                        var val = id;
                        classificationId = id;
                        $.ajax({
                            url: domainName + "classifications/getClassificationByParent",
                            type: "GET",
                            dataType: "json",
                            data: {
                                parentId: val
                            },
                            success: function (res) {
                                $("#classificationSecondSelect").empty();
                                $("#classificationSecondSelect").append('<option value="-1">不选择二级分类</option>');
                                for(var i = 0;i < res.length;i++){
                                    var id = res[i].id;
                                    var name = res[i].name;
                                    $("#classificationSecondSelect").append('<option value =' + id + '>' + name +'</option>');
                                }
                            }
                        });
                    }

                }*/
            }
        });


    });


    //确认上架商品
    $("#addCommodity >:last-child > button:nth-child(2)").click(function () {
        console.log(myEditor.getData());
        var title = $("#addCommodity > form > div:nth-child(1) > input[type=text]").val();
        var describe = $("#addCommodity > form > div:nth-child(2) > input[type=text]").val();
        var stock = $("#addCommodity > form > div:nth-child(3) > input[type=text]").val();
        var price = $("#addCommodity > form > div:nth-child(4) > input[type=text]").val();

        var showImg = showImgPath;
        console.log(showImg);
        if(title.trim().length <= 0 || describe.trim().length <= 0 || stock.trim().length <= 0 || price.trim().length <= 0){
            alert("请输入所有内容");
            return;
        }
        $.ajax({
            url: domainName + "commodities/addCommodity",
            type: "POST",
            dataType: "json",
            data: {
                title: title,
                describe: describe,
                stock: stock,
                price: price,
                content: myEditor.getData(),
                classificationId: classificationId,
                showImgSrc: showImg
            },
            success: function (res) {

            }
        });
    });
    //预览
    $("#addCommodity >:last-child > button:nth-child(1)").click(function () {
        var text = myEditor.getData();
        $("#preview").modal('show');
        $("#preview > .modal-dialog").empty();
        $("#preview > .modal-dialog").append(text);
    });

    $("#addCommodity > form > div:nth-child(7) > input[type=file]").change(function () {
        if(!$(this)[0].files[0]){
            return;
        }
        var formData = new FormData();
        var flag = $(this)[0].files[0].type.indexOf("image");
        if(flag != 0){
            alert("请选择图片文件");
            return;
        }
        formData.append('file',$(this)[0].files[0]);
        $.ajax({
            url: domainName + "commodities/uploadCommodityImg",
            data: formData,
            type: "POST",
            dataType: "json",
            cache: false,
            processData: false,
            contentType: false,
            success: function (res) {
                showImgPath = res.url;
                console.log(showImgPath);
            }
        });
    });
}
function ckEditor() {
    ClassicEditor
        .create(document.querySelector("#editor"),{
          ckfinder: {
              uploadUrl: domainName + "commodities/uploadCommodityImg"
          }
        }).then(function (value) {
            myEditor = value;
        }).catch(function (reason) {
        console.error(error);
    });
}