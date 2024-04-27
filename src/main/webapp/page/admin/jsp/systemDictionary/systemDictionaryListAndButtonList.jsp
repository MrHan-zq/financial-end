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
								<label>字典中文名称:</label>
								<input id="cnNameSearch" type="text" placeholder="字典中文名称" class="input-sm" />
							</span>
							<span class="">
								<label>字典英文名称:</label>
								<input id="enNameSearch" type="text" placeholder="字典英文名称" class="input-sm" />
							</span>
						</div>
						<div class="col-xs-12 col-sm-8">
							<div class="input-group">
								<span class="input-group-btn">
									<button id="searchBtn" type="button" class="btn btn-purple btn-xs" onclick="dictionarySearchBtn()">
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
			<div class="row" id="systemDictionaryList">
				<div class="col-xs-12">
					<div class="table-header">
                    		字典列表
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
									<th class="center">字典中文名称</th>
									<th class="center">字典英文名称</th>
									<th class="center">状态</th>
									<th class="center">创建人</th>
									<th class="center">创建时间</th>
									<th class="center">更新人</th>
									<th class="center">更新时间</th>
									<th class="center">备注</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${dictionaryList}" var="dictionary">
									<tr>
										<td class="center">
											<label>
												<input type="checkbox" class="ace" value="${dictionary.id}" />
												<span class="lbl"></span>
											</label>
										</td>
										<td class="center">
											<a>${dictionary.cn_name}</a>
										</td>
										<td class="center">${dictionary.en_name}</td>
										<td class="center">
											<c:if test="${dictionary.flag eq 0}">
												不可编辑
											</c:if>
											<c:if test="${dictionary.flag eq 1}">
												可编辑
											</c:if>
										</td>
										<td class="center">${dictionary.create_user_name}</td>
										<td class="center">
											<fmt:formatDate value="${dictionary.create_time}" type="both" />
										</td>
										<td class="center">${dictionary.update_user_name}</td>
										<td class="center">
											<fmt:formatDate value="${dictionary.update_time}" type="both" />
										</td>
										<td class="center">${dictionary.remark}</td>
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