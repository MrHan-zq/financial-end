<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h8 class="blue">字典列表</h8> -->
	<div class="table-header">
		字典列表
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
					<th class="center">字典中文名称</th>
					<th class="center">字典英文名称</th>
					<th class="center">状态</th>
					<th class="center">创建人</th>
					<th class="center">创建时间</th>
					<th class="center">更新人</th>
					<th class="center">更新时间</th>
					<th class="center">备注</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dictionaryList}" var="dictionary">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${dictionary.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<td class="center">
							<a>${dictionary.cn_name}</a>
						</td>
						<td class="center">${dictionary.en_name}</td>
						<td class="center">
							<c:if test="${dictionary.flag eq 0}">
								不可编辑
							</c:if>
							<c:if test="${dictionary.flag eq 1}">
								可编辑
							</c:if>
						</td>
						<td class="center">${dictionary.create_user_name}</td>
						<td class="center">
							<fmt:formatDate value="${dictionary.create_time}" type="both" />
						</td>
						<td class="center">${dictionary.update_user_name}</td>
						<td class="center">
							<fmt:formatDate value="${dictionary.update_time}" type="both" />
						</td>
						<td class="center">${dictionary.remark}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>