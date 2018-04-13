<article class="page-container" id="tag_outStore_div" style="display: none">
    <form class="form form-horizontal" id="form-outStore-add" method="post" action="">

           <div class="row cl" >
               <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>发送方网点：</label>
               <div class="formControls col-xs-8 col-sm-8">
                   <select id="formOutSendPlazaNo" name="outSendPlazaNo" class="select">
                       <option value="${(currentUser.sysPlaza.plaNo)?string}" selected>${(currentUser.sysPlaza.plaName)?string}</option>
                   </select>
               </div>

           </div>
           </div>

        <div class="row cl" id="receivedselect_div_input" >
            <label class="form-label col-xs-4 col-sm-2">
                接收方网点：</label>
            <div class="formControls col-xs-6 col-sm-8">
                <input type="hidden" id="formOutRecievedPlazaNo_input" name="outRecievedlazaNoListinput">
                <input type="text" class="input-text" placeholder="" multiple readonly="readonly" id="formOutRecievedPlazaNameList">
                </div>
        </div>
        <div class="row cl" id="receivedselect_div" >
            <label class="form-label col-xs-4 col-sm-2">
                接收方网点：</label>
            <div class="formControls col-xs-6 col-sm-8">
            <select id="formOutRecievedPlazaNo" name="outRecievedlazaNoList" class="easyui-combobox"style="width:300px;" data-options="multiple:true,multiline:true,panelHeight:'auto'">
            <#list plazaList as plaza>
                <option value="${plaza.plaNo?string}">${plaza.plaName}</option>
            </#list>
            </select>
            </div>
        </div>


        <div class="row cl" >
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>数量：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" required
                       class="input-text" minlength="1" digits
                data-msg-checkWithOutCount="接收网点不可为空或网点库库存不足"
                 checkWithOutCount="${absoluteContextPath}/tag/checkOutCount"
                       placeholder="" id="formOutcount" name="count">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                备注：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" maxlength="200"
                       placeholder="" id="formRemark"
                       name="remark">
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

<script>
    //表单验证
    $("#form-outStore-add").validate({
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            if($("#formOutRecievedPlazaNameList").val()==""){
                var ids=$("#formOutRecievedPlazaNo").combobox('getValues');
                $("#formOutRecievedPlazaNo_input").val(ids);
            }
            var data = $(form).serializeObject();
            $.post('${absoluteContextPath}/tag/outStore', data, function (result) {
                result = JSON.parse(result);
                if (result.success != undefined && result.success) {
                    layer.msg("出库成功", {icon: 1, time: 1000});
                    layer.close(tagoutStoreIndex);
                    setTimeout(function () {
                        location.replace(location.href);
                    }, 1000);
                } else {
                    layer.alert("保存失败");
                }
            });
        }
    });

    if ($.validator != undefined) {
        $.validator.addMethod("checkWithOutCount", function (value, element, params) {
            if($("#formOutRecievedPlazaNameList").val()==""){
                var ids=$("#formOutRecievedPlazaNo").combobox('getValues');
                $("#formOutRecievedPlazaNo_input").val(ids);
            }
            var outSendPlazaNo = $("#formOutSendPlazaNo").val();
            var outRecievedlazaNoListinput = $("#formOutRecievedPlazaNo_input").val();
            var data = {};
            data[$("#formOutSendPlazaNo").attr("name")] = outSendPlazaNo;
            data[$("#formOutRecievedPlazaNo_input").attr("name")] = outRecievedlazaNoListinput;
            data[$(element).attr("name")] = value;
            var res = false;
            $.ajax({
                async: false,
                type: "GET",
                url: params,
                data: data,
                success: function (result) {
                    res = result == "true";
                }
            });
            return res;
        }, "");
    }
</script>