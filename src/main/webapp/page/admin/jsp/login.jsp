<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<%@include file="/page/admin/jsp/common/common.jspf"%>
		<link rel="stylesheet" href="${ctx}/static/ace/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctx}/static/ace/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${ctx}/static/ace/assets/css/family.css" />
		<link rel="stylesheet" href="${ctx}/static/ace/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/static/ace/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/static/ace/assets/css/ace-ie.min.css" />

		<script src="${ctx}/static/ace/assets/js/html5shiv.js"></script>
		<script src="${ctx}/static/ace/assets/js/respond.min.js"></script>

		<%@include file="/page/admin/jsp/common/title.jspf"%>
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-bar-chart green"></i>
									<span class="red">金融财务</span>
									<span class="white">分析系统</span>
								</h1>
							</div>
							<div class="space-6"></div>
							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												金融财务分析系统
											</h4>
											<div class="space-6"></div>
											<form action="${ctx}/admin/pc/login" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="accountName" id="accountName" type="text" class="form-control" placeholder="Username" />
															<i class="icon-user"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="password" id="password" type="password" class="form-control" placeholder="Password" />
															<i class="icon-lock"></i>
														</span>
														<span class="text-danger" id="message">
															${message}
														</span>
													</label>
													<div class="space"></div>
													<div class="clearfix">
														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="icon-key"></i>
															登陆
														</button>
													</div>
													<div class="space-4"></div>
												</fieldset>
											</form>
											<div class="social-or-login center">
												<span class="bigger-110"></span>
											</div>
											<div class="social-login center"></div>
										</div>
										<div class="toolbar clearfix">
											<div></div>
											<div></div>
											<div></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script src="${ctx}/static/ace/assets/js/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/static/ace/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctx}/static/ace/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<script type="text/javascript">
		$(function() {

			$("form").submit(function(){
	            var accountName = $("#accountName").val();
	            var password = $("#password").val();
	            if(accountName == null || accountName == undefined || accountName == '')
	            {
	            	$("#message").text("请输入账号");
	            	return false;
	            }
	            if(password == null || password == undefined || password == '')
	            {
	            	$("#message").text("请输入密码");
	            	return false;
	            }
	            return true;
	        });

		});
		</script>
	</body>
</html>