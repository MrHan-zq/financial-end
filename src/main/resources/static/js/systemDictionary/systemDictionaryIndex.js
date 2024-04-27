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
		url : rootPath+'/admin/pc/systemDictionary/systemDictionaryListAndButtonList',
		data: parm,
		success:function(data){
			$("#systemDictionaryListAndButtonList").html(data);  
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
		type : "POST",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/admin/pc/systemDictionary/systemDictionaryList',
		data: parm,
		success:function(data){
			$("#systemDictionaryList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

function dictionarySearchBtn(){
	var cnNameSearch = $("#cnNameSearch").val();
	var enNameSearch = $("#enNameSearch").val();
	
	var parm = {
			cnNameSearch : cnNameSearch,
			enNameSearch :enNameSearch
		};
	$.ajax({
		type : "POST",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/admin/pc/systemDictionary/systemDictionaryList',
		data: parm,
		success:function(data){
			$("#systemDictionaryList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}


function addSystemDictionary(){
	var parm = {};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/admin/pc/systemDictionary/systemDictionaryAddPage',
		data: parm,
		success:function(data){
			$("#systemDictionaryListAndButtonList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

function delSystemDictionary()
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
			var dictionaryId = "";
			var count = 0;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{	
					var str_2 = $(this).attr("value");
					if(count > 0)
					{
						dictionaryId = dictionaryId + "," + str_2;
					}
					else
					{
						dictionaryId = str_2;
					}
					count++;
				}
			});
			var parm = {
					dictionaryId : dictionaryId
				};
			$.ajax({
				type : "POST",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/admin/pc/systemDictionary/systemDictionaryDelete',
				data: parm,
				success:function(data){
					$("#systemDictionaryList").html(data);  
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
function editSystemDictionary()
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
	var dictionaryId = "";
	var count = 0;
	$("input:checkbox").each(function(){
		if($(this).is(':checked'))
		{
			dictionaryId = $(this).attr("value");
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
				dictionaryId : dictionaryId	
			};
			$.ajax({
				type : "GET",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/admin/pc/systemDictionary/systemDictionaryWhetherCanEdit',
				data: parm,
				success:function(data){
					if(data == true || data == "true"){
						$.ajax({
							type : "GET",
							cache : false,
							dataType : "html",
							async : true,// 设置异步为false,重要！
							url : rootPath+'/admin/pc/systemDictionary/systemDictionaryEditPage',
							data: parm,
							success:function(data){
								$("#systemDictionaryListAndButtonList").html(data);  
							},
							error: function (msg) {//ajax请求失败后触发的方法
								console.log(msg);//弹出错误信息
					        }
						});
					}else{
						alert("系统字典不可编辑");
					}
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