<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style type="text/css">
	#content .form p em{
    display: inline-block;
    width:70px;
    text-align: right;
    margin-right: 20px;
}  	
</style>

<form id="codeMapperAddForm" class="form-horizontal" role="form" action="codeLibr/codeMapperUpdateSave" method="POST">

	<div class="page-content">
		<div class="page-header">
			<h4>
				编辑
				<small>
					<i class="icon-double-angle-right"></i>
				</small>
			</h4>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">basiField:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="basiField" id="basiField" value="${tCodeMapperBean.basiField}"/>
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">codeNo:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text"  disabled="disabled" name="codeNo" id="codeNo" value="${tCodeMapperBean.codeNo}"/>
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4"></label>
					<div class="col-sm-9">
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-xs-12">
		<div class="clearfix form-actions">
			<div class="col-md-offset-3 col-md-9">
				<button class="btn btn-info" type="button" id="saveButton">
					<i class="icon-ok bigger-110"></i>
					保存
				</button>
				&nbsp; &nbsp; &nbsp;
				<button id="returnBtn" class="btn" type="reset">
					<i class="icon-arrow-left"></i>
					返回
				</button>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		//点击事件
		$("#saveButton").click("click", function() {
			var data = new FormData();/* 表单数据 */
        	data.append('basiField',  $("#basiField").val());
        	data.append('codeNo',  $("#codeNo").val());
			$.ajax({
					type : "POST",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/codeLibr/codeMapperUpdateSave',
					data: data,
					processData: false,
            		contentType: false,
					success:function(data){
						
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
			});
			alert("编辑成功！");
			$("#returnBtn").click();
		 	//$("#tModeAddForm").submit();
		});
		
		$("#returnBtn").click("click", function() {
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
		
	});
</script>