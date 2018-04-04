<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<base target="_self" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/easyui/css/default.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/icon.css" />
		
		<script type="text/javascript" src="${ctx}/js/rbiTool.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.js" ></script>
		<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min-1.2.4.js"></script>
		<script type="text/javascript" src='${ctx}/js/jquery-easyui/locale/easyui-lang-zh_CN.js'></script>
		<style type="text/css">
		<!--
					.rlselect{
						width:190px;
						height:250px;
					}
		-->
		</style>
		<SCRIPT LANGUAGE="JavaScript">
		<!--
		
		function getSelected(){
			
			var datas = new Array();
			var ele = document.getElementById('rightSelect');
			var ops = ele.options;
			for(var i = 0;i < ops.length; i++){
				var uId = ops[i].value;
				var uName = ops[i].text;
				datas[datas.length] = new ReutrnData(uId, uName);
			}
			window.returnValue = datas;
			window.close();
		}
		
		//-->
		</SCRIPT>
	</head>
	<title>单位选择</title>
	
	<BODY>
					<div class="easyui-layout" fit="true">
			       	<div region="center" border="false" style="padding: 10px; height: 320px;background: #fff; border: 1px solid #ccc;">
						<xmrbi:multiSelect id="useunitSelect" allItems="${datas}" 
						valueProperty="id" 
						textProperty="name" selectedIdStr="${selectedIdStr}"
						rightId="rightSelect"
						rightCss="rlselect"
						leftCss="rlselect"
						/>
						
					</div>
					<div region="south" border="false" style="padding: 5px; text-align: right; height: 35px; line-height: 30px;">
						<a id="btnEp" class="easyui-linkbutton" 
			            	href='javascript:getSelected();'><span style="padding-left:6;">确定</span></a> 
			            <a class="easyui-linkbutton"  href="javascript:void(0);" 
			            	onclick="closeWin();"><span style="padding-left:6;">取消</span></a>
					</div>
				</div>
		
		
		
	</BODY>
</HTML>