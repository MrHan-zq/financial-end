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
					<th class="center" width="51%">标题</th>
					<th class="center" width="5%">阅读数</th>
					<th class="center" width="8%">作者</th>
					<th class="center" width="10%">发布日期</th>
					<th class="center" width="8%">发布人</th>
					<th class="center" width="8%">手机号</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${msgList}" var="msgList">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${msgList.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<td class="left"   width="51%">${msgList.title}</td>
						<td class="center" width="5%">${msgList.viewNum}</td>
						<td class="center" width="8%">${msgList.author}</td>
						<td class="center" width="10%"><fmt:formatDate value="${msgList.createTime}" type="both" /></td>
						<td class="center" width="8%">${msgList.upPerson}</td>
						<td class="center" width="8%">${msgList.upPersonTel}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>