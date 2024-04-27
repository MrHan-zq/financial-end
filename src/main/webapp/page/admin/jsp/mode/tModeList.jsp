<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h8 class="blue">列表</h8> -->
	<div class="table-header">
		列表
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
					<th class="center">报表类型</th>
					<th class="center">模板描述</th>
					<th class="center">模板条件</th>
					<th class="center">模板内容</th>
					<th class="center">模板内容中的参数</th>
					<th class="center">描述</th>
					<th class="center">模板编号</th>
					<th class="center">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tModeList}" var="tMode">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${tMode.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<td class="center">${tMode.reportType}</td>
						<td class="center">${tMode.reportConten}</td>
						<td class="center">${tMode.modeCondition}</td>
						<td class="center">${tMode.modeContent}</td>
						<td class="center">${tMode.modeValues}</td>
						<td class="center">${tMode.remark}</td>
						<td class="center">${tMode.modeArea}</td>
						<td class="center">${tMode.px}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<input type="hidden" value="${curPage}" id="pages">
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>