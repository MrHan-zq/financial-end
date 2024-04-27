<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h3 class="header smaller lighter blue">其他账龄分析表</h3> -->
	<div class="table-header">
		账龄分析表
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
					<th class="center">会计年月</th>
					<th class="center">核算项目代码</th>
					<th class="center">核算项目名称</th>
					<th class="center">余额</th>
					<th class="center">0-90天</th>
					<th class="center">90-180天</th>
					<th class="center">180-360天</th>
					<th class="center">360-720天</th>
					<th class="center">720-1080天</th>
					<th class="center">1080天以上</th>
					<th class="center">导入时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${otherTAgeAnalysisList}" var="tKmye">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${role.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<!--<td class="center">
							<a>${tKmye.orgId}</a>
						</td>-->
						<td class="center">${tKmye.kjyearMoth}</td>
						<td class="center">${tKmye.projectCode}</td>
						<td class="center">${tKmye.projectName}</td>
						<td class="center">${tKmye.balance}</td>
						<td class="center">${tKmye.due1To30Days}</td>
						<td class="center">${tKmye.due91To180Days}</td>
						<td class="center">${tKmye.due181To360Days}</td>
						<td class="center">${tKmye.due361To720Days}</td>
						<td class="center">${tKmye.due721To1080Days}</td>
						<td class="center">${tKmye.due1081To1440Days}</td>
						<td class="center">${tKmye.impTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>