<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form id="systemDictionaryAddForm" class="form-horizontal" role="form" action="admin/pc/systemDictionary/systemDictionarySave" method="post">

	<div class="page-content">
		<div class="page-header">
			<h4>
				新增字典
				<small>
					<i class="icon-double-angle-right"></i>
				</small>
			</h4>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">字典中文名称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="cnName" id="cnName" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">字典英文名称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="enName" id="enName" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">可编辑:</label>
					<div class="col-xs-3">
						<label>
							<input id="flagCheckbox" checked="checked" name="flag" value="1" class="ace ace-switch ace-switch-5" type="checkbox" />
							<span class="lbl"></span>
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">描述:</label>
					<div class="col-sm-9">
						<textarea class="form-control" name="description" placeholder="系统字典描述" style="margin: 0px -0.34375px 0px 0px; height: 72px; width: 516px;"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4"></label>
					<div class="col-sm-9">
					</div>
				</div>
				
				<div class="widget-box">
					<div class="widget-header widget-header-blue widget-header-flat">
						<h4 class="lighter">系统字典明细</h4>
						<div class="widget-toolbar"></div>
					</div>
					<div class="widget-body">
						<div class="widget-main" id="dictionaryDetail">
							<div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
								<div class="widget-box">
									<div class="widget-toolbar"></div>
									<div class="widget-body">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">名称:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailName" id="detailName" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">值:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailValue" id="detailValue" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">备注:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailRemark" id="detailRemark" />
												<button id="addColumn" type="button" class="btn btn-xs btn-info active">增加一栏</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- 增加一栏div -->
						<div class="widget-main hide" id="dictionaryDetail2">
							<div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
								<div class="widget-box">
									<div class="widget-toolbar"></div>
									<div class="widget-body">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">名称:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailName" id="detailName2" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">值:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailValue" id="detailValue2" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">备注:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailRemark" />
												<button type="button" class="btn btn-xs btn-info" id="addColumn2">增加一栏</button>
												<button type="button" class="btn btn-xs btn-danger" id="deleteColumn">删除栏</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="widget-main hide" id="dictionaryDetail3">
							<div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
								<div class="widget-box">
									<div class="widget-toolbar"></div>
									<div class="widget-body">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">名称:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailName" id="detailName3" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">值:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailValue" id="detailValue3" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">备注:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailRemark" />
												<button type="button" class="btn btn-xs btn-info" id="addColumn3">增加一栏</button>
												<button type="button" class="btn btn-xs btn-danger" id="deleteColumn2">删除栏</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="widget-main hide" id="dictionaryDetail4">
							<div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
								<div class="widget-box">
									<div class="widget-toolbar"></div>
									<div class="widget-body">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">名称:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailName" id="detailName4" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">值:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailValue" id="detailValue4" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">备注:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailRemark" />
												<button type="button" class="btn btn-xs btn-info" id="addColumn4">增加一栏</button>
												<button type="button" class="btn btn-xs btn-danger" id="deleteColumn3">删除栏</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="widget-main hide" id="dictionaryDetail5">
							<div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
								<div class="widget-box">
									<div class="widget-toolbar"></div>
									<div class="widget-body">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">名称:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailName" id="detailName5" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">值:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailValue" id="detailValue5" />
												<div class="space-2"></div>
												<div class="help-block" id="input-size-slider"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-4">备注:</label>
											<div class="col-sm-9">
												<input class="input-sm" type="text" name="detailRemark" />
												<button type="button" class="btn btn-xs btn-danger" id="deleteColumn4">删除栏</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- 增加一栏div -->
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
		$("#addColumn").click("click", function() {
			$("#dictionaryDetail2").removeClass('hide');
		});
		//点击事件
		$("#deleteColumn").click("click", function() {
			$("#dictionaryDetail2").addClass('hide');
		});

		//点击事件
		$("#addColumn2").click("click", function() {
			$("#dictionaryDetail3").removeClass('hide');
		});
		//点击事件
		$("#deleteColumn").click("click", function() {
			$("#dictionaryDetail2").addClass('hide');
		});

		//点击事件
		$("#addColumn3").click("click", function() {
			$("#dictionaryDetail4").removeClass('hide');
		});
		//点击事件
		$("#deleteColumn2").click("click", function() {
			$("#dictionaryDetail3").addClass('hide');
		});

		//点击事件
		$("#addColumn4").click("click", function() {
			$("#dictionaryDetail5").removeClass('hide');
		});
		//点击事件
		$("#deleteColumn3").click("click", function() {
			$("#dictionaryDetail4").addClass('hide');
		});
		
		//点击事件
		$("#deleteColumn4").click("click", function() {
			$("#dictionaryDetail5").addClass('hide');
		});
		
		//点击事件
		$("#saveButton").click("click", function() {
			var cnName = $("#cnName").val();
			if (cnName == null || cnName == undefined || cnName == '') { 
				alert("请输入字典中文名称");
				return;
			} 
			var enName = $("#enName").val();
			if (enName == null || enName == undefined || enName == '') { 
				alert("请输入字典英文名称");
				return;
			} 
			
			var cnName = $("#cnName").val();
			var enName = $("#enName").val();
			var parm = {
					cnName : cnName,
					enName : enName
				};
				
				$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/admin/pc/systemDictionary/systemDictionaryAddCnNameAndEnNameValidate',
					data: parm,
					success:function(data){
						if(data == true || data == "true"){
							$("#systemDictionaryAddForm").submit();
						}else{
							alert("字典中文名称已存在或字典英文名称已存在");
						}
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
				});
			
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
		
	});
</script>