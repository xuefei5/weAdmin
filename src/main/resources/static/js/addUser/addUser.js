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
		async: true,
        url: "/user/addUser",
        contentType: "application/json; charset=utf-8",
        data: data,
        dataType: "json",
        success: function (message) {
        	if(message.code != '100602'){
        		layer.open({
        			title : '提示',
        			content : "用户添加成功"
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
