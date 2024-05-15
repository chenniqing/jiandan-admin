<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>数据字典表 - ${system_name}</title>
<#include "/common/style.ftl">
</head>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-header">
				<div class="javaex-breadcrumb">
					<span>系统管理</span>
					<span class="divider">/</span>
					<span class="active">字典管理</span>
				</div>
			</div>
			
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-search-wrapper">
						<div class="javaex-search-line">
							<div class="javaex-search-control">
								字典编码：<input type="text" class="javaex-text mr-10" id="dictCode" placeholder="请输入字典编码" />
							</div>
							<div class="javaex-search-control">
								字典描述：<input type="text" class="javaex-text mr-10" id="dictCodeComment" placeholder="请输入字典编码描述" />
							</div>
							<!-- 确认查询按钮 -->
							<div class="javaex-search-control javaex-search-submit">
								<button class="javaex-btn red" onclick="reset()">重置</button>
								<button class="javaex-btn primary" onclick="search()">确认查询</button>
							</div>
						</div>
						<div class="javaex-search-line">
							<div class="javaex-search-control">
								字典键值：<input type="text" class="javaex-text mr-10" id="dictValue" placeholder="请输入字典键值" />
							</div>
							<div class="javaex-search-control">
								字典文本：<input type="text" class="javaex-text mr-10" id="dictText" placeholder="请输入字典文本" />
							</div>
							<div class="javaex-search-control">
								字典状态：<select id="isDeleted">
									<option value="">所有</option>
									<option value="0">正常</option>
									<option value="1">停用</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="javaex-list-toolbar clear">
						<span class="fl">
							<button class="javaex-btn blue" onclick="add()">新增</button>
						</span>
					</div>
					
					<div class="javaex-block">
						<table id="table" class="javaex-table td-c-1 vertical-line-white">
							<thead>
								<tr>
									<th class="javaex-table-num-col"></th>
									<th>字典编码</th>
									<th>字典编码描述</th>
									<th>字典键值</th>
									<th>字典文本</th>
									<th>是否默认值</th>
									<th>状态</th>
									<th>备注</th>
									<th>显示顺序</th>
									<th style="width:180px;">更新时间</th>
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
var param = {
	"dictCode" : "",
	"dictCodeComment" : "",
	"dictValue" : "",
	"dictText" : "",
	"pageNum" : 1,
	"pageSize" : 10
};

javaex.select({
	id : "isDeleted"
});

// 搜索
function search() {
	param.dictCode = $("#dictCode").val();
	param.dictCodeComment = $("#dictCodeComment").val();
	param.dictValue = $("#dictValue").val();
	param.dictText = $("#dictText").val();
	if (javaex.ifnull($("#isDeleted").val()) == "") {
		delete param['isDeleted'];
	} else {
		param.isDeleted = $("#isDeleted").val();
	}
	param.pageNum = 1;
	
	getList();
}
// 重置
function reset() {
	$("#dictCode").val('');
	$("#dictCodeComment").val('');
	$("#dictValue").val('');
	$("#dictText").val('');
	$("#isDeleted").val('');
	
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
		url : "${contextPath}/sys/dict/data/list",
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
						html += '	<td>'+data.dictCode+'</td>';
						html += '	<td>'+javaex.ifnull(data.dictCodeComment)+'</td>';
						html += '	<td>'+data.dictValue+'</td>';
						html += '	<td>'+data.dictText+'</td>';
						if (data.isDefault==1) {
							html += '<td><font class="red">是</font></td>';
						} else {
							html += '<td>--</td>';
						}
						if (data.isDeleted==1) {
							html += '<td><font class="red">停用</font></td>';
						} else {
							html += '<td><font class="green">正常</font></td>';
						}
						html += '	<td>'+javaex.ifnull(data.remark)+'</td>';
						html += '	<td>'+javaex.ifnull(data.sort)+'</td>';
						html += '	<td>'+javaex.dateFormat(data.updateTime, 'yyyy-MM-dd HH:mm:ss')+'</td>';
						html += '	<td>';
						html += '		<a href="javascript:;" class="blue mr-10" onclick="edit(\''+data.id+'\')">编辑</a>';
						html += '		<a href="javascript:;" class="red mr-10" onclick="del(this, \''+data.id+'\')">删除</a>';
						html += '	</td>';
						html += '</tr>';
					});
				} else {
					html = '<tr><td colspan="11" class="tc">暂无数据</td></tr>';
				}
				
				$("#data").html(html);
				
				javaex.table({
					id : "table",
					mergeColArr : [2, 3]
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
		url : "${contextPath}/sys/dict/data/delete/" + id,
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
		url : "${contextPath}/sys/dict/data/page/add",
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
		url : "${contextPath}/sys/dict/data/page/edit?id=" + id,
		width : "1000",
		height : "618"
	});
}
</script>
</html>
