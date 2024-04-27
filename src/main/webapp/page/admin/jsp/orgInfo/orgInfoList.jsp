<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld" %>

<div class="col-xs-12">
	<!-- <h3 class="header smaller lighter blue">财务科目列表</h3> -->
	<div class="table-header">
		财务科目列表
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
					<th class="center">机构名称</th>
					<th class="center">简称</th>
					<th class="center">营业号</th>
					<th class="center">法人</th>
					<th class="center">是否上市</th>
					<th class="center">负责人</th>
					<th class="center">负责人电话</th>
					<th class="center">公司地址</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orgInfoList}" var="orgInfo">
					<tr>
						<td class="center">
							<label>
								<input type="checkbox" class="ace" value="${orgInfo.id}" />
								<span class="lbl"></span>
							</label>
						</td>
						<td class="center">
							<a>${orgInfo.orgName}</a>
						</td>
						<td class="center">${orgInfo.shortName}</td>
						<td class="center">${orgInfo.businessNum}</td>
						<td class="center">${orgInfo.legalerName}</td>
						<td class="center">
							<c:if test="${orgInfo.isList eq 0}">
								是
							</c:if>
							<c:if test="${orgInfo.isList eq 1}">
								否
							</c:if>
						</td>
						<td class="center">
							${orgInfo.resPersonName}
						</td>
						<td class="center">
							${orgInfo.resPersonTel}
						</td>
						<td class="center">
						    ${orgInfo.registeredAddr}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${curPage}" />
</div>
<!-- <div id="dialog-message" class="hide">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-16">
			<form id="addSubject" class="form-horizontal" role="form" action="/subject/addSubject" method="post">
        		<fieldset>
        		<div class="form-group">
        		  <div style="float:left;width:260px">
            		<label class="col-sm-4 control-label no-padding-right text-danger" for="form-field-1">公司名称:</label>
            		<input type="text" name="orgName" id="orgName"  class="text ui-widget-content ui-corner-all" style="size:9;">
            	  </div>	
            	  <div style="float:left;width:260px">	
            		<label class="col-sm-4 control-label no-padding-right text-danger" for="form-field-1">公司简称:</label>
            		<input type="text" name="shortName" id="shortName"  class="text ui-widget-content ui-corner-all" style="size:9;">
            	  </div>
            	</div>
            	<div class="form-group">
            	  <div style="float:left;width:260px">
           		 	<label  class="col-sm-4 control-label no-padding-right" for="form-field-1">父科目编号:</label>
            		<input  name="paterCode" id="paterCode-s"  class="text ui-widget-content ui-corner-all" style="size:9;">
            	  </div>	
            	  <div style="float:left;width:260px">
            		<label  class="col-sm-4 control-label no-padding-right" for="form-field-1">科目类型:</label>
            		<input type="text" name="subjectClass" id="subjectClass-s"  class="text ui-widget-content ui-corner-all " style="size:9;">
            	  </div>
            	</div>
            	<div class="form-group">
            	  <div style="float:left;width:260px">
           		 	<label  class="col-sm-4 control-label no-padding-right" for="form-field-1">是否禁用:</label>
           		 	<select name="isdelete" id="isdelete-s">
           		 		<option value="0">否</option>
           		 		<option value="1">是</option>
           		 	</select>
            	  </div>	
            	  <div style="float:left;width:260px">
            		<label  class="col-sm-4 control-label no-padding-right" for="form-field-1">来源:</label>
            		<select name="source" id="source-s">
           		 		<option value="0">初始化</option>
           		 		<option value="1">导入新增</option>
           		 	</select>
            	  </div>
            	</div>
            	<div class="form-group">
            	  <div style="float:left;width:580px">
           		 	<label  class="col-sm-4 control-label no-padding-right" for="form-field-1">科目描述</label>
            		<input name="subjectRemark" id="subjectRemark-s"  class="text ui-widget-content ui-corner-all" style="size:9;">
            	  </div>	
            	</div>
        		</fieldset>
    		</form>
			</div>
		</div>
	</div>
</div> -->






