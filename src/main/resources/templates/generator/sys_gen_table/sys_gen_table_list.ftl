<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>代码生成-数据库表 - ${system_name}</title>
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
				</div>
			</div>
			
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-list-toolbar clear">
						<span class="fl">
							<button class="javaex-btn blue" onclick="add()">新增</button>
							<button class="javaex-btn yellow" onclick="getTableFromDatabase()">从数据库导出</button>
							<button class="javaex-btn wathet disabled relevance" onclick="viewSQL()" disabled="disabled">查看SQL语句</button>
							<button class="javaex-btn wathet disabled relevance" onclick="synchronyToDatabase()" disabled="disabled">同步到数据库</button>
							<button class="javaex-btn wathet disabled relevance" onclick="genCode()" disabled="disabled">代码生成</button>
						</span>
						<span class="fr javaex-search-group">
							<input type="text" class="javaex-text" id="keyword" placeholder="请输入搜索内容" />
							<button id="search" class="javaex-btn blue" onclick="search()">查询</button>
						</span>
					</div>
					
					<div class="javaex-block">
						<table id="table" class="javaex-table td-c-1 vertical-line-white">
							<thead>
								<tr>
									<th class="javaex-table-num-col"></th>
									<th class="javaex-table-filter-col"><input type="checkbox" fill listen="listen-1"/> </th>
									<th>表名</th>
									<th>表描述</th>
									<th>备注</th>
									<th>创建时间</th>
									<th style="width:100px;">创建人</th>
									<th>更新时间</th>
									<th style="width:100px;">更新人</th>
									<th style="width:80px;">状态</th>
									<th style="width:240px;">操作</th>
								</tr>
							</thead>
							<tbody id="data">
								
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<div class="javaex-page-footer">
				<div class="javaex-page">
					<ul id="page" class="javaex-pagination"></ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var ids = new Array();
	var param = {
		"keyword" : "",
		"sorts" : "",
		"pageNum" : 1,
		"pageSize" : 100
	};
	
	$(function() {
		getList();
	});
	
	function search() {
		param.keyword = $("#keyword").val();
		param.pageNum = 1;
		delete param['sorts'];
		
		// 将所有列的排序标识初始化
		javaex.initTable("table");
		
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
			url : "${contextPath}/sys/gen/table/list",
			type : "GET",
			dataType : "json",
			traditional : true,
			data : param,
			success : function(rtn) {
				javaex.loading({
					mode : "manual",
					containerId : "table",
					type : "close"
				});
				
				if (rtn.code==0) {
					let pageInfo = rtn.data.pageInfo;
					let list = pageInfo.list;
					let html = '';
					
					if (list!=null && list.length>0) {
						$.each(list, function(i, data) {
							html += '<tr>';
							html += '	<td>'+(i+1)+'</td>';
							html += '	<td><input type="checkbox" fill name="id" listen="listen-1-2" value="'+data.id+'"/> </td>';
							html += '	<td>'+data.tableName+'</td>';
							html += '	<td>'+javaex.ifnull(data.tableComment)+'</td>';
							html += '	<td>'+javaex.ifnull(data.remark)+'</td>';
							html += '	<td>'+javaex.dateFormat(data.createTime, 'yyyy-MM-dd HH:mm:ss')+'</td>';
							html += '	<td>'+javaex.ifnull(data.createUsername)+'</td>';
							html += '	<td>'+javaex.dateFormat(data.updateTime, 'yyyy-MM-dd HH:mm:ss')+'</td>';
							html += '	<td>'+javaex.ifnull(data.updateUsername)+'</td>';
							if (data.isDeleted==0) {
								html += '<td><font class="green">启用</font></td>';
							} else {
								html += '<td><font class="red">停用</font></td>';
							}
							html += '	<td>';
							html += '		<a href="javascript:;" class="blue mr-10" onclick="edit(\''+data.id+'\')">编辑</a>';
							html += '		<a href="javascript:;" class="red mr-10" onclick="del(this, \''+data.id+'\')">删除</a>';
							html += '		<a href="${contextPath}/sys/gen/table/column/page/list?tableId='+data.id+'&tableName='+data.tableName+'" class="navy ml-10">管理字段</a>';
							html += '	</td>';
							html += '</tr>';
						});
					} else {
						html = '<tr><td colspan="11" class="tc">暂无数据</td></tr>';
					}
					
					$("#data").html(html);
					javaex.render();
					
					javaex.table({
						id : "table",
						isClose : true,
						sort : {
							"3" : "table_name",
							"6" : "create_time",
							"8" : "update_time"
						},
						sortCallback : function(rtn) {
							param.sorts = rtn.sortArr;
							param.pageNum = 1;
							
							getList();
						}
					});
					
					javaex.page({
						id : "page",
						pageNum : pageInfo.pageNum,        // 默认选中第几页
						pageSize : pageInfo.pageSize,      // 每页显示多少条
						totalPages : pageInfo.pages,       // 总页数
						totalNum : pageInfo.total,         // 总条数，不填时，不显示
						isShowSelect : true,   // 是否显示条数选择控件
						position : "left",
						callback : function(rtn) {
							param.pageNum = rtn.pageNum;
							param.pageSize = rtn.pageSize;
							
							getList();
						}
					});
				}
			}
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
			url : "${contextPath}/sys/gen/table/delete/" + id,
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
	 * 添加
	 */
	function add() {
		top.javaex.dialog({
			type : "dialog",
			title : "新增",
			url : "${contextPath}/sys/gen/table/page/add",
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
			url : "${contextPath}/sys/gen/table/page/edit?id="+id,
			width : "1000",
			height : "618"
		});
	}
	
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
	
	/**
	 * 查看sql语句
	 */
	function viewSQL() {
		top.javaex.dialog({
			type : "dialog",
			title : "表创建SQL语句",
			url : "${contextPath}/sys/gen/table/page/view-sql?idstrs="+ids.join(","),
			width : "1000",
			height : "618"
		});
	}
	
	/**
	 * 从数据库导出表
	 */
	function getTableFromDatabase() {
		top.javaex.dialog({
			type : "dialog",
			title : "从数据库导出表",
			url : "${contextPath}/sys/gen/table/page/get-table-from-db",
			width : "1000",
			height : "618"
		});
	}
	
	/**
	 * 同步到数据库
	 */
	function synchronyToDatabase() {
		var html = '<label><input type="radio" fill name="synchronyType" value="1" checked />普通同步（新建表或追加字段）</label><br/>';
		html += '<label class="mt-10"><input type="radio" fill name="synchronyType" value="2" />强制同步（先删除已存在的表，再创建）</label>';
		top.javaex.confirm({
			content : html,
			width : 400,
			confirm : function() {
				var synchronyType = top.$(':radio[name="synchronyType"]:checked').val();
				
				javaex.tip({
					content : "数据同步中，请稍候...",
					type : "submit"
				});
				
				$.ajax({
					url : "${contextPath}/sys/gen/table/synchrony-to-db",
					type : "POST",
					dataType : "json",
					contentType : "application/json;charset=utf-8",
					data : JSON.stringify({
						"ids" : ids,
						"synchronyType" : synchronyType
					}),
					success : function(rtn) {
						if (rtn.code==0) {
							javaex.tip({
								content : rtn.message,
								type : "success"
							});
						} else {
							javaex.tip({
								content : rtn.message,
								type : "error"
							});
						}
					}
				});
			}
		});
		top.javaex.render();
	}
	
	/**
	 * 代码生成
	 */
	function genCode() {
		top.javaex.dialog({
			type : "dialog",
			title : "代码生成",
			url : "${contextPath}/sys/gen/table/page/gen-code?idstrs=" + ids.join(","),
			width : "1000",
			height : "618"
		});
	}
</script>
</html>
