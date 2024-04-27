<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h3 class="header smaller lighter blue">利润列表</h3> -->
	<div class="table-header">
		字典列表
	</div>
	<div class="table-responsive">
		<table id="sample-table-2" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
						<label>
							<span class="lbl">选择</span>
						</label>
					</th>
					<th class="center">字典ID</th>
					<th class="center">字典名称</th>
					<th class="center">字典编码</th>
					<th class="center">报表类型</th>
					<th class="center">作用域</th>
					<th class="center">作用域说明</th>
					<th class="center">数据类型</th>
					
					<th class="center">资产类型</th>
					<th class="center">是否有详情</th>
					<th class="center">概要</th>
					<th class="center">预警</th>
					<th class="center">模块</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${codeLibrList}" var="codeLibr">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${codeLibr.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<td class="center">${codeLibr.id}</td>
						<td class="center">${codeLibr.codeName}</td>
						<td class="center">${codeLibr.codeNo}</td>
						<td class="center">${codeLibr.reportType}</td>
						<td class="center">${codeLibr.useArea}</td>
						<td class="center">${codeLibr.useAreaRemark}</td>
						<td class="center">${codeLibr.dataType}</td>
						
						<td class="center">${codeLibr.isCurrent}</td>
						<td class="center">${codeLibr.isDetails}</td>
						<td class="center">${codeLibr.survey}</td>
						<td class="center">${codeLibr.warn}</td>
						<td class="center">${codeLibr.clzz}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<input type="hidden" value="${curPage}" id="pages">
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>