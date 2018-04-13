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


    <title>网点列表</title>
</head>
<script type="text/javascript"></script>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 基础信息管理 <span
        class="c-gray en">&gt;</span> 网点管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="text-c" style="margin: 10px">

</div>
<div class="page-container" style="padding-top: 0px">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
	<span class="l">
		<a class="btn btn-success radius" onclick="show_user_add()" href="javascript:;"><i
                class="Hui-iconfont">&#xe600;</i> 添加网点</a>

                	<#--<a class="btn btn-danger radius"
                       onclick="delete_all();" href="javascript:;"><i
                            class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>-->
	</span>
        <span class="r">共有数据：<strong>${sysPlazaList?size}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
<#--                <th width="100">网点编号</th>-->
                <th width="150">网点名称</th>
                <th width="100">联系人</th>
                <th width="50">电话</th>
                <th width="50">传真</th>
                <th width="100">地址</th>
                <th width="100">邮编  </th>
                <th width="100">备注</th>
                <th width="50">是否启用</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list sysPlazaList as sysPlaza>
            <tr class="text-c" id="user_${sysPlaza.plaNo?string}">
                <td><input userId="${(sysPlaza.plaNo)?c}" type="checkbox" value="" name=""></td>
<#--                <td>${(sysPlaza.plaNo)!''}</td>-->
                <td>${sysPlaza.plaName!''}</td>
                <td>${sysPlaza.plaLinkMan!''}</td>
                <td>${sysPlaza.plaPhone!''}</td>
                <td>${sysPlaza.plaFax!''}</td>
                <td>${sysPlaza.plaAddress!''}</td>
                <td>${sysPlaza.plaZipCode!''}</td>
                <td>${sysPlaza.plaRemark!''}</td>
                <td> <#if sysPlaza.plaInUse==0>
                    <span >在用</span>
                <#else>
                    <span class="c-red">停用</span>
                </#if></td>
                <td class="f-14 td-manage">
                    <a style="text-decoration:none" class="ml-5"
                       onclick="alert_user(this,${sysPlaza.plaNo?c})"
                       data-title="编辑网点"
                       title="编辑"><i class="Hui-iconfont">
                        &#xe6df;</i></a>
                    <#if sysPlaza.plaInUse==0>
                        <a style="text-decoration:none" class="ml-5" onClick="alter_plaza(this,${sysPlaza.plaNo?c},1)" href="javascript:;"
                           title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
                    <#else>
                        <a style="text-decoration:none" class="ml-5" onClick="alter_plaza_on(this,${sysPlaza.plaNo?c},0)" href="javascript:;"
                           title="启用"><i class="Hui-iconfont">&#xe615;</i></a>
                    </#if>
                    <a style="text-decoration:none" class="ml-5" onClick="del_user(this,'${sysPlaza.plaNo?c}')" href="javascript:;"
                       title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
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
<#include "plaza-add.ftl">

<script type="text/javascript">
    var addMoneyIndex;
    var userAddIndex;


    $('.table-sort').dataTable({
        "aaSorting": [[1, "desc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "pading": false,
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            // {"orderable":false,"aTargets":[0,8]}// 不参与排序的列
        ]
    });

    function show_user_add() {
//        layer_show(title, url, w, h);
        $("#form-plaza-add")[0].reset();
        //$("#form-plaza-add").find("input[name='plaNo']").removeAttr("readonly");
        userAddIndex = addMoneyIndex = layer.open({
            title: "添加网点",
            type: 1,
            area: ['700px', '470px'],
            content: $('#plaza_add_div')
        });
    }

    function alert_user(obj, id) {
        $("#form-plaza-add")[0].reset();
        var $tds = $(obj).parents("tr").find("td");
        var plaName = $tds.eq(1).text();
        var plaLinkMan = $tds.eq(2).text();
        var plaPhone = $tds.eq(3).text();
        var plaFax = $tds.eq(4).text();
        var plaAddress = $tds.eq(5).text();
        var plaZipCode = $tds.eq(6).text();
        var plaRemark = $tds.eq(7).text();
        $("#form-plaza-add").find("input[name='plaNo']").val(id);
        $("#form-plaza-add").find("input[name='plaName']").val(plaName);
        $("#form-plaza-add").find("input[name='plaLinkMan']").val(plaLinkMan);
        $("#form-plaza-add").find("input[name='plaPhone']").val(plaPhone);
        $("#form-plaza-add").find("input[name='plaFax']").val(plaFax);
        $("#form-plaza-add").find("input[name='plaAddress']").val(plaAddress);
        $("#form-plaza-add").find("input[name='plaZipCode']").val(plaZipCode);
        $("#form-plaza-add").find("input[name='plaRemark']").val(plaRemark);

        userAddIndex = addMoneyIndex = layer.open({
            title: "修改网点信息",
            type: 1,
            area: ['700px', '470px'],
            content: $('#plaza_add_div')
        });
    }


    function del_user(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'GET',
                url: '${absoluteContextPath}/user/deleteplaza',
                data: {plazaNo: id},
                success: function (data) {
                    if (data == "ok") {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {icon: 1, time: 1000});
                    } else if(data=='userExist'){
                        layer.alert("该网点下存在用户，不可删除", {icon: 1, time: 1000});
                    }else{
                        layer.alert(data, {icon: 1});
                    }
                },
                error: function (data) {
                    layer.alert("删除出错啦,请刷新一下页面再操作!", {icon: 5});
                }
            });
        });
    }

    function delete_all() {
        var idArray = [];
        $("td input[type='checkbox']:checked").each(function () {
            idArray.push($(this).attr("userId"));
        });
        var ids = idArray.join(",");

        layer.confirm('确认要删除这些网点吗？', function (index) {
            $.ajax({
                type: 'GET',
                url: '${absoluteContextPath}/user/deleteAllplaza',
                data: {plazaNos: ids},
                success: function (data) {
                    if (data == "ok") {
                        layer.msg("删除成功", {icon: 1, time: 1000});
                        setTimeout(function () {
                            location.replace(location.href);
                        }, 1000);

                    } else {
                        layer.alert(data, {icon: 1});
                    }
                },
                error: function (data) {
                    layer.alert("删除出错啦,请刷新一下页面再操作!", {icon: 5});
                }
            });
        });
    }


    function alter_plaza(obj, id,inuse) {
        layer.confirm('确认要停用吗？', function (index) {
            $.get('${absoluteContextPath}/user/alterPlazainUse', {plazaNo: id, inUse: inuse}).done(function (data) {

                layer.msg('已停用!', {icon: 1, time: 1000});
                setTimeout(function () {
                    location.replace(location.href);
                }, 1000);
            }).fail(function (xhr, status) {
                layer.msg('停用失败!', {icon: 1, time: 1000});
            });
        });
    }

    function alter_plaza_on(obj, id,inuse) {
        layer.confirm('确认要启用吗？', function (index) {
            $.get('${absoluteContextPath}/user/alterPlazainUse', {plazaNo: id, inUse: inuse}).done(function (data) {

                layer.msg('已启用!', {icon: 1, time: 1000});
                setTimeout(function () {
                    location.replace(location.href);
                }, 1000);
            }).fail(function (xhr, status) {
                layer.msg('启用失败!', {icon: 1, time: 1000});
            });
        });
    }
/*    function alter_type_setting(userId) {
        layer.open({
            type: 2,
            title: "类型设置",
            content: "${absoluteContextPath}/userItemType/" + userId + "/list",
            area: ['1000px', '500px']
        });
    }*/
</script>
</body>
</html>