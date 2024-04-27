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

<form id="administratorAddForm" class="form-horizontal" role="form" action="admin/pc/administratorManage/administratorUpdateSave" method="post">

	<div class="page-content">
		<div class="page-header">
			<h4>
				编辑后台用户
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
						<input class="input-sm" type="text" name="accountName" id="accountName" value="${administratorInfo.accountName}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">账号锁定:</label>
					<input type="hidden" name="systemUserId" id="systemUserId" value="${administratorInfo.id}" />
					<input type="hidden" name="locked" id="locked" value="${administratorInfo.locked}" />
					<div class="col-xs-3">
						<label>
							<c:choose>
								<c:when test="${administratorInfo.locked eq 0}">
									<input id="lockedCheckbox" class="ace ace-switch ace-switch-5" type="checkbox" />
								</c:when>
								<c:otherwise>
									<input id="lockedCheckbox" checked="checked" class="ace ace-switch ace-switch-5" type="checkbox" />
								</c:otherwise>
							</c:choose>
							<span class="lbl"></span>
						</label>
					</div>
				</div>
				<%-- <div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">密码:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="password" name="pwd" id="pwd" value="${administratorInfo.pwd}"/>
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">确认密码:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="password" name="password" id="password" value="${administratorInfo.pwd}"/>
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div> --%>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">所属角色:</label>
					<div class="col-sm-9">
						<select class="width-25" id="roleId" name="roleId">
							<option value="">请选择角色</option>
							<c:forEach items="${roleList}" var="role">
								<c:choose>
									<c:when test="${role.id eq userRoleInfo.roleId}">
										<option  selected="selected" value="${role.id}">${role.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${role.id}">${role.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">所属公司:</label>
					<div class="col-sm-9">
						<select name="orgId" id="orgId"> 
							<option value="">请选择</option> 
							<c:forEach items="${orgList}" var="orgList">
								<%-- <option value="${orgList.id}">${orgList.orgName}</option>  --%>
								<c:choose>
									<c:when test="${orgList.id eq administratorInfo.orgId}">
										<option  selected="selected" value="${orgList.id}">${orgList.orgName}</option>
									</c:when>
									<c:otherwise>
										<option value="${orgList.id}">${orgList.orgName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">描述:</label>
					<div class="col-sm-9">
						<p class="pr">
							<em class="pa"></em><em></em>
							<textarea name="description" style="height:72px;width:516px;">${administratorInfo.description}</textarea>
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

<script type="text/javascript">
	$(function() {

		//点击事件
		$("#saveButton").click("click", function() {
			
			var userName = $("#userName").val();
			if (userName == null || userName == undefined || userName == '') { 
				alert("请输入用户名称");
				return;
			} 
			var accountName = $("#accountName").val();
			if (accountName == null || accountName == undefined || accountName == '') { 
				alert("请输入账号名称");
				return;
			} 
			/* var pwd = $("#pwd").val();
			if (pwd != null && pwd != undefined && pwd != '') { 
				var password = $("#password").val();
				if (password == null || password == undefined || password == '') { 
					alert("请输入确认密码");
					return;
				} 
				if(pwd != password){
					alert("俩次密码不正确");
					return;
				}
			}  */
			var roleId = $("#roleId").val();
			if (roleId == null || roleId == undefined || roleId == '') { 
				alert("请选择所属角色");
				return;
			} 
			
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{
					$("#locked").val("1");
				}else{
					$("#locked").val("0");
				}
			});
			
			var systemUserId = $("#systemUserId").val();
			var accountName = $("#accountName").val();
			var parm = {
				systemUserId : systemUserId,
				accountName : accountName
			};
				
				$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/admin/pc/administratorManage/administratorUpdateAccountNameValidate',
					data: parm,
					success:function(data){
						if(data == true || data == "true"){
							$("#administratorAddForm").submit();
						}else{
							alert("账号名称已存在");
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