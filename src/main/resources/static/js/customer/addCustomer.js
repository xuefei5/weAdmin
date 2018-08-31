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
var updateFlag = getUrlParam("updateFlag");
//如果是修改就进行查询然后填入表格中
if(null!=updateFlag&&updateFlag==1){
	var id = getUrlParam("id");
	
	var data ='{ "id":"' + id + '"}';
	$.ajax({
		type: "post",
		async: false,
        url: "/cust/qryCustomerById",
        contentType: "application/json; charset=utf-8",
        data: data,
        dataType: "json",
        success: function (message) {
        	if(message.code == 0){
        		var customer = message.data;
        		$("input[name='id']").val(customer.id);
        		$("input[name='name']").val(customer.name);
        		$("input[name='nickName']").val(customer.nickName);
        		$("select[name='sex']").val(customer.sex);
        		//时间格式转换
        		var reg =/(\d{4})\-(\d{2})\-(\d{2})/;
        		$("input[name='birthday']").val((customer.birthday).replace(reg,"$2/$3/$1"));
        		$("input[name='telephone']").val(customer.telephone);
        		//$("input[name='headFile']").val(customer.headFile);
        		$("textarea[name='remarks']").val(customer.remarks);
        	}else{
        		layuiAlert(message.msg);
        	}
        	return true;
        },
        error:function(){
        	layuiAlert("系统环境异常");
        	return false;
        }
  });
}else{//如果是新增就移除该插件，否则会出错
	$("input[name='id']").remove();
}
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
	//客户名验证-只能是汉字
	var name = $("input[name='name']");
	var name_p = $("#name_p");
	var reg = /^[\u4e00-\u9fa5]+$/;
	//if (name.val() == "" || !reg.test(name.val())) {
	if (name.val() == "" ) {
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
	if (nickName.val() == "" ) {
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

//更新验证表单
function upCheckForm() {
	//客户名验证-只能是汉字
	var name = $("input[name='name']");
	var name_p = $("#name_p");
	var reg = /^[\u4e00-\u9fa5]+$/;
	//if (name.val() == "" || !reg.test(name.val())) {
	if (name.val() == "" ) {
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
	if (nickName.val() == "" ) {
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
		}
	}
	return true;
}

//保存
$("#custSubmitBtn").click(function() {
	
	var sendUrl = "/cust/addCustomer";
	//如果是修改操作
	if(null!=updateFlag&&updateFlag==1){
		sendUrl = "/cust/updateCustomer";
	}
	if(sendUrl == "/cust/addCustomer"){
		if(!checkForm()){
			return false;
		}
	}else{
		if(!upCheckForm()){
			return false;
		}
	}
	$("#CustomerForm").ajaxSubmit({
		type : "post",
		forceSync : false,
		url : sendUrl,
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