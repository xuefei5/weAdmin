/**
 * 客户增加js
 * @author xuefei
 */

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
	
	var data ='{ "id":"' + id + '"}'
	$.ajax({
		type: "post",
		async: true,
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
        		$("input[name='sex']").val(customer.sex);
        		var reg =/(\d{4})\-(\d{2})\-(\d{2})/;
        		$("input[name='birthday']").val((customer.birthday).replace(reg,"$2/$3/$1"));
        		$("input[name='telephone']").val(customer.telephone);
        		//$("input[name='headFile']").val(customer.headFile);
        		$("input[name='remarks']").val(customer.remarks);
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
	var sendUrl = "/cust/addCustomer";
	//如果是修改操作
	if(null!=updateFlag&&updateFlag==1){
		sendUrl = "/cust/updateCustomer";
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

// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  