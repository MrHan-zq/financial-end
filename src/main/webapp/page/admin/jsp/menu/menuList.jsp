<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
	<div class="col-sm-6">
		<div class="doc-buttons" style="padding: 10px 0">
			<c:forEach items="${buttonList}" var="key">
				<button type="button" id="${fn:split(key.btn,',')[0]}" name="${fn:split(key.btn,',')[1]}" class="${fn:split(key.btn,',')[2]}">${fn:split(key.btn,',')[3]}</button>
			</c:forEach>
		</div>
	</div>
	<div class="col-sm-6">
		<div class="nav-search" id="nav-search">
			<form class="form-search">
				<span class="input-icon">
					<!--
					<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
					<i class="icon-search nav-search-icon"></i> 
					-->
				</span>
			</form>
		</div>
	</div>
</div>

<c:forEach var="key" items="${menuList}" varStatus="s">
	<div class="tree-folder">
		<c:if test="${key.parentId eq 0}">
			<div>
				<label>
					<input type="checkbox" class="ace" name="rootMenu" value="${key.id}" />
					<span class="lbl"></span>
				</label>
			</div>
			<div class="tree-folder-header">
				<i class="icon-minus" id="tree${s.index}"></i>
				<div class="tree-folder-name">${key.name}</div>
			</div>
		</c:if>
		<div class="tree-folder-content" style="display: block;" id="tree-folder-name${s.index}">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">
							<label>
								<span class="lbl"></span>
							</label>
						</th>
						<th class="center">菜单名称</th>
						<th class="center">菜单键值</th>
						<th class="center">菜单地址</th>
						<th class="center">菜单图标</th>
						<th class="center">菜单类型</th>
						<th class="center">描述</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="kc" items="${key.children}" varStatus="ks">
						<tr>
							<td class="center">
								<label>
									<input type="checkbox" class="ace" id="father${kc.id}" name="father${kc.id}" value="${kc.id}" />
									<span class="lbl"></span>
								</label>
							</td>
							<td class="center">
								<c:if test="${!empty kc.children}">
									<span class="badge badge-success">2</span>
									<a>${kc.name}</a>
								</c:if>
								<c:if test="${empty kc.children}">
									<span class="badge badge-success">2</span>
									<a>${kc.name}</a>
								</c:if>
							</td>
							<td class="center">${kc.resKey}</td>
							<td class="center">${kc.resUrl}</td>
							<td class="center"><i class="${kc.icon}"></i></td>
							<td class="center">
								<c:if test="${kc.type eq 0}">
									<span class="label label-sm label-warning">根菜单</span>
								</c:if>
								<c:if test="${kc.type eq 1}">
									<span class="label label-sm label-warning">子菜单</span>
								</c:if>
								<c:if test="${kc.type eq 2}">
									<span class="label label-sm label-warning">按钮</span>
								</c:if>
							</td>
							<td class="center">
								<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
									${kc.description}
								</div>
							</td>
						</tr>
						
						<c:forEach var="kc_2" items="${kc.children}" varStatus="ks_2">
							<tr>
								<td class="center">
									<label>
										<input type="checkbox" class="ace" name="father${kc_2.parentId}" value="${kc_2.id}" />
										<span class="lbl"></span>
									</label>
								</td>
								<td class="center">
									<div class="tree-folder-header">
										<div class="tree-folder-name_2">
											<a>
												<span class="badge badge-info">3</span>
												${kc_2.name}
											</a>
										</div>
									</div>
								</td>
								<td class="center">${kc_2.resKey}</td>
								<td class="center">${kc_2.resUrl}</td>
								<td class="center">
									<button type="button" class="${fn:split(kc_2.btn,',')[2]}" id="${fn:split(kc_2.btn,',')[0]}" name="${fn:split(kc_2.btn,',')[1]}">
										${fn:split(kc_2.btn,',')[3]}
									</button>
								</td>
								<td class="center">
									<c:if test="${kc_2.type eq 0}">
										<span class="label label-sm label-warning">根菜单</span>
									</c:if>
									<c:if test="${kc_2.type eq 1}">
										<span class="label label-sm label-warning">子菜单</span>
									</c:if>
									<c:if test="${kc_2.type eq 2}">
										<span class="label label-sm btn-success">按钮</span>
									</c:if>
								</td>
								<td class="center">
									<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										${kc_2.description}
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</c:forEach>

<div id="btnAddRootMenu-dialog-message" class="hide">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-16">
				<form id="saveRootMenu" class="form-horizontal" role="form" action="admin/pc/resources/menuRootSave" method="post">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">菜单类型:</label>
						<div class="col-sm-9">
							<select class="col-xs-10 col-sm-8" id="menuTypeSelect" name="menuType">
								<option value="0">根菜单</option>
								<option value="1">子菜单</option>
								<option value="2">按钮</option>
							</select>
						</div>
					</div>
					<div class="form-group" id="rootMenuDiv" style="display: none;">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">父菜单:</label>
						<div class="col-sm-9" id="rootMenuList">
							
						</div>
					</div>
					<div class="form-group" id="sonMenuDiv" style="display: none;">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">子菜单:</label>
						<div class="col-sm-9" id="sonMenuList">
							
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">菜单名称:</label>
						<div class="col-sm-9">
							<input type="text" id="rootMenuName" name="name" placeholder="菜单名称" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">菜单Key:</label>
						<div class="col-sm-9">
							<input type="text" id="resKey" name="resKey" placeholder="菜单Key" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">菜单地址:</label>
						<div class="col-sm-9">
							<input type="text" id="resUrl" name="resUrl" placeholder="菜单地址" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group" id="buttonDiv" style="display: none;">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">按钮:</label>
						<div class="col-sm-9">
							<input type="text" id="btn" name="btn" placeholder="按钮" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group" id="menuIconDiv">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">菜单图标:</label>
						<div class="col-sm-9">
							<input type="text" id="rootMenuIcon" name="icon" placeholder="菜单图标" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">菜单位置:</label>
						<div class="col-sm-9">
							<input type="text" id="level" name="level" placeholder="菜单位置" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">菜单描述:</label>
						<div class="col-sm-9">
							<input type="text" id="description" name="description" placeholder="菜单描述" class="col-xs-10 col-sm-8" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div>
	<form id="deleteMenuForm" class="form-horizontal" role="form" action="admin/pc/resources/menuDelete" method="post">
		<input type="hidden" id="deleteMenuInput" name="ids" />
		<input type="hidden" id="deleteRootMenuInput" name="deleteRootMenu" />
	</form>
</div>

<div id="btnEditFunMenu-dialog-message" class="hide">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-16">
				<form id="editMenuForm" class="form-horizontal" role="form" action="admin/pc/resources/menuUpdate" method="post">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">菜单名称:</label>
						<div class="col-sm-9">
							<input type="hidden" name="resourcesId" id="resourcesId" />
							<input type="text" id="editMenuName" name="name" placeholder="菜单名称" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">菜单Key:</label>
						<div class="col-sm-9">
							<input type="text" id="editResKey" name="resKey" placeholder="菜单Key" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">菜单地址:</label>
						<div class="col-sm-9">
							<input type="text" id="editResUrl" name="resUrl" placeholder="菜单地址" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group" id="editButtonDiv" style="display: none;">
						<label class="col-sm-3 control-label no-padding-right text-danger" for="form-field-1">按钮:</label>
						<div class="col-sm-9">
							<input type="text" id="editBtn" name="btn" placeholder="按钮" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group" id="editMenuIconDiv">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">菜单图标:</label>
						<div class="col-sm-9">
							<input type="text" id="editMenuIcon" name="icon" placeholder="菜单图标" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">菜单位置:</label>
						<div class="col-sm-9">
							<input type="text" id="editLevel" name="level" placeholder="菜单位置" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">菜单描述:</label>
						<div class="col-sm-9">
							<input type="text" id="editDescription" name="description" placeholder="菜单描述" class="col-xs-10 col-sm-8" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		
		$("#btn").val(" ");
		
		//点击根菜单名称
		$(".tree-folder-name").click(function(){
			var index = $('.tree-folder-name').index(this); //点击的元素下标
			var obj = $("#tree-folder-name" + index);
			
			if(obj.css("display") == "none")
			{
				obj.show();
				$("#tree" + index).attr("class", "icon-minus");
			}
			else
			{
				obj.hide();
				$("#tree" + index).attr("class", "icon-plus");
			}
		});
		
		//点击事件
		$("#addFun").click("click", function() {
			addFun();
		});
		
		//点击事件
		$("#delFun").click("click", function() {
			delFun();
		});
		
		//点击事件
		$("#editFun").click("click", function() {
			editFun();
		});
		
		//菜单类型选择
		$("#menuTypeSelect").change(function() {
			var menuTypeSelect = $("#menuTypeSelect").val();
			//根菜单
			if(0 == menuTypeSelect)
			{
				$("#menuIconDiv").show();
				$("#rootMenuDiv").hide();
				$("#buttonDiv").hide();
				$("#sonMenuDiv").hide();
			}
			//子菜单
			if(1 == menuTypeSelect)
			{
				$("#btn").val(" ");
				var parm = {};
				$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/admin/pc/resources/menuRootList',
					data: parm,
					success:function(data){
						$("#rootMenuList").html(data);  
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
				});
				$("#rootMenuDiv").show();
				$("#menuIconDiv").show();
				$("#buttonDiv").hide();
				$("#sonMenuDiv").hide();
			}
			//按钮
			if(2 == menuTypeSelect)
			{
				$("#rootMenuIcon").val(" ");
				
				//所有根菜单
				var parm = {};
				$.ajax({
					type : "GET",
					cache : false,
					dataType : "html",
					async : true,// 设置异步为false,重要！
					url : rootPath+'/admin/pc/resources/menuRootList',
					data: parm,
					success:function(data){
						$("#rootMenuList").html(data);  
					},
					error: function (msg) {//ajax请求失败后触发的方法
						console.log(msg);//弹出错误信息
			        }
				});
				$("#rootMenuDiv").show();
				$("#sonMenuDiv").show();
				$("#buttonDiv").show();
				$("#menuIconDiv").hide();
			}
        });
		
		function addFun() {
			var dialog = $("#btnAddRootMenu-dialog-message").removeClass('hide').dialog({
				modal: true,
				title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>添加菜单</h4></div>",
				title_html: true,
				buttons: [ 
					{
						text: "取消",
						"class" : "btn-my btn btn-xs",
						click: function() {
							$(this).dialog("close"); 
						} 
					},
					{
						text: "保存",
						"class" : "btn btn-primary btn-xs",
						click: function() {
							var rootMenuName = $("#rootMenuName").val();
							var resKey = $("#resKey").val();
							var resUrl = $("#resUrl").val();
							var btn = $("#btn").val();
							
							if (rootMenuName == null || rootMenuName == undefined || rootMenuName == '') { 
								alert("请输入菜单名称");
								return;
							} 
							if (resKey == null || resKey == undefined || resKey == '') { 
								alert("请输入菜单Key");
								return;
							} 
							if (resUrl == null || resUrl == undefined || resUrl == '') { 
								alert("请输入菜单地址");
								return;
							} 
							if (btn == null || btn == undefined || btn == '') { 
								alert("请输入按钮");
								return;
							} 
							$("#btn").val(ltrim(btn));
							$("#rootMenuIcon").val(ltrim($("#rootMenuIcon").val()));
							
							$("#saveRootMenu").submit();
							$(this).dialog("close"); 
						} 
					}
				]
			});
		}
		
		//删除菜单
		function delFun()
		{
			//是否有选一个
			var bo = false;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{
					bo = true;
					return false; // false时相当于break,如果return true 就相当于continure。
				}
			});

			//判断选择了只有一个
			var count = 0;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{
					if(count > 1)
					{
						return false; // false时相当于break,如果return true 就相当于continure。
					}
					count++;
				}
			});

			var bo_2 = false;
			if(count == 1)
			{
				bo_2 = true;
			}

			if(bo)
			{
			    if (bo_2){
                    alert("删除根菜单:根菜单下所有子菜单、按钮都会被删除;\n删除子菜单:子菜单所有按钮都会被删除");
                    if(confirm("确认删除吗"))
                    {
                        var str = "";
                        var count = 0;
                        $("input:checkbox").each(function(){
                            if($(this).is(':checked'))
                            {
                                //删除根菜单判断
                                var nameAttr = $(this).attr("name");
                                if(nameAttr == null || nameAttr == undefined || nameAttr == '')
                                {

                                }
                                else
                                {
                                    if(nameAttr == "rootMenu")
                                    {
                                        $("#deleteRootMenuInput").val(1);
                                    }
                                    else
                                    {
                                        $("#deleteRootMenuInput").val(0);
                                    }
                                }

                                //删除子菜单判断
                                var str_2 = $(this).attr("value");
                                if(count > 0)
                                {
                                    str = str + "," + str_2;
                                }
                                else
                                {
                                    str = str_2;
                                }
                                count++;
                            }
                        });
                        $("#deleteMenuInput").val(str);
                        $("#deleteMenuForm").submit();
                    }
                    else
                    {
                        return;
                    }
                } else {
                    alert("只能选择一个");
                }
			}
			else
			{
				alert("至少选择一个");
			}
		}
		
		function editFun()
		{
			//是否有选一个
			var bo = false;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{
					bo = true;
					return false; // false时相当于break,如果return true 就相当于continure。  
				}
			});
			
			//判断选择了只有一个
			var resourceId = "";
			var count = 0;
			$("input:checkbox").each(function(){
				if($(this).is(':checked'))
				{
					resourceId = $(this).attr("value");
					if(count > 1)
					{
						return false; // false时相当于break,如果return true 就相当于continure。  
					}
					count++;
				}
			});
			
			var bo_2 = false;
			if(count == 1)
			{
				bo_2 = true;
			}
			
			if(bo)
			{
				if(bo_2)
				{
					var parm = {
							resourceId : resourceId
						};
					$.ajax({
						type : "POST",
						cache : false,
						dataType : "html",
						async : true,// 设置异步为false,重要！
						url : rootPath+'/admin/pc/resources/menuEdit',
						data: parm,
						success:function(data){
							var obj = eval("(" + data + ")");
							if(obj.type == "2")
							{
								$("#editMenuIconDiv").hide();
								$("#editButtonDiv").show();
								$("#editMenuIcon").val(" ");
								$("#editBtn").val(obj.btn);
							}
							else
							{
								$("#editMenuIconDiv").show();
								$("#editButtonDiv").hide();
								$("#editBtn").val(" ");
								$("#editMenuIcon").val(obj.icon);
							}
							$("#resourcesId").val(obj.id);
							$("#editMenuName").val(obj.name);
							$("#editResKey").val(obj.resKey);
							$("#editResUrl").val(obj.resUrl);
							$("#editLevel").val(obj.orderNo);
							$("#editDescription").val(obj.description);
						},
						error: function (msg) {//ajax请求失败后触发的方法
							console.log(msg);//弹出错误信息
				        }
					});
					editFunWindow();
				}
				else
				{
					alert("只能选择一个");
				}
			}
			else
			{
				alert("至少选择一个");
			}
		}
		
		function editFunWindow()
		{
			var dialog = $("#btnEditFunMenu-dialog-message").removeClass('hide').dialog({
				modal: true,
				title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>编辑菜单</h4></div>",
				title_html: true,
				buttons: [ 
					{
						text: "取消",
						"class" : "btn-my btn btn-xs",
						click: function() {
							$(this).dialog("close"); 
						} 
					},
					{
						text: "保存",
						"class" : "btn btn-primary btn-xs",
						click: function() {
							var rootMenuName = $("#editMenuName").val();
							var resKey = $("#editResKey").val();
							var resUrl = $("#editResUrl").val();
							var btn = $("#editBtn").val();
							
							if (rootMenuName == null || rootMenuName == undefined || rootMenuName == '') { 
								alert("请输入菜单名称");
								return;
							} 
							if (resKey == null || resKey == undefined || resKey == '') { 
								alert("请输入菜单Key");
								return;
							} 
							if (resUrl == null || resUrl == undefined || resUrl == '') { 
								alert("请输入菜单地址");
								return;
							} 
							if (btn == null || btn == undefined || btn == '') { 
								alert("请输入按钮");
								return;
							} 
							$("#editBtn").val(ltrim(btn));
							$("#editMenuIcon").val(ltrim($("#editMenuIcon").val()));
							
							$("#editMenuForm").submit();
							$(this).dialog("close"); 
						} 
					}
				]
			});
		}
		
		$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
			_title: function(title) {
				var $title = this.options.title || '&nbsp;';
				if( ("title_html" in this.options) && this.options.title_html == true )
					title.html($title);
				else title.text($title);
			}
		}));
	
	});
	
	//去左空格
	function ltrim(s){
	    return s.replace(/(^\s*)/g, "");
	}
		
</script>
