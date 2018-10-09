/**
 * 用户页面
 */
layui.use('upload', function() {});

//编辑器用到的
var editorText,layedit ;
layui.use('layedit', function(){
	  layedit = layui.layedit;
	  editorText=layedit.build('reamrks',{
		  tool: [  'strong' ,'italic' ,'underline' ,'del','|','left', 'center', 'right', '|','link' ,'unlink' ,'face' ]
	  }); //建立编辑器	
});

var id = getUrlParam("id");
getDefaultData(id);

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
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

function getDefaultData(id){
	var data ='{ "id":"' + id + '"}';
	$.ajax({
		type: "post",
		async: false,
        url: "/prod/qryProductById",
        contentType: "application/json; charset=utf-8",
        data: data,
        dataType: "json",
        success: function (message) {
        	if(message.code == 0){
        		var rtnData = message.data;
            	
        		$("input[name='id']").val(rtnData.id);
        		$("input[name='name']").val(rtnData.name);
        		$("textarea[name='tip']").val(rtnData.tip);
        		$("input[name='price']").val(rtnData.price);
        	}else{
        		layer.open({
        			title : '提示',
        			content : message.msg
        		});
        	}
        	return true;
        },
        error: function (message) {
            //alert("系统环境异常");
        	layer.open({
    			title : '提示',
    			content : "系统环境异常"
    		});
            return false;
        }
	});
}

//用户修改提交
layui.use('upload', function(){});
$("#editProductBtn").click(function(){
	editProduct();
});

function editProduct(){
	//debugger;
	layedit.sync(editorText);//同步编辑器的内容到textarea
	var data = '{ "name":"' + $("input[name='name']").val() + '",id:' + $("input[name='id']").val()+ ',"tip":"' + $("input[name='tip']").val()
				+'","price":' + $("input[name='price']").val() + '}';
	
	$("#CustomerForm").ajaxSubmit({
		type : "post",
		forceSync : false,
		url : "/prod/updateProduct",
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
	
}