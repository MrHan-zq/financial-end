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
								<label>字典名称:</label>
								<input id="code_name" type="text" placeholder="字典名称" class="input-sm" />
							</span>&nbsp
							<span class="">
								<label>字典编码:</label>
								<input id="code_no" type="text" placeholder="字典编码" class="input-sm" />
							</span>&nbsp
							<span class="">
								<label>来源表名称:</label>
								<select id="use_area" name="use_area">
									<option value="">--</option>
									<option value="1">资产负债表</option>
									<option value="2">利润表</option>
									<option value="3">现金流量表</option>
								</select>
							</span>&nbsp
						</div>
						<div class="col-xs-12 col-sm-8">
							<div class="input-group">
								<span class="input-group-btn">
									<button id="searchBtn" type="button" class="btn btn-purple btn-xs" onclick="onSearchBtn()">
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
			<div class="row" id="codeLibrList">
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
									<th class="center">字典ID</th>
									<th class="center">字典名称</th>
									<th class="center">字典编码</th>
									<th class="center">报表类型</th>
									<th class="center">作用域</th>
									<th class="center">作用域说明</th>
									<th class="center">数据类型</th>
									
									<th class="center">资产类型</th>
									<th class="center">是否有详情</th>
									<th class="center">概要</th>
									<th class="center">预警</th>
									<th class="center">模块</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${codeLibrList}" var="codeLibr">
									<tr>
										<td class="center">
											<label>
												<input type="checkbox" class="ace" value="${codeLibr.id}" />
												<span class="lbl"></span>
											</label>
										</td>
										<td class="center">${codeLibr.id}</td>
										<td class="center">${codeLibr.codeName}</td>
										<td class="center">${codeLibr.codeNo}</td>
										<td class="center">${codeLibr.reportType}</td>
										<td class="center">${codeLibr.useArea}</td>
										<td class="center">${codeLibr.useAreaRemark}</td>
										<td class="center">${codeLibr.dataType}</td>
										
										<td class="center">${codeLibr.isCurrent}</td>
										<td class="center">${codeLibr.isDetails}</td>
										<td class="center">${codeLibr.survey}</td>
										<td class="center">${codeLibr.warn}</td>
										<td class="center">${codeLibr.clzz}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<input type="hidden" value="${curPage}" id="pages">
					<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
				</div>
			</div>
		</div>
	</div>
</div>