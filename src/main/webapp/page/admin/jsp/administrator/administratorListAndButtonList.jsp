<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="row">
	<div class="widget-box">
		<div class="widget-header widget-header-small">
			<h8 class="lighter">搜&nbsp;索</h8>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form class="form-search">
					<div class="row">
						<div class="widget-main">
							<span class="">
								<label>用户名称:</label>
								<input id="userNameSearch" type="text" placeholder="用户名称" class="input-sm" />
							</span>
							&nbsp;
							<span class="">
								<label>账号名称:</label>
								<input id="accountNameSearch" type="text" placeholder="账号名称" class="input-sm" />
							</span>
						</div>
						<div class="col-xs-12 col-sm-8">
							<div class="input-group">
								<span class="input-group-btn">
									<button id="searchBtn" type="button" class="btn btn-purple btn-xs" onclick="adminSearchBtn()">
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
			<div class="row" id="administratorList">
				<div class="col-xs-12">
					<!-- <h8 class="header smaller lighter blue">后台用户列表</h8> -->
					<div class="table-header">
						后台用户列表
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
									<th class="center">用户名称</th>
									<th class="center">账号名称</th>
									<th class="center">创建人</th>
									<th class="center">所属角色</th>
									<th class="center">创建时间</th>
									<th class="center">账号锁定</th>
									<th class="center">描述</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${administratorList}" var="administrator">
									<tr>
										<td class="center">
											<label>
												<input type="checkbox" class="ace" value="${administrator.id}" />
												<span class="lbl"></span>
											</label>
										</td>
										<td class="center">
											<a>${administrator.user_name}</a>
										</td>
										<td class="center">${administrator.account_name}</td>
										<td class="center">${administrator.create_name}</td>
										<td class="center">
											${administrator.role_name}
										</td>
										<td class="center">
											<fmt:formatDate value="${administrator.create_time}" type="both" />
										</td>
										<td class="center">
											<label>
												<c:choose>
													<c:when test="${administrator.locked eq 0}">
														未锁定
														<i class="icon-unlock"></i>
													</c:when>
													<c:otherwise>
														已锁定
														<i class="icon-lock"></i>
													</c:otherwise>
												</c:choose>
												<span class="lbl"></span>
											</label>
										</td>
										<td class="center">
											${administrator.description}
										</td>
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

