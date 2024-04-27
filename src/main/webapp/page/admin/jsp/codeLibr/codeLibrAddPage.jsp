<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form id="codeLibrSaveForm" class="form-horizontal" role="form" action="codeLibr/codeLibrSave" method="post">
	<input type="hidden" id="pages" name="pages" value="${pages}">
	<div class="page-content">
		<div class="page-header">
			<h4>
				新增
				<small>
					<i class="icon-double-angle-right"></i>
				</small>
			</h4>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">id:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="id" id="id" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">编号名称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="codeName" id="codeName" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">编号编码:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="codeNo" id="codeNo" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">类型(1资产负债,2利润,现金流量):</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="reportType" id="reportType" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
							
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">编号:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="useArea" id="useArea" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">类型说明:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="useAreaRemark" id="useAreaRemark" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">是否展示(0展示):</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="dataType" id="dataType" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">排序:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="dataTypeDesc" id="dataTypeDesc" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">isCurrent:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="isCurrent" id="isCurrent" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">是否有详情(1有详情):</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="isDetails" id="isDetails" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">概要:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="survey" id="survey" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">预警:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="warn" id="warn" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">类型:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="clzz" id="clzz" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">饼状科目余额:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="km1" id="km1" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">树状科目余额:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="km2" id="km2" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">饼状树状:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="km3" id="km3" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">说明:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="remark" id="remark" />
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
			$("#codeLibrSaveForm").submit();
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
		
	});
</script>