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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 卡管理 <span
        class="c-gray en">&gt;</span> 卡类型管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
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
		<a class="btn btn-success radius" onclick="show_tagtype_add()" href="javascript:;"><i
                class="Hui-iconfont">&#xe600;</i> 添加卡类型</a>

                	<#--<a class="btn btn-danger radius"
                       onclick="delete_all();" href="javascript:;"><i
                            class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>-->
	</span>
        <span class="r">共有数据：<strong>${tmTagTypeList?size}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="100">卡类型ID</th>
                <th width="100">卡类型名称</th>
                <th width="100">通讯速率</th>
                <th width="120">生产厂家</th>
                <th width="120">是否在用</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list tmTagTypeList as tmTagType>
            <tr class="text-c" id="tagtype_${tmTagType.typeId?c}">
                <td><input userId="${tmTagType.typeId?c}" type="checkbox" value="" name=""></td>
                <td>${tmTagType.typeId?c}</td>
                <td>${tmTagType.tagType!''}</td>
                <td>${tmTagType.communicateRate!''}</td>
                <td>${tmTagType.factory!''}</td>
                <td> <#if tmTagType.inUse==1>
                    <span >在用</span>
                <#else>
                    <span class="c-red">停用</span>
                </#if></td>
                <td class="f-14 td-manage">
                    <#--<a style="text-decoration:none" class="ml-5"
                       onclick="alert_tagtype(${tmTagType.typeId?c})"
                       data-title="编辑卡类型"
                       title="编辑"><i class="Hui-iconfont">
                        &#xe6df;</i></a>-->
                        <#if tmTagType.inUse==1>
                            <a style="text-decoration:none" class="ml-5" onClick="alter_tag(this,${tmTagType.typeId?c},0)" href="javascript:;"
                               title="停用"><span class="c-red">停用</span><i class="Hui-iconfont">&#xe631;</i></a>
                        <#else>
                            <a style="text-decoration:none" class="ml-5" onClick="alter_tag_on(this,${tmTagType.typeId?c},1)" href="javascript:;"
                               title="启用"><span class="c-green">启用</span><i class="Hui-iconfont">&#xe615;</i></a>
                        </#if>
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

<#include "tagtype-add.ftl">
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

    function show_tagtype_add() {
//        layer_show(title, url, w, h);
        $("#form-tagtype-add")[0].reset();
        $("#form-tagtype-add").find("input[name='tagType']").removeAttr("readonly");
        userAddIndex = addMoneyIndex = layer.open({
            title: "添加卡类型",
            type: 1,
            area: ['700px', '470px'],
            content: $('#tagtype_add_div')
        });
    }

   /* function alert_tagtype(obj) {
        $("#form-tagtype-add")[0].reset();
        //$("#form-tagtype-add").find("input[name='userNo']").attr("readonly", "readonly");

        var $tds = $(obj).parents("tr").find("td");
        var userName = $tds.eq(2).text();
        var realName = $tds.eq(3).text();
        var phone = $tds.eq(4).text();
        var remark = $tds.eq(7).text();
        $("#form-tagtype-add").find("input[name='id']").val(id);
        $("#form-tagtype-add").find("input[name='userNo']").val(userName);
        $("#form-tagtype-add").find("input[name='userName']").val(realName);
        $("#form-tagtype-add").find("input[name='telphone']").val(phone);
        $("#form-tagtype-add").find("input[name='password']").val(password);
        $("#passwordAgain").val(password);
        $("#form-tagtype-add").find("select[name='roleId']").val(roleId);
        $("#plazaNo").val(plazaNo);
        //$("#form-user-add").find("select[id='sysPlaza.plaNo']").val(plazaNo);

        $("#form-tagtype-add").find("input[name='remark']").val(remark);

        userAddIndex = addMoneyIndex = layer.open({
            title: "修改用户信息",
            type: 1,
            area: ['700px', '470px'],
            content: $('#tagtype_add_div')
        });
    }*/

    function alter_tag(obj, id,inuse) {
        layer.confirm('确认要停用吗？', function (index) {
            $.get('${absoluteContextPath}/tag/alterTagType', {typeId: id, inUse: inuse}).done(function (data) {

                layer.msg('已停用!', {icon: 1, time: 1000});
                setTimeout(function () {
                    location.replace(location.href);
                }, 1000);
            }).fail(function (xhr, status) {
                layer.msg('停用失败!', {icon: 1, time: 1000});
            });
        });
    }

    function alter_tag_on(obj, id,inuse) {
        layer.confirm('确认要启用吗？', function (index) {
            $.get('${absoluteContextPath}/tag/alterTagType', {typeId: id, inUse: inuse}).done(function (data) {

                layer.msg('已启用!', {icon: 1, time: 1000});
                setTimeout(function () {
                    location.replace(location.href);
                }, 1000);
            }).fail(function (xhr, status) {
                layer.msg('启用失败!', {icon: 1, time: 1000});
            });
        });
    }

    function delete_all() {
        var idArray = [];
        $("td input[type='checkbox']:checked").each(function () {
            idArray.push($(this).attr("userId"));
        });
        var ids = idArray.join(",");

        layer.confirm('确认要删除这些账号吗？', function (index) {
            $.ajax({
                type: 'GET',
                url: '${absoluteContextPath}/user/deleteAll',
                data: {userIds: ids},
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