
var browser = {
    versions: function () {
        var u = navigator.userAgent, app = navigator.appVersion;
        return {//移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
        };
    } (),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}
var flag = false;
//1.是否mobile，否，肯定不是。
if (browser.versions.mobile) {
    //2.是否ios或android终端，有一个是
    if (browser.versions.android || browser.versions.ios) {
        flag = true;
    }
}
//pc端逻辑
if(!flag){
    //追加css文件
    var domain = window.location.host;
    var filename="http://"+domain+contextPath+"/static/css/style.css";
    var linkNode = document.createElement("link");
    linkNode.setAttribute("rel","stylesheet");
    linkNode.setAttribute("type","text/css");
    linkNode.setAttribute("href",filename);
    document.head.appendChild(linkNode);
    $(function(){
        //设置图片大小
        $("#title img").attr("width","128px");
        //按键动画效果
        $("#navigations img").mouseenter(
            function(){
                $(this).animate({width:'25px',height:'25px'},200);
            }
        )
        $("#navigations img").mouseleave(
            function(){
                $(this).animate({width:'18px',height:'18px'},200);
            }
        )
        $("#navigations img#homepage").click(
            function() {
                $(location).attr('href', "http://"+window.location.host+contextPath+"/hello");
            }
        )
    });
    //移动端逻辑
}else{
    var domain = window.location.host;
    var filename="http://"+domain+contextPath+"/static/css/phoneStyle.css";
    var linkNode = document.createElement("link");

    linkNode.setAttribute("rel","stylesheet");

    linkNode.setAttribute("type","text/css");

    linkNode.setAttribute("href",filename);

    document.head.appendChild(linkNode);
    $(function(){
        $("#navigations img").attr("style","width:50px;height:50px");
        $("#title img").attr("width","300px");
        $("#navigations img#homepage").click(
            function() {
                $(location).attr('href', "http://"+window.location.host+contextPath+"/hello");
            }
        )
    });
}

