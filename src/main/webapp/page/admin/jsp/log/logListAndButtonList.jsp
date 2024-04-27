<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

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
							<span class="">
								<label>用户名称:</label>
								<input id="userNameSearch" type="text" placeholder="用户名称" class="input-sm" />
							</span>
						</div>
						<div class="col-xs-12 col-sm-8">
							<div class="input-group">
								<span class="input-group-btn">
									<button id="searchBtn" type="button" class="btn btn-purple btn-xs">
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
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-6">
		<div class="doc-buttons" style="padding: 10px 0">
			<c:forEach items="${buttonList}" var="key">
				<button type="button" id="${fn:split(key.btn,',')[0]}" name="${fn:split(key.btn,',')[1]}" class="${fn:split(key.btn,',')[2]}" onclick="${fn:split(key.btn,',')[0]}()">${fn:split(key.btn,',')[3]}</button>
			</c:forEach>
		</div>
	</div>
</div>

<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="row" id="logList">
				<div class="col-xs-12">
					<!-- <h8 class="blue">用户操作日志列表</h8> -->
					<div class="table-header">
						用户操作日志列表
					</div>
					<div class="table-responsive">
						<table id="sample-table-2" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">
										<label>
											<span class="lbl"></span>
										</label>
									</th>
									<th class="center">用户Id</th>
									<th class="center">用户名称</th>
									<th class="center">账号名称</th>
									<th class="center">接口名称</th>
									<th class="center">接口地址</th>
									<th class="center">方法名</th>
									<th class="center">接口状态</th>
									<th class="center">请求IP</th>
									<th class="center">用户代理信息</th>
									<th class="center">服务器地址</th>
									<th class="center">请求时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userApiLogList}" var="userApiLog">
									<tr>
										<td class="center">
											<label>
												<input type="checkbox" class="ace" value="${userApiLog.id}" />
												<span class="lbl"></span>
											</label>
										</td>
										<td class="center">${userApiLog.userId}</td>
										<td class="center">
											<a>${userApiLog.userName}</a>
										</td>
										<td class="center">${userApiLog.accountName}</td>
										<td class="center">
											${userApiLog.apiName}
										</td>
										<td class="center">
											${userApiLog.apiUriPath}
										</td>
										<td class="center">
											${userApiLog.classMethodName}
										</td>
										<td class="center">
											<c:choose>
												<c:when test="${userApiLog.status eq 0}">
													失败
												</c:when>
												<c:otherwise>
													成功
												</c:otherwise>
											</c:choose>
										</td>
										<td class="center">
											${userApiLog.reqIp}
										</td>
										<td class="center">
											${userApiLog.userAgentInfo}
										</td>
										<td class="center">
											${userApiLog.servAddr}
										</td>
										<td class="center">
											<fmt:formatDate value="${userApiLog.createTime}" type="both" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
				</div>
			</div>
		</div>
	</div>
</div>