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
                            								<label>报表类别:</label>
                            								<select id="reportType" name="reportType">
                            									<option value="">--</option>
                            									<option value="1">资产负债表</option>
                            									<option value="2">利润表</option>
                            									<option value="3">现金流量表</option>
                            									<option value="5">比率分析</option>
                            								</select>
                            							</span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
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
			<div class="row" id="tReportResultList">
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
			</div>
		</div>
	</div>
</div>