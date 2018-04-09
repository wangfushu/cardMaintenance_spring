<article class="page-container" id="plaza_add_div" style="display: none">
    <form class="form form-horizontal" id="form-plaza-add" method="post" action="">
        <input type="hidden" name="id" id="formUserId">
        <input type="hidden" name="userId" id="userId">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>网点编号：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text"
                       data-msg-checkWithId="该网点已存在"
                       checkWithId="${absoluteContextPath}/user/sysPlazaNotExist"
                       class="input-text" required minlength="3"
                       placeholder="" id="" name="plaNo">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>网点名称：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" required
                       class="input-text" minlength="2"
                       placeholder="" id="" name="plaName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                邮编  ：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" required maxlength="6" number="true"
                       placeholder="" id=""
                       name="plaZipCode"/>
            </div>
        </div>



        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                备注：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text"
                       placeholder="" id=""
                       name="plaRemark">
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
    $("#form-plaza-add").validate({
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var data = $(form).serializeObject();
            $.post('${absoluteContextPath}/user/addplaza', data, function (result) {
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