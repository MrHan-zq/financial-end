layui.use(['layer'], function () {
    var layer = layui.layer;
});
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
		url : rootPath+'/report/result/buttonList',
		data: parm,
		success:function(data){
			$("#tReportResultListAndButtonList").html(data);
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
		url : rootPath+'/report/result/list',
		data: parm,
		success:function(data){
			$("#tReportResultList").html(data);
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

function msgSearchBtn(){
	var reportType = $("#reportType").val();
	var parm = {
			pageNow : 1,
			pageSize : 10,
			reportType : reportType
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/report/result/list',
		data: parm,
		success:function(data){
			$("#tReportResultList").html(data);
		},
		error: function (msg) {//ajax请求失败后触发的方法
			console.log(msg);//弹出错误信息
        }
	});
}

//全量数据转换
function dataFormatAll(){
 	id = layer.msg('正在操作，请稍后', {
        icon: 16,
        shade: 0.4,
        time: false //取消自动关闭
    });
	var parm = {
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
			msgSearchBtn();
		},
		error: function (msg) {//ajax请求失败后触发的方法
			layer.close(id);//手动关闭
            layer.msg('转换失败');
        }
	})
}
