<article class="page-container" id="user_add_div" style="display: none">
    <form class="form form-horizontal" id="form-user-add" method="post" action="">
        <input type="hidden" name="id" id="formUserId">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>工号：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text"
                       data-msg-checkWithId="该账号已被注册"
                       checkWithId="${absoluteContextPath}/user/usernameNotEx ist"
                       class="input-text" required minlength="3"
                       placeholder="" id="formUserNo" name="userNo">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>姓名：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" required
                       class="input-text" minlength="2"
                       placeholder="" id="formUserName" name="userName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                手机号码：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" required isMobile="1"  data-msg-isMobile="请输入有效的手机号码" maxlength="11"
                       placeholder="" id="formTelphone"
                       name="telphone"/>
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
            <div class="formControls col-xs-6 col-sm-3"> <span class="select-box" style="width:150px;">
			<select class="select" name="roleId" id="roleId-select" size="1" onchange=" rolechange()">
            <#list roleList as role>
                <option value="${role.id}">${role.remark}</option>
            </#list>
            </select>
            </div>
            <label class="form-label col-xs-4 col-sm-2">
                网点号：</label>
            <div class="formControls col-xs-4 col-sm-2">
                <input id="plazaNo_input" name="sysPlaza.plaNo" type="hidden">
                <select id="plazaNo"  class="easyui-combobox"style="width:195px;" data-options="panelHeight:'auto'"  onchange=" rolechange()">
                <#list plazaList as plaza>
                    <option value="${plaza.plaNo?c}">${plaza.plaName}</option>
                </#list>
                </select>
            <#--
                        <select class="select" id="plazaNo"  name="sysPlaza.plaNo" size="1">
                        <#list plazaList as sysPlaza>
                            <option value="${sysPlaza.plaNo?c}">${sysPlaza.plaName}</option>
                        </#list>
                        </select>-->
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                传真：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" isPhone="1" data-msg-isMobile="请输入有效的传真" maxlength="7"
                       placeholder="" id="formfax"
                       name="fax"/>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                身份证号：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" isIdCardNo="1" data-msg-checkEmail="请输入有效的身份证"
                       placeholder="" id="formiDCard"
                       name="iDCard"/>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                邮箱：</label>

            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" checkEmail="${absoluteContextPath}" data-msg-checkEmail="请输入有效的E_mail"
                       placeholder="" id="formemail"
                       name="email"/>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                邮编：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text" isZipCode="1" data-msg-isZipCode="请输入有效的邮编"
                       placeholder="" id="formzip"
                       name="zip"/>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                地址：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text"
                       placeholder="" id="formaddress"
                       name="address"/>
            </div>
        </div>



        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                备注：</label>
            <div class="formControls col-xs-8 col-sm-8">
                <input type="text" class="input-text"
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
    $('#plazaNo').combobox({
        onChange: function (newValue, oldValue) {
            //alert(newValue)
            if(newValue!=""){
                var rolename = $("#form-user-add").find("select[name='roleId']").val();
                if (rolename == 1 || rolename == 3) {
                    if (newValue != "所有网点") {
                        layer.msg("管理员与卡管理的网点权限只能为所有网点权限", {icon: 1, time: 2000});
                        $('#plazaNo').combobox('select', 0);
                    }
                }
            }

        }
    });
    function rolechange(){
        var ids=$("#plazaNo").combobox('getText');
        var rolename= $("#form-user-add").find("select[name='roleId']").val();
        //alert(rolename);
        if(rolename==1||rolename==3){
            //alert(ids);
            if(ids!="所有网点"){
                layer.msg("管理员与卡管理的网店权限只能为所有权限", {icon: 1, time: 2000});
                $('#plazaNo').combobox('select', 0);
            }
        }

        //return false;
    }
    if ($.validator != undefined) {

        $.validator.addMethod("checkEmail", function (value, element, params) {
            var myreg = /^[_a-zA-Z0-9\-]+(\.[_a-zA-Z0-9\-]*)*@[a-zA-Z0-9\-]+([\.][a-zA-Z0-9\-]+)+$/;

            if(value !=''){if(!myreg.test(value)){return false;}};

            return true;

        } ,  "");
        $.validator.addMethod( "isZipCode",function(value,element, params){

            var pattern =/^[0-9]{6}$/;

            if(value !=''){if(!pattern.exec(value)){return false;}};

            return true;

        } ,  "请输入有效的邮政编码");


    }

    //表单验证
    $("#form-user-add").validate({
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var ids=$("#plazaNo").combobox('getValues');
            $("#plazaNo_input").val(ids);
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