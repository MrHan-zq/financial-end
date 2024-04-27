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
		url : rootPath+'/codeLibr/codeLibrListAndButtonList',
		data: parm,
		success:function(data){
			$("#codeLibrListAndButtonList").html(data);  
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
	//alert("1");
	var code_name = $("#code_name").val();
	var code_no = $("#code_no").val();
	var use_area = $("#use_area").val();
	var parm = {
			pageNow : x,
			pageSize : 10,
			code_name : code_name,
			code_no : code_no,
			use_area : use_area
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/codeLibr/codeLibrList',
		data: parm,
		success:function(data){
			//alert("ss");
			$("#codeLibrList").html(data);  
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
	//alert("22");
	goPage(1); 
 	/*id = layer.msg('正在查询，请稍后', {
        icon: 16,
        shade: 0.4,
        time: false //取消自动关闭
    });
	var code_name = $("#code_name").val();
	var code_no = $("#code_no").val();
	var use_area = $("#use_area").val();
	var parm = {
			pageNow : 1,
			pageSize : 10,
			code_name : code_name,
			code_no : code_no,
			use_area : use_area
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/codeLibr/codeLibrList',
		data: parm,
		success:function(data){
			$("#reportList").html(data);  
			layer.close(id);//手动关闭
		},
		error: function (msg) {//ajax请求失败后触发的方法
			layer.close(id);//手动关闭
            layer.msg('查询失败');
			console.log(msg);//弹出错误信息
        }
	});*/
}
  
function addCodeLibr(){
	var pages=$("#pages").val();
	var parm = {pages : pages};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/codeLibr/addCodeLibr',
		data: parm,
		success:function(data){
			$("#codeLibrListAndButtonList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}


//编辑按钮
function editCodeLibr()
{
	var pages=$("#pages").val();
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
	var codeLibrId = "";
	var count = 0;
	$("input:checkbox").each(function(){
		if($(this).is(':checked'))
		{
			codeLibrId = $(this).attr("value");
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
					pages: pages,
				codeLibrId : codeLibrId	
			};
			$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/codeLibr/codeLibrEditPage',
					data: parm,
					success:function(data){
						$("#codeLibrListAndButtonList").html(data);  
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
    
function delCodeLibr()
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
			var codeLibrId = "";
			var count = 0;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{	
					var str_2 = $(this).attr("value");
					if(count > 0)
					{
						codeLibrId = codeLibrId + "," + str_2;
					}
					else
					{
						codeLibrId = str_2;
					}
					count++;
				}
			});
			var parm = {
					codeLibrId : codeLibrId
				};
			$.ajax({
				type : "POST",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/codeLibr/codeLibrDelete',
				data: parm,
				success:function(data){
					$("#codeLibrList").html(data);  
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
//全量数据转换
function dataFormatAll(){
 	id = layer.msg('正在操作，请稍后', {
        icon: 16,
        shade: 0.4,
        time: false //取消自动关闭
    });
	var parm = {
			table_name : '',
			imp_user : '',
			org_id : ''
		};
	$.ajax({
		type : "POST",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/dataFormat/dataFormatAll',
		data: parm,
		success:function(data){
			layer.close(id);//手动关闭
			layer.msg('转换成功');
			console.log(msg);//弹出错误信息
		},
		error: function (msg) {//ajax请求失败后触发的方法
			layer.close(id);//手动关闭
            layer.msg('转换失败');
			console.log(msg);//弹出错误信息
        }
	})
}











