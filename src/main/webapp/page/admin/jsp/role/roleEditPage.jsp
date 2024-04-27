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

<form id="roleAddForm" class="form-horizontal" role="form" action="admin/pc/roleManage/roleUpdateSave" method="post">

	<div class="page-content">
		<div class="page-header">
			<h4>
				编辑角色
				<small>
					<i class="icon-double-angle-right"></i>
				</small>
			</h4>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">角色名称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="hidden" name="roleId" id="roleId" value="${roleModel.id}" />
						<input class="input-sm" type="text" name="roleName" id="roleName" value="${roleModel.name}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">角色描述:</label>
					<div class="col-sm-9">
						<p class="pr">
							<em class="pa"></em><em></em>
							<textarea name="roleDescription" style="height:72px;width:516px;">${roleModel.description}</textarea>
						</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">角色权限:</label>
					<div class="col-sm-9">
						<c:forEach var="key" items="${menuList}" varStatus="s">
							<div class="tree-folder" style="display: block;">
								<c:if test="${key.parentId eq 0}">
									<div class="tree-folder-header">
										<i class="red icon-folder-open"></i>
										<div class="tree-folder-name">
											<label>
												<c:if test="${key.permissionsStatus eq 0}">
													<input type="checkbox" class="ace" name="resourcesId" value="${key.id}" checked="checked" />
												</c:if>
												<c:if test="${key.permissionsStatus eq 1}">
													<input type="checkbox" class="ace" name="resourcesId" value="${key.id}" />
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
															<input type="checkbox" class="ace" name="resourcesId" value="${kc.id}" checked="checked" />
														</c:if>
														<c:if test="${kc.permissionsStatus eq 1}">
															<input type="checkbox" class="ace" name="resourcesId" value="${kc.id}" />
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
																	<input type="checkbox" class="ace" name="resourcesId" value="${kc_2.id}" checked="checked" />
																</c:if>
																<c:if test="${kc_2.permissionsStatus eq 1}">
																	<input type="checkbox" class="ace" name="resourcesId" value="${kc_2.id}" />
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
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{
					bo = true;
					return false; // false时相当于break,如果return true 就相当于continure。  
				}
			});
			if(bo)
			{
				var roleId = $("#roleId").val();
				var roleName = $("#roleName").val();
				var parm = {
						roleId : roleId,
						roleName : roleName
					};
					
					$.ajax({
						type : "GET",
						cache : false,
						dataType : "html",
						async : true,// 设置异步为false,重要！
						url : rootPath+'/admin/pc/roleManage/roleUpdateNameValidate',
						data: parm,
						success:function(data){
							if(data == true || data == "true"){
								$("#roleAddForm").submit();
							}else{
								alert("角色名称已存在");
							}
						},
						error: function (msg) {//ajax请求失败后触发的方法
							console.log(msg);//弹出错误信息
				        }
					});
			}
			else
			{
				alert("请为角色分配权限");
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
					url : rootPath+'/admin/pc/roleManage/roleListAndButtonList',
					data: parm,
					success:function(data){
						$("#roleListAndButtonList").html(data);  
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
				});
		});
		
	});
</script>