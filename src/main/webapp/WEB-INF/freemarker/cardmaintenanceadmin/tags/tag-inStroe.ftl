<article class="page-container" id="tag_inStore_div" style="display: none">
    <form class="form form-horizontal" id="form-inStore-add" method="post" action="">
        <input type="hidden" name="id" id="formUserId">
        <input type="hidden" name="userId" id="userId">

        <div class="row cl" >
            <label class="form-label col-xs-4 col-sm-2">
                入库类型：</label>
            <div class="formControls col-xs-6 col-sm-3"> <span class="select-box" style="width:150px;">

                <select name="inStoreType" class="select">
                <#list tagInStoreTypeList as inStoreType>
                    <option value="${inStoreType.biValue?c}">${inStoreType.biDescription}</option>
                </#list>
                </select>

            </div>
        </div>

        <div class="row cl" >
            <label class="form-label col-xs-4 col-sm-2">
                发送方网点：</label>
            <div class="formControls col-xs-6 col-sm-3"> <span class="select-box" style="width:150px;">

                <select name="inSendPlazaNo" class="select">
                <#list plazaList as plaza>
                    <option value="${plaza.plaNo?string}">${plaza.plaName}</option>
                </#list>
                </select>

            </div>
        </div>

        <div class="row cl" >
            <label class="form-label col-xs-4 col-sm-2">
                卡类型：</label>
            <div class="formControls col-xs-6 col-sm-3"> <span class="select-box" style="width:150px;">
              <select name="tagType" class="select">
              <#list tmTagTypeList as tmTagTyp>
                  <option value="${tmTagTyp.typeId?c}"
                          <#if param.typeId?? && param.typeId==tmTagTyp.typeId>selected</#if>>${tmTagTyp.tagType}</option>
              </#list>
              </select>

            </div>
        </div>
        <div class="row cl" >
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>数量：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" required
                       class="input-text" minlength="1" number="true"
                       placeholder="" id="formcount" name="count">
            </div>

            </div>
        </div>

        <div class="row cl" >
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>接收方网点：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <select name="inRecievedPlazaNo" class="select">
                    <option value="${(currentUser.sysPlaza.plaNo)?string}" selected>${(currentUser.sysPlaza.plaName)?string}</option>

                </select>
            </div>

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
    $("#form-inStore-add").validate({
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var data = $(form).serializeObject();
            $.post('${absoluteContextPath}/tag/inStore', data, function (result) {
                result = JSON.parse(result);
                if (result.success != undefined && result.success) {
                    layer.msg("入库成功", {icon: 1, time: 1000});
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