<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
		  content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
	<meta http-equiv="Cache-Control" content="no-siteapp"/>
	<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/H-ui-admin/static/h-ui/css/H-ui.min.css"/>
	<link rel="stylesheet" type="text/css"
		  href="${absoluteContextPath}/H-ui-admin/static/h-ui.admin/css/H-ui.admin.css"/>
	<link rel="stylesheet" type="text/css"
		  href="${absoluteContextPath}/H-ui-admin/lib/Hui-iconfont/1.0.8/iconfont.css"/>
	<link rel="stylesheet" type="text/css"
		  href="${absoluteContextPath}/H-ui-admin/static/h-ui.admin/skin/default/skin.css" id="skin"/>
	<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/H-ui-admin/static/h-ui.admin/css/style.css"/>
	<title>厦门路桥年费征收系统</title>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript"
			src="${absoluteContextPath}/H-ui-admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
			src="${absoluteContextPath}/H-ui-admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
			src="${absoluteContextPath}/H-ui-admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
			src="${absoluteContextPath}/H-ui-admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<!--/_footer 作为公共模版分离出去-->
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
			src="${absoluteContextPath}/H-ui-admin/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript" src="${absoluteContextPath}/js/common.js"></script>

	<!--[if lt IE 9]>
	<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/html5shiv.js"></script>
	<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/respond.min.js"></script>
	<![endif]-->
	<!--[if IE 6]>
	<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
</head>

<script type="text/javascript">
$(function(){
	//$.Huifold("#Huifold1 .item h4","#Huifold1 .item .info","fast",1,"click"); /*5个参数顺序不可打乱，分别是：相应区,隐藏显示的内容,速度,类型,事件*/
	/*$("#min_title_list li").contextMenu('Huiadminmenu', {
		bindings: {
			'closethis': function(t) {
				console.log(t);
				if(t.find("i")){
					t.find("i").trigger("click");
				}		
			},
			'closeall': function(t) {
				alert('Trigger was '+t.id+'\nAction was Email');
			},
		}
	});*/
});

var passwordIndex;
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>个人信息</div>'
	});
}

/*资讯-添加*/
/*function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}*/
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*订单-获取*/
/*function order_get(title,url,w,h){
	layer_show(title,url,w,h);
}*/
/*消息-获取*/
function msg_get(title,url,w,h){
	layer_show(title,url,w,h);
}

function change_password() {
	$("#form-password-change")[0].reset();
	passwordIndex = layer.open({
		type: 1,
		area: ['400px', '300px'],
		maxmin: true,
		title: '修改密码',
		content: $("#change_password")
	});
}

</script> 

<style type="text/css">
.fa{display:inline-block;font:normal normal normal 14px/1 FontAwesome;font-size:inherit;text-rendering:auto;-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale}
.fa-envelope-o:before{content:"\f003"}.fa-heart:before{content:"\f004"}
.fa-envelope:before{content:"\f0e0"}
.fa-envelope-square:before{content:"\f199"}
.fa-fw{width:1.28571429em;text-align:center}
.pull-right{float:right}
.text-muted{color:#777}
small{font-size:80%}
.dropdown-alerts{margin-left:-123px;width:310px;min-width:0}
.dropdown-menu li{display:block}
.dropdown-menu li:last-child{margin-right:0}
.link-block{font-size:12px;padding:10px}
.nav.navbar-top-links .link-block a{font-size:12px}
</style>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="">厦门路桥年费征收系统</a>
			<span class="logo navbar-slogan f-l mr-10 hidden-xs"></span>
		
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">

				<li>${currentUser.sysPlaza.plaName} </li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">${currentUser.eUserName} <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius">
<%--						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>--%>
						<li><a href="javascript:;" onClick="change_password()">修改密码</a></li>
						<li><a href="${absoluteContextPath}/j_spring_security_logout">安全退出</a></li>
				</ul>
			</li>

			
			<!-- <li class="dropDown dropDown_hover">
			<a href="javascript:;" title="消息">
				<span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> 
			</a>
				<ul class="dropDown-menu menu radius box-shadow">	
					<li><a href="javascript:;" onclick="order_get('获取订单','order-get.html','','310')"><i class="Hui-iconfont">&#xe620;</i> 获取订单</a>
					</li>
					<li><a href="javascript:;" onclick="article_add('添加店铺','store-add.html')"><i class="Hui-iconfont">&#xe67f;</i> 添加店铺</a>
					</li>
				</ul>
			</li>	 -->

				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-order">
			<dt><i class="Hui-iconfont">&#xe616;</i> 发票打印<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd >
				<ul>
					<li><a data-href="${absoluteContextPath}/invoice/list" data-title="打印列表" href="javascript:void(0)">打印列表</a></li>
					<%--<li><a data-href="order-list.html" data-title="订单管理" href="javascript:void(0)">报表管理</a></li>--%>
			</ul>
		</dd>
	</dl>


		<dl id="menu-report">
			<dt><i class="Hui-iconfont">&#xe616;</i> 报表管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd >
				<ul>
					<li><a data-href="${absoluteContextPath}/report/vehicleDayReport" data-title="车辆办理日统计报表" href="javascript:void(0)">车辆办理日统计报表</a></li>
					<li><a data-href="${absoluteContextPath}/report/afirReport" data-title="年费票发放记录" href="javascript:void(0)">年费票发放记录</a></li>
					<li><a data-href="${absoluteContextPath}/report/afturReport" data-title="年费票使用记录" href="javascript:void(0)">年费票使用记录</a></li>
					<%--<li><a data-href="order-list.html" data-title="订单管理" href="javascript:void(0)">报表管理</a></li>--%>
				</ul>
			</dd>
		</dl>


</div>
</aside>


<%--<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>--%>


<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" data-href="store-add.html">我的桌面</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="${absoluteContextPath}/login/welcome"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<article id="change_password" class="page-container">
<form class="form form-horizontal" id="form-password-change">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>原有密码：</label>
		<div class="formControls col-xs-8 col-sm-8">
			<input type="password"
				   data-msg-remote="密码不正确"
				   class="input-text" required
				   placeholder="请输入旧密码" name="oldpassword">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>
			新密码：</label>
		<div class="formControls col-xs-8 col-sm-8">
			<input type="password" class="input-text" required
				   minlength="6"
				   placeholder="" id="newpassword"
				   name="newpassword">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>
			确认密码：</label>
		<div class="formControls col-xs-8 col-sm-8">
			<input type="password" class="input-text" required
				   equalTo="#newpassword"
				   data-msg-equalTo="两次输入的密码不相同"
				   placeholder="">
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
			<button onClick="" class="btn btn-primary radius" type="submit"><i
					class="Hui-iconfont">&#xe632;</i> 修改密码
			</button>
		</div>
	</div>
</form>
</article>
<script>
	$("#form-password-change").validate({
		onkeyup: false,
		focusCleanup: true,
		success: "valid",
		submitHandler: function (form) {
			var data = $(form).serializeObject();
			$.post('${absoluteContextPath}/login/changePassWord', data, function (result) {
				if (result == "success") {
					/*layer.msg("密码修改成功", {icon: 1, time: 1000});*/
					alert("密码修改成功");
					layer.close(passwordIndex);
					window.location.href="${absoluteContextPath}/j_spring_security_logout";

				} else {
					alert(result);
					//layer.alert(result);

				}
			});
		}
	});

</script>
</body>
</html>