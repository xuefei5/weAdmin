/**
 * 客户管理页面js
 * @author xuefei
 */
//定义客户信息的总条数
var custTotal = 0;
//获取总条数
$.ajax({
	type : "post",
	async : false,
	url : "/cust/qryAllCustomerCount",
	contentType : "application/json; charset=utf-8",
	data : "",
	dataType : "json",
	success : function(message) {
		if (message.code == 0) {
			custTotal =  message.data;
			}
		else {
			layuiAlert(message.msg);
		}
		return true;
	},
	error : function() {
		layuiAlert("系统环境异常");
		return false;
	}
});

layui.use('upload', function() {
});

//layui统一弹框
function layuiAlert(content) {
	layer.open({
		title : '提示',
		content : content
	});
}

//添加客户信息
$("#addCustomer").click(function() {
	layer.open({
		type : 2,
		title : '添加客户信息',
		shadeClose : false,
		shade : 0.7,
		maxmin : true, //开启最大化最小化按钮
		scrollbar : true,//是否允许出现滚动条
		anim : 5,
		moveOut : true,//是否允许拖动到外面
		area : [ '85%', '82%' ],
		content : '../staticPages/addCustomer.html?updateFlag=0',
		end : function(index, layero) {

		}
	});

});

//修改客户信息
function updateCustomer(id) {
	layer.open({
		type : 2,
		title : '修改客户信息',
		shadeClose : false,
		shade : 0.7,
		maxmin : true, //开启最大化最小化按钮
		scrollbar : true,//是否允许出现滚动条
		anim : 5,
		moveOut : true,//是否允许拖动到外面
		area : [ '85%', '82%' ],
		content : '../staticPages/addCustomer.html?updateFlag=1&id=' + id,
		end : function(index, layero) {

		}
	});
}

//删除客户信息
function deleteCustomer(id) {
	var data = '{ "id":"' + id + '"}';
	$.ajax({
		type : "post",
		async : true,
		url : "/cust/deleteCustomer",
		contentType : "application/json; charset=utf-8",
		data : data,
		dataType : "json",
		success : function(message) {
			if (message.code == 0) {
				layer.msg('删除成功', {
					icon : 1,
					time : 500
				//1秒关闭（如果不配置，默认是3秒）
				}, function() {
					//刷新页面
					location.reload();
				});
			} else {
				layuiAlert(message.msg);
			}
			return true;
		},
		error : function() {
			layuiAlert("系统环境异常");
			return false;
		}
	});
}
//进来就加载信息
function getAllCustomerInfo(startPage, endPage) {
	var data = '{ "startPage":"' + startPage + '","endPage":"' + endPage + '"}';
	$.ajax({
				type : "post",
				async : true,
				url : "/cust/qryCustomerByPageNum",
				contentType : "application/json; charset=utf-8",
				data : data,
				dataType : "json",
				success : function(message) {
					if (message.code == 0) {
						var rtnData = message.data;
						//获取客户信息
						var custData = rtnData.customerList;
						var html = '';
						$.each(
										custData,
										function(i, item) {

											var r = Number(i);
											var className = "";
											//如果是偶数
											if (r % 2 == 0) {
												className = "even"
											} else {
												className = "odd"
											}
											var trHead = '<tr class="'
													+ className + '">';
											var tdName = '<td class="sorting_1">'
													+ item.name + '</td>';
											//性别判断
											var tdSex;
											if (item.sex == 0) {//男
												tdSex = '<td class="center">'
														+ '男' + '</td>';
											} else {
												tdSex = '<td class="center">'
														+ '女' + '</td>';
											}
											var tdBirthday = '<td class="center">'
													+ item.birthday + '</td>';
											var tdPhone = '<td class="center">'
													+ item.telephone + '</td>';
											var tdAddTime = '<td class="center">'
													+ item.addTime + '</td>';
											var tdRemarks = '<td class="center">'
													+ item.remarks + '</td>';
											//状态判断
											var tdState;
											if (item.state == 0) {//正常
												tdState = '<td class="center">'
														+ '正常' + '</td>';
											}

											var btn = '<td class="center "><a class="btn btn-info" href="#" onClick="updateCustomer('
													+ item.id
													+ ')"><i class="halflings-icon white edit"></i></a><a class="btn btn-danger" href="#" onClick="deleteCustomer('
													+ item.id
													+ ')"><i class="halflings-icon white trash"></i></a></td>';
											var trTail = '</tr>';
											html += trHead + tdName + tdSex
													+ tdBirthday + tdPhone
													+ tdAddTime + tdRemarks
													+ tdState + btn + trTail;

										});

						$("#tbody").html(html);
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
//分页有关的js
layui.use('laypage', function() {
	var laypage = layui.laypage;
	//执行一个laypage实例
	laypage.render({
		elem : 'paging', //注意，这里的 test1 是 ID，不用加 # 号
		count : custTotal,//数据总数，从服务端得到
		//curr:1,//获取起始页
		groups : 3,//连续出现的页码个数
		theme : '#578ebe',//自定义颜色
		jump : function(obj, first) {//切换分页的回调
			//首次执行
			if (first) {
				getAllCustomerInfo(0, obj.limit);
			} else {
				//obj包含了当前分页的所有参数
				var custCurr = obj.curr;//当前页
				var custLimit = obj.limit;//每页显示的条数
				getAllCustomerInfo((custCurr - 1) * custLimit, custCurr * custLimit);
			}
		}

	});
});