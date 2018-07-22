/**
 * 登录操作
 */
$(document).ready(function(){
	
	$("#loginBtn").click(function(){
		alert(0);
		doLogin();
		return false;
	})
});

function doLogin(){
	var inputPass = $("input[name='password']").val();
	var str = ""+salt.charAt(0)+salt.charAt(1) + inputPass +salt.charAt(3) + salt.charAt(5);
	var password = md5(str);
	
	alert(password);
	
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
        		alert("返回成功结果");
        	}else{
        		alert("返回失败结果");
        	}
        	return true;
        },
        error: function (message) {
            alert("系统环境异常");
            return false;
        }
	});
}
