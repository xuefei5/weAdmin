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
//根据客户ID获取到客户信息
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
        		var tdSex ="";
        		if (customer.sex == 0) {// 男
        			tdSex = '男';
        		} else {
        			tdSex = '女';
        		}
        		$("#id").html(customer.id);
        		$("#name").html(customer.name);
        		$("#sex").html(tdSex);
        		$("#telephone").html(customer.telephone);
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