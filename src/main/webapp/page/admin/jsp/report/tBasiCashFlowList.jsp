<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h3 class="header smaller lighter blue">现金流量列表</h3> -->
	<div class="table-header">
		现金流量列表
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
					<th class="center">经营活动现金流量净额(本期)</th>
					<th class="center">经营活动现金流量净额(本年)</th>
					<th class="center">投资活动现金流量净额(本期)</th>
					<th class="center">投资活动现金流量净额(本年)</th>
					<th class="center">筹资活动现金流量净额(本期)</th>
					<th class="center">筹资活动现金流量净额(本年)</th>
					<th class="center">汇率变动的影响(本期)</th>
					<th class="center">汇率变动的影响(本年)</th>
					<th class="center">期末现金及现金等价物余额(本期)</th>
					<th class="center">期末现金及现金等价物余额(本年)</th>
					<th class="center">导入时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tBasiCashFlowList}" var="tBasiCashFlow">
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
						<td class="center">${tBasiCashFlow.kjyearMoth}</td>
						<td class="center">${tBasiCashFlow.bqJyhdJyhdcsdje}</td>
						<td class="center">${tBasiCashFlow.jyhdJyhdcsdje}</td>
						<td class="center">${tBasiCashFlow.bqTzhdTzhdscsdxjllje}</td>
						<td class="center">${tBasiCashFlow.tzhdTzhdscsdxjllje}</td>
						<td class="center">${tBasiCashFlow.bqCzhdCzhdcsdxjllje}</td>
						<td class="center">${tBasiCashFlow.czhdCzhdcsdxjllje}</td>
						<td class="center">${tBasiCashFlow.bqCzhdHlbdxjdyx}</td>
						<td class="center">${tBasiCashFlow.czhdHlbdxjdyx}</td>
						<td class="center">${tBasiCashFlow.bqQmxjjxjdjwye}</td>
						<td class="center">${tBasiCashFlow.qmxjjxjdjwye}</td>
						<td class="center">${tBasiCashFlow.impTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>