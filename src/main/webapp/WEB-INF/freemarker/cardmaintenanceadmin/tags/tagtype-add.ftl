<article class="page-container" id="tagtype_add_div" style="display: none">
    <form class="form form-horizontal" id="form-tagtype-add" method="post" action="">
        <input type="hidden" name="id" id="formUserId">
        <input type="hidden" name="userId" id="userId">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>卡类型名称：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text"
                       data-msg-checkWithId="该卡类型已存在"
                       checkWithId="${absoluteContextPath}/tag/tagTypeNotExist"
                       class="input-text" required minlength="3"
                       placeholder="" id="" name="tagType">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>通讯速率：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" required
                       class="input-text"
                       placeholder="" id="" name="communicateRate">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                厂家  ：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text"
                       placeholder="" id=""
                       name="factory"/>
            </div>
        </div>

        <div class="row cl" >
            <label class="form-label col-xs-4 col-sm-2">
                是否在用：</label>
            <div class="formControls col-xs-6 col-sm-3"> <span class="select-box" style="width:150px;">
			<select class="select" name="inUse" size="1">
                <option value="1">在用</option>
                <option value="0">作废</option>
            </select>
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
            $.post('${absoluteContextPath}/tag/add', data, function (result) {
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