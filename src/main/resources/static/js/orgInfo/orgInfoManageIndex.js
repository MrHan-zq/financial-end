$(function() {
	goPage(1); 
});

//分页
function goPage(x) 
{
	var orgName = $("#orgName").val();
	var legalerName = $("#legalerName").val();
	var isList = $("#isList").val();
	var resPersonTel = $("#resPersonTel").val();
	var parm = {
			pageNow : x,
			pageSize : 10,
			orgName : orgName,
			legalerName : legalerName,
			isList : isList,
			resPersonTel : resPersonTel
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/orgInfo/orgInfoList',
		data: parm,
		success:function(data){
			$("#orgInfoList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

function onSearchBtn(){
	goPage(1);
	/*var orgName = $("#orgName").val();
	var legalerName = $("#legalerName").val();
	var isList = $("#isList").val();
	var resPersonTel = $("#resPersonTel").val();
	var parm = {
			pageNow : 1,
			pageSize : 10,
			orgName : orgName,
			legalerName : legalerName,
			isList : isList,
			resPersonTel : resPersonTel
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/orgInfo/orgInfoList',
		data: parm,
		success:function(data){
			$("#orgInfoList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});*/
}
//新增
function addFunOrg(){
	var parm = {};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/orgInfo/orgAddPage',
		data: parm,
		success:function(data){
			$("#orgInfoList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

//编辑按钮
function editOrg()
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
	var orgId = "";
	var count = 0;
	$("input:checkbox").each(function(){
		if($(this).is(':checked'))
		{
			orgId = $(this).attr("value");
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
				orgId : orgId	
			};
			$.ajax({
				type : "GET",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/orgInfo/orgEditPage',
				data: parm,
				success:function(data){
					$("#roleListAndButtonList").html(data);  
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








