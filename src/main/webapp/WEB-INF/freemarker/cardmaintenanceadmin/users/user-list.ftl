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
    <title>订单列表</title>
</head>
<script type="text/javascript"></script>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span
        class="c-gray en">&gt;</span> 用户列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="text-c" style="margin: 10px">
    <!-- <button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button> -->
    <form action="${absoluteContextPath}/user/list" method="post">
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
    </form>
</div>
<div class="page-container" style="padding-top: 0px">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
	<span class="l">
		<a class="btn btn-success radius" onclick="show_user_add()" href="javascript:;"><i
                class="Hui-iconfont">&#xe600;</i> 添加用户</a>

                	<a class="btn btn-danger radius"
                       onclick="delete_all();" href="javascript:;"><i
                            class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
	</span>
        <span class="r">共有数据：<strong>${userList?size}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="100">用户名</th>
                <th width="100">客户名称</th>
                <th width="200">用户手机号</th>
                <th width="200">用户邮箱</th>
                <th width="160">注册时间</th>
                <th width="160">上次登录时间</th>
                <th width="160">上次登录ip</th>
                <th width="100">账户余额</th>
                <th width="120">备注</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list userList as user>
            <tr class="text-c" id="user_${user.id?c}">
                <td><input userId="${user.id?c}" type="checkbox" value="" name=""></td>
                <td>${user.userName}</td>
                <td>${user.realName!'管理员'}</td>
                <td>${user.telphone!''}</td>
                <td>${user.email!''}</td>
                <td>${user.gmtCreate?string("yyyy-MM-dd HH:mm:ss")!''}</td>
                <td>${user.lastLoginTime?string("yyyy-MM-dd HH:mm:ss")!''}</td>
                <td>${user.lastLoginIp!''}</td>
                <td field="money">
                    <#if user.isUser>
                    ${user.money?c!''}
                    <#else>
                        -
                    </#if>
                </td>
                <td>${user.remark!''}</td>
                <td class="f-14 td-manage">
                    <a style="text-decoration:none" class="ml-5"
                       data-href="${absoluteContextPath}/user/add?id=${user.id?c}"
                       onclick="alert_user(this,${user.id?c},'${user.password}',${user.roleId})"
                       data-title="编辑用户"
                       title="编辑"><i class="Hui-iconfont">
                        &#xe6df;</i></a>
                    <a style="text-decoration:none" class="ml-5" onClick="del_user(this,${user.id?c})" href="javascript:;"
                       title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
                    <#if user.isUser>
                        <a style="text-decoration:none" class="ml-5"
                           onClick="add_money(${user.id?c},'${user.realName!'管理员'}')"
                           href="javascript:;"
                           title="充值"><i class="Hui-iconfont">&#xe63a;</i></a>

                        <a style="text-decoration:none" class="ml-5"
                           onClick="alter_type_setting(${user.id?c})"
                           href="javascript:;"
                           title="类型配置"><i class="Hui-iconfont">&#xe61d;</i></a>
                    </#if>


                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<article class="page-container" id="add_money" style="display: none">
    <input type="hidden" id="userId">
    <input type="hidden" id="userName">
    <div class="row cl">
        <label class="form-label col-xs-6 col-sm-6">充值金额：</label>
        <div class="formControls col-xs-6 col-sm-6">
            <input type="text" class="input-text"
                   placeholder="" id="money">
        </div>
    </div>
    <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
            <input class="btn btn-primary radius" type="submit"
                   onclick="add_user_money()"
                   value="&nbsp;&nbsp;充值&nbsp;&nbsp;">
        </div>
    </div>
</article>
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

<#include "user-add.ftl">
<script type="text/javascript">
    var addMoneyIndex;
    var userAddIndex;
    function add_money(user_id, userName) {
        var money = $("#money").val("");
        $("#userId").val(user_id);
        $("#userName").val(userName);
        addMoneyIndex = layer.open({
            title: "余额充值",
            type: 1,
            content: $('#add_money')
        });
    }

    function add_user_money() {
        var money = $("#money").val();
        if (money === undefined || money == "") {
            layer.msg('请填写具体金额', {icon: 5, time: 1000});
            return;
        }
        var userName = $("#userName").val();
        var userId = $("#userId").val();

        layer.confirm('确定要给用户[' + userName + ']充值' + money + '元吗？', {
            btn: ['确认', '返回'] //按钮
        }, function (index) {
            $.get('${absoluteContextPath}/user/addMoney', {money: money, userId: userId}).done(function (data) {
                layer.close(index);
                layer.close(addMoneyIndex);
                $("#user_" + userId).find("td[field='money']").text(data);
                layer.msg('充值成功!', {icon: 1, time: 1000});
            }).fail(function (xhr, status) {
                layer.msg('充值失败!', {icon: 1, time: 1000});
            });
        }, function (index) {
            layer.close(index);
        });
    }

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
        $("#form-user-add")[0].reset();
        $("#form-user-add").find("input[name='userName']").removeAttr("readonly");
        userAddIndex = addMoneyIndex = layer.open({
            title: "添加用户",
            type: 1,
            area: ['700px', '520px'],
            content: $('#user_add_div')
        });
    }

    function alert_user(obj, id, password, roleId) {
        $("#form-user-add")[0].reset();
        $("#form-user-add").find("input[name='userName']").attr("readonly", "readonly");

        var $tds = $(obj).parents("tr").find("td");
        var userName = $tds.eq(1).text();
        var realName = $tds.eq(2).text();
        var phone = $tds.eq(3).text();
        var email = $tds.eq(4).text();
        var remark = $tds.eq(9).text();
        $("#form-user-add").find("input[name='id']").val(id);
        $("#form-user-add").find("input[name='userName']").val(userName);
        $("#form-user-add").find("input[name='realName']").val(realName);
        $("#form-user-add").find("input[name='telphone']").val(phone);
        $("#form-user-add").find("input[name='email']").val(email);
        $("#form-user-add").find("input[name='password']").val(password);
        $("#passwordAgain").val(password);
        $("#form-user-add").find("select[name='roleId']").val(roleId);
        if (roleId == 1) {
            $("#user_role_div").hide();
        } else {
            $("#user_role_div").show();
        }
        $("#form-user-add").find("input[name='remark']").val(remark);

        userAddIndex = addMoneyIndex = layer.open({
            title: "修改用户信息",
            type: 1,
            area: ['700px', '520px'],
            content: $('#user_add_div')
        });
    }

    function del_user(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'GET',
                url: '${absoluteContextPath}/user/delete',
                data: {userId: id},
                success: function (data) {
                    if (data == "ok") {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {icon: 1, time: 1000});
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

    function alter_type_setting(userId) {
        layer.open({
            type: 2,
            title: "类型设置",
            content: "${absoluteContextPath}/userItemType/" + userId + "/list",
            area: ['1000px', '500px']
        });
    }
</script>
</body>
</html>