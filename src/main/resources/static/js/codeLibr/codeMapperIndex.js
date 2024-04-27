layui.use(['layer'], function () {
    var layer = layui.layer;  
});

$(function() {
	//goPage(1); 
	var parm = {
		pageNow : 1,
		pageSize : 10
	};
	
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/codeLibr/codeMapperListAndButtonList',
		data: parm,
		success:function(data){
			$("#codeMapperListAndButtonList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
});
    
//分页
function goPage(x) 
{
	id = layer.msg('正在查询，请稍后', {
        icon: 16,
        shade: 0.4,
        time: false //取消自动关闭
    });
	var codeName = $("#codeName").val();
	var codeNo = $("#codeNo").val();
	var parm = {
			pageNow : x,
			pageSize : 10,
			codeName : codeName,
			codeNo : codeNo
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/codeLibr/codeMapperList',
		data: parm,
		success:function(data){
			$("#codeMapperList").html(data);  
			layer.close(id);//手动关闭
		},
		error: function (msg) {//ajax请求失败后触发的方法
			layer.close(id);//手动关闭
            layer.msg('查询失败');
			console.log(msg);//弹出错误信息
        }
	});
}

function onSearchBtn(){
	goPage(1); 
 	/*id = layer.msg('正在查询，请稍后', {
        icon: 16,
        shade: 0.4,
        time: false //取消自动关闭
    });
	var codeName = $("#codeName").val();
	var codeNo = $("#codeNo").val();
	var parm = {
			pageNow : 1,
			pageSize : 10,
			codeName : codeName,
			codeNo : codeNo
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/codeLibr/codeMapperList',
		data: parm,
		success:function(data){
			$("#codeMapperList").html(data);  
			layer.close(id);//手动关闭
		},
		error: function (msg) {//ajax请求失败后触发的方法
			layer.close(id);//手动关闭
            layer.msg('查询失败');
			console.log(msg);//弹出错误信息
        }
	});*/
}
  
function addCodeMapper(){
	var parm = {};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/codeLibr/addCodeMapper',
		data: parm,
		success:function(data){
			$("#codeMapperListAndButtonList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}


//编辑按钮
function editCodeMapper()
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
	var codeNo = "";
	var count = 0;
	$("input:checkbox").each(function(){
		if($(this).is(':checked'))
		{
			codeNo = $(this).attr("value");
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
				codeNo : codeNo	
			};
			$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/codeLibr/codeMapperEditPage',
					data: parm,
					success:function(data){
						$("#codeMapperListAndButtonList").html(data);  
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
    
function delCodeMapper()
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
			var codeNo = "";
			var count = 0;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{	
					var str_2 = $(this).attr("value");
					if(count > 0)
					{
						codeNo = codeNo + "," + str_2;
					}
					else
					{
						codeNo = str_2;
					}
					count++;
				}
			});
			var parm = {
					codeNo : codeNo
				};
			$.ajax({
				type : "POST",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/codeLibr/codeMapperDelete',
				data: parm,
				success:function(data){
					$("#codeMapperList").html(data);  
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