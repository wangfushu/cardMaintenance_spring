<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>﻿
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
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${absoluteContextPath}/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${absoluteContextPath}/lib/respond.min.js"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script type="text/javascript" src="${absoluteContextPath}/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->


    <title>卡类型列表</title>
</head>
<script type="text/javascript"></script>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 基础信息管理 <span
        class="c-gray en">&gt;</span> 基本信息配置管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="text-c" style="margin: 10px">
   <#-- <form action="${absoluteContextPath}/user/list" method="post">
    <span class="select-box inline">
		<select name="roleId" class="select">
            <option value="-1">角色</option>
        <#list roleList as role>
            <option value="${role.id}"
                    <#if param.roleId?? && param.roleId==role.id>selected</#if>>${role.remark!'超级管理员'}</option>
        </#list>
        </select>
		</span>
        根据：
        <span class="select-box inline">
		<select name="timeType" class="select">
            <option value="0" <#if param.timeType==0>selected</#if>>上次登录时间</option>
            <option value="1" <#if param.timeType==1>selected</#if>>注册时间</option>
        </select>
		</span>
        <input type="text" name="timefrom" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
               id="logmin" value="${param.timefrom!''}"
               class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax"
               value="${param.timeto!''}"
               class="input-text Wdate" name="timeto" style="width:120px;">

        <button class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        <button class="btn btn-success" type="reset"><i class="Hui-iconfont">&#xe609;</i> 清空</button>
    </form>-->
</div>
<div class="page-container" style="padding-top: 0px">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
	<span class="l">

	</span>
        <span class="r">共有数据：<strong>${sysConfigList?size}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="300">描述</th>
                <th width="200">值</th>
                <th width="200">更新时间</th>
                <th width="250">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list sysConfigList as sysConfig>
            <tr class="text-c" id="tagtype_${sysConfig.cfConfigName?string}">
                <td><input userId="${sysConfig.cfConfigName?string}" type="checkbox" value="" name=""></td>
                <td>${sysConfig.cfConfigDescription?string}</td>
                <td>${sysConfig.cfConfigValue!''}</td>
                <td>${sysConfig.cfUpdateTime!''}</td>

                <td class="f-14 td-manage">
                    <a style="text-decoration:none" class="ml-5"
                       onclick="alert_config(this,'${sysConfig.cfConfigName?string}')"
                       data-title="编辑基础信息"
                       title="编辑"><i class="Hui-iconfont">
                        &#xe6df;</i></a>

                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"
        src="${absoluteContextPath}/H-ui-admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/laypage/1.2/laypage.js"></script>

<script type="text/javascript"
        src="${absoluteContextPath}/H-ui-admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
        src="${absoluteContextPath}/H-ui-admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
        src="${absoluteContextPath}/H-ui-admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${absoluteContextPath}/js/common.js"></script>

<article class="page-container" id="config_add_div" style="display: none">
    <form class="form form-horizontal" id="form-config-add" method="post" action="">
        <input type="hidden" name="cfConfigName" id="formUserId">

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>描述：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" readonly
                       class="input-text"
                       placeholder="" id="" name="cfConfigDescription">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                值  ：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" required maxlength="6" number="true"
                       placeholder="" id=""
                       name="cfConfigValue"/>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="" class="btn btn-primary radius" type="submit"><i
                        class="Hui-iconfont">&#xe632;</i> 保存
                </button>
            </div>
        </div>
    </form>
</article>


<script type="text/javascript">
    var addMoneyIndex;
    var userAddIndex;
    $('.table-sort').dataTable({
        "aaSorting": [[1, "asc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "pading": false,
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            // {"orderable":false,"aTargets":[0,8]}// 不参与排序的列
        ]
    });


    function alert_config(obj,id) {
        $("#form-config-add")[0].reset();
        var $tds = $(obj).parents("tr").find("td");
        var cfConfigDescription = $tds.eq(1).text();
        var cfConfigValue = $tds.eq(2).text();
        $("#form-config-add").find("input[name='cfConfigName']").val(id);
        $("#form-config-add").find("input[name='cfConfigDescription']").val(cfConfigDescription);
        $("#form-config-add").find("input[name='cfConfigValue']").val(cfConfigValue);

        userAddIndex = addMoneyIndex = layer.open({
            title: "修改基础信息",
            type: 1,
            area: ['700px', '270px'],
            content: $('#config_add_div')
        });
    }


    $("#form-config-add").validate({
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var data = $(form).serializeObject();
            $.post('${absoluteContextPath}/user/alertbaseinfo', data, function (result) {
                result = JSON.parse(result);
                if (result.success != undefined && result.success) {
                    layer.msg("保存成功", {icon: 1, time: 1000});
                    layer.close(userAddIndex);
                    setTimeout(function () {
                        location.replace(location.href);
                    }, 1000);
                } else {
                    layer.alert("保存失败");
                }
            });
        }
    });



</script>
</body>
</html>