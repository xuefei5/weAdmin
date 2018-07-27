/**
 * 客户管理页面js
 * @author xuefei
 */

	layui.use('upload', function(){}); 
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
	function updateCustomer(id){
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
			content : '../staticPages/addCustomer.html?updateFlag=1&id='+id,
			end : function(index, layero) {

			}
		});
	}
	
	//删除客户信息
	function deleteCustomer(id){
		var data ='{ "id":"' + id + '"}'
		$.ajax({
			type: "post",
			async: true,
	        url: "/cust/deleteCustomer",
	        contentType: "application/json; charset=utf-8",
	        data: data,
	        dataType: "json",
	        success: function (message) {
	        	if(message.code == 0){
	        		layer.msg('删除成功', {
						icon : 1,
						time : 500//1秒关闭（如果不配置，默认是3秒）
					}, function() {
						//刷新页面
						location.reload();
					});
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
	}
	//进来就加载信息
	 	$.ajax({
			type: "post",
			async: true,
	        url: "/cust/qryAllCustomer",
	        contentType: "application/json; charset=utf-8",
	        data: "",
	        dataType: "json",
	        success: function (message) {
	        	if(message.code == 0){
	        		var rtnData = message.data;
	        		var html = '';
	            	$.each(rtnData,function(i,item){
	            		
	                	    var r = Number(i);
	                	    var className = "";
	                	    //如果是偶数
	                	    if(r%2==0){className = "even"}else{className = "odd"}
	                	    var trHead = '<tr class="'+className+'">';
	          				var tdName = '<td class="sorting_1">'+item.name+'</td>';
	          				//性别判断
	          				var tdSex;
	          				if(item.sex == 0){//男
	          					tdSex = '<td class="center">'+'男'+'</td>';
	          				}else{
	          					tdSex = '<td class="center">'+'女'+'</td>';
	          				} 
	          				var tdBirthday = '<td class="center">'+item.birthday+'</td>';
	          				var tdPhone = '<td class="center">'+item.telephone+'</td>';
	          				var tdAddTime = '<td class="center">'+item.addTime+'</td>';
	          				var tdRemarks= '<td class="center">'+item.remarks+'</td>';
	          				//状态判断
	          				var tdState;
	          				if(item.state == 0){//正常
	          					tdState = '<td class="center">'+'正常'+'</td>';
	          				}
	          				
	            			var btn = '<td class="center "><a class="btn btn-info" href="#" onClick="updateCustomer('+item.id+')"><i class="halflings-icon white edit"></i></a><a class="btn btn-danger" href="#" onClick="deleteCustomer('+item.id+')"><i class="halflings-icon white trash"></i></a></td>'; 
	          				var trTail = '</tr>';
	          				html+= trHead + tdName + tdSex + tdBirthday + tdPhone + tdAddTime + tdRemarks + tdState + btn + trTail;

	            	});
	            	
	            	$("#tbody").html(html);
	        	}else{
	        		layuiAlert(message.msg);
	        	}
	        	return true;
	        },
	        error: function (message) {
	        	layuiAlert("系统环境异常");
	            return false;
				}
			});