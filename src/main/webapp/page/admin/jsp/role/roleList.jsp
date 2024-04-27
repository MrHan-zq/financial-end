<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h3 class="header smaller lighter blue">角色列表</h3> -->
	<div class="table-header">
		角色列表
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
					<th class="center">角色名称</th>
					<th class="center">创建人</th>
					<th class="center">描述</th>
					<th class="center">创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roleList}" var="role">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${role.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<td class="center">
							<a>${role.name}</a>
						</td>
						<td class="center">${role.user_name}</td>
						<td class="center">
							${role.description}
						</td>
						<td class="center">
							<fmt:formatDate value="${role.create_time}" type="both" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>