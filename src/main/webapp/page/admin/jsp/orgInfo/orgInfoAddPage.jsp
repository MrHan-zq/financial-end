<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form id="orgAddForm" class="form-horizontal" role="form" action="orgInfo/orgSave" method="post">

	<div class="page-content">
		<div class="page-header">
			<h4>
				新增公司信息
				<small>
					<i class="icon-double-angle-right"></i>
				</small>
			</h4>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">公司名称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="orgName" id="orgNames" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">公司简称:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="shortName" id="shortName" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">营业号:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="businessNum" id="businessNum" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">法人:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="legalerName" id="legalerName" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">实缴资本:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="paidCapital" id="paidCapital" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">认缴资本:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="confusingCapital" id="confusingCapital" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">注册地址:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="registeredAddr" id="registeredAddr" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">所属行业:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="industry" id="industry" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">是否上市:</label>
					<div class="col-sm-9">
						<select name="isList" id="isList"> 
						<option value="">请选择</option> 
						<option value="0">是</option> 
						<option value="1">否</option>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">公司性质:</label>
					<div class="col-sm-9">
						<select name="orgProperty" id="orgProperty"> 
						<option value="">请选择</option> 
							<option value="0">国企</option> 
							<option value="1">外资</option>
							<option value="2">私营</option>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">负责人:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="resPersonName" id="resPersonName"  />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">负责人电话:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="resPersonTel" id="resPersonTel"  />
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
								<option value="${repor.value}">${repor.name}</option> 
							</c:forEach>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4">上级公司:</label>
					<div class="col-sm-9">
						<select name="parentId" id="parentId"> 
							<option value="">请选择</option> 
							<c:forEach items="${orgList}" var="orgList">
								<option value="${orgList.id}">${orgList.orgName}</option> 
							</c:forEach>
						</select> 
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
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
			var orgNames = $("#orgNames").val();
			if (orgNames == null || orgNames == undefined || orgNames == '') { 
				alert("请输入公司名称");
				return;
			} 
			var reportLimit = $("#reportLimit").val();
			if (reportLimit == null || reportLimit == undefined || reportLimit == '') { 
				alert("请选择报表类型");
				return;
			} 
			$("#orgAddForm").submit();
		});
		
		$("#returnBtn").click("click", function() {
			var parm = {
					pageNow : 1,
					pageSize : 10
				};
				
				$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/orgInfo/orgInfoList',
					data: parm,
					success:function(data){
						$("#orgInfoList").html(data);    
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
				});
		});
		
	});
</script>