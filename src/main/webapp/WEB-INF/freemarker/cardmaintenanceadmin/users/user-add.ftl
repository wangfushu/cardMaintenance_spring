<article class="page-container" id="user_add_div" style="display: none">
    <form class="form form-horizontal" id="form-user-add" method="post" action="">
        <input type="hidden" name="id" id="formUserId">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>登录账号名：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text"
                       data-msg-checkWithId="该账号已被注册"
                       checkWithId="${absoluteContextPath}/user/usernameNotExist"
                       class="input-text" required minlength="3"
                       placeholder="" id="" name="userName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>客户名称：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" required
                       class="input-text" minlength="2"
                       placeholder="" id="" name="realName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                手机号码：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" required minlength="11" maxlength="11" number="true"
                       data-msg-checkWithId="该电话已被使用"
                       checkWithId="${absoluteContextPath}/user/telPhoneNotExist"
                       placeholder="" id=""
                       name="telphone">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                邮箱：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" email="true"
                       placeholder="" id=""
                       name="email">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                密码：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="password" class="input-text" required
                       minlength="4"
                       placeholder="" id="passwordForm"
                       name="password">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                确认密码：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="password" class="input-text" required
                       equalTo="#passwordForm" id="passwordAgain"
                       data-msg-equalTo="两次输入的密码不相同"
                       placeholder="">
            </div>
        </div>

        <div class="row cl" id="user_role_div">
            <label class="form-label col-xs-4 col-sm-2">
                用户角色：</label>
            <div class="formControls col-xs-8 col-sm-8"> <span class="select-box" style="width:150px;">
			<select class="select" name="roleId" size="1">
            <#list roleList as role>
                <#if role.id!=1>
                    <option value="${role.id}">${role.remark}</option>
                </#if>
            </#list>
			</select>
			</span></div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                备注：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text"
                       placeholder="" id=""
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
    $("#form-user-add").validate({
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var data = $(form).serializeObject();
            $.post('${absoluteContextPath}/user/add', data, function (result) {
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