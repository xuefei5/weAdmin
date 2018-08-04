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

	return true;
}

//添加商品
$("#prodSubmitBtn").click(function() {
	if(!checkForm()){
		return false;
	}
	var data = '{ "name":"' + $("input[name='name']").val() + '","tip":"' + $("input[name='tip']").val()
				+ '","price":"' + $("input[name='price']").val()  + '"}'; 
	$.ajax({
		type: "post",
		forceSync : false,
        url: "/prod/addProduct",
        contentType: "application/json; charset=utf-8",
        data: data,
        dataType: "json",
        success: function (message) {
        	if(message.code != '100310'){
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
})