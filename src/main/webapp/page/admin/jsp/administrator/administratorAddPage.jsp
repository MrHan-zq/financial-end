<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form id="administratorAddForm" class="form-horizontal" role="form" action="admin/pc/administratorManage/administratorSave" method="post">

	<div class="page-content">
		<div class="page-header">
			<h4>
				新增后台用户
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
						<input class="input-sm" type="text" name="userName" id="userName" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">账号名称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="accountName" id="accountName" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">密码:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="password" name="pwd" id="pwd" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">确认密码:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="password" name="password" id="password" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">所属角色:</label>
					<div class="col-sm-9">
						<select class="width-25" id="roleId" name="roleId">
							<option value="" selected="selected">请选择角色</option>
							<c:forEach items="${roleList}" var="role">
								<option value="${role.id}">${role.name}</option>
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
								<option value="${orgList.id}">${orgList.orgName}</option> 
							</c:forEach>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">描述:</label>
					<div class="col-sm-9">
						<textarea class="form-control" name="description" placeholder="后台用户描述" style="margin: 0px -0.34375px 0px 0px; height: 72px; width: 516px;">
						</textarea>
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
			var pwd = $("#pwd").val();
			if (pwd == null || pwd == undefined || pwd == '') { 
				alert("请输入密码");
				return;
			} 
			var password = $("#password").val();
			if (password == null || password == undefined || password == '') { 
				alert("请输入确认密码");
				return;
			} 
			if(pwd != password){
				alert("俩次密码不正确");
				return;
			}
			var roleId = $("#roleId").val();
			if (roleId == null || roleId == undefined || roleId == '') { 
				alert("请选择所属角色");
				return;
			} 
			
			var accountName = $("#accountName").val();
			var parm = {
					accountName : accountName
				};
				
				$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/admin/pc/administratorManage/administratorAddAccountNameValidate',
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