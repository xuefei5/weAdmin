/**
 * 客户增加js
 * @author xuefei
 */

layui.use('upload', function() {});
$("#closePage").click(function() {
	var index = parent.layer.getFrameIndex(window.name);
	layer.confirm('确定关闭此页面?', {
		icon : 3,
		title : '提示'
	}, function() {
		parent.layer.close(index);
	});
});
//layui统一弹框
function layuiAlert(content) {
	layer.open({
		title : '提示',
		content : content
	});
}

//验证表单
function checkForm() {
	//客户名验证-只能是汉字
	var name = $("input[name='name']");
	var name_p = $("#name_p");
	var reg = /^[\u4e00-\u9fa5]+$/;
	if (name.val() == "" || !reg.test(name.val())) {
		name_p.css("color","red");
		return false;
	}else{
		name_p.css("color","#578ebe");
	}
	//昵称验证-只能是汉字
	var nickName = $("input[name='nickName']");
	var nickName_p = $("#nickName_p");
	var reg = /^[\u4e00-\u9fa5]+$/;
	if (nickName.val() == "" || !reg.test(nickName.val())) {
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
	return true;
}

//保存
$("#custSubmitBtn").click(function() {
	if(!checkForm()){
		return false;
	}
	$("#CustomerForm").ajaxSubmit({
		type : "post",
		forceSync : false,
		url : "/cust/addCustomer",
		success : function(message) {
			if (message.code == 0) {
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
			} else {
				layuiAlert(message.msg);
			}
			return true;
		},
		error : function(message) {
			layuiAlert("系统环境异常");
			return false;
		}
	});
})