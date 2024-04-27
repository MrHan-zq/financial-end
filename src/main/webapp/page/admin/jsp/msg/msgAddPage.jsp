<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form id="tModeAddForm" class="form-horizontal" role="form" action="msg/msgSave" method="post">

	<div class="page-content">
		<div class="page-header">
			<h4>
				新增
				<small>
					<i class="icon-double-angle-right"></i>
				</small>
			</h4>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-4">数组队列:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="arryQue" id="arryQue" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-4">标题:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="title" id="title" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-4">视图总数:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="viewNum" id="viewNum" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-4">作者:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="author" id="author" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-4">更新姓名:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="upPerson" id="upPerson" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-4">更新人电话:</label>
					<div class="col-sm-9">
						<input class="input-sm" type="text" name="upPersonTel" id="upPersonTel" />
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-4">资讯类型:</label>
					<div class="col-sm-9">
						<select id="clzz">
							<option value="1" selected="selected">每日资讯</option>
							<option value="2">定制资讯</option>
						</select>
						<div class="space-2"></div>
						<div class="help-block" id="input-size-slider"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-4">内容:</label>
					<div class="col-sm-9">
						<textarea id="demo" style="display: none;"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-4"></label>
					<div class="col-sm-9">
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
    layui.use(['layedit'], function() {
	   var layedit = layui.layedit;
       layedit.set({
         uploadImage: {
            url: rootPath+'/msg/uploadImg',
            type: 'post' //默认post
         }
       });
	   var editIndex = layedit.build('demo',{
	           height:400
	   });
  		
  		$("#saveButton").click("click", function() {
			var content=layedit.getContent(editIndex);
			var arryQue =$("#arryQue").val();
			var title =$("#title").val();
			//var content =$("#content").val();
			var viewNum =$("#viewNum").val();
			var author =$("#author").val();
			var upPerson =$("#upPerson").val();
			var upPersonTel =$("#upPersonTel").val();
			var clzz=$("#clzz").val();
			if (clzz == null || clzz == undefined || clzz == '') { 
				alert("请输入资讯类型");
				return;
			} 
			var mode = {arryQue:arryQue,
						title:title,
						content:content,
						viewNum:viewNum,
						author:author,
						upPerson:upPerson,
						upPersonTel:upPersonTel,
						clzz:clzz
			};
			$.ajax({
				type : "POST",
				contentType: 'application/json;charset=UTF-8',
				dataType: "json",
				cache : false,
				async : true,// 设置异步为false,重要！
				url : rootPath+'/msg/msgSave',
				data: JSON.stringify(mode),
				success:function(data){
					$("#returnBtn").click();
				},
				error: function (msg) {//ajax请求失败后触发的方法
					console.log(msg);//弹出错误信息
		        }
			});
		});
	});
	
	$(function() {
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
					url : rootPath+'/msg/msgListAndButtonList',
					data: parm,
					success:function(data){
						$("#msgListAndButtonList").html(data);  
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
				});
		});
		
	});
</script>