$(function() {
	var parm = {
		pageNow : 0,
		pageSize : 10
	};
	
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/admin/pc/resources/menuList',
		data: parm,
		success:function(data){
			$("#menuList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
    
});