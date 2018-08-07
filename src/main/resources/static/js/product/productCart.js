/**
 * 商品管理页面js
 * 
 * @author xuefei
 */
// 定义商品信息的总条数
getAllProdCartInfo();

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
			url : "/prod/qryProductByPageNum",
			contentType : "application/json; charset=utf-8",
			data : data,
			dataType : "json",
			success : function(message) {
				if (message.code == 0) {
					var rtnData = message.data;
					// 获取商品信息
					var custData = rtnData.productList;
					var html = '';
					$
							.each(
									custData,
									function(i, item) {

										var r = Number(i);
										var className = "";
										// 如果是偶数
										if (r % 2 == 0) {
											className = "even"
										} else {
											className = "odd"
										}
										var trHead = '<tr class="'
												+ className + '">';
										var tdCheckBox = '<td class="sorting_1">'
											+ '<label class="checkbox inline"><input type="checkbox" id="" value=""></label>' + '</td>';
										var tdImg = '<td class="sorting_1">'
											+ item.imgRef + '</td>';
										var tdName = '<td class="sorting_1">'
												+ item.name + '</td>';
										var tdTip = '<td class="center">'
												+ item.tip + '</td>';
										var tdPrice = '<td class="center">'
												+ item.price + '</td>';
										var tdAddTime = '<td class="center">'
												+ item.addTime + '</td>';
										var btn = '<td class="center "><a class="btn btn-danger" href="#" onClick="deleteProdCart('
												+ item.id
												+ ')"><i class="halflings-icon white trash"></i></a></td>';
										var trTail = '</tr>';
										html += trHead + tdCheckBox + tdImg + tdName
												+ tdTip + tdPrice
												+ tdAddTime + btn + trTail;

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
