<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
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
                    <th class="center">年月</th>
                    <th class="center">编码</th>
                    <th class="center">值</th>
                    <th class="center">机构</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tReportResultList}" var="tReportResultList">
					<tr>
						<td class="center">
							<label>
								<span class="lbl"></span>
							</label>
						</td>
										<td class="center" >${tReportResultList.ryearAndMonth}</td>
										<td class="center" >${tReportResultList.codeNo}</td>
										<td class="center" >${tReportResultList.dataValue}</td>
										<td class="center" >${tReportResultList.orgId}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>