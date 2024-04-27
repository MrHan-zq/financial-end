
$(function() {
	var parm = {
		pageNow : 1,
		pageSize : 10
	};
	
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/msg/msgListAndButtonList',
		data: parm,
		success:function(data){
			$("#msgListAndButtonList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});

});

//分页
function goPage(x) 
{
	var parm = {
		pageNow : x,
		pageSize : 10
	};
		
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/msg/msgList',
		data: parm,
		success:function(data){
			$("#msgList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

function msgSearchBtn(){
	var title = $("#title").val();
	var author = $("#author").val();
	var parm = {
			pageNow : 1,
			pageSize : 10,
			title : title,
			author	: author
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/msg/msgList',
		data: parm,
		success:function(data){
			$("#msgList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}


function addMsg(){
	var parm = {};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/msg/addMsg',
		data: parm,
		success:function(data){
			$("#msgListAndButtonList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

function delMsg()
{
	//判断有没有选一个
	var bo = false;
	$("input:checkbox").each(function(){
		if($(this).is(':checked'))
		{
			bo = true;
			return false; // false时相当于break,如果return true 就相当于continure。  
		}
	});
	
	if(bo)
	{
		if(confirm("确认删除吗"))
		{
			var msgId = "";
			var count = 0;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{	
					var str_2 = $(this).attr("value");
					if(count > 0)
					{
						msgId = msgId + "," + str_2;
					}
					else
					{
						msgId = str_2;
					}
					count++;
				}
			});
			var parm = {
					msgId : msgId
				};
			$.ajax({
				type : "POST",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/msg/msgDelete',
				data: parm,
				success:function(data){
					$("#msgList").html(data);  
				},
				error: function (msg) {//ajax请求失败后触发的方法
					console.log(msg);//弹出错误信息
		        }
			});
		}
		else
		{
			return;
		}
	}
	else
	{
		alert("至少选择一个");
	}
}

//编辑按钮
function editMsg()
{
	//判断有没有选一个
	var bo = false;
	$("input:checkbox").each(function(){
		if($(this).is(':checked'))
		{
			bo = true;
			return false; // false时相当于break,如果return true 就相当于continure。  
		}
	});
	//判断选择了只有一个
	var msgId = "";
	var count = 0;
	$("input:checkbox").each(function(){
		if($(this).is(':checked'))
		{
			msgId = $(this).attr("value");
			if(count > 1)
			{
				return false; // false时相当于break,如果return true 就相当于continure。  
			}
			count++;
		}
	});
	
	var bo_2 = false;
	if(count == 1)
	{
		bo_2 = true;
	}
	if(bo)
	{
		if(bo_2)
		{	
			var parm = {
				msgId : msgId	
			};
			$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/msg/msgEditPage',
					data: parm,
					success:function(data){
						$("#msgListAndButtonList").html(data);  
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
			});
		}
		else
		{
			alert("只能选择一个");
		}
	}
	else
	{
		alert("至少选择一个");
	}
}