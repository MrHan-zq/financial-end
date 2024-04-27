<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h3 class="header smaller lighter blue">科目余额表</h3> -->
	<div class="table-header">
		科目余额表
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
					<!--<th class="center">单位名称</th>-->
					<th class="center">会计年月</th>
					<th class="center">科目编码</th>
					<th class="center">科目名称</th>
					<th class="center">期初余额</th>
					<th class="center">本期借方发生</th>
					<th class="center">本期贷方发生</th>
					<th class="center">累计借方发生</th>
					<th class="center">累计贷方发生</th>
					<th class="center">期末余额</th>
					<th class="center">导入时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tKmyeList}" var="tKmye">
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
						<td class="center">${tKmye.subjectCode}</td>
						<td class="center">${tKmye.subjectName}</td>
						<td class="center">${tKmye.qcjfye}</td>
						<td class="center">${tKmye.bqjffse}</td>
						<td class="center">${tKmye.bqdffse}</td>
						<td class="center">${tKmye.bnjflj}</td>
						<td class="center">${tKmye.bndflj}</td>
						<td class="center">${tKmye.qmjfye}</td>
						<td class="center">${tKmye.impTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>