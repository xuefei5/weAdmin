/**
 * 用户页面
 */

	$(function(){
		getDefaultData();
	});
	
	function getDefaultData(){

		$.ajax({
			type: "post",
			async: true,
	        url: "/user/qryUserByPageNum",
	        contentType: "application/json; charset=utf-8",
	        data: "",
	        dataType: "json",
	        success: function (message) {
	        	if(message.code == 0){
	        		var rtnData = message.data.userList;
	        		var pageNum = message.data.pageNum;
	            	
	        		debugger;
	        		
					var html = '';	            	
	            	for (var order in rtnData)
	            	{
            			var trHead = '<tr class="odd">';
            			var name = '<td class="  sorting_1">'+rtnData[order].name+'</td>';
            			var telephone = '<td class="center ">'+rtnData[order].telephone+'</td>';
            			var remarks = '<td class="center ">'+rtnData[order].remarks+'</td>';
            			var registerTime = '<td class="center ">'+rtnData[order].registerTime+'</td>';
            			var btn = '<td class="center "><a class="btn btn-info" href="#"> <i class="halflings-icon white edit"></i></a><a class="btn btn-danger" href="#"  onClick="deleteUser('+rtnData[order].id+')"><i class="halflings-icon white trash"></i></a></td>'; 
            			var trTail = '</tr>';
	      				html = html + trHead + name + telephone +remarks +registerTime+ btn+ trTail;
	            	}
	            	$("#userInfo").html(html);	
	        		
	            	var pageHtml = '';
	            	var liHead = '<li class="prev disabled"><a href="#">← 上一页</a></li>';
        			pageHtml = pageHtml + liHead;
	            	for (var page in pageNum)
	            	{
            			if(page==1){
            				pageItem = '<li class="active"><a href="#">'+pageNum[page]+'</a></li>';
            				pageHtml = pageHtml + pageItem;
            			}else{
            				pageItem = '<li><a href="#">'+pageNum[page]+'</a></li>';
            				pageHtml = pageHtml + pageItem;
            			}
	            	}
	            	var liTail = '<li class="next"><a href="#">下一页   →</a></li>';
        			pageHtml = pageHtml + liTail;
	            	$("#pageInfo").html(pageHtml);	
	
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
        content: '../staticPages/addCustomer.html',
        end:function(index, layero){
        	
        }
      });
    });
	
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