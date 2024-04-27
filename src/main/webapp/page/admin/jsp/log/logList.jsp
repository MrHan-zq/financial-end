<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

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