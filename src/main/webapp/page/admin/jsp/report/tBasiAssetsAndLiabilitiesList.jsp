<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h3 class="header smaller lighter blue">资产负债列表</h3> -->
	<div class="table-header">
		资产负债列表
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
					<th class="center">流动资产合计(年初)</th>
					<th class="center">流动资产合计(期末)</th>
					<!-- <th class="center">非流动资产合计(年初)</th>
					<th class="center">非流动资产合计(期末)</th> -->
					<th class="center">资产总计(年初)</th>
					<th class="center">资产总计(期末)</th>
					<th class="center">流动负债合计(年初)</th>
					<th class="center">流动负债合计(期末)</th>
					<!-- <th class="center">非流动负债合计(年初)</th>
					<th class="center">非流动负债合计(期末)</th> -->
					<th class="center">负债合计(年初)</th>
					<th class="center">负债合计(期末)</th>
					<!-- <th class="center">所有者权益合计(年初)</th>
					<th class="center">所有者权益合计(期末)</th> -->
					<th class="center">股东权益总计(年初)</th>
					<th class="center">股东权益总计(期末)</th>
					<th class="center">导入时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tBasiAssetsAndLiabilitiesList}" var="tBasiAssetsAndLiabilities">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${role.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<!--<td class="center">
							<a>${tBasiAssetsAndLiabilities.orgId}</a>
						</td>-->
						<td class="center">${tBasiAssetsAndLiabilities.kjyearMoth}</td>
						<td class="center">${tBasiAssetsAndLiabilities.ncLdzcHj}</td>
						<td class="center">${tBasiAssetsAndLiabilities.qmLdzcHj}</td>
						<!-- <td class="center">${tBasiAssetsAndLiabilities.ncFldzcFldzchj}</td>
						<td class="center">${tBasiAssetsAndLiabilities.qmFldzcFldzchj}</td>-->
						<td class="center">${tBasiAssetsAndLiabilities.ncZczj}</td>
						<td class="center">${tBasiAssetsAndLiabilities.qmZczj}</td>
						<td class="center">${tBasiAssetsAndLiabilities.ncLdfzLdfzhj}</td>
						<td class="center">${tBasiAssetsAndLiabilities.qmLdfzLdfzhj}</td>
						<!--<td class="center">${tBasiAssetsAndLiabilities.ncFldfzCqfzhj}</td>
						<td class="center">${tBasiAssetsAndLiabilities.qmFldfzCqfzhj}</td>-->
						<td class="center">${tBasiAssetsAndLiabilities.ncDysxFzhj}</td>
						<td class="center">${tBasiAssetsAndLiabilities.qmDysxFzhj}</td>
						<!--<td class="center">${tBasiAssetsAndLiabilities.ncGdqySyzqyhj}</td>
						<td class="center">${tBasiAssetsAndLiabilities.qmGdqySyzqyhj}</td>-->
						<td class="center">${tBasiAssetsAndLiabilities.ncGdqyFzhsyzqy}</td>
						<td class="center">${tBasiAssetsAndLiabilities.qmGdqyFzhsyzqy}</td>
						<td class="center">${tBasiAssetsAndLiabilities.impTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>