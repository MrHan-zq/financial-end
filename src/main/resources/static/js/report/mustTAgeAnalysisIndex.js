layui.use(['layer'], function () {
    var layer = layui.layer;  
});

$(function() {
	goPage(1); 
});
    
//分页
function goPage(x) {
	var orgId = $("#orgId").val();
	var kjyearMoth = $("#kjyearMoth").val();
	var importType = $("#importType").val();
	var parm = {
			pageNow : x,
			pageSize : 10,
			orgId : orgId,
			kjyearMoth : kjyearMoth,
			importType : importType
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/report/otherTAgeAnalysisList',
		data: parm,
		success:function(data){
			$("#reportList").html(data);  
		},
		error: function (msg) {//ajax请求失败后触发的方法
            layer.msg('查询失败');
			console.log(msg);//弹出错误信息
        }
	});
}

function onSearchBtn(){
	goPage(1);
}












