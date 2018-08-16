/**
 * 商品管理页面js
 * 
 * @author xuefei
 */
getAllProdCartInfo();
initCustomer();
layui.use('upload', function() {
});

// layui统一弹框
function layuiAlert(content) {
	layer.open({
		title : '提示',
		content : content
	});
}


//返回到商品列表
$("#backBtn").click(function() {
	window.history.back(-1); 
});


// 删除商品信息
function deleteProdCart(id) {
	var data = '{ "id":"' + id + '"}';
	$.ajax({
		type : "post",
		async : true,
		url : "/prod/deleteProdCart",
		contentType : "application/json; charset=utf-8",
		data : data,
		dataType : "json",
		success : function(message) {
			if (message.code == 0) {
				layer.msg('删除成功', {
					icon : 1,
					time : 500
				// 1秒关闭（如果不配置，默认是3秒）
				}, function() {
					// 刷新页面
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

//查询所有购物车商品
function getAllProdCartInfo() {
	$.ajax({
			type : "post",
			async : true,
			url : "/prod/qryAllProdCart",
			contentType : "application/json; charset=utf-8",
			data : "",
			dataType : "json",
			success : function(message) {
				if (message.code == 0) {
					var rtnData = message.data;
					// 获取商品信息
					var custData = rtnData.productCartList;
					var html = '';
					
					for (var order in custData)
	            	{
						
						var trHead = '<tr class="">';
						
					var tdCheckBox = '<td class="sorting_1">'
						+ '<label class="checkbox inline"><input type="checkbox" class="productBox" value=""></label>' + '</td>';
					var tdImg = '<td class="sorting_1">'
						+ custData[order].imgRef + '</td>';
					var tdName = '<td class="sorting_1">'
							+ custData[order].productName + '</td>';
					var tdTip = '<td class="center">'
							+ custData[order].productTip + '</td>';
					var tdPrice = '<td class="center">'
							+ custData[order].productPrice + '</td>';
					var tdAddTime = '<td class="center">'+'<input type="text" class="span6 typeahead" data-provide="typeahead" data-items="4" name="count" value="1"/>'
							+ '</td>';
					var btn = '<td class="center "><a class="btn btn-danger" href="#" onClick="deleteProdCart('
							+ custData[order].id
							+ ')"><i class="halflings-icon white trash"></i></a></td>';
					var trTail = '</tr>';
					html += trHead + tdCheckBox + tdImg + tdName
							+ tdTip + tdPrice
							+ tdAddTime + btn + trTail;
	            	}
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

//初始化所有用户
function initCustomer() {
	$.ajax({
			type : "post",
			async : true,
			url : "/cust/qryAllCustomer",
			contentType : "application/json; charset=utf-8",
			data : "",
			dataType : "json",
			success : function(message) {
				if (message.code == 0) {
					var rtnData = message.data;
					
					// 获取客户信息
					var custData = rtnData.productCartList;
					var html = '';
					
					for (var order in custData)
	            	{
						
					var trHead = '<tr class="">';
					
					var tdCheckBox = '<td class="sorting_1">'
						+ '<label class="checkbox inline"><input type="checkbox" class="productBox" value=""></label>' + '</td>';
					var tdImg = '<td class="sorting_1">'
						+ custData[order].imgRef + '</td>';
					var tdName = '<td class="sorting_1">'
							+ custData[order].productName + '</td>';
					var tdTip = '<td class="center">'
							+ custData[order].productTip + '</td>';
					var tdPrice = '<td class="center">'
							+ custData[order].productPrice + '</td>';
					var tdAddTime = '<td class="center">'+'<input type="text" class="span6 typeahead" data-provide="typeahead" data-items="4" name="count" value="1"/>'
							+ '</td>';
					var btn = '<td class="center "><a class="btn btn-danger" href="#" onClick="deleteProdCart('
							+ custData[order].id
							+ ')"><i class="halflings-icon white trash"></i></a></td>';
					var trTail = '</tr>';
					html += trHead + tdCheckBox + tdImg + tdName
							+ tdTip + tdPrice
							+ tdAddTime + btn + trTail;
	            	}
					$("#test").html(html);
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














