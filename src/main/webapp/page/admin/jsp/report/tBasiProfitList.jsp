<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h3 class="header smaller lighter blue">利润列表</h3> -->
	<div class="table-header">
		利润列表
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
					<th class="center">营业收入(本期)</th>
					<th class="center">营业收入(本年)</th>
					<th class="center">营业利润(本期)</th>
					<th class="center">营业利润(本年)</th>
					<th class="center">利润总额(本期)</th>
					<th class="center">利润总额(本年)</th>
					<th class="center">净利润(本期)</th>
					<th class="center">净利润(本年)</th>
					<th class="center">每股收益(本期)</th>
					<th class="center">每股收益(本年)</th>
					<th class="center">导入时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tBasiProfitList}" var="tBasiProfit">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${role.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<!--<td class="center">
							<a>${tBasiProfit.orgId}</a>
						</td>-->
						<td class="center">${tBasiProfit.kjyearMoth}</td>
						<td class="center">${tBasiProfit.byZyywsr}</td>
						<td class="center">${tBasiProfit.bnZyywsr}</td>
						<td class="center">${tBasiProfit.byYylr}</td>
						<td class="center">${tBasiProfit.bnYylr}</td>
						<td class="center">${tBasiProfit.byLrze}</td>
						<td class="center">${tBasiProfit.bnLrze}</td>
						<td class="center">${tBasiProfit.byJlr}</td>
						<td class="center">${tBasiProfit.bnJlr}</td>
						<td class="center">${tBasiProfit.byMgsy}</td>
						<td class="center">${tBasiProfit.bnMgsy}</td>
						<td class="center">${tBasiProfit.impTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>