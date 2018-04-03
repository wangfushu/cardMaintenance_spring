/**
 * Created by Administrator on 2017/7/1.
 */
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
if ($.validator != undefined) {
    $.validator.addMethod("checkWithId", function (value, element, params) {
        var id = $("#formUserId").val();
        if (id !== undefined && id !== "") {
            return true;
        }
        var data = {};
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

function fileNamePost(fileName) {
    var extStart = fileName.lastIndexOf(".");
    var ext = fileName.substring(extStart + 1, fileName.length).toUpperCase();
    return ext.toLowerCase();
}


