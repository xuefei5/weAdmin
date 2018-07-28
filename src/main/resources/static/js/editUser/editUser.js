/**
 * 用户页面
 */

var id = getUrlParam("id");
getDefaultData(id);

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function getDefaultData(id){
	var data ='{ "id":"' + id + '"}';
	$.ajax({
		type: "post",
		async: false,
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
        		$("textarea[name='remarks']").val(rtnData.remarks);
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

//用户修改提交
layui.use('upload', function(){});
$("#editUserBtn").click(function(){
	editUser();
});

function editUser(){
	debugger;
	var data = '{ "name":"' + $("input[name='name']").val() + '",id:"' + $("input[name='id']").val()+ '","nickName":"' + $("input[name='nickName']").val()
				+ '","telephone":"' + $("input[name='telephone']").val() + '","password":"' + $("input[name='password']").val()
				+'","remarks":"' + $("textarea[name='remarks']").val() + '"}';
	$.ajax({
		type: "post",
		forceSync : false,
        url: "/user/updateUser",
        contentType: "application/json; charset=utf-8",
        data: data,
        dataType: "json",
        success: function (message) {
        	if(message.code == 0){
        		//点击确认关闭该层
				var index = parent.layer.getFrameIndex(window.name);
				layer.msg('保存成功', {
					icon : 1,
					time : 1200
				//1.2秒关闭（如果不配置，默认是3秒）
				}, function() {
					//关闭弹出层并且刷新父页面
					parent.layer.close(index);
					parent.location.reload();
				});
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