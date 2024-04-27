layui.use(['layer'], function () {
    var layer = layui.layer;  
});

$(function() {
	goPage(1); 
});
    
//分页
function goPage(x) 
{
	/*var parm = {
		pageNow : x,
		pageSize : 10
	};*/
	id = layer.msg('正在查询，请稍后', {
        icon: 16,
        shade: 0.4,
        time: false //取消自动关闭
    });
	var orgId = $("#orgId").val();
	var kjyearMoth = $("#kjyearMoth").val();
	var subjectCode = $("#subjectCode").val();
	var parm = {
			pageNow : x,
			pageSize : 10,
			orgId : orgId,
			kjyearMoth : kjyearMoth,
			subjectCode : subjectCode
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/report/tKmyeList',
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
	});
}

function onSearchBtn(){
	goPage(1);
 	/*id = layer.msg('正在查询，请稍后', {
        icon: 16,
        shade: 0.4,
        time: false //取消自动关闭
    });
	var orgId = $("#orgId").val();
	var kjyearMoth = $("#kjyearMoth").val();
	var subjectCode = $("#subjectCode").val();
	var parm = {
			pageNow : 1,
			pageSize : 10,
			orgId : orgId,
			kjyearMoth : kjyearMoth,
			subjectCode : subjectCode
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/report/tKmyeList',
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
    
function upload(){
    var oInput = document.createElement('input');
    oInput.type = 'file';
    oInput.onchange = function (e) {
        var data = new FormData();/* 表单数据 */
        var file = oInput.files[0];
        data.append('excelFile', file);/* 加入文件 */
        data.append('proSubject', '3');/*报表类型*/
     	id = layer.msg('正在导入数据，请稍后', {
            icon: 16,
            shade: 0.4,
            time: false //取消自动关闭
        });
        $.ajax({
            url: rootPath+'/report/importTKmye',
            type: 'POST',
            data: data,
            dataType: 'JSON',
            cache: false,
            processData: false,
            contentType: false,
            success: function (res) {
                layer.close(id);//手动关闭
                var flag=res.flag;
                if(flag.indexOf("数据不正确") >= 0){
                	alert(flag);
                	layer.msg('导入失败');
                } else if(flag=="-1"){
                   layer.msg('导入失败，请先关联公司！');
                }  else {
                	layer.msg('导入成功');
                	goPage(1); 
                }
            },
            error: function (err) {
            	layer.close(id);//手动关闭
            	layer.msg('导入失败');
            }
        });
    }
    oInput.click();
}
    












