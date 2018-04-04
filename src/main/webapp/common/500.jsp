<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>500 - 系统内部错误</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
	<style type="text/css">
		body {
			background-color: #fff;
			scroll: auto;
			margin: 0px;
			border: none;
			overflow: auto;
			font-size: 13px;
		}
		a { 
			color:Blue;
			text-decoration:none;
		}
		a:hover { 
			color:Blue;
			text-decoration:underline;
		}
	</style>
</head>

<body>
	<div style='text-align: center; width: 100%; margin-top: 30px;'>
        <div style='width: 500px; border: 2px solid #99BBE8; padding: 25px; text-align: left;margin:0px auto 0px auto;
            padding-left: 90px; background: #fafafa url(${ctx}/images/icon_apperror.gif) no-repeat 25px 35px;'>
            <div style='border-bottom: 1px solid #E0E0E0; font-size: 18pt; padding: 5px;'>
                <span style="font-size:18pt;">登录已超时，请重新登录！</span>
            </div>

            <div style='padding: 5px; margin-top: 8px; line-height: 150%;'>
                <span>很抱歉，系统内部遇到了一个异常情况。</span>
            </div>
			
            <div style='padding: 5px;  line-height: 150%;'>
                <span>您可以：</span>
                <br />
                <img src='${ctx}/images/link_gobackto.gif' /> <a href='#' onclick='history.go(-1);'>返回上一页</a>
                <br />
                <img src='${ctx}/images/link_gobackto.gif' /> <a href='${ctx }/main.action' target="_top">返回桌面</a>
                <br />
                <img src='${ctx}/images/link_gobackto.gif' /> <a href='${ctx}/j_spring_security_logout' target="_top">重新登录</a>
            </div>

            <div style='padding: 5px;  line-height: 150%;'>
                <span >如果您遇到的问题，欢迎您联系我们的客服获取帮助。谢谢您的合作！</span>
			 </div>
        </div>
   </div>
</body>
</html>
