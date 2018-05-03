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


    <title>发卡信息详细报表</title>
</head>
<script type="text/javascript"></script>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 报表管理 <span
        class="c-gray en">&gt;</span> 发卡信息详细报表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="text-c" style="margin: 10px">
    <form id="issuetagdetail-list-form" action="${absoluteContextPath}/statistic/issuetag-detail-report" method="post">
        网点：<span class="select-box inline">

       <select  name="plaNo" class="easyui-combobox"style="width:195px;" data-options="panelHeight:'auto'">
       <#list plazaList as plaza>
           <option value="${plaza.plaNo?c}"
                   <#if param.plaNo?? && param.plaNo==plaza.plaNo>selected</#if>>${plaza.plaName}</option>
       <#--<option value="${plaza.plaNo?c}">${plaza.plaName}</option>-->
       </#list>
       </select>
        <span> 时间</span>
        <input type="text" name="timefrom" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
               id="logmin" value="${param.timefrom!''}"
               class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
               id="logmax"
               value="${param.timeto!''}"
               class="input-text Wdate" name="timeto" style="width:120px;">

        <button class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        <button class="btn btn-success" type="reset"><i class="Hui-iconfont">&#xe609;</i> 清空</button>
    </form>
</div>
<div class="page-container" style="padding-top: 0px">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
	<span class="l">
		<a class="btn btn-success radius" onclick="export_excel()" href="javascript:;"><i
                class="Hui-iconfont">&#xe600;</i> 导出</a>

                	<#--<a class="btn btn-danger radius"
                       onclick="delete_all();" href="javascript:;"><i
                            class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>-->
	</span>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort  table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25">序号</th>
                <th width="100">网点名称</th>
                <th width="100">发卡账号  </th>
                <th width="100">发卡姓名</th>
                <th width="100">车牌号</th>
                <th width="100">车牌颜色</th>
                <th width="100">车型</th>
                <th width="100">发卡时间</th>
                <th width="50">发卡类型</th>
                <th width="100">金额(元)</th>
            </tr>
            </thead>
            <tbody>
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

<script type="text/javascript">
    var addMoneyIndex;
    var userAddIndex;

    $(function () {
    var data = $("#issuetagdetail-list-form").serializeObject();
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
            "url": "${absoluteContextPath}/statistic/issueTagDetail",
            "data": data
        },
        "columns": [
            {"data" : null} ,
            {"data": "plazaName", defaultContent:""},
            {"targets": 4,
                "data": "userNo",
                "width": "150px",
                "class":"text-c",
                "defaultContent": "",
                "render": function (data, type, row, meta) {
                    return  data;
                }},
            {"targets": 4,
                "data": "userName",
                "width": "100px",
                "class":"text-c",
                "defaultContent": "",
                "render": function (data, type, row, meta) {
                    return  data;
                }},
            {"targets": 4,
                "data": "plateNo",
                "width": "80px",
                "class":"text-c",
                "defaultContent": "",
                "render": function (data, type, row, meta) {
                    return  data;
                }},
            {"targets": 4,
                "data": "plateColor",
                "width": "50px",
                "class":"text-c",
                "defaultContent": "",
                "render": function (data, type, row, meta) {
                    return  data;
                }},
            {"targets": 4,
                "data": "vKindNo",
                "width": "80px",
                "class":"text-c",
                "defaultContent": "",
                "render": function (data, type, row, meta) {
                    if(data==1){
                        return  "客车";
                    }else if(data==2){
                        return "货车";
                    }else if(data==3){
                        return "农用车";
                    }
                }},
            {"targets": 4,
                "data": "installDate",
                "width": "100px",
                "class":"text-c",
                "defaultContent": "",
                "render": function (data, type, row, meta) {
                    return  new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                }},
            {"targets": 4,
                "data": "installType",
                "width": "80px",
                "class":"text-c",
                "defaultContent": "",
                "render": function (data, type, row, meta) {
                    if(data==1){
                        return  "新卡发放";
                    }else if(data==2){
                        return "保内换卡";
                    }else if (data==3){
                        return "保外换卡";
                    }
                    return  data;
                }},
            {"targets": 4,
                "data": "fee",
                "width": "50px",
                "class":"text-c",
                "defaultContent": "",
                "render": function (data, type, row, meta) {
                    return  data;
                }}],
        "fnDrawCallback": function(){
            　　 var api = this.api(); 　
            　   var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数 　　
            api.column(0).nodes().each(function(cell, i) { 　　　
                　   cell.innerHTML = startIndex + i + 1; 　　
            });
        }

    });
    setTimeout(function () {
        $("table").css("width", "100%");
    }, 2000);
    });

    function export_excel() {
        var data = $("#issuetagdetail-list-form").serializeObject();
        var param = "?";
        $.each(data, function (name, value) {
            param += name + "=" + value + "&";
        });
        param.substring(0, param.length - 1);
        window.location.href = "${absoluteContextPath}/statistic/issueTagDetailexport" + param;
    }

</script>
</body>
</html>