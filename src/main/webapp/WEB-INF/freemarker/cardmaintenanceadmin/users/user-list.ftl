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


    <title>用户列表</title>
</head>
<script type="text/javascript"></script>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span
        class="c-gray en">&gt;</span> 用户列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="text-c" style="margin: 10px">
    <form id="user_searchlist_form" action="${absoluteContextPath}/user/list" method="post">

        网点：<span class="select-box inline">

       <select  name="plaNo" class="easyui-combobox"style="width:195px;" data-options="panelHeight:'auto'">
       <#list plazaList as plaza>
           <option value="${plaza.plaNo?c}"
                   <#if param.plaNo?? && param.plaNo==plaza.plaNo>selected</#if>>${plaza.plaName}</option>
       <#--<option value="${plaza.plaNo?c}">${plaza.plaName}</option>-->
       </#list>
       </select>
  <#--  <span class="select-box inline">
		<select name="roleId" class="select">
            <option value="-1">角色</option>
        <#list roleList as role>
            <option value="${role.id}"
                    <#if param.roleId?? && param.roleId==role.id>selected</#if>>${role.remark}</option>
        </#list>
        </select>
		</span>-->
        根据：
        <span class="select-box inline">
		<select name="timeType" class="select">
            <option value="1" <#if param.timeType==1>selected</#if>>注册时间</option>
            <option value="0" <#if param.timeType==0>selected</#if>>上次登录时间</option>
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
        <button class="btn btn-success" type="reset"><i class="Hui-iconfont">&#xe609;</i> 重置</button>
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
        <#--<span class="r">共有数据：<strong>${userList?size}</strong> 条</span>-->
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="150">网点号</th>
                <th width="40">工号</th>
                <th width="40">姓名</th>
                <th width="80">手机号</th>
                <th width="80">角色</th>
                <th width="100">注册时间</th>
<#--                <th width="160">上次登录时间</th>-->
                <th width="120">备注</th>
                <th width="150">操作</th>
            </tr>
            </thead>
           <#-- <tbody>
            <#list userList as user>
            <tr class="text-c" id="user_${user.id?c}">
                <td><input userId="${user.id?c}" type="checkbox" value="" name=""></td>
                <td>${(user.sysPlaza.plaName)!''}</td>
                <td>${user.userNo}</td>
                <td>${user.userName!'管理员'}</td>
                <td>${user.telphone!''}</td>
                <td>${user.email!''}</td>
                <td>${user.gmtCreate!''}</td>
&lt;#&ndash;                <td>${user.lastLoginTime!''}</td>&ndash;&gt;
                <td>${user.remark!''}</td>
                <td class="f-14 td-manage">
                    <a style="text-decoration:none" class="ml-5"
                       data-href="${absoluteContextPath}/user/add?id=${user.id?c}"
                       onclick="alert_user(this,${user.id?c},'${user.password}',${user.roleId},${(user.sysPlaza.plaNo)?c!''},'${user.fax!''}','${user.address!''}','${user.zip!''}','${user.iDCard!''}')"
                       data-title="编辑用户"
                       title="编辑"><i class="Hui-iconfont">
                        &#xe6df;</i></a>
                    <a style="text-decoration:none" class="ml-5" onClick="del_user(this,${user.id?c})" href="javascript:;"
                       title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>

                </td>
            </tr>
            </#list>
            </tbody>-->
        </table>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${absoluteContextPath}/H-ui-admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--easyUI的引入-->
<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/easyui/1.5.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/easyui/1.5.4.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/easyui/1.5.4.5/demo/demo.css">
<script type="text/javascript" src="${absoluteContextPath}/easyui/1.5.4.5/jquery.easyui.min.js"></script>

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
/*    $('.table-sort').dataTable({
        "aaSorting": [[5, "asc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "pading": false,
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            // {"orderable":false,"aTargets":[0,8]}// 不参与排序的列
        ]
    });*/

    $(function () {

        $("#plazaNo").combo("setText", "text").combo('setValue',"");
        var data = $("#user_searchlist_form").serializeObject();
        $('.table-sort').dataTable({
            "bDeferRender": true,
            "processing": true, //打开数据加载时的等待效果
            "serverSide": true,//打开后台分页
            "bAutoWidth": false,
            "ordering": false,
            "aLengthMenu": [20, 100, 200, 500, 1000], //更改显示记录数选项
            "iDisplayLength": 20, //默认显示的记录数
            "searching": false,
            "ajax": {
                "url": "${absoluteContextPath}/user/page",
                "data": data
            },
            "columns": [{
                "targets": 0,
                "data": "id",
                "width": "25px",
                "render": function (data, type, row, meta) {

                    return '<input userId="'+data+'" type="checkbox" value="" name="">';
                }
            },
                {"data": "sysPlaza.plaName", defaultContent:""},
                {"data": "userNo"},
                {"data": "userName",defaultContent:"管理员" },
                {"data": "telphone", defaultContent:""},
                {
                    "targets": 4,
                    "data": "roleName",
                    "width": "100px",
                    "defaultContent": "",
                    "render": function (data, type, row, meta) {
                        if(data){
                            return  data;
                        }else{
                            return "";
                        }

                    }
                },
                {
                    "targets": 4,
                    "data": "gmtCreate",
                    "width": "100px",
                    "defaultContent": "",
                    "render": function (data, type, row, meta) {
                        if(data){
                            return  new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                        }else{
                            return "";
                        }

                    }
                },
                {"data": "remark", defaultContent:""},
                {
                    "targets": 0,
                    "data": "sysPlaza",
                    "width": "25px",
                    "class":"f-14 td-manage",
                    "render": function (data, type, row, meta) {
                        var address="",zip="",iDCard="",fax="";
                        if(row.fax){
                            fax=row.fax;
                        }
                        if(row.address){
                            address=row.address;
                        }
                        if(row.zip){zip=row.zip;}
                        if(row.iDCard){iDCard=row.iDCard;}
                        var result=' <a style="text-decoration:none" class="ml-5" data-href="${absoluteContextPath}/user/add?id='+row.id+'" onclick="alert_user(this,'+row.email+','+row.id+',\''+row.password+'\','+row.roleId+','+row.sysPlaza.plaNo+',\''+fax+'\',\''+address+'\',\''+zip+'\',\''+iDCard+'\')" data-title="编辑用户" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="del_user(this,'+row.id+')" href="javascript:;"title="删除"> <i class="Hui-iconfont">&#xe6e2;</i></a>';


                        return result;
                    }
                }
            ],
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                $(nRow).addClass('text-c');
            }

        });
        setTimeout(function () {
            $("table").css("width", "100%");
        }, 2000);
    });

    function show_user_add() {
//        layer_show(title, url, w, h);
        $("#plazaNo").combobox('select', 0);
        $("#form-user-add")[0].reset();
        $("#form-user-add").find("input[name='id']").val(null)
        $("#form-user-add").find("input[name='userNo']").removeAttr("readonly");
        userAddIndex = addMoneyIndex = layer.open({
            title: "添加用户",
            type: 1,
            zIndex:1989,
            area: ['800px', '470px'],
            content: $('#user_add_div')
        });
    }

    function alert_user(obj, email,id, password, roleId,plazaNo,fax,address,zip,idcard) {
        $("#form-user-add")[0].reset();
        $("#plazaNo").combobox('select', 0);
        $("#form-user-add").find("input[name='userNo']").attr("readonly", "readonly");

        var $tds = $(obj).parents("tr").find("td");
        var userName = $tds.eq(2).text();
        var realName = $tds.eq(3).text();
        var phone = $tds.eq(4).text();
        /*var email = $tds.eq(5).text();*/
        var remark = $tds.eq(7).text();
        $("#form-user-add").find("input[name='id']").val(id);
        $("#form-user-add").find("input[name='userNo']").val(userName);
        $("#form-user-add").find("input[name='userName']").val(realName);
        $("#form-user-add").find("input[name='telphone']").val(phone);
        $("#form-user-add").find("input[name='password']").val(password);
        $("#form-user-add").find("input[name='fax']").val(fax);
        $("#form-user-add").find("input[name='iDCard']").val(idcard);
        $("#form-user-add").find("input[name='email']").val(email);
        $("#form-user-add").find("input[name='zip']").val(zip);
        $("#form-user-add").find("input[name='address']").val(address);
        $("#passwordAgain").val(password);
        $("#form-user-add").find("select[name='roleId']").val(roleId);

        $('#plazaNo').combobox('select', plazaNo);

        $("#plazaNo_input").val(plazaNo);
        //$("#form-user-add").find("select[id='sysPlaza.plaNo']").val(plazaNo);
        if (roleId == 1) {
            $("#user_role_div").show();
        } else {
            $("#user_role_div").show();
        }
        $("#form-user-add").find("input[name='remark']").val(remark);

        userAddIndex = addMoneyIndex = layer.open({
            title: "修改用户信息",
            type: 1,
            zIndex:1989,
            area: ['800px', '470px'],
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
                        //$(obj).parents("tr").remove();
                        location.replace(location.href);
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
        if(ids==""){
            layer.msg('请先勾选用户!', {icon: 1, time: 1000});
        }else {
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