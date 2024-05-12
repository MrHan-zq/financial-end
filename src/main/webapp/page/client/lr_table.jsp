<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<%@include file="/page/admin/jsp/common/common.jspf"%>
		<script src="${ctx}/static/js/report/layui/layui.js"></script>
		<%@include file="/page/admin/jsp/common/title.jspf"%>
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
		<link href="https://cdn.bootcss.com/jquery-datetimepicker/2.5.17/jquery.datetimepicker.min.css" rel="stylesheet">
		<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-datetimepicker/2.5.17/jquery.datetimepicker.full.min.js"></script>

	</head>
	<body>
		<%@include file="/page/admin/jsp/common/head.jspf"%>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed');}catch(e){}
			</script>
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="javascript:void(0)"><span class="menu-text"></span></a>
				<%@include file="/page/client/client_menu_lr_tb.jspf"%>
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
							<li><a>报表明细</a></li>
							<li class="active">利润报表明细</li>
						</ul>
						<div class="nav-search" id="nav-search">
							<input type="hidden" id="parentId" value="${parentId}" />
						</div>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="page-header">
										<div class="widget-box">
											<div class="widget-body">
												<div class="widget-main padding-8">
													<div id="roleListAndButtonList" class="tree">


<div class="row">
	<div class="widget-box">
		<div class="widget-header widget-header-small">
			<h7 class="lighter">搜&nbsp;索</h7>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form class="form-search">
					<div class="row">
						<div class="widget-main">
							<!--<span class="">
								<label>单位名称:</label>
								<input id="orgId" type="text" placeholder="单位名称" class="input-sm" />
							</span>&nbsp-->
							<span class="">
								<label>开始时间:</label>
<%--								<input id="kjyearMoth" type="text" placeholder="会计年月" class="input-sm" />--%>
								<input class="datetimepicker" id="startTime" type="text" >
							</span>&nbsp
							<span class="">
								<label>结束时间:</label>
<%--								<input id="kjyearMoth" type="text" placeholder="会计年月" class="input-sm" />--%>
								<input class="datetimepicker" id="endTime" type="text" >
							</span>&nbsp

						</div>
						<div class="col-xs-12 col-sm-8">
							<div class="input-group">
								<span class="input-group-btn">
									<button id="searchBtn" type="button" class="btn btn-purple btn-xs" onclick="onSearchBtn()">
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button>
									&nbsp; &nbsp; &nbsp;
									<button id="returnBtn" class="btn btn-xs" type="reset">
										<i class="icon-undo bigger-110"></i>
										重置
									</button>
								</span>
							</div>
						</div>
					</div>
				</form><br>
<%--				<c:forEach items="${buttonList}" var="key">--%>
<%--					<button type="button" id="${fn:split(key.btn,',')[0]}" name="${fn:split(key.btn,',')[1]}" class="${fn:split(key.btn,',')[2]}" onclick="${fn:split(key.btn,',')[0]}()">${fn:split(key.btn,',')[3]}</button>--%>
<%--					&nbsp; &nbsp; &nbsp;--%>
<%--				</c:forEach>--%>
			</div>
		</div>
	</div>
</div>

<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="row" id="reportList">

			</div>
		</div>
	</div>
</div>


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
	<script type="text/javascript">
		layui.use(['layer'], function () {
			var layer = layui.layer;
		});
		function onSearchBtn(){
			id = layer.msg('正在查询，请稍后', {
				icon: 16,
				shade: 0.4,
				time: false //取消自动关闭
			});

			var startTime = $("#startTime").val();
			if(startTime == ""){
				alert("请选择开始时间");
				layer.close(id);//手动关闭
				return;
			}
			var endTime = $("#endTime").val();
			if(endTime == ""){
				alert("请选择结束时间");
				layer.close(id);//手动关闭
				return;
			}
			if(startTime > endTime){
				alert("开始时间不能大于结束时间");
				layer.close(id);//手动关闭
				return;
			}
			let url = "${ctx}/client/lr/table/list"+ "?startTime=" + startTime + "&endTime=" + endTime;
			$.ajax({
				type: "GET",
				url: url,
				dataType: "html",
				success: function(data){
					$("#reportList").html(data);
					layer.close(id);//手动关闭
				}, error: function (msg) {//ajax请求失败后触发的方法
					layer.close(id);//手动关闭
					layer.msg('查询失败');
					console.log(msg);//弹出错误信息
				}
			});
		}
	</script>
	</body>
</html>