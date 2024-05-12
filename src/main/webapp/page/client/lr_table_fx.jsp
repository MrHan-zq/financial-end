<%@ page import="org.apache.commons.collections.MapUtils" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<%@include file="/page/admin/jsp/common/common.jspf" %>
	<script src="${ctx}/static/js/report/layui/layui.js"></script>
	<%@include file="/page/admin/jsp/common/title.jspf" %>
	<script src="${ctx}/static/ace/assets/js/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${ctx}/static/ace/assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
	</script>
	<script type="text/javascript">
		if ("ontouchend" in document) document.write("<script src='${ctx}/static/ace/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
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
	<style type="text/css">
		.analyzeProperty {
			height: 100vh;
			display: flex;
			overflow: auto;
			flex-direction: column;
			background-color: #fff;

			.contentDesc {
				display: flex;
				flex-direction: column;
				height: 250rpx;
				margin-top: 25rpx;
				background-color: #fff;

				.propertyDesc {
					display: flex;
					flex-direction: row;
					height: 160rpx;

					.totalProperty {
						flex: 1;
						display: flex;
						flex-direction: column;
						text-align: center;
						height: 60rpx;
					}
				}

				.dataUpdateTime {
					height: 20rpx;
					font-size: 15rpx;
					line-height: 20rpx;
					margin-bottom: 25rpx;
					margin-left: 20rpx;
					color: #999;
				}
			}

			.overviewClass {
				background-color: #f3efef;
				border-top: 10rpx solid #e8e2e2;
				padding: 20rpx 16rpx;
				height: 3%;
				font-size: 20px;
				margin-top: 5%;

				/deep/ .u-section__title__text {
					color: #999;
				}

				/deep/ .u-section__title__icon-wrap {
					.uicon-column-line {
						color: #999 !important;
					}
				}

				/deep/ .u-section__right-info {
					color: rgb(58, 118, 238) !important
				}

			}

			.chartClass {
				width: 100%;
				height: 300px;
				margin-top: 20rpx;
				padding-bottom: 50px;
			}
		}
	</style>
</head>
<body>
<%@include file="/page/admin/jsp/common/head.jspf" %>
<div class="main-container" id="main-container">
	<script type="text/javascript">
		try {
			ace.settings.check('main-container', 'fixed');
		} catch (e) {
		}
	</script>
	<div class="main-container-inner">
		<a class="menu-toggler" id="menu-toggler" href="javascript:void(0)"><span class="menu-text"></span></a>
		<%@include file="/page/client/client_menu_lr_fx.jspf" %>
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed');
					} catch (e) {
					}
				</script>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="admin/pc/index">首页</a>
					</li>
					<li><a>报表明细</a></li>
					<li class="active">利润报表分析</li>
				</ul>
				<div class="nav-search" id="nav-search">
					<input type="hidden" id="parentId" value="${parentId}"/>
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
																<form class="form-search"
																	  action="${ctx}/client/zcfz/table/fx" type="get">
																	<div class="row">
																		<div class="widget-main">
																			<!--<span class="">
                                                                                <label>单位名称:</label>
                                                                                <input id="orgId" type="text" placeholder="单位名称" class="input-sm" />
                                                                            </span>&nbsp-->
																			<span class="">
								<label>开始时间:</label>
<%--								<input id="kjyearMoth" type="text" placeholder="会计年月" class="input-sm" />--%>
								<input class="datetimepicker" id="startTime" name="startTime" type="text">
							</span>&nbsp
																			<span class="">
								<label>结束时间:</label>
<%--								<input id="kjyearMoth" type="text" placeholder="会计年月" class="input-sm" />--%>
								<input class="datetimepicker" id="endTime" name="endTime" type="text">
							</span>&nbsp

																		</div>
																		<div class="col-xs-12 col-sm-8">
																			<div class="input-group">
								<span class="input-group-btn">
									<button id="searchBtn" type="submit" class="btn btn-purple btn-xs">
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
																</form>
																<br>
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
															<div class="row">
																<div class="analyzeProperty">
																	<div class="contentDesc">
																		<div class="propertyDesc">
																			<div class="totalProperty"
																				 @click="gotoDetail(1)">
																				<label style="font-size: 20px">资产总计</label>
																				<label style="font-size: 20px">${data.yysr.sumMoney}万</label>
																				<%
																					Map data = (Map) request.getAttribute("data");
																					Map yysr = MapUtils.getMap(data, "yysr");
																					Map yycb = MapUtils.getMap(data, "yycb");
																					Map yylr = MapUtils.getMap(data, "yylr");
																					Map lrze = MapUtils.getMap(data, "lrze");
																					Map jlr = MapUtils.getMap(data, "jlr");
																					String yysr_onRise = MapUtils.getString(yysr, "onRise");
																					String yycb_onRise = MapUtils.getString(yycb, "onRise");
																					String yylr_onRise = MapUtils.getString(yylr, "onRise");
																					String lrze_onRise = MapUtils.getString(lrze, "onRise");
																					String jlr_onRise = MapUtils.getString(jlr, "onRise");
																					String yysrHtml = "";
																					String yycbHtml = "";
																					String yylrHtml = "";
																					String lrzeHtml = "";
																					String jlrHtml = "";
																					if (yysr_onRise.equals("-")) {
																						yysrHtml = "<label>同比<span style=\"color:green;font-size: 20px\">↓-%</span></label>";
																					} else if (Integer.parseInt(yysr_onRise)>0){
																						yysrHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↑"+yysr_onRise+"%</span></label>";
																					}else {
																						yysrHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↓"+yysr_onRise+"%</span></label>";
																					}
																					
																					if (yycb_onRise.equals("-")) {
																						yycbHtml = "<label>同比<span style=\"color:green;font-size: 20px\">↓-%</span></label>";
																					} else if (Integer.parseInt(yycb_onRise)>0){
																						yycbHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↑"+yycb_onRise+"%</span></label>";
																					}else {
																						yycbHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↓"+yycb_onRise+"%</span></label>";
																					}
																					
																					if (yylr_onRise.equals("-")) {
																						yylrHtml = "<label>同比<span style=\"color:green;font-size: 20px\">↓-%</span></label>";
																					} else if (Integer.parseInt(yylr_onRise)>0){
																						yylrHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↑"+yylr_onRise+"%</span></label>";
																					}else {
																						yylrHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↓"+yylr_onRise+"%</span></label>";
																					}

																					if (lrze_onRise.equals("-")) {
																						lrzeHtml = "<label>同比<span style=\"color:green;font-size: 20px\">↓-%</span></label>";
																					} else if (Integer.parseInt(lrze_onRise)>0){
																						lrzeHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↑"+lrze_onRise+"%</span></label>";
																					}else {
																						lrzeHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↓"+lrze_onRise+"%</span></label>";
																					}

																					if (jlr_onRise.equals("-")) {
																						jlrHtml = "<label>同比<span style=\"color:green;font-size: 20px\">↓-%</span></label>";
																					} else if (Integer.parseInt(jlr_onRise)>0){
																						jlrHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↑"+jlr_onRise+"%</span></label>";
																					}else {
																						jlrHtml = "<label>同比<span style=\"color:red;font-size: 20px\">↓"+jlr_onRise+"%</span></label>";
																					}
																				%>
																				<%= yysrHtml%>

																			</div>
																			<div class="totalProperty">
																				<label style="font-size: 20px">营业成本</label>
																				<label style="font-size: 20px">${data.yycb.sumMoney}万</label>
																				<%= yycbHtml%>
																			</div>
																			<div class="totalProperty">
																				<label style="font-size: 20px">营业利润</label>
																				<label style="font-size: 20px">${data.yylr.sumMoney}万</label>
																				<%= yylrHtml%>
																			</div>
																			<div class="totalProperty">
																				<label style="font-size: 20px">利润总额</label>
																				<label style="font-size: 20px">${data.lrze.sumMoney}万</label>
																				<%= lrzeHtml%>
																			</div>
																			<div class="totalProperty">
																				<label style="font-size: 20px">净利润</label>
																				<label style="font-size: 20px">${data.jlr.sumMoney}万</label>
																				<%= jlrHtml%>
																			</div>
																		</div>
																	</div>
																	<table>
																		<thead>
																		<span class="overviewClass">
                                                                                概述
                                                                            </span>
																		</thead>
																		<tbody>
																		<tr>
																			<div :content="gyContent"
																				 style="font-size: 15px">${info.gy}</div>
																		</tr>
																		</tbody>

																	</table>
																	<table>
																		<thead>
																		<span class="overviewClass">
                                                                                预警
                                                                            </span>
																		</thead>
																		<tbody>
																		<tr>
																			<div :content="gyContent"
																				 style="font-size: 15px">${info.yj}</div>
																		</tr>
																		</tbody>

																	</table>
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
						</div>
						<!-- PAGE CONTENT ENDS -->
					</div>
				</div>
			</div>
		</div>
		<%@include file="/page/admin/jsp/common/right.jspf" %>
	</div>
	<a href="javascript:void(0)" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="icon-double-angle-up icon-only bigger-110"></i>
	</a>
</div>
</body>
</html>