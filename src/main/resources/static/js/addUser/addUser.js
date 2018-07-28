/**
 * 添加用户操作
 */
$(document).ready(function(){
		
	$("#addUserBtn").click(function(){
		addUser();
		return false;
	})
});

function addUser(){
	debugger;
	var data = '{ "name":"' + $("input[name='name']").val() + '","nickName":"' + $("input[name='nickName']").val()
				+ '","telephone":"' + $("input[name='telephone']").val() + '","password":"' + $("input[name='password']").val()
				+'","remarks":"' + $("textarea[name='remarks']").val() + '"}'; 
	$.ajax({
		type: "post",
		forceSync : false,
        url: "/user/addUser",
        contentType: "application/json; charset=utf-8",
        data: data,
        dataType: "json",
        success: function (message) {
        	if(message.code != '100602'){
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
