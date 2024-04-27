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
					<th class="center">币种</th>
					<th class="center">主体账簿</th>
					<th class="center">凭证号</th>
					<th class="center">科目名称</th>
					<th class="center">摘要</th>
					<th class="center">对方科目</th>
					<th class="center">借方</th>
					<th class="center">贷方</th>
					<th class="center">方向</th>
					<th class="center">余额</th>
					<th class="center">导入时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tDetailList}" var="tDetail">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${role.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<!--<td class="center">
							<a>${tDetail.orgId}</a>
						</td>-->
						<td class="center">${tDetail.kjyearMothDay}</td>
						<td class="center">${tDetail.hbzl}</td>
						<td class="center">${tDetail.ztzb}</td>
						<td class="center">${tDetail.bzh}</td>
						<td class="center">${tDetail.subjectName}</td>
						<td class="center">${tDetail.remark}</td>
						<td class="center">${tDetail.ohterSubName}</td>
						<td class="center">${tDetail.jf}</td>
						<td class="center">${tDetail.df}</td>
						<td class="center">${tDetail.fx}</td>
						<td class="center">${tDetail.ye}</td>
						<td class="center">${tDetail.impTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>