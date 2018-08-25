/**
 * 添加用户操作
 */
$(document).ready(function(){
		
	$("#addUserBtn").click(function(){
		addUser();
		return false;
	})
});

//验证表单
function checkForm() {
	//客户名验证-只能是汉字
	var name = $("input[name='name']");
	var name_p = $("#name_p");
	var reg = /^[\u4e00-\u9fa5]+$/;
	//if (name.val() == "" || !reg.test(name.val())) {
	if (name.val() == "") {
		name_p.css("color","red");
		return false;
	}else{
		name_p.css("color","#578ebe");
	}
	//昵称验证-只能是汉字
	var nickName = $("input[name='nickName']");
	var nickName_p = $("#nickName_p");
	var reg = /^[\u4e00-\u9fa5]+$/;
	//if (nickName.val() == "" || !reg.test(nickName.val())) {
	if (nickName.val() == "") {
		nickName_p.css("color","red");
		return false;
	}else{
		nickName_p.css("color","#578ebe");
	}
	//联系方式验证-符合手机号规范
	var telephone = $("input[name='telephone']");
	var telephone_p = $("#telephone_p");
	var reg = /^1[3,5,8]\d{9}$/;
	if (telephone.val() == "" || !reg.test(telephone.val())) {
		telephone_p.css("color","red");
		return false;
	}else{
		telephone_p.css("color","#578ebe");
	}
	//验证用户密码不可为空
	var password = $("input[name='password']");
	var password_p = $("#password_p");
	var reg = /^[\u4e00-\u9fa5]+$/;
	//if (nickName.val() == "" || !reg.test(nickName.val())) {
	if (password.val() == "") {
		password_p.css("color","red");
		return false;
	}else{
		password_p.css("color","#578ebe");
	}
	return true;
}

function addUser(){
	if(!checkForm()){
		return false;
	}
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
