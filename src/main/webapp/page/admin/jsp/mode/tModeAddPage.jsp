<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form id="tModeAddForm" class="form-horizontal" role="form" action="mode/tModeSave" method="post">
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
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">报表类型:</label>
					<div class="col-sm-9">
						<select id="reportType" name="reportType">
							<option value="1" selected="selected">资产负债</option>
							<option value="2">利润表</option>
							<option value="3">现金流量表</option>
						</select>
						
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">模板描述:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="reportConten" id="reportConten" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">模板条件:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="modeCondition" id="modeCondition" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">模板内容:</label>
					<div class="col-sm-9">
						<textarea class="form-control" name="modeContent" placeholder="modeContent" style="margin: 0px -0.34375px 0px 0px; height: 72px; width: 516px;"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">模板内容中的参数:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="modeValues" id="modeValues" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">描述:</label>
					<div class="col-sm-9">
						<textarea class="form-control" name="remark" placeholder="modeContent" style="margin: 0px -0.34375px 0px 0px; height: 72px; width: 516px;"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">conditionValues:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="conditionValues" id="conditionValues" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">modeArea:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="modeArea" id="modeArea" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">排序:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="px" id="px" />
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
			var reportType = $("#reportType").val();
			if (reportType == null || reportType == undefined || reportType == '') { 
				alert("请输入财务报表类型");
				return;
			} 
			var modeCondition = $("#modeCondition").val();
			if (modeCondition == null || modeCondition == undefined || modeCondition == '') { 
				alert("请输入计算公式");
				return;
			} 
			$("#tModeAddForm").submit();
			
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
					url : rootPath+'/mode/tModeListAndButtonList',
					data: parm,
					success:function(data){
						$("#tModeListAndButtonList").html(data);  
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
				});
		});
		
	});
</script>