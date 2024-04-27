<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="row">
	<div class="widget-box">
		<div class="widget-header widget-header-small">
			<h7 class="lighter">搜&nbsp;索</h7>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form class="form-search">
					<div class="row">
						<div class="widget-main">
							<span class="">
								<label>标题:</label>
								<input id="title" type="text" placeholder="标题" class="input-sm" size="50"  />
							</span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							<span class="">
								<label>作者:</label>
								<input id="author" type="text" placeholder="作者" class="input-sm" />
							</span>
						</div>
						<div class="col-xs-12 col-sm-8">
							<div class="input-group">
								<span class="input-group-btn">
									<button id="searchBtn" type="button" class="btn btn-purple btn-xs" onclick="msgSearchBtn()">
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button>
									&nbsp; &nbsp; &nbsp;
									<button id="returnBtn" class="btn btn-xs" type="reset">
										<i class="icon-undo bigger-110"></i>
										重置
									</button>
								</span>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-6">
		<div class="doc-buttons" style="padding: 10px 0">
			<c:forEach items="${buttonList}" var="key">
				<button type="button" id="${fn:split(key.btn,',')[0]}" name="${fn:split(key.btn,',')[1]}" class="${fn:split(key.btn,',')[2]}" onclick="${fn:split(key.btn,',')[0]}()">${fn:split(key.btn,',')[3]}</button>
			</c:forEach>
		</div>
	</div>
</div>

<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="row" id="msgList">
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
			</div>
		</div>
	</div>
</div>