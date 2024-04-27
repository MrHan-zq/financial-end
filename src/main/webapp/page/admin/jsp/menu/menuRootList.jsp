<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<select class="col-xs-10 col-sm-8" id="rootMenuSelect" name="parentId">
	<c:forEach var="rootMenu" items="${menuRootList}" varStatus="s">
		<option value="${rootMenu.id}">${rootMenu.name}</option>
	</c:forEach>
</select>

<script type="text/javascript">
	$(function() {	
		
		var menuTypeSelect = $("#menuTypeSelect").val();
		if(2 == menuTypeSelect)
		{
			//根菜单的所有子菜单
			var rootMenuId = $("#rootMenuSelect").val();
			var parm_son = {
					rootMenuId : rootMenuId
			};
			$.ajax({
				type : "GET",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/admin/pc/resources/menuRootSonList',
				data: parm_son,
				success:function(data){
					$("#sonMenuList").html(data);  
				},
				error: function (msg) {//ajax请求失败后触发的方法
					console.log(msg);//弹出错误信息
		        }
			});
		}
		
		$("#rootMenuSelect").change(function() {
			var menuTypeSelect = $("#menuTypeSelect").val();
			if(2 == menuTypeSelect)
			{
				//根菜单的所有子菜单
				var rootMenuId = $("#rootMenuSelect").val();
				var parm_son = {
						rootMenuId : rootMenuId
				};
				$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/admin/pc/resources/menuRootSonList',
					data: parm_son,
					success:function(data){
						$("#sonMenuList").html(data);  
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
				});
			}
		});
		
	});
		
</script>