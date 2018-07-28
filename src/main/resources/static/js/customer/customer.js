/**
 * 客户管理页面js
 * 
 * @author xuefei
 */
// 定义客户信息的总条数
var custTotal = 0;
// 获取总条数
$.ajax({
	type : "post",
	async : false,
	url : "/cust/qryAllCustomerCount",
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

// 添加客户信息
$("#addCustomer").click(function() {
	layer.open({
		type : 2,
		title : '添加客户信息',
		shadeClose : false,
		shade : 0.7,
		maxmin : true, // 开启最大化最小化按钮
		scrollbar : true,// 是否允许出现滚动条
		anim : 5,
		moveOut : true,// 是否允许拖动到外面
		area : [ '85%', '82%' ],
		content : '../staticPages/addCustomer.html?updateFlag=0',
		end : function(index, layero) {

		}
	});

});

// 修改客户信息
function updateCustomer(id) {
	layer.open({
		type : 2,
		title : '修改客户信息',
		shadeClose : false,
		shade : 0.7,
		maxmin : true, // 开启最大化最小化按钮
		scrollbar : true,// 是否允许出现滚动条
		anim : 5,
		moveOut : true,// 是否允许拖动到外面
		area : [ '85%', '82%' ],
		content : '../staticPages/addCustomer.html?updateFlag=1&id=' + id,
		end : function(index, layero) {

		}
	});
}

// 删除客户信息
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
//显示客户信息
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
/*    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '300px;'
        ,shade: 0
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,btn: ['火速围观', '残忍拒绝']
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>layer ≠ layui<br><br>layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>我们此后的征途是星辰大海 ^_^</div>'
        ,success: function(layero){
          var btn = layero.find('.layui-layer-btn');
          btn.css('text-align', 'center');
          btn.find('.layui-layer-btn0').attr({
            href: 'http://www.layui.com/'
            ,target: '_blank'
          });
        }
      });*/
}

//鼠标移出事件
function mouseOut(){
	var layuiPro = layer.getFrameIndex("LAY_layuipro");
	layer.close(layuiPro);
}
// 进来就加载信息
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
						// 获取客户信息
						var custData = rtnData.customerList;
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
											var tdName = '<td class="sorting_1">'
													+ item.name + '</td>';
											// 性别判断
											var tdSex;
											if (item.sex == 0) {// 男
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
											// 状态判断
											var tdState;
											if (item.state == 0) {// 正常
												tdState = '<td class="center">'
														+ '正常' + '</td>';
											}
											// <a class="btn btn-success"
											// href="#"><i class="halflings-icon
											// white zoom-in"></i></a>
											var btn = '<td class="center "><a class="btn btn-success" href="#" onmouseover="seeCustomerInfo('
													+ item.id
													+ ')" onmouseout="mouseOut()"><i class="halflings-icon white zoom-in"></i></a><a class="btn btn-info" href="#" onClick="updateCustomer('
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