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
						
						var trHead = '<tr class="productCartInfo">';
						
					var tdCheckBox = '<td class="sorting_1">'
						+ '<input type="checkbox" class="productBox" value="">' + '</td>';
					var tdId = '<td class="sorting_1 productId" style="display: none;">'
						+ custData[order].productId + '</td>';
					var tdImg = '<td class="img">'
						+ '<img class="productImgRef" src="' + custData[order].productImgRef + '" height="50" width="50"></img></td>';
					var tdName = '<td class="productCartName">'
							+ custData[order].productName + '</td>';
					var tdTip = '<td class="productCartTip">'
							+ custData[order].productTip + '</td>';
					var tdPrice = '<td class="center productPrice">'
							+ custData[order].productPrice + '</td>';
					var tdAddTime = '<td class="center tdCount">'+'<input type="text" class="productCount" data-provide="typeahead" data-items="4" name="count" value="1"/>'
							+ '</td>';
					var btn = '<td class="center"><a class="btn btn-danger" href="#" onClick="deleteProdCart('
							+ custData[order].id
							+ ')"><i class="halflings-icon white trash"></i></a></td>';
					var trTail = '</tr>';
					html += trHead + tdCheckBox + tdId + tdImg + tdName
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
					debugger;
					var rtnData = message.data;
					
					// 获取客户信息
					var custData = rtnData;
					var html = '';
					
					for (var order in custData)
	            	{
						html = html + '<option value="' + custData[order].id + '">' + custData[order].name + '</option>';
	            	}
					$("#customerSelect").html(html);
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

//商品模拟购买
$("#payBtn").click(function() {
	var $productBox = $('.productBox');
	var customerSelected=$("#customerSelect option:selected");
	var data = '{"params":{"productList":[';
	$productBox.each(function () {
        if ($(this).is(':checked')) {
        	debugger;
			var productId = parseInt($(this).parents('.productCartInfo').find('.productId').html().substring(0));
			var productCartName = $(this).parents('.productCartInfo').find('.productCartName').html().substring(0);
			var productCartTip = $(this).parents('.productCartInfo').find('.productCartTip').html().substring(0);
			var productPrice = $(this).parents('.productCartInfo').find('.productPrice').html().substring(0);
			var productCount = $(this).parents('.productCartInfo').find('.tdCount').find('.productCount').val();
			var productImgRef = $(this).parents('.productCartInfo').find('.img').find('.productImgRef')[0].src;
			
			var productMsgStr = '{"product": { "productId":' + productId + ',"productName":"' + productCartName;
			productMsgStr += '","productTip":"' + productCartTip;
			productMsgStr += '","productPrice":' + productPrice;
			productMsgStr += ',"productCount":' + productCount;
			productMsgStr += ',"productImgRef":"' + productImgRef + '"}},';
			data+=productMsgStr;
        }
    });
	data = data.substr(0, data.length - 1);
	data+='],';
	data += '"customerId":' + customerSelected.val() + '}}';
	//alert(data);
	
	$.ajax({
		async: false,
        url: "/prod/purchaseProduct",
		type : "post",
		contentType : "application/json; charset=utf-8",
		data : data,
		dataType : "json",
		success : function(message) {
			if (message.code == 0) {
				layer.msg('购买成功', {
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
	
});












