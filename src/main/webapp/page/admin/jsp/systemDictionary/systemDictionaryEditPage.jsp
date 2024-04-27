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

<form id="systemDictionaryAddForm" class="form-horizontal" role="form" action="admin/pc/systemDictionary/systemDictionaryUpdateSave" method="post">

	<div class="page-content">
		<div class="page-header">
			<h4>
				编辑字典
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
						<input type="hidden" name="dictionaryId" id="dictionaryId" value="${dictionaryModel.id}" />
						<input class="input-sm" type="text" name="cnName" id="cnName" value="${dictionaryModel.cnName}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">字典英文名称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="enName" id="enName" value="${dictionaryModel.enName}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">可编辑:</label>
					<div class="col-xs-3">
						<label>
							<c:choose>
								<c:when test="${dictionaryModel.flag eq 1}">
									<input id="flagCheckbox" checked="checked" name="flag" value="1" class="ace ace-switch ace-switch-5" type="checkbox" />
								</c:when>
								<c:otherwise>
									<input id="lockedCheckbox" class="ace ace-switch ace-switch-5" type="checkbox" />
								</c:otherwise>
							</c:choose>
							<span class="lbl"></span>
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">描述:</label>
					<div class="col-sm-9">
						<p class="pr">
							<em class="pa"></em><em></em>
							<textarea name="description" style="height:72px;width:516px;">${dictionaryModel.remark}</textarea>
						</p>
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
						<c:choose>
							<c:when test="${empty dictionaryDetaiList}">
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
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
						<c:forEach items="${dictionaryDetaiList}" var="dictionaryDetai" varStatus="status">
							<div class="widget-main" id="dictionaryDetailforEach${status.index}">
								<div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
									<div class="widget-box">
										<div class="widget-toolbar"></div>
										<div class="widget-body">
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-4">名称:</label>
												<div class="col-sm-9">
													<input class="input-sm" type="text" name="detailName" id="detailName${status.index}" value="${dictionaryDetai.name}" />
													<div class="space-2"></div>
													<div class="help-block" id="input-size-slider"></div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-4">值:</label>
												<div class="col-sm-9">
													<input class="input-sm" type="text" name="detailValue" id="detailValue${status.index}" value="${dictionaryDetai.value}" />
													<div class="space-2"></div>
													<div class="help-block" id="input-size-slider"></div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-4">备注:</label>
												<div class="col-sm-9">
													<input class="input-sm" type="text" name="detailRemark" id="detailRemark${status.index}" value="${dictionaryDetai.remark}" />
													<c:choose>
														<c:when test="${fn:length(dictionaryDetaiList) eq (status.index + 1)}">
															<button id="addColumn" type="button" class="btn btn-xs btn-info active">增加一栏</button>
														</c:when>
														<c:otherwise>
														</c:otherwise>
													</c:choose>
													<button type="button" class="btn btn-xs btn-danger" id="deleteColumnForEach${status.index}">删除栏</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
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
			
			var dictionaryId = $("#dictionaryId").val();
			var cnName = $("#cnName").val();
			var enName = $("#enName").val();
			var parm = {
					dictionaryId : dictionaryId,
					cnName : cnName,
					enName : enName
				};
				
				$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/admin/pc/systemDictionary/systemDictionaryUpdateCnNameAndEnNameValidate',
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
		
		//点击事件
		$("#deleteColumnForEach0").click("click", function() {
			$("#dictionaryDetailforEach0").addClass('hide');
			$("#detailName0").val("");
			$("#detailValue0").val("");
			$("#detailRemark0").val("");
		});
		
		//点击事件
		$("#deleteColumnForEach1").click("click", function() {
			$("#dictionaryDetailforEach1").addClass('hide');
			$("#detailName1").val("");
			$("#detailValue1").val("");
			$("#detailRemark1").val("");
		});
		
		//点击事件
		$("#deleteColumnForEach2").click("click", function() {
			$("#dictionaryDetailforEach2").addClass('hide');
			$("#detailName2").val("");
			$("#detailValue2").val("");
			$("#detailRemark2").val("");
		});
		
		//点击事件
		$("#deleteColumnForEach3").click("click", function() {
			$("#dictionaryDetailforEach3").addClass('hide');
			$("#detailName3").val("");
			$("#detailValue3").val("");
			$("#detailRemark3").val("");
		});
		
		//点击事件
		$("#deleteColumnForEach4").click("click", function() {
			$("#dictionaryDetailforEach4").addClass('hide');
			$("#detailName4").val("");
			$("#detailValue4").val("");
			$("#detailRemark4").val("");
		});
		
	});
</script>