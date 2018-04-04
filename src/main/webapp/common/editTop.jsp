<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<title>URL添加</title>
<%

String ids = request.getParameter("ids");
String[] idArray = ids.split(",");

String url = request.getParameter("url");

String parameterName = request.getParameter("name");
 %>
<SCRIPT LANGUAGE="JavaScript">
<!--
	var url = '<%=url%>';
	var parameterName = '<%=parameterName%>';
	var currentNum = 0;
	var idArray = new Array();
	<%
	for(int i=0;i<idArray.length;i++){
 	%>
	idArray[idArray.length] = <%=idArray[i]%>;	
	<%}%>
	
	
	function loadPage(num){
	
		ifEditPage.document.location.href = url + '?'+ parameterName + '=' + idArray[num];
		currentNum = num;
		iniBar();
	}
	
	
	function iniBar(){
	    
		spanBar.innerHTML = leftButton() + '&nbsp;&nbsp;&nbsp;' +  numButtons() + '&nbsp;&nbsp;&nbsp;' + rigthButton();
		
		
	}
	
	function leftButton(){
		if(currentNum==0)
			return '<a>|<</a> <a><</a>';
		else
			return '<a href="javascript:loadPage(0);">|<</a> <a href="javascript:loadPage('+(currentNum-1)+');"><</a>';
	}
	
	function rigthButton(){
		if(currentNum==(idArray.length-1))
			return ' <a>></a><a>>|</a>';
		else
			return ' <a href="javascript:loadPage('+(currentNum+1)+');">></a> <a href="javascript:loadPage('+(idArray.length-1)+');">>|</a>';
	}
	function numButtons(){
	    var mnMidNum = 2;
	    var mxMidNum = idArray.length -3;
	    var midNum = 2;
		if(idArray.length<5){
			//显示全部
			var html = '';
			for(var i=0;i<idArray.length;i++){
				html += numButton(i);
			}
			return html;
		}
		if(currentNum<mnMidNum){
			midNum = mnMidNum;
		}else
			if(currentNum > mxMidNum)
				midNum = mxMidNum;
			else
				midNum = currentNum;
		
			var html = numButton((midNum-2));
			html += numButton((midNum-1));
			html += numButton(midNum);
			html += numButton((midNum+1));
			html += numButton((midNum+2));
		
		return html;
	}
	
	function numButton(num){
	
		if(num==currentNum)
			return ' ' + (num+1) + ' ';
		else{
			return ' <a href="javascript:loadPage(' + (num) +');">' + (num+1) + '</a> ';
		}
	}
	
//-->
</SCRIPT>


</head>
<body onLoad="iniBar();">  
<span id="spanBar">
</span>
<iframe scrolling="false" frameborder="0" name="ifEditPage" 
				style="width:100%;height:90%;" src="<%=url%>?<%=parameterName%>=<%=idArray[0]%>"></iframe>

</body>
</html>