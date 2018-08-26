/**
 * 客户增加js
 * @author xuefei
 */

//最大上传文件的大小设定
var FILE_MAX_SIZE = 4*1024*1024;

layui.use('upload', function() {});
//获取参数方法
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
//获取前面传过来的状态：0-增加,1-修改
//var updateFlag = getUrlParam("updateFlag");

//关闭页面
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
	//商品名验证-只能是汉字
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
	//商品备注验证-只能是汉字
	var tip = $("input[name='tip']");
	var tip_p = $("#tip_p");
	var reg = /^[\u4e00-\u9fa5]+$/;
	//if (nickName.val() == "" || !reg.test(nickName.val())) {
	if (tip.val() == "") {
		tip_p.css("color","red");
		return false;
	}else{
		tip_p.css("color","#578ebe");
	}
	//商品单价不可为空
	var price = $("input[name='price']");
	var price_p = $("#price_p");
	var reg = /^[\u4e00-\u9fa5]+$/;
	//if (nickName.val() == "" || !reg.test(nickName.val())) {
	if (price.val() == "") {
		price_p.css("color","red");
		return false;
	}else{
		price_p.css("color","#578ebe");
	}
	//对文件大小以及文件类型做判断
	var headFile = $("input[name='headFile']");
	var path = headFile.val();
	var headFile_p = $("#headFile_p");
	if (path != "") {
		var fileSize = headFile[0].files[0].size;
		var extStart = path.lastIndexOf('.'), ext = path.substring(extStart,
				path.length).toUpperCase();
		if (ext !== '.PNG' && ext !== '.JPG' && ext !== '.JPEG'
				&& ext !== '.GIF' && fileSize > FILE_MAX_SIZE) {
			headFile_p.css("color", "red");
			headFile.val("");
			return false;
		} else {
			headFile_p.css("color", "#578ebe");
		}
	}else{
		headFile_p.css("color","red");
		return false;
	}
	return true;
}

//添加商品
$("#prodSubmitBtn").click(function() {
	if(!checkForm()){
		return false;
	}
	var data = '{ "name":"' + $("input[name='name']").val() + '","tip":"' + $("input[name='tip']").val()
				+ '","price":"' + $("input[name='price']").val()  + '"}'; 
	
	$("#CustomerForm").ajaxSubmit({
		type : "post",
		forceSync : false,
		url : "/prod/addProduct",
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