<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style type="text/css">
	#content .form p em{
    display: inline-block;
    width:70px;
    text-align: right;
    margin-right: 20px;
}  	
</style>

<form id="orgEdit" class="form-horizontal" role="form" action="orgInfo/orgEdit" method="post">

	<div class="page-content">
		<div class="page-header">
			<h4>
				编辑公司信息
				<small>
					<i class="icon-double-angle-right"></i>
				</small>
			</h4>
		</div>
		<input type="hidden" name="id" value="${orgInfo.id }">
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">公司名称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="orgName" id="orgNames" value="${orgInfo.orgName}"/>
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">公司简称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="shortName" id="shortName" value="${orgInfo.shortName}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">营业号:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="businessNum" id="businessNum" value="${orgInfo.businessNum}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">法人:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="legalerName" id="legalerName" value="${orgInfo.legalerName}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">实缴资本:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="paidCapital" id="paidCapital" value="${orgInfo.paidCapital}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">认缴资本:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="confusingCapital" id="confusingCapital"  value="${orgInfo.confusingCapital}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">注册地址:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="registeredAddr" id="registeredAddr" value="${orgInfo.registeredAddr}"/>
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">所属行业:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="industry" id="industry" value="${orgInfo.industry}" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">是否上市:</label>
					<div class="col-sm-9">
						<select name="isList" id="isList"> 
						<option value="">请选择</option> 
						<c:if test="${orgInfo.isList eq 0}">
							<option value="0" selected="selected">是</option> 
							<option value="1">否</option>
						</c:if>
						<c:if test="${orgInfo.isList eq 1}">
							<option value="0">是</option> 
							<option value="1" selected="selected">否</option>
						</c:if>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">公司性质:</label>
					<div class="col-sm-9">
						<select name="orgProperty" id="orgProperty"> 
						<option value="">请选择</option> 
							<c:if test="${orgInfo.orgProperty eq 0}">
								<option value="0" selected="selected">国企</option> 
								<option value="1">外资</option>
								<option value="2">私营</option>
							</c:if>
							<c:if test="${orgInfo.isList eq 1}">
								<option value="0">国企</option> 
								<option value="1" selected="selected">外资</option>
								<option value="2">私营</option>
							</c:if>
							<c:if test="${orgInfo.isList eq 2}">
								<option value="0">国企</option> 
								<option value="1">外资</option>
								<option value="2" selected="selected">私营</option>
							</c:if>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">负责人:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="resPersonName" id="resPersonName" value="${orgInfo.resPersonName}"  />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">负责人电话:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="resPersonTel" id="resPersonTel" value="${orgInfo.resPersonTel}"  />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">报表标准:</label>
					<div class="col-sm-9">
						<select name="reportLimit" id="reportLimit"> 
							<option value="">请选择</option> 
							<c:forEach items="${disList}" var="repor">
								<c:choose>
									<c:when test="${repor.value eq orgInfo.reportLimit}">
									
									</c:when>
									<c:otherwise>
										<option value="${repor.value}">${repor.name}</option> 				
									</c:otherwise>
								</c:choose>
								
							</c:forEach>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<%-- <label class="col-sm-3 control-label no-padding-right" for="form-field-4">上级公司:</label>
					<div class="col-sm-9">
						<select name="parentId" id="parentId"> 
							<option value="">请选择</option> 
							<c:forEach items="${orgList}" var="orgList">
								<option value="${orgList.id}">${orgList.orgName}</option> 
							</c:forEach>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div> --%>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">公司简介:</label>
					<div class="col-sm-9">
						<p class="pr">
							<em class="pa"></em><em></em>
							<textarea name="orgRemark" style="height:72px;width:516px;"></textarea>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-xs-12">
		<div class="clearfix form-actions">
			<div class="col-md-offset-3 col-md-9">
				<button class="btn btn-info" type="button" id="saveButton">
					<i class="icon-ok bigger-110"></i>
					保存
				</button>
				&nbsp; &nbsp; &nbsp;
				<button id="returnBtn" class="btn" type="reset">
					<i class="icon-arrow-left"></i>
					返回
				</button>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript">
$(function() {
	
	//点击事件
	$("#saveButton").click("click", function() {
		
		$("#orgEdit").submit();
		alert("编辑成功！");
		$("#returnBtn").click();
	});
	
	$("#returnBtn").click("click", function() {
		var parm = {
				pageNow : 1,
				pageSize : 10,
			};
			
			$.ajax({
				type : "GET",
				cache : false,
				dataType : "html",
				async : true,// 设置异步为false,重要！
				url : rootPath+'/orgInfo/orgInfoList',
				data: parm,
				success:function(data){
					$("#roleListAndButtonList").html(data);  
				},
				error: function (msg) {//ajax请求失败后触发的方法
					console.log(msg);//弹出错误信息
		        }
			});
	});
	
});
</script>