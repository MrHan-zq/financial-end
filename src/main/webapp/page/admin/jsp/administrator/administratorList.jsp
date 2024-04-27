<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	
	<div class="table-header">
		后台用户列表
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
					<th class="center">用户名称</th>
					<th class="center">账号名称</th>
					<th class="center">创建人</th>
					<th class="center">所属角色</th>
					<th class="center">创建时间</th>
					<th class="center">账号锁定</th>
					<th class="center">描述</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${administratorList}" var="administrator">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${administrator.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<td class="center">
							<a>${administrator.user_name}</a>
						</td>
						<td class="center">${administrator.account_name}</td>
						<td class="center">${administrator.create_name}</td>
						<td class="center">
							${administrator.role_name}
						</td>
						<td class="center">
							<fmt:formatDate value="${administrator.create_time}" type="both" />
						</td>
						<td class="center">
							<label>
								<c:choose>
									<c:when test="${administrator.locked eq 0}">
										未锁定
										<i class="icon-unlock"></i>
									</c:when>
									<c:otherwise>
										已锁定
										<i class="icon-lock"></i>
									</c:otherwise>
								</c:choose>
								<span class="lbl"></span>
							</label>
						</td>
						<td class="center">
							${administrator.description}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>