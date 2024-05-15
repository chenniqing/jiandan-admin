<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>代码生成-数据库表字段 - ${system_name}</title>
<#include "/common/style.ftl">
</head>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-header">
				<div class="javaex-breadcrumb">
					<span>系统工具</span>
					<span class="divider">/</span>
					<span class="active">代码生成</span>
					<span class="divider">/</span>
					<span class="active">${tableName}</span>
				</div>
			</div>
			
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-list-toolbar clear">
						<span class="fl">
							<button class="javaex-btn blue" onclick="add()">新增</button>
							<button class="javaex-btn wathet disabled relevance" onclick="viewSQL()" disabled="disabled">查看SQL语句</button>
						</span>
						<span class="fr javaex-search-group">
							<input type="text" class="javaex-text" id="keyword" placeholder="请输入搜索内容" />
							<button id="search" class="javaex-btn blue" onclick="search()">查询</button>
						</span>
					</div>
					
					<div id="tab" class="javaex-tab javaex-tab-card-tail">
						<!--选项卡标题部分-->
						<div class="javaex-tab-title">
							<ul class="clear">
								<li>字段列表</li>
								<li>代码生成配置</li>
							</ul>
						</div>
						<!--选项卡内容部分-->
						<div class="javaex-tab-content">
							<div>
								<div class="javaex-block">
									<table id="table" class="javaex-table td-c-1 td-c-2">
										<thead>
											<tr>
												<th class="javaex-table-num-col"></th>
												<th class="javaex-table-filter-col"><input type="checkbox" fill listen="listen-1"/> </th>
												<th>字段名</th>
												<th>字段描述</th>
												<th style="width:120px;">字段类型</th>
												<th style="width:80px;">默认值</th>
												<th>备注</th>
												<th style="width:80px;">必填</th>
												<th>更新时间</th>
												<th style="width:120px;">更新人</th>
												<th style="width:80px;">状态</th>
												<th style="width:200px;">操作</th>
											</tr>
										</thead>
										<tbody id="data">
											
										</tbody>
									</table>
								</div>
							</div>
							<div>
								<div class="javaex-block">
									<table id="table2" class="javaex-table td-c-1">
										<thead>
											<tr>
												<th class="javaex-table-num-col"></th>
												<th>字段名</th>
												<th>字段描述</th>
												<th>字段类型</th>
												<th>java类型</th>
												<th>是否查询</th>
												<th>唯一性校验</th>
											</tr>
										</thead>
										<tbody id="data2">
											
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var ids = new Array();
	var tableId = "${tableId}";
	var param = {
		"keyword" : "",
		"tableId" : tableId,
		"pageNum" : 1,
		"pageSize" : 100
	};
	
	$(function() {
		getList();
	});
	
	function search() {
		param.keyword = $("#keyword").val();
		param.pageNum = 1;
		
		getList();
	}
	
	/**
	 * 加载列表数据
	 */
	function getList() {
		javaex.loading({
			mode : "manual",
			containerId : "table",
			type : "open"
		});
		
		$.ajax({
			url : "${contextPath}/sys/gen/table/column/list",
			type : "GET",
			dataType : "json",
			data : param,
			success : function(rtn) {
				javaex.loading({
					mode : "manual",
					containerId : "table",
					type : "close"
				});
				
				if (rtn.code==0) {
					let list = rtn.data.list;
					let javaTypeList = rtn.data.javaTypeList;
					
					loadTable1(list);
					loadTable2(list, javaTypeList);
				}
			}
		});
	}
	
	function loadTable1(list) {
		let html = '';
		
		if (list!=null && list.length>0) {
			$.each(list, function(i, data) {
				html += '<tr>';
				html += '	<td>'+(i+1)+'</td>';
				html += '	<td><input type="checkbox" fill name="id" listen="listen-1-2" value="'+data.id+'"/> </td>';
				html += '	<td>'+data.columnName+'</td>';
				html += '	<td>'+javaex.ifnull(data.columnComment)+'</td>';
				html += '	<td>'+data.columnType+'('+data.length+')</td>';
				html += '	<td>'+javaex.ifnull(data.defaultValue)+'</td>';
				html += '	<td>'+javaex.ifnull(data.remark).replace(/\r\n/g, "<br/>").replace(/\n/g, "<br/>")+'</td>';
				if (data.isNotNull==1) {
					html += '	<td><span class="icon-check2" style="color:red;"></span></td>';
				} else {
					html += '	<td><span class="icon-close2" style="color:green;"></span></td>';
				}
				html += '	<td>'+javaex.dateFormat(data.updateTime, 'yyyy-MM-dd HH:mm:ss')+'</td>';
				html += '	<td>'+javaex.ifnull(data.updateUsername)+'</td>';
				if (data.isDeleted==0) {
					html += '<td><font class="green">启用</font></td>';
				} else {
					html += '<td><font class="red">停用</font></td>';
				}
				html += '	<td>';
				html += '		<a href="javascript:;" class="blue" onclick="edit(\''+data.id+'\')">编辑</a>';
				html += '		<a href="javascript:;" class="red ml-10" onclick="del(this, \''+data.id+'\')">删除</a>';
				if (i>0) {
					html += '	<a href="javascript:;" class="navy ml-10" onclick="move(\''+data.id+'\', \'up\')">上移</a>';
				}
				if (i<(list.length-1)) {
					html += '	<a href="javascript:;" class="navy ml-10" onclick="move(\''+data.id+'\', \'down\')">下移</a>';
				}
				html += '	</td>';
				html += '</tr>';
			});
		} else {
			html = '<tr><td colspan="12" class="tc">暂无数据</td></tr>';
		}
		
		$("#data").html(html);
		javaex.render();
	}
	
	function loadTable2(list, javaTypeList) {
		let html = '';
		
		if (list!=null && list.length>0) {
			$.each(list, function(i, data) {
				let columnJavaType = data.javaType;
				let columnType = data.columnType;
				
				if (!columnJavaType) {
					columnJavaType = "String";
				}
				
				html += '<tr>';
				html += '	<td>'+(i+1)+'</td>';
				html += '	<td>'+data.columnName+'</td>';
				html += '	<td>'+javaex.ifnull(data.columnComment)+'</td>';
				html += '	<td>'+columnType+'('+data.length+')</td>';
				html += '	<td>';
				html += '		<select id="select-'+data.id+'">';
				$.each(javaTypeList, function(j, javaType) {
					if (columnJavaType==javaType) {
						html += '		<option value="'+javaType+'" selected>'+javaType+'</option>';
					} else {
						html += '		<option value="'+javaType+'">'+javaType+'</option>';
					}
				});
				html += '		</select>';
				html += '	</td>';
				if (data.isQuery==1) {
					html += '<td><input type="checkbox" fill name="isQuery" id="'+data.id+'" value="1" checked/> </td>';
				} else {
					html += '<td><input type="checkbox" fill name="isQuery" id="'+data.id+'" value="1"/> </td>';
				}
				if (columnJavaType=="String" && data.isPrimaryKey!=1) {
					if (data.isUnique==1) {
						html += '<td><input type="checkbox" fill name="isUnique" id="'+data.id+'" value="1" checked/> </td>';
					} else {
						html += '<td><input type="checkbox" fill name="isUnique" id="'+data.id+'" value="1"/> </td>';
					}
				} else {
					html += '<td></td>';
				}
				html += '</tr>';
			});
		} else {
			html = '<tr><td colspan="7" style="text-align:center;">暂无数据</td></tr>';
		}
		
		$("#data2").html(html);
		javaex.render();
		
		$.each(list, function(i, data) {
			let selectId = "select-" + data.id;
			javaex.select({
				id : selectId,
				maxNum : 10,
				callback: function (rtn) {
					$.ajax({
						url : "${contextPath}/sys/gen/table/column/update/" + data.id,
						type : "POST",
						dataType : "json",
						contentType : "application/json;charset=utf-8",
						data : JSON.stringify({
							"javaType" : rtn.val
						}),
						success : function(rtn) {
							
						}
					});
				}
			});
		});
		
		$(':checkbox[name="isQuery"]').change(function() {
			let isQuery = 0;
			if ($(this).is(":checked")) {
				isQuery = 1;
			}
			
			$.ajax({
				url : "${contextPath}/sys/gen/table/column/update/" + $(this).attr("id"),
				type : "POST",
				dataType : "json",
				contentType : "application/json;charset=utf-8",
				data : JSON.stringify({
					"isQuery" : isQuery
				}),
				success : function(rtn) {
					
				}
			});
		});
		
		$(':checkbox[name="isUnique"]').change(function() {
			let isUnique = 0;
			if ($(this).is(":checked")) {
				isUnique = 1;
				
				$(':checkbox[name="isUnique"]').attr("checked", false);
				$(this).attr("checked", true);
			}
			
			$.ajax({
				url : "${contextPath}/sys/gen/table/column/update/" + $(this).attr("id"),
				type : "POST",
				dataType : "json",
				contentType : "application/json;charset=utf-8",
				data : JSON.stringify({
					"isUnique" : isUnique
				}),
				success : function(rtn) {
					
				}
			});
		});
	}
	
	/**
	 * 删除
	 */
	function del(obj, id) {
		javaex.deleteDialog(obj, {
			content : "确定要删除么",
			confirm : function() {
				delCallback(id);
			}
		});
	}
	function delCallback(id) {
		javaex.tip({
			content : "删除中，请稍候...",
			type : "submit"
		});
		
		$.ajax({
			url : "${contextPath}/sys/gen/table/column/delete/" + id,
			type : "POST",
			dataType : "json",
			success : function(rtn) {
				if (rtn.code==0) {
					javaex.tip({
						hasMask : false,
						content : rtn.message,
						type : "success"
					});
					
					getList();
				} else {
					javaex.tip({
						hasMask : false,
						content : rtn.message,
						type : "error"
					});
				}
			}
		});
	}
	
	/**
	 * 新增
	 */
	function add() {
		top.javaex.dialog({
			type : "dialog",
			title : "新增",
			url : "${contextPath}/sys/gen/table/column/page/add?tableId=" + tableId,
			width : "1000",
			height : "618"
		});
	}
	
	/**
	 * 编辑
	 */
	function edit(id) {
		top.javaex.dialog({
			type : "dialog",
			title : "编辑",
			url : "${contextPath}/sys/gen/table/column/page/edit?id="+id,
			width : "1000",
			height : "618"
		});
	}
	
	/**
	 * 上移下移
	 */
	function move(id, action) {
		$.ajax({
			url : "${contextPath}/sys/gen/table/column/move/" + id,
			type : "POST",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify({
				"action" : action
			}),
			success : function(rtn) {
				if (rtn.code==0) {
					search();
				} else {
					javaex.tip({
						content : rtn.message,
						type : "error"
					});
				}
			}
		});
	}
	
	/**
	 * 查看sql语句
	 */
	function viewSQL() {
		top.javaex.dialog({
			type : "dialog",
			title : "表创建SQL语句",
			url : "${contextPath}/sys/gen/table/column/page/view-sql?idstrs="+ids.join(","),
			width : "1000",
			height : "618"
		});
	}
	
	javaex.tab({
		id : "tab",    // tab的id
		current : 1,    // 默认选中第几个选项卡，从1开始计
		mode : "click"
	});
	
	/**
	 * 监听复选框选中事件
	 */
	javaex.listenCheckbox({
		callback : function() {
			ids = [];
			
			$(':checkbox[name="id"]:checked').each(function(i) {
				ids.push($(this).val());
			});
			
			if (ids.length==0) {
				$('button.relevance').addClass("disabled");
				$('button.relevance').attr("disabled", true);
			} else {
				$('button.relevance').removeClass("disabled");
				$('button.relevance').attr("disabled", false);
			}
		}
	});
</script>
</html>
