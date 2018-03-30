<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="xmrbi" uri="http://www.xmrbi.com/tags" %>
<%@ taglib prefix="gmms" uri="http://www.gmms.com/tags" %>
<%@ taglib uri="/WEB-INF/utils-annex.tld" prefix="annex"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>--%>
<html>
<head>
    <meta charset="utf-8">
    <title>厦门路桥年费征收系统</title>
    <script type="text/javascript" src="${absoluteContextPath}/js/jquery-1.8.0.min.js"></script>
    <%--<script type="text/javascript" src="js/jquery.js" ></script>--%>
  <%--  <script type="text/javascript">
        $(document).ready(function() {

            $("#btnSubmit").click(function() {
                if(checkForm()){ //若验证通过，则调用保存方法
                    submits();
                    //document.forms[0].submit();
                }
            });
            document.forms[0].j_username1.focus();
            initIframe();

        });

        //登录综合养护系统GMMS
        function submits(){
            var passWord = $.trim($('#j_password').val());
            var loginName = $.trim($('#j_username1').val());
            var host = window.location.host;
            var httpUrl = 'http://'+host+'/gmms/';
            setCookie("loginUrl",window.location.href,null,"/");
            if(loginName != '' && passWord != '') {
                $.post(httpUrl + "modules/security/user!validateUP.action",
                        {
                            loginName : loginName,
                            passWord : passWord
                        },
                        function(returnedData, status){
                            if(status == 'success'){
                                if(returnedData == 'fail'){
                                    $('#loginErr').attr("style","display:");
                                    $('#j_password').val('');
                                } else {
                                    var userName = $.trim($('#j_username1').val());
                                    $('#j_username').val(userName);
                                    document.forms[0].action = httpUrl + 'j_spring_security_check';
                                    document.forms[0].submit();
                                }
                            }
                        }
                );
            }
        }

        function keySubmit(event){
            if (event.keyCode == 13) {
                if(checkForm()){ //若验证通过，则调用保存方法
                    submits();
                }
            }
            if($("#j_username1").val().indexOf('用户名')>-1)
                $("#j_username1").val('');
        }

        function checkForm(){
            var userEle = document.getElementById('j_username1');
            var passEle = document.getElementById('j_password');
            var str = '';
            if(userEle.value == '')
                str += '用户名';
            if(passEle.value == '')
                str+= '密码';
            if(str != ''){
                alert(str + '不可为空！');
                return false;
            }

            return true;

        }

        function clearName(){
            if($("#j_username1").val()=='')
                $("#j_username1").val('用户名');
        }

        function initIframe(){
            var host = window.location.host;
            var src = 'http://'+host+'/gmms/pages/main-file.jsp';
            document.getElementById('modelframe').src=src;
        }
        /**
         * 设置cookie
         * @param name
         * @param value
         * @param expiredays 有效期天数（可选）
         * @param path 路径范围（可选）
         * @param domain 域名（可选）
         * @param isSecure 是否安全传输（可选）
         * 例 setCookie('username','test',30) 或 setCookie('username','test',30)
         */

        function setCookie(pName, value, expiredays,path,domain,isSecure){
            var exdate=new Date();
            exdate.setDate(exdate.getDate() + expiredays);
            var cookieStr = pName + "=" + escape(value) + ((expiredays) ? ";expires="+exdate.toGMTString():"")
                    + (path ? (";path="+path):"")
                    + (domain ? (";domain="+domain):"")
                    + (isSecure ? (";secure"):"")

            document.cookie = cookieStr;
        }

    </script>--%>
    <style type="text/css">
        #body {
            margin: 0px;
            padding: 0px;
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
        }

        #top {
            width: 1024px;
            margin-top: 0px;
            margin-right: auto;
            margin-bottom: 0px;
            margin-left: auto;
            position: relative;
            padding: 0px;
            background-image: url(${absoluteContextPath}/images/bg_02.png);
            background-repeat: no-repeat;
            background-size:100%;
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
            padding-top:12%;
        }
        #login {
            background-image: url(${absoluteContextPath}/images/login_09.png);
            background-repeat: no-repeat;
            margin: 0px;
            padding: 0px;
            height: 396px;
            width: 70%;
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
            margin-top: 20%;
            margin-right: auto;
            margin-left: auto;
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
        $(function(){
            //alert("11  "+${absoluteContextPath});
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
                    //alert("输入用户名")
             /*       $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath() %>/modules/main/main!getRoleTypeId.action",
                        cache: false,
                        dataType : "json",
                        data:{loginName:loginName},
                        async:false,
                        success: function(data){
                            if(data==true){
                                $("#td_1").css("display", "block");

                            }
                        }
                    });*/
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
    <img src="images/bg.jpg" id="bg_1">
</div>
<div style="width:100%; height:100%; padding:0; margin:0;">
    <div id="top">
        <div id="logo"><img src="images/login_06.png" width="100%" height="100%" >
      <%--  <h1  align="center" >年费发票打印系统</h1>--%>
        </div>
    </div>
</div>
<div id="center">
    <form name="loginForm" method="post" action="${absoluteContextPath}/j_spring_security_check" onsubmit="return checkForm();">
      <%-- <input type="hidden" id="j_username" name="j_username" value="" />--%>
        <div id="neirong">
            <div id="title"><img src="images/login_11.png" width="100%" height="100%"></div>
            <div id="login">
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
