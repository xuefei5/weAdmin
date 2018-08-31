/**
 * 商品管理页面js
 * 
 * @author xuefei
 */
// 定义商品信息的总条数
var custTotal = 0;
//商品查看-弹出层ID
var LAY_layuipro;
// 获取总条数
$.ajax({
	type : "post",
	async : false,
	url : "/prod/qryAllProductCount",
	contentType : "application/json; charset=utf-8",
	data : "",
	dataType : "json",
	success : function(message) {
		if (message.code == 0) {
			custTotal = message.data;
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

layui.use('upload', function() {
});

// layui统一弹框
function layuiAlert(content) {
	layer.open({
		title : '提示',
		content : content
	});
}

// 添加商品信息
$("#addProd").click(function() {
	layer.open({
		type : 2,
		title : '添加商品信息',
		shadeClose : false,
		shade : 0.7,
		maxmin : true, // 开启最大化最小化按钮
		scrollbar : true,// 是否允许出现滚动条
		anim : 5,
		moveOut : true,// 是否允许拖动到外面
		area : [ '85%', '82%' ],
		content : '../staticPages/addProduct.html',
		end : function(index, layero) {

		}
	});

});

//购物车商品信息
$("#toProdCart").click(function() {
	window.location.href="../staticPages/productCart.html"; 
});

// 修改商品信息
function updateProduct(id) {
	layer.open({
		type : 2,
		title : '修改商品信息',
		shadeClose : false,
		shade : 0.7,
		maxmin : true, // 开启最大化最小化按钮
		scrollbar : true,// 是否允许出现滚动条
		anim : 5,
		moveOut : true,// 是否允许拖动到外面
		area : [ '85%', '82%' ],
		content : '../staticPages/editProduct.html?id=' + id,
		end : function(index, layero) {

		}
	});
}

// 删除商品信息
function deleteProduct(id) {
	var data = '{ "id":"' + id + '"}';
	$.ajax({
		type : "post",
		async : true,
		url : "/prod/deleteProduct",
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

//添加商品购物车信息
function addProdToCart(id) {
	var data = '{ "id":"' + id + '"}';
	$.ajax({
		type : "post",
		async : true,
		url : "/prod/addProdToCart",
		contentType : "application/json; charset=utf-8",
		data : data,
		dataType : "json",
		success : function(message) {
			if (message.code == 0) {
				layer.msg('添加到购物车成功', {
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

//显示商品信息
function seeCustomerInfo(id) {
	var data = '{ "id":"' + id + '"}';
	$.ajax({
		type : "post",
		async : true,
		url : "/cust/qryCustomerById",
		contentType : "application/json; charset=utf-8",
		data : data,
		dataType : "json",
		success : function(message) {
			//成功返回则弹出层
			if (message.code == 0) {
				openSeeCustInfo(message.data);
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
//弹出框
function openSeeCustInfo(customer){
	LAY_layuipro=layer.open({
        type: 1
        ,offset :"["+getMouseXY()+"]"
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: ['50%','30%']
        ,shade: 0
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '这是一个提示层'
      });
}

//鼠标移出事件
function mouseOut(){
	layer.close(LAY_layuipro);
}

function getMouseXY(){  
    var varpositionX=$("#aaaaa").pageX-$("#aaaaa").offset().left; //获取当前鼠标相对img的X坐标  
    var varpositionY=$("#aaaaa").offset().top; //获取当前鼠标相对img的Y坐标  
    return [varpositionX,varpositionY];
}
// 进来就加载信息
function getAllCustomerInfo(startPage, endPage) {
	debugger;
	var data = '{ "startPage":"' + startPage + '","endPage":"' + endPage + '"}';
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
											//+ item.imgRef 
											var tdImg = '<td class="sorting_1">'
												+ '<img src="'+item.imgRef +'" height="50" width="50"></img></td>';
											var tdName = '<td class="sorting_1">'
													+ item.name + '</td>';
											var tdTip = '<td class="center">'
													+ item.tip + '</td>';
											var tdPrice = '<td class="center">'
													+ item.price + '</td>';
											var tdAddTime = '<td class="center">'
													+ item.addTime + '</td>';
											var btn = '<td class="center "><a class="btn btn-success" href="#"  onClick="addProdToCart('
													+ item.id
													+ ')" onmouseout="mouseOut()"><i class=" halflings-icon shopping-cart white"></i></a><a class="btn btn-info" href="#" onClick="updateProduct('
													+ item.id
													+ ')"><i class="halflings-icon white edit"></i></a><a class="btn btn-danger" href="#" onClick="deleteProduct('
													+ item.id
													+ ')"><i class="halflings-icon white trash"></i></a></td>';
											var trTail = '</tr>';
											html += trHead + tdImg + tdName
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
// 分页有关的js
layui.use('laypage', function() {
	var laypage = layui.laypage;
	// 执行一个laypage实例
	laypage.render({
				elem : 'paging', // 注意，这里的 test1 是 ID，不用加 # 号
				count : custTotal,// 数据总数，从服务端得到
				// curr:1,//获取起始页
				groups : 3,// 连续出现的页码个数
				theme : '#578ebe',// 自定义颜色
				limits : [ 10, 20, 30, 50 ],
				jump : function(obj, first) {// 切换分页的回调
					// 首次执行
					if (first) {
						getAllCustomerInfo(0, obj.limit);
					} else {
						// obj包含了当前分页的所有参数
						var custCurr = obj.curr;// 当前页
						var custLimit = obj.limit;// 每页显示的条数
						getAllCustomerInfo((custCurr - 1) * custLimit,custLimit);
					}
				},
				layout : [ 'prev', 'page', 'next', 'skip', 'count', 'limit','refresh' ]

			});
});
$("[data-toggle='tooltip']").tooltip();