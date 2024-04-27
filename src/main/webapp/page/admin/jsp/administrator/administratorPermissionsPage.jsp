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

<form id="administratorPermissionsForm" class="form-horizontal" role="form" action="admin/pc/administratorManage/administratorPermissionsSave" method="post">

	<div class="page-content">
		<div class="page-header">
			<h4>
				后台用户分配权限
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
						<input type="hidden" name="systemUserId" id="systemUserId" value="${userModel.id}" />
						<input type="hidden" name="resourcesId" id="resourcesId" value="0" />
						<input class="input-sm" type="text" disabled="disabled" value="${userModel.userName}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">账号名称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" disabled="disabled" value="${userModel.accountName}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">所属角色:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" disabled="disabled" value="${roleModel.name}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">角色描述:</label>
					<div class="col-sm-9">
						<p class="pr">
							<em class="pa"></em><em></em>
							<textarea disabled="disabled" style="height:72px;width:516px;">${roleModel.description}</textarea>
						</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">用户描述:</label>
					<div class="col-sm-9">
						<p class="pr">
							<em class="pa"></em><em></em>
							<textarea disabled="disabled" style="height:72px;width:516px;">${userModel.description}</textarea>
						</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4"></label>
					<div class="col-sm-9 text-warning bigger-110 orange">
						<i class="icon-bell bigger-110 purple"></i>
						&nbsp;
						选项框禁用的代表的是角色的权限;您可以分配角色权限以外的权限
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">用户权限:</label>
					<div class="col-sm-9">
						<c:forEach var="key" items="${menuList}" varStatus="s">
							<div class="tree-folder" style="display: block;">
								<c:if test="${key.parentId eq 0}">
									<div class="tree-folder-header">
										<i class="red icon-folder-open"></i>
										<div class="tree-folder-name">
											<label>
												<c:if test="${key.permissionsStatus eq 0}">
													<input type="checkbox" class="ace" name="resourcesIdDisabled" value="${key.id}" checked="checked" disabled="disabled" />
												</c:if>
												<c:if test="${key.permissionsStatus eq 1}">
													<c:if test="${key.permissionsStatus2 eq 0}">
														<input type="checkbox" class="ace" name="resourcesId" value="${key.id}" checked="checked" />
													</c:if>
													<c:if test="${key.permissionsStatus2 eq 1}">
														<input type="checkbox" class="ace" name="resourcesId" value="${key.id}" />
													</c:if>
												</c:if>
												<span class="lbl"></span>
											</label>
											${key.name}
										</div>
									</div>
								</c:if>
								<c:forEach var="kc" items="${key.children}" varStatus="ks">
									<div class="tree-folder-content" style="display: block;">
										<div class="tree-folder" style="display: block;">
											<div class="tree-folder-header">
												<i class="pink icon-folder-open"></i>
												<div class="tree-folder-name">
													<label>
														<c:if test="${kc.permissionsStatus eq 0}">
															<input type="checkbox" class="ace" name="resourcesIdDisabled" value="${kc.id}" checked="checked" disabled="disabled" />
														</c:if>
														<c:if test="${kc.permissionsStatus eq 1}">
															<c:if test="${kc.permissionsStatus2 eq 0}">
																<input type="checkbox" class="ace" name="resourcesId" value="${kc.id}" checked="checked" />
															</c:if>
															<c:if test="${kc.permissionsStatus2 eq 1}">
																<input type="checkbox" class="ace" name="resourcesId" value="${kc.id}" />
															</c:if>
														</c:if>
														<span class="lbl"></span>
													</label>
													${kc.name}
												</div>
											</div>
											<div class="tree-folder-content" style="display: block;">
												<c:forEach var="kc_2" items="${kc.children}" varStatus="ks_2">
													<div class="tree-item" style="display: block;">
														<div class="tree-item-name">
															<label>
																<c:if test="${kc_2.permissionsStatus eq 0}">
																	<input type="checkbox" class="ace" name="resourcesIdDisabled" value="${kc_2.id}" checked="checked" disabled="disabled" />
																</c:if>
																<c:if test="${kc_2.permissionsStatus eq 1}">
																	<c:if test="${kc_2.permissionsStatus2 eq 0}">
																		<input type="checkbox" class="ace" name="resourcesId" value="${kc_2.id}" checked="checked" />
																	</c:if>
																	<c:if test="${kc_2.permissionsStatus2 eq 1}">
																		<input type="checkbox" class="ace" name="resourcesId" value="${kc_2.id}" />
																	</c:if>
																</c:if>
																<span class="lbl"></span>
															</label>
															${kc_2.name}
														</div>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</c:forEach>
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
			//判断有没有选一个
			var bo = false;
			$("input[name='resourcesId']").each(function(){
				if($(this).is(':checked'))
				{
					bo = true;
					return false; // false时相当于break,如果return true 就相当于continure。  
				}
			});
			if(bo)
			{
				$("#administratorPermissionsForm").submit();
			}
			else
			{
				$("#resourcesId").val("-1");
				$("#administratorPermissionsForm").submit();
			}
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
					url : rootPath+'/admin/pc/administratorManage/administratorListAndButtonList',
					data: parm,
					success:function(data){
						$("#administratorListAndButtonList").html(data);  
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
				});
		});
		
	});
</script>