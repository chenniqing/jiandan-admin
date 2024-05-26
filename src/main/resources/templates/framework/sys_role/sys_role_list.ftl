<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>角色列表 - ${system_name}</title>
<#include "/common/style.ftl">
<style>

</style>
</head>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-header">
				<div class="javaex-breadcrumb">
					<span>系统管理</span>
					<span class="divider">/</span>
					<span class="active">角色管理</span>
				</div>
			</div>
			
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-list-toolbar clear">
						<span class="fl">
							<button class="javaex-btn blue" onclick="add()">新增</button>
						</span>
						<span class="fr javaex-search-group">
							角色名称：<input type="text" class="javaex-text mr-10" id="roleName" placeholder="请输入角色名称" />
							角色标识：<input type="text" class="javaex-text mr-10" id="roleCode" placeholder="请输入角色标识" />
							<button id="search" class="javaex-btn blue" onclick="search()">查询</button>
							<button id="search" class="javaex-btn red" onclick="reset()">重置</button>
						</span>
					</div>
					
					<div class="javaex-block">
						<table id="table" class="javaex-table td-c-1 vertical-line-white">
							<thead>
								<tr>
									<th class="javaex-table-num-col"></th>
									<th>角色名称</th>
									<th>角色标识</th>
									<th>数据权限范围</th>
									<th style="width:200px;">排序</th>
									<th style="width:400px;">操作</th>
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
	"roleName" : "",
	"roleCode" : "",
	"pageNum" : 1,
	"pageSize" : 100
};

$(function() {
	getList();
});

function search() {
	param.roleName = $("#roleName").val();
	param.roleCode = $("#roleCode").val();
	param.pageNum = 1;
	
	getList();
}
//重置
function reset() {
	$("#roleName").val('');
	$("#roleCode").val('');
	
	search();
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
		url : "${contextPath}/sys/role/list",
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
						html += '	<td>'+data.roleName+'</td>';
						html += '	<td>'+data.roleCode+'</td>';
						html += '	<td>';
						if (javaex.ifnull(data.dataScope, 0) == 1) {
							html += '全部数据权限';
						} else if (javaex.ifnull(data.dataScope, 0) == 2) {
							html += '本部门及以下数据权限';
						} else if (javaex.ifnull(data.dataScope, 0) == 3) {
							html += '本部门数据权限';
						} else if (javaex.ifnull(data.dataScope, 0) == 4) {
							html += '仅自己数据权限';
						}
						html += '	</td>';
						html += '	<td>';
						if (i>0) {
							html += '	<a href="javascript:;" class="navy mr-10" onclick="move(\''+data.id+'\', \'up\')">上移</a>';
						}
						if (i<(list.length-1)) {
							html += '	<a href="javascript:;" class="navy mr-10" onclick="move(\''+data.id+'\', \'down\')">下移</a>';
						}
						html += '	</td>';
						html += '	<td>';
						html += '		<a href="javascript:;" class="blue mr-10" onclick="edit(\''+data.id+'\')">编辑</a>';
						html += '		<a href="javascript:;" class="red mr-10" onclick="del(this, \''+data.id+'\')">删除</a>';
						html += '		<a href="javascript:;" class="green mr-10" onclick="editUser(\''+data.id+'\', \''+data.roleName+'\')">用户配置</a>';
						html += '		<a href="javascript:;" class="yellow" onclick="editMenuPerm(\''+data.id+'\', \''+data.roleName+'\')">菜单配置</a>';
						html += '	</td>';
						html += '</tr>';
					});
				} else {
					html = '<tr><td colspan="5" class="tc">暂无数据</td></tr>';
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
			}
		}
	});
}

/**
 * 上移下移
 */
function move(id, action) {
	$.ajax({
		url : "${contextPath}/sys/role/move/" + id,
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
		url : "${contextPath}/sys/role/delete/" + id,
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
		url : "${contextPath}/sys/role/page/add",
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
		url : "${contextPath}/sys/role/page/edit?id="+id,
		width : "1000",
		height : "618"
	});
}

/**
 * 用户配置
 */
function editUser(roleId, roleName) {
	javaex.drawer({
		width : "800",
		title : "用户配置：" + roleName,
		url : "${contextPath}/sys/role-user/page/bind-list?roleId=" + roleId,
		closeOnClickMask : true,
		position : "right"
	});
}

/**
 * 菜单配置
 */
function editMenuPerm(roleId, roleName) {
	javaex.drawer({
		width : "500",
		title : "菜单配置：" + roleName,
		url : "${contextPath}/sys/role-menu/page/list?roleId=" + roleId,
		closeOnClickMask : true,
		position : "right"
	});
}

</script>
</html>