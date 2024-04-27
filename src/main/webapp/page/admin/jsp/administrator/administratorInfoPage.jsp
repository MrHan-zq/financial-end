<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<%@include file="/page/admin/jsp/common/common.jspf"%>
		<%@include file="/page/admin/jsp/common/title.jspf"%>
	</head>

	<body>

		<%@include file="/page/admin/jsp/common/head.jspf"%>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed');}catch(e){}
			</script>
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="javascript:void(0)">
					<span class="menu-text"></span>
				</a>

				<%@include file="/page/admin/jsp/common/menu.jspf"%>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed');}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="admin/pc/index">首页</a>
							</li>
						</ul>
						<div class="nav-search" id="nav-search">
							<input type="hidden" id="parentId" value="${parentId}" />
						</div>
					</div>
					<div class="page-content">
						<div class="page-header">
							<h1>
								个人资料
							</h1>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="page-header">
										<div class="widget-box">
											<div class="widget-header header-color-blue2">
												<h4 class="lighter smaller"></h4>
											</div>
											<div class="widget-body">
												<div class="widget-main padding-8">
													<div class="tree">
														<form class="form-horizontal" role="form">
															<div class="page-content">
																<div class="page-header">
																	<h4>
																		个人资料
																		<small>
																			<i class="icon-double-angle-right"></i>
																		</small>
																	</h4>
																</div>
																<div class="row">
																	<div class="col-xs-12">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-4">用户名称:</label>
																			<div class="col-sm-9">
																				<input class="input-sm" type="text" name="userName" id="userName" value="${administratorInfo.userName}" />
																				<div class="space-2"></div>
																				<div class="help-block" id="input-size-slider"></div>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-4">账号名称:</label>
																			<div class="col-sm-9">
																				<input class="input-sm" type="text" disabled="disabled" value="${administratorInfo.accountName}" />
																				<div class="space-2"></div>
																				<div class="help-block" id="input-size-slider"></div>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-4">原密码:</label>
																			<div class="col-sm-9">
																				<input class="input-sm" type="password" name="oldPwd" id="oldPwd" />
																				<div class="space-2"></div>
																				<div class="help-block" id="input-size-slider"></div>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-4">新密码:</label>
																			<div class="col-sm-9">
																				<input class="input-sm" type="password" name="pwd" id="pwd" />
																				<div class="space-2"></div>
																				<div class="help-block" id="input-size-slider"></div>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-4">确认新密码:</label>
																			<div class="col-sm-9">
																				<input class="input-sm" type="password" name="password" id="password" />
																				<div class="space-2"></div>
																				<div class="help-block" id="input-size-slider"></div>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-4">所属角色:</label>
																			<div class="col-sm-9">
																				<input class="input-sm" type="text" value="${roleModel.name}" disabled="disabled" />
																				<div class="space-2"></div>
																				<div class="help-block" id="input-size-slider"></div>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-4">描述:</label>
																			<div class="col-sm-9">
																				<p class="pr">
																					<em class="pa"></em><em></em>
																					<textarea id="description" style="height:72px;width:516px;">${administratorInfo.description}</textarea>
																				</p>
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
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- PAGE CONTENT ENDS -->
							</div>
						</div>
					</div>
				</div>

				<%@include file="/page/admin/jsp/common/right.jspf"%>
				
			</div>
			<a href="javascript:void(0)" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div>

		<script src="${ctx}/static/ace/assets/js/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/static/ace/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctx}/static/ace/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx}/static/ace/assets/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/ace/assets/js/typeahead-bs2.min.js"></script>
		<script src="${ctx}/static/ace/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script>
		<script src="${ctx}/static/ace/assets/js/fuelux/fuelux.tree.min.js"></script>
		<script src="${ctx}/static/ace/assets/js/ace-elements.min.js"></script>
		<script src="${ctx}/static/ace/assets/js/ace.min.js"></script>
		
		<script src="${ctx}/static/ace/assets/js/jquery-ui-1.10.3.full.min.js"></script>
		<script type="text/javascript">
			$(function() {	
				
				$("#saveButton").click("click", function() {
					var userName = $("#userName").val();
					if (userName == null || userName == undefined || userName == '') { 
						alert("请输入用户名称");
						return;
					} 
					var oldPwd = $("#oldPwd").val();
					if (oldPwd != null && oldPwd != undefined && oldPwd != '') { 
						var pwd = $("#pwd").val();
						var password = $("#password").val();
						if (pwd == null || pwd == undefined || pwd == '') { 
							alert("请输入新密码");
							return;
						} 
						if (password == null || password == undefined || password == '') { 
							alert("请输入确认新密码");
							return;
						} 
						if(pwd != password){
							alert("俩次密码不正确");
							return;
						}
					} 
					
					var parm = {
						userName : userName,
						oldPwd : oldPwd,
						pwd : $("#pwd").val(),
						password : $("#password").val(),
						description : $("#description").val()
					};
							
					$.ajax({
						type : "POST",
						cache : false,
						dataType : "html",
						async : true,// 设置异步为false,重要！
						url : rootPath+'/admin/pc/administratorManage/administratorInfoUpdate',
						data: parm,
						success:function(data){
							if(data == "success"){
								alert("修改成功");
								window.location.href = "${ctx}/admin/pc/index"; 
							}else if(data == "fail"){
								alert("修改失败");
							}else if(data == "userNameIsNull"){
								alert("用户名称为空");
							}else if(data == "pwdOrPasswordIsNull"){
								alert("新密码或者确认新密码为空");
							}else if(data == "pwdOrPasswordIsErr"){
								alert("俩次密码不正确");
							}else if(data == "oldPwdIsErr"){
								alert("原密码不正确");
							}else{
								alert("修改失败");
							}
						},
						error: function (msg) {//ajax请求失败后触发的方法
							console.log(msg);//弹出错误信息
				        }
					});
				});
				
				$("#returnBtn").click("click", function() {
					window.history.back(-1); 
				});
				
			});
		</script>
	</body>
</html>