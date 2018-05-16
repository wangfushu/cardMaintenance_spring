<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
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


    <title>网点卡库存列表</title>
</head>
<script type="text/javascript"></script>
<body >
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 卡管理 <span
        class="c-gray en">&gt;</span> 卡出入库管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="text-c" style="margin: 10px">
   <form id="tagstore_searchlist_form" action="${absoluteContextPath}/tag/tagstorelist" method="post">
    网点：<span class="select-box inline">

       <select  name="plaNo" class="easyui-combobox"style="width:195px;" data-options="panelHeight:'auto'">
       <#list plazaList as plaza>
           <option value="${plaza.plaNo?c}"
           <#if param.plaNo?? && param.plaNo==plaza.plaNo>selected</#if>>${plaza.plaName}</option>
           <#--<option value="${plaza.plaNo?c}">${plaza.plaName}</option>-->
       </#list>
       </select>
		<#--<select name="plaNo" class="select">
        <#list plazaList as plaza>
            <option value="${plaza.plaNo?string}"
                    <#if param.plaNo?? && param.plaNo==plaza.plaNo>selected</#if>>${plaza.plaName}</option>
        </#list>
        </select>-->
	</span>


   <#-- 卡类型：<span class="select-box inline">
		<select name="typeId" class="select">
        <#list tmTagTypeList as tmTagTyp>
            <option value="${tmTagTyp.typeId?c}"
                    <#if param.typeId?? && param.typeId==tmTagTyp.typeId>selected</#if>>${tmTagTyp.tagType}</option>
        </#list>
        </select>
		</span>-->

        <button class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        <button class="btn btn-success" type="reset"><i class="Hui-iconfont">&#xe609;</i> 重置</button>
    </form>
</div>
<div class="page-container" style="padding-top: 0px">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
	<span class="l">
		<a class="btn btn-success-outline radius" onclick="tag_inStore()" href="javascript:;"><i
                class="Hui-iconfont">&#xe600;</i> 入库</a>
<#--
        <a class="btn btn-success-outline radius" onclick="tag_outStore()" href="javascript:;"><i
                class="Hui-iconfont">&#xe600;</i> 出库</a>
-->

        <a class="btn btn-danger-outline radius" onclick="tag_outStore()" href="javascript:;"><i
                class="Hui-iconfont">&#xe600;</i> 批量出库</a>
                	<#--<a class="btn btn-danger radius"
                       onclick="delete_all();" href="javascript:;"><i
                            class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>-->
	</span>
      <#--  <span class="r">共有数据：<strong>${tmTagStoreList?size}</strong> 条</span>-->
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort  table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="100">网点</th>
               <#-- <th width="100">卡类型</th>-->
                <th width="100">好卡数量</th>
                <th width="100">坏卡数量</th>
                <th width="120">更新时间</th>
            </tr>
            </thead>
           <#-- <tbody>
            <#list tmTagStoreList as tmTagStore>
            <tr class="text-c" id="tagtype_${tmTagStore.plazaNo?string}">
                <td><input userId="${tmTagStore.plazaNo?string}" userName="${tmTagStore.plazaName!''}" type="checkbox" value="" name=""></td>
                <td>${tmTagStore.plazaName!''}</td>
                &lt;#&ndash;<td>${tmTagStore.tagType!''}</td>&ndash;&gt;
                <td>${tmTagStore.goodTagCount!''}</td>
                <td>${tmTagStore.badTagCount!''}</td>
                <td>${tmTagStore.updateTime!''} </td>
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

<!--easyUI的引入-->
<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/easyui/1.5.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/easyui/1.5.4.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/easyui/1.5.4.5/demo/demo.css">
<script type="text/javascript" src="${absoluteContextPath}/easyui/1.5.4.5/jquery.easyui.min.js"></script>
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

<#include "tag-inStroe.ftl">
<#include "tag-outStroe.ftl">
<script type="text/javascript">
    var taginStoreIndex;
    var tagoutStoreIndex;
    var addMoneyIndex;
    $(function () {
        var data = $("#tagstore_searchlist_form").serializeObject();
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
                "url": "${absoluteContextPath}/tag/tagstorePage",
                "data": data
            },
            "columns": [

        {
            "targets": 0,
            "data": "plazaNo",
            "width": "25px",
            "render": function (data, type, row, meta) {

                return '<input userId="'+data+'" userName="'+row.plazaName+'" type="checkbox" value="" name="">';
            }
        },
                {"data": "plazaName"},
                {"targets": 4,
                    "data": "goodTagCount",
                    "width": "100px",
                    "class":"text-c",
                    "defaultContent": "",
                    "render": function (data, type, row, meta) {
                        return  data;
                    }},
                {"targets": 4,
                    "data": "badTagCount",
                    "width": "100px",
                    "class":"text-c",
                    "defaultContent": "",
                    "render": function (data, type, row, meta) {
                        return  data;
                    }},
                {"targets": 4,
                    "data": "updateTime",
                    "width": "100px",
                    "defaultContent": "",
                    "render": function (data, type, row, meta) {
                        return  new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                    }
                }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            $(nRow).addClass('text-c');
        }

    });
        setTimeout(function () {
            $("table").css("width", "100%");
        }, 2000);
    });

    function tag_inStore() {
//        layer_show(title, url, w, h);
        $("#form-inStore-add")[0].reset();
        //$("#form-tagtype-add").find("input[name='tagType']").removeAttr("readonly");
        taginStoreIndex  =addMoneyIndex= layer.open({
            title: "入库",
            type: 1,
            zIndex:1989,
            area: ['700px', '470px'],
            content: $('#tag_inStore_div')

        });
    }
    function tag_outStore() {
        $("#form-outStore-add")[0].reset();
        $("#formOutRecievedPlazaNo").combobox('clear');
        var idArray = [];
        var nameArray=[];
        $("td input[type='checkbox']:checked").each(function () {
            if($(this).attr("userId")!="${currentUser.sysPlaza.plaNo}") {
                idArray.push($(this).attr("userId"));
                nameArray.push($(this).attr("userName"))
            }
        });
        var ids = idArray.join(",");
        var namelist=nameArray.join(",")
        $("#formOutRecievedPlazaNo_input").val(ids);
        $("#formOutRecievedPlazaNameList").val(namelist);
        if(undefined !=ids  && ids !=""){
            $("#receivedselect_div_input").show();
            $("#receivedselect_div").hide();
        }else{
            $("#receivedselect_div_input").hide();
            $("#receivedselect_div").show();
        }
        $("#form-outStore-add").find("input[name='outRecievedlazaNo']").val(ids);


        tagoutStoreIndex = layer.open({
            title: "批量出库",
            type: 1,
            zIndex:1989,
            area: ['700px', '470px'],
            content: $('#tag_outStore_div')

        });
    }

</script>
</body>
</html>