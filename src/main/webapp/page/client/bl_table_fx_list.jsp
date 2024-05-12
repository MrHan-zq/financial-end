<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>--%>

<div class="col-xs-12">
	<!-- <h3 class="header smaller lighter blue">资产负债列表</h3> -->
	<div class="table-header">
		利润明细列表
	</div>
	<div class="table-responsive">
		<table id="sample-table-2" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">科目</th>
					<th class="center">金额</th>
					<th class="center">同比</th>
					<th class="center">环比</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${dataList}" var="data">
				<tr>
					<td class="center">${data.codeName}</td>
					<td class="center">${data.sumMoney}</td>
					<td class="center">${data.onRise}</td>
					<td class="center">${data.linkRise}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
<%--	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />--%>
</div>