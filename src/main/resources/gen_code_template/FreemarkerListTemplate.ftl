<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>#TableComment#表 - ${system_name}</title>
<#include "/common/style.ftl">
</head>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-header">
				<div class="javaex-breadcrumb">
					<span>菜单目录名称</span>
					<span class="divider">/</span>
					<span class="active">菜单名称</span>
				</div>
			</div>
			
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-list-toolbar clear">
						<span class="fl">
							<button class="javaex-btn blue" onclick="add()">新增</button>
						</span>
						<span class="fr javaex-search-group">#QueryParamInputs#
							<button class="javaex-btn blue" onclick="search()">查询</button>
							<button class="javaex-btn red" onclick="reset()">重置</button>
						</span>
					</div>
					
					<div class="javaex-block">
						<table id="table" class="javaex-table td-c-1 vertical-line-white">
							<thead>
								<tr>
									<th class="javaex-table-num-col"></th>
#TableHeaders#
									<th>操作</th>
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
var param = {#QueryParams#
	"pageNum" : 1,
	"pageSize" : 10
};

// 搜索
function search() {#QueryParamValues#
	param.pageNum = 1;
	
	getList();
}
// 重置
function reset() {#QueryParamValueNulls#
	
	search();
}

$(function() {
	getList();
});

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
		url : "${contextPath}/#TableNameURL#/list",
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
				let pageInfo = rtn.data.pageInfo;
				let list = pageInfo.list;
				let html = '';
				
				if (list!=null && list.length>0) {
					$.each(list, function(i, data) {
						html += '<tr>';
						html += '	<td>'+(i+1)+'</td>';
#TableCells#
						html += '	<td>';
						html += '		<a href="javascript:;" class="blue mr-10" onclick="edit(\''+data.#primaryKeyField#+'\')">编辑</a>';
						html += '		<a href="javascript:;" class="red mr-10" onclick="del(this, \''+data.#primaryKeyField#+'\')">删除</a>';
						html += '	</td>';
						html += '</tr>';
					});
				} else {
					html = '<tr><td colspan="#TableHeaderCount#" class="tc">暂无数据</td></tr>';
				}
				
				$("#data").html(html);
				
				javaex.page({
					id : "page",
					pageNum : pageInfo.pageNum,        // 默认选中第几页
					pageSize : pageInfo.pageSize,      // 每页显示多少条
					totalPages : pageInfo.pages,       // 总页数
					totalNum : pageInfo.total,         // 总条数，不填时，不显示
					isShowSelect : true,               // 是否显示条数选择控件
					position : "left",
					callback : function(rtn) {
						param.pageNum = rtn.pageNum;
						param.pageSize = rtn.pageSize;
						
						getList();
					}
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

/**
 * 删除
 */
function del(obj, #primaryKeyField#) {
	javaex.deleteDialog(obj, {
		content : "确定要删除么",
		confirm : function() {
			delCallback(#primaryKeyField#);
		}
	});
}
function delCallback(#primaryKeyField#) {
	javaex.tip({
		content : "删除中，请稍候...",
		type : "submit"
	});
	
	$.ajax({
		url : "${contextPath}/#TableNameURL#/delete/" + #primaryKeyField#,
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
		title : "添加",
		url : "${contextPath}/#TableNameURL#/page/add",
		width : "1000",
		height : "618"
	});
}

/**
 * 编辑
 */
function edit(#primaryKeyField#) {
	top.javaex.dialog({
		type : "dialog",
		title : "编辑",
		url : "${contextPath}/#TableNameURL#/page/edit?#primaryKeyField#=" + #primaryKeyField#,
		width : "1000",
		height : "618"
	});
}
</script>
</html>
