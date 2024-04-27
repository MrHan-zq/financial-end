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
		url : rootPath+'/admin/pc/administratorManage/administratorListAndButtonList',
		data: parm,
		success:function(data){
			$("#administratorListAndButtonList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});

});

//分页
function goPage(x) 
{
	var userName = $("#userNameSearch").val();
	var accountName = $("#accountNameSearch").val();
	
	var parm = {
		pageNow : x,
		pageSize : 10,
		userName : userName,
		accountName :accountName
	};
		
	$.ajax({
		type : "POST",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/admin/pc/administratorManage/administratorList',
		data: parm,
		success:function(data){
			$("#administratorList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

function adminSearchBtn(){
	var userName = $("#userNameSearch").val();
	var accountName = $("#accountNameSearch").val();
	
	var parm = {
			userName : userName,
			accountName :accountName
		};
	$.ajax({
		type : "POST",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/admin/pc/administratorManage/administratorList',
		data: parm,
		success:function(data){
			$("#administratorList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

function addAccount(){
	var parm = {};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/admin/pc/administratorManage/administratorAddPage',
		data: parm,
		success:function(data){
			$("#administratorListAndButtonList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}


function delAccount()
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
			var systemUserIds = "";
			var count = 0;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{	
					var str_2 = $(this).attr("value");
					if(count > 0)
					{
						systemUserIds = systemUserIds + "," + str_2;
					}
					else
					{
						systemUserIds = str_2;
					}
					count++;
				}
			});
			var parm = {
					systemUserIds : systemUserIds
				};
			$.ajax({
				type : "POST",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/admin/pc/administratorManage/administratorDelete',
				data: parm,
				success:function(data){
					$("#administratorList").html(data);  
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
function chushihua()
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
		if(confirm("确认初始化密码吗"))
		{
			var systemUserIds = "";
			var count = 0;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{	
					var str_2 = $(this).attr("value");
					if(count > 0)
					{
						systemUserIds = systemUserIds + "," + str_2;
					}
					else
					{
						systemUserIds = str_2;
					}
					count++;
				}
			});
			var parm = {
					systemUserIds : systemUserIds
				};
			$.ajax({
				type : "POST",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/admin/pc/administratorManage/chushihua',
				data: parm,
				success:function(data){
					$("#administratorList").html(data);  
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
function editAccount()
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
	var systemUserId = "";
	var count = 0;
	$("input:checkbox").each(function(){
		if($(this).is(':checked'))
		{
			systemUserId = $(this).attr("value");
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
					systemUserId : systemUserId
				};
			$.ajax({
				type : "GET",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/admin/pc/administratorManage/administratorEditPage',
				data: parm,
				success:function(data){
					$("#administratorListAndButtonList").html(data);  
				},
				error: function (msg) {//ajax请求失败后触发的方法
					console.log(msg);//弹出错误信息
		        }
			});
			editAccountWindow();
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

//分配权限按钮
function permissions()
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
	var systemUserId = "";
	var count = 0;
	$("input:checkbox").each(function(){
		if($(this).is(':checked'))
		{
			systemUserId = $(this).attr("value");
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
				systemUserId : systemUserId
			};
			$.ajax({
				type : "POST",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/admin/pc/administratorManage/administratorPermissionsPage',
				data: parm,
				success:function(data){
					$("#administratorListAndButtonList").html(data);  
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




