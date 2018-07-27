/**
 * 用户页面
 */
$(document).ready(function(){
	    var id = getUrlParam("id");
	    getDefaultData(id);
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function getDefaultData(id){
	var data ='{ "id":"' + id + '"}';
	$.ajax({
		type: "post",
		async: true,
        url: "/user/qryUserById",
        contentType: "application/json; charset=utf-8",
        data: data,
        dataType: "json",
        success: function (message) {
        	if(message.code == 0){
        		var rtnData = message.data;
            	
        		$("input[name='id']").val(rtnData.id);
        		$("input[name='name']").val(rtnData.name);
        		$("input[name='nickName']").val(rtnData.nickName);
        		$("input[name='telephone']").val(rtnData.telephone);
        		$("input[name='password']").val(rtnData.password);
        		$("input[name='remarks']").val(rtnData.remarks);
        	}else{
        		layer.open({
        			title : '提示',
        			content : message.msg
        		});
        	}
        	return true;
        },
        error: function (message) {
            alert("系统环境异常");
            return false;
        }
	});
}