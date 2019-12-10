$(function(){
    topFixedSearch();
});
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