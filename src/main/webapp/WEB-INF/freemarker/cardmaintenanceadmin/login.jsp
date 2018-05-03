<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>--%>
<html>
<head>
    <meta charset="utf-8">
    <title>后台管理</title>
    <script type="text/javascript" src="${absoluteContextPath}/js/jquery-1.8.0.min.js"></script>

    <style type="text/css">
        #body {
            margin: 0px;
            padding: 0px;
            background-image: url(${absoluteContextPath}/images/bg1.jpg);
        }
        #bg {
            height: 100%;
            width: 100%;
            margin: 0px;
            padding: 0px;
            position: absolute;
            position: fixed;
        }
        #bg_1{
            height: 100%;
            width: 100%;
            margin: 0px;
            padding: 0px;
            display:block;
            z-index: -1;
        }
        #bg_2{
            height: 100%;
            width: 100%;
            left: auto;
            top: auto;
            margin: 0px;
            padding: 0px;
            display:block;
        }
        #top {
            width: 1024px;
            margin-top: 0px;
            margin-right: auto;
            margin-bottom: 0px;
            margin-left: auto;
            position: relative;
            padding: 0px;
   /*         background-image: url(${absoluteContextPath}/images/bg_02.png);*/
/*            background-repeat: no-repeat;
            background-size:100%;*/
        }
        #center {
            padding: 0px;
            width: 1024px;
            margin-top: 0px;
            margin-right: auto;
            margin-bottom: 0px;
            margin-left: auto;
            position: relative;
        }
        #logo {
            width: 65%;
            padding: 0px;
            margin-top: 0px;
            margin-right: auto;
            margin-bottom: 0px;
            margin-left: auto;
            padding-top: 10%;
        }
        #login {
            background-image: url(${absoluteContextPath}/images/login_09.png);
            background-repeat: no-repeat;
            margin: 0px;
            margin-left: 15%;
            padding: 0px;
            height: 596px;
            width: 80%;
            float: left;
        }
        #title {
            float: left;
            width: 30%;
            margin: 0px;
            padding-top: 10%;
            padding-right: 0px;
            padding-bottom: 0px;
            padding-left: 0px;
        }
        .loginErr{
            float:right;
            padding-top:12px;
            padding-right:140px;;
            color:#FF0000;
            font-size:13px;
        }
        #yonghu {
            padding: 0px;
            width: 80%;
            margin-top: 10%;
            margin-right: auto;
            margin-left: 20%;
        }
        #yonghu ul {
            margin: 0px;
            padding: 0px;
            list-style-type: none;
        }
        .shuru {
            padding: 5px;
            width: 285px;
            border: 1px solid #7dcbfc;
            margin: 0px;
            height: 32px;
            font-size: 15px;
            color: #8c8c8c;
            border-radius: 5px;
            line-height:32px;
        }
        .a1 {
            color: #0582c1;
            text-decoration: none;
            font-size: 14px;
            margin-left: 5px;
        }
        .a2 {
            background-image: url(${absoluteContextPath}/images/botton_12.png);
            background-size:100% 100%;
            background-repeat: no-repeat;
            height: 49px;
            width: 295px;
            border:none;
            display:block;
        }
        #neirong {
            padding: 0px;
            width: 60%;
            margin-top: 0px;
            margin-right: auto;
            margin-bottom: 0px;
            margin-left: auto;
        }
        #yonghu ul li {
            padding: 0px;
            margin-top: 10px;
            margin-right: 0px;
            margin-bottom: 0px;
            margin-left: 0px;
        }

    </style>
    <script>
        /**
         * 用于跳出iframe 登录超时问题
         */
        var _topWin = window;
        while (_topWin != _topWin.parent.window) {
            _topWin = _topWin.parent.window;
        }
        if (window != _topWin)
            _topWin.document.location.href = '${absoluteContextPath}/login';


        $(function(){
//取出有clear类的input域
            $('#j_username').each(function () {
                //使用data方法存储数据
                $(this).data("txt", $.trim($(this).val()));
                $(this).data("txt3", $.trim($(this).val()));
            }).focus(function () {
                // 获得焦点时判断域内的值是否和默认值相同，如果相同则清空
                if ($.trim($(this).val()) === $(this).data("txt")) {
                    $(this).val("");
                    var currtA = document.getElementById("j_username");
                    currtA.style.color="#000";
                    currtA.style.marginTop="6px";
                }
            }).blur(function () {
                var loginName = $.trim($(this).val());
                if (loginName === "") {
                    //Restore saved data
                    $(this).val($(this).data("txt"));
                    var currtA = document.getElementById("j_username");
                    currtA.style.color="#808080";
                    currtA.style.marginTop="6px";
                }else{

                }
            });

//取出有clear类的input域
            $('#j_password2').each(function () {
                //使用data方法存储数据
                $(this).data("txt1", $.trim($(this).val()));
            }).focus(function () {
                // 获得焦点时判断域内的值是否和默认值相同，如果相同则清空
                if ($.trim($(this).val()) === $(this).data("txt1")) {
                    $(this).css("display", "none");
                    $("#j_password").css("display", "block").focus();
                }
            });

            $("#j_password").blur(
                    function () {
                        if ($(this).val() === "") {
                            $(this).css("display", "none");
                            $("#j_password2").css("display", "block");
                        }
                    });

        });
        function checkForm(){
            var userEle = document.getElementById('j_username');
            var passEle = document.getElementById('j_password');
//	var uuValue = $('#useunit').combobox('getValue');
            var str = '';
            if(userEle.value=='')
                str += ' 用户名';
            if(passEle.value=='')
                str+= ' 密码';
                //  管理员及开发人员没有单位，不需要选择单位。
                // 	 if(userEle.value != 'admin' && userEle.value != 'dev'){
                // 		 if(uuValue==''||uuValue=='...请选择...')
                // 		 	str+= ' 单位';
                //  }


            if(str!=''){
                alert(str + ' 不可为空！');
                return false;
            }



            return true;

        }
    </script>
</head>

<body id="body">
<div id="bg">
    <img src="${absoluteContextPath}/images/bg2.png" id="bg_1">
</div>
<div style="width:100%; height:100%; padding:0; margin:0;">
    <div id="top">

    </div>
</div>
<div id="center">
    <form name="loginForm" method="post" action="${absoluteContextPath}/j_spring_security_check" onsubmit="return checkForm();">
      <%-- <input type="hidden" id="j_username" name="j_username" value="" />--%>
        <div id="neirong">
<%--            <div id="title"><img src="${absoluteContextPath}/images/login_11.png" width="100%" height="100%"></div>--%>
            <div id="login">
                <div id="logo"><img src="${absoluteContextPath}/images/login_06.png" width="100%" height="100%" >
                </div>
                <div id="yonghu">
                    <ul>
                        <li><input type="text" class="shuru"  value="用户名"   name="j_username"  id="j_username" ></li>
                        <li><input type="text" class="shuru" name="j_password2" value="密码"   id="j_password2" ><input  type="password" class="shuru" style="display:none;" id="j_password" name="j_password" /> </li>

                        <li><input name="" type="submit" class="a2" id="" value=""></a> </li>
                    </ul>
                </div>
         <%--       <%
                    if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
                %>
                <div style="margin-left:15px;color:#0069B9;" >&nbsp;&nbsp;&nbsp;&nbsp;登录失败，<%=((AuthenticationException)session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage() %></div>
                <%
                    }
                %>--%>
                <div id='loginErr' style='display:none' class="loginErr">登录失败，请检查用户名或密码是否正确。</div>
            </div>
        </div>
    </form>
</div>

<iframe id="modelframe" height="0" src="" style="display:none"></iframe>
</body>
<script>
    var msg;
    if( '${msg}'!= null){
        msg = "${msg}";
    }else{
        msg="";
    }
    if (msg != "") {
        $("#loginErr").show();
        setTimeout(function () {
            $("#loginErr").hide();
        }, 5000);
    }

</script>
</html>
