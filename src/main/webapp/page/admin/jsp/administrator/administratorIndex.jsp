<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<%@include file="/page/admin/jsp/common/common.jspf"%>
		<script src="${ctx}/static/js/administrator/administratorIndex.js"></script>
		<%@include file="/page/admin/jsp/common/title.jspf"%>
	</head>
	<body>
		<%@include file="/page/admin/jsp/common/head.jspf"%>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed');}catch(e){}
			</script>
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="javascript:void(0)"><span class="menu-text"></span></a>
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
							<li><a>系统管理</a></li>
							<li class="active">后台用户管理</li>
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
											<div class="widget-header header-color-blue2">
												<h4 class="lighter smaller"></h4>
											</div>
											<div class="widget-body">
												<div class="widget-main padding-8">
													<div id="administratorListAndButtonList" class="tree">
														
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
	</body>
</html>