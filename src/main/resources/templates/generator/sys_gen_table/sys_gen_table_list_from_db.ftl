<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>从数据库导出表 - ${system_name}</title>
<#include "/common/style.ftl">
</head>

<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="javaex-main">
					<div class="javaex-list-toolbar clear">
						<span class="fr">
							<div class="javaex-search-group">
								<input type="text" class="javaex-text" id="keyword" placeholder="请输入搜索内容" />
								<button class="javaex-btn blue" onclick="search()">查询</button>
							</div>
						</span>
					</div>
					
					<table id="table" class="javaex-table td-c-1 td-c-2">
						<thead>
							<tr>
								<th class="javaex-table-num-col"></th>
								<th class="javaex-table-filter-col"><input type="checkbox" fill listen="listen-1"/> </th>
								<th>表名</th>
								<th>表中文名</th>
							</tr>
						</thead>
						<tbody id="data">
							
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="javaex-page-footer">
				<input type="button" id="save" onclick="exportTableFromDatabase()" class="javaex-btn blue disabled" value="导出" disabled="disabled"/>
				<input type="button" onclick="closeDialog()" class="javaex-btn wathet" value="关闭" />
			</div>
		</div>
	</div>
</body>
<script>
	var tableNames = new Array();
	var param = {
		"keyword" : ""
	};
	
	$(function() {
		getList();
	});
	
	/**
	 * 搜索
	 */
	function search() {
		param.keyword = $("#keyword").val();
		
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
			url : "${contextPath}/sys/gen/table/list-from-db",
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
					let html = '';
					
					if (list!=null && list.length>0) {
						$.each(list, function(i, data) {
							html += '<tr>';
							html += '	<td>'+(i+1)+'</td>';
							html += '	<td><input type="checkbox" fill name="tableName" listen="listen-1-2" value="'+data.tableName+'"/> </td>';
							html += '	<td>'+data.tableName+'</td>';
							html += '	<td>'+javaex.ifnull(data.tableComment)+'</td>';
							html += '</tr>';
						});
					} else {
						html = '<tr><td colspan="4" style="text-align:center;">暂无数据</td></tr>';
					}
					
					$("#data").html(html);
					javaex.render();
				}
			}
		});
	}
	
	/**
	 * 监听复选框选中事件
	 */
	javaex.listenCheckbox({
		callback : function() {
			tableNames = [];
			
			$(':checkbox[name="tableName"]:checked').each(function(i) {
				tableNames.push($(this).val());
			});
			
			if (tableNames.length==0) {
				$('#save').addClass("disabled");
				$('#save').attr("disabled", true);
			} else {
				$('#save').removeClass("disabled");
				$('#save').attr("disabled", false);
			}
		}
	});
	
	/**
	 * 导出表
	 */
	function exportTableFromDatabase() {
		javaex.tip({
			content : "数据导出中，请稍候...",
			type : "submit"
		});
		
		$.ajax({
			url : "${contextPath}/sys/gen/table/synchrony-from-db",
			type : "POST",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify({
				"tableNames" : tableNames
			}),
			success : function(rtn) {
				if (rtn.code==0) {
					javaex.tip({
						content : rtn.message,
						type : "success"
					});
					
					setTimeout(function() {
						// 关闭弹出层
						parent.javaex.close();
						// 刷新页面
						parent.document.getElementById("page").contentWindow.search();
					}, 1000);
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
	 * 关闭弹窗
	 */
	function closeDialog() {
		parent.javaex.close();
	}
</script>
</html>