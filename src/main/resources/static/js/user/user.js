/**
 * 用户页面
 */
debugger;
var custTotal = 0;
//获取总条数
$.ajax({
	type : "post",
	async : false,
	url : "/user/qryAllUserCount",
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

	function getDefaultData(startPage, endPage){
		var data = '{ "startPage":"' + startPage + '","endPage":"' + endPage + '"}';
		$.ajax({
			type: "post",
			async: true,
	        url: "/user/qryUserByPageNum",
	        contentType: "application/json; charset=utf-8",
	        data: data,
	        dataType: "json",
	        success: function (message) {
	        	if(message.code == 0){
	        		var rtnData = message.data.userList;
	        		
					var html = '';	            	
	            	for (var order in rtnData)
	            	{
            			var trHead = '<tr class="odd">';
            			var name = '<td class="  sorting_1">'+rtnData[order].name+'</td>';
            			var nickName = '<td class="center ">'+rtnData[order].nickName+'</td>';
            			var telephone = '<td class="center ">'+rtnData[order].telephone+'</td>';
            			var remarks = '<td class="center ">'+rtnData[order].remarks+'</td>';
            			var registerTime = '<td class="center ">'+rtnData[order].registerTime+'</td>';
            			var btn = '<td class="center "><a class="btn btn-info" href="#" onClick="editUser('+rtnData[order].id+')"><i class="halflings-icon white edit"></i></a><a class="btn btn-danger" href="#"  onClick="deleteUser('+rtnData[order].id+')"><i class="halflings-icon white trash"></i></a></td>'; 
            			var trTail = '</tr>';
	      				html = html + trHead + name + nickName + telephone +remarks +registerTime+ btn+ trTail;
	            	}
	            	$("#userInfo").html(html);	
	        	
	        	}else{
	        		layer.open({
	        			title : '提示',
	        			content : message.msg
	        		});
	        	}
	        	return true;
	        },
	        error: function (message) {
	            alert("系统环境异常");
	            return false;
	        }
		});
	}
	
	//用户添加
	layui.use('upload', function(){});
	$("#addUser").click(function(){
    layer.open({
        type: 2,
        title: '添加用户信息',
        shadeClose: false,
        shade: 0.7,
        maxmin: true, //开启最大化最小化按钮
        scrollbar: true,//是否允许出现滚动条
        anim: 5,
        moveOut: true,//是否允许拖动到外面
        area: ['85%', '82%'],
        content: '../staticPages/addUser.html',
        end:function(index, layero){
        	
        }
      });
    });
	
	function getUserInfoByPage(pageNum){
		getDefaultData(pageNum);
	}
	
	//删除用户信息
	function deleteUser(id){
		var data ='{ "id":"' + id + '"}'
		$.ajax({
			type: "post",
			async: true,
	        url: "/user/deleteUser",
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
	
	//修改用户信息
	function editUser(id){
		var data ='{ "id":"' + id + '"}';
		layer.open({
	        type: 2,
	        title: '修改用户信息',
	        shadeClose: false,
	        shade: 0.7,
	        maxmin: true, //开启最大化最小化按钮
	        scrollbar: true,//是否允许出现滚动条
	        anim: 5,
	        moveOut: true,//是否允许拖动到外面
	        area: ['85%', '82%'],
	        content: '../staticPages/editUser.html?id='+id,
	        end:function(index, layero){
	        	
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
			limits:[10, 20, 30, 50],
			jump : function(obj, first) {//切换分页的回调
				//首次执行
				if (first) {
					getDefaultData(0, obj.limit);
				} else {
					//obj包含了当前分页的所有参数
					var custCurr = obj.curr;//当前页
					var custLimit = obj.limit;//每页显示的条数
					getDefaultData((custCurr - 1) * custLimit, custLimit);
				}
			},
			layout : [ 'prev', 'page', 'next', 'skip', 'count', 'limit','refresh' ]
		});
	});