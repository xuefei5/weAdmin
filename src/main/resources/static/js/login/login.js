/**
 * 登录操作
 */
$(document).ready(function(){
	
	$("#loginBtn").click(function(){
		doLogin();
		return false;
	})
});

function doLogin(){
	var inputPass = $("input[name='password']").val();
	var str = ""+salt.charAt(0)+salt.charAt(1) + inputPass +salt.charAt(3) + salt.charAt(5);
	var password = md5(str);
	
	var data = '{ "name":"' + $("input[name='name']").val() + '","password":"' + $("input[name='password']").val() + '"}'; 
	
	$.ajax({
		type: "post",
		async: true,
        url: "login",
        contentType: "application/json; charset=utf-8",
        data: data,
        dataType: "json",
        success: function (message) {
        	if(message.code == 0){
        		window.location = "/user/toMainPage";
        	}else{
        		layer.open({
        			title : '提示',
        			content : message.msg
        		});
        	}
        	return true;
        },
        error: function (message) {
        	layer.open({
    			title : '注意',
    			content : '系统环境异常'
    		});
            return false;
        }
	});
}
