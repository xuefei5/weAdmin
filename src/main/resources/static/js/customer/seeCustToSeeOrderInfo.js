/**
 * 订单详情js
 * @author xuefei
 */

layui.use('upload', function() {
});
//获取参数方法
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; //返回参数值
}
//根据订单ID获取到订单信息与商品信息
var id = getUrlParam("id");

var data = '{ "id":"' + id + '"}';
$.ajax({
	type : "post",
	async : false,
	url : "/cust/qryProductOrderInfoByOrderId",
	contentType : "application/json; charset=utf-8",
	data : data,
	dataType : "json",
	success : function(message) {
		if (message.code == 0) {
			var data = message.data;
			var order = data.order;
			$("#id").html(order.id);
			$("#customerId").html(order.customerId);
			$("#ordertime").html(order.ordertime);
			$("#total").html(order.total);
			//显示商品信息
			disPlayProductInfo(data.productOrderList);
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
//显示订单信息
function disPlayProductInfo(productOrderList) {
	var html = '';
	$
			.each(
					productOrderList,
					function(i, item) {

						var r = Number(i);
						var className = "";
						// 如果是偶数
						if (r % 2 == 0) {
							className = "even"
						} else {
							className = "odd"
						}
						var trHead = '<tr class="' + className + '">';
						var tdProductImgRef = '<td class="sorting_1"><img height="50" width="50" src="'+item.productImgRef+'"></img></td>';
						var tdProductName = '<td class="center">' + item.productName + '</td>';
						var tdPrice = '<td class="center">' + item.price + '</td>';
						var tdAmount = '<td class="center">'+ item.amount + '</td>';
						var tdProductTip = '<td class="center">'+ item.productTip + '</td>';
						var trTail = '</tr>';
						html += trHead + tdProductImgRef + tdProductName + tdPrice
								+ tdAmount + tdProductTip + trTail;

					});

	$("#tbody").html(html);
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