layui.use(['layer'], function () {
    var layer = layui.layer;  
});
var successFile ="";
$(function() {
	goPage(1); 
});

//分页
function goPage(x) 
{
/*	id = layer.msg('正在查询，请稍后', {
      icon: 16,
      shade: 0.4,
      time: false //取消自动关闭
	});*/
	var name = $("#name").val();
	var parm = {
			pageNow : x,
			pageSize : 10,
			name : name
		};
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "html",
		async : true,// 设置异步为false,重要！
		url : rootPath+'/report/bulkList',
		data: parm,
		success:function(data){
			$("#reportList").html(data);  
			//layer.close(id);//手动关闭
		},
		error: function (msg) {//ajax请求失败后触发的方法
			//layer.close(id);//手动关闭
            layer.msg('查询失败');
			console.log(msg);//弹出错误信息
      }
	});
}


function onSearchBtn(){
	goPage(1);
}

function importFile(proSubject,type,file,url) {
	var data = new FormData();
    data.append('excelFile', file);
    data.append('proSubject', proSubject);
    if(type != null){
    	data.append('type', type);
    }
    $.ajax({
        url: rootPath+url,
        type: 'POST',
        data: data,
        dataType: 'JSON',
        cache: false,
        processData: false,
        contentType: false,
        async:false,
        success: function (res) {
            var flag=res.flag;
            if(flag.indexOf("数据不正确") >= 0){
            	alert(flag);
            	layer.msg('导入失败');
            } else if(flag=="-1"){
               layer.msg('导入失败，请先关联公司！');
            }  else {
            	successFile=successFile+flag+",";
            	//layer.msg('导入成功');
            }
        },
        error: function (err) {
        	layer.msg('导入失败');
        }
    });
}

function upload(){
    var oInput = document.createElement('input');
    oInput.type = 'file';
    oInput.multiple = 'multiple';
    var successFile ="";
    //alert("df");
    oInput.onchange = function (e) {
    	id = layer.msg('正在导入数据，请稍后', {
            icon: 16,
            shade: 0.4,
            time: false //取消自动关闭
        });
    	var files = oInput.files;
    	for (var i = 0, length = files.length; i < length; i++) {
    		var file=files[i];
    		var fileName=file.name;
    		//alert(fileName);
    		if(fileName.indexOf("利润表") >= 0 || fileName.indexOf("损益表") >= 0){
    			importFile('1',null,file,'/report/importTBasiProfitBulk');
    		}else if(fileName.indexOf("资产负债表") >= 0){
    			importFile('2',null,file,'/report/importTBasiAssetsAndLiabilitiesBulk');
    		}else if(fileName.indexOf("科目余额表") >= 0){
    			importFile('3',null,file,'/report/importTKmyeBulk');
    		}else if(fileName.indexOf("现金流量表") >= 0){
    			importFile('5',null,file,'/report/importTBasiCashFlowBulk');
    		}else if(fileName.indexOf("其他应付账龄分析") >= 0){
    			importFile('22','3',file,'/report/importTAgeAnalysisBulk');
    		}else if(fileName.indexOf("其他应收账龄分析") >= 0){
    			importFile('23','4',file,'/report/importTAgeAnalysisBulk');
    		}else if(fileName.indexOf("应付账款账龄分析") >= 0){
    			importFile('22','5',file,'/report/importTAgeAnalysisBulk');
    		}else if(fileName.indexOf("应收账款账龄分析") >= 0){
    			importFile('23','6',file,'/report/importTAgeAnalysisBulk');
    		}else if(fileName.indexOf("预付账款账龄分析") >= 0){
    			importFile('23','7',file,'/report/importTAgeAnalysisBulk');
    		}else if(fileName.indexOf("预收账款账龄分析") >= 0){
    			importFile('22','8',file,'/report/importTAgeAnalysisBulk');
    		}else if(fileName.indexOf("产成品账龄分析") >= 0){
    			importFile('16','9',file,'/report/importTAgeAnalysisBulk');
    		}else if(fileName.indexOf("原材料账龄分析") >= 0){
    			importFile('16','10',file,'/report/importTAgeAnalysisBulk');
    		}
    	}
    	//alert("df");
    	layer.close(id);//手动关闭;
    	//layer.msg('导入成功');
    	goPage(1);
    }
    oInput.click();
}
