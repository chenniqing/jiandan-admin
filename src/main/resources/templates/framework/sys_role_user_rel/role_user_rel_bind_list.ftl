<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>用户列表 - ${system_name}</title>
<#include "/common/style.ftl">
</head>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-list-toolbar clear mt-10">
						<span class="fl">
							<button class="javaex-btn blue" onclick="addUser()">添加</button>
							<button class="javaex-btn wathet disabled relevance" onclick="batchDelUser()" disabled="disabled">批量移除</button>
						</span>
						<span class="fr javaex-search-group">
							<input type="text" class="javaex-text" id="drawer-keyword" placeholder="搜索用户账号 / 用户姓名" />
							<button id="drawer-search" class="javaex-btn blue" onclick="searchBindUser()">查询</button>
						</span>
					</div>
					
					<div class="javaex-block">
						<table id="role-user-bind" class="javaex-table td-c-1 vertical-line-white">
							<thead>
								<tr>
									<th class="javaex-table-num-col"></th>
									<th class="javaex-table-filter-col"><input type="checkbox" fill listen="listen-1"/> </th>
									<th>用户账号</th>
									<th>用户姓名</th>
									<th style="width:100px;">操作</th>
								</tr>
							</thead>
							<tbody id="role-user-bind-data">
								
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
var roleId = "${roleId}";
var param = {
	"keyword" : "",
	"pageNum" : 1,
	"pageSize" : 10
};

$(function() {
	getRoleUserBindList();
});

/**
 * 搜索绑定的用户
 */
function searchBindUser() {
	param.keyword = $("#drawer-keyword").val();
	param.pageNum = 1;
	
	getRoleUserBindList();
}

/**
 * 加载列表数据
 */
function getRoleUserBindList() {
	javaex.loading({
		mode : "manual",
		containerId : "role-user-bind",
		type : "open"
	});
	
	$.ajax({
		url : "${contextPath}/sys/role-user/list/bind/" + roleId,
		type : "GET",
		dataType : "json",
		data : param,
		success : function(rtn) {
			javaex.loading({
				mode : "manual",
				containerId : "role-user-bind",
				type : "close"
			});
			
			if (rtn.code==0) {
				var pageInfo = rtn.data.pageInfo;
				var list = pageInfo.list;
				var html = '';
				
				if (list!=null && list.length>0) {
					$.each(list, function(i, data) {
						html += '<tr>';
						html += '	<td>'+(i+1)+'</td>';
						html += '	<td><input type="checkbox" fill name="userId" listen="listen-1-2" value="'+data.id+'"/> </td>';
						html += '	<td>'+data.username+'</td>';
						html += '	<td>'+data.nickname+'</td>';
						html += '	<td>';
						html += '		<a href="javascript:;" class="red" onclick="deleteUser(\''+data.id+'\')">移除</a>';
						html += '	</td>';
						html += '</tr>';
					});
				} else {
					html = '<tr><td colspan="5" class="tc">暂无数据</td></tr>';
				}
				
				$("#role-user-bind-data").html(html);
				javaex.render();
				
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
						
						getRoleUserBindList();
					}
				});
			}
		}
	});
}

var userIds = new Array();
// 监听复选框选中事件
javaex.listenCheckbox({
	callback : function() {
		userIds = [];
		
		$(':checkbox[name="userId"]:checked').each(function(i) {
			userIds.push($(this).val());
		});
		
		if (userIds.length==0) {
			$('button.relevance').removeClass("red empty").addClass("wathet disabled");
			$('button.relevance').attr("disabled", true);
		} else {
			$('button.relevance').removeClass("wathet disabled").addClass("red empty");
			$('button.relevance').attr("disabled", false);
		}
	}
});

/**
 * 批量移除用户
 */
function batchDelUser() {
	$.ajax({
		url : "${contextPath}/sys/role-user/batch-delete/user/" + roleId,
		type : "POST",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : JSON.stringify({
			"userIds" : userIds
		}),
		success : function(rtn) {
			if (rtn.code==0) {
				getRoleUserBindList();
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
 * 移除用户
 */
function deleteUser(userId) {
	$.ajax({
		url : "${contextPath}/sys/role-user/delete/user/" + roleId,
		type : "POST",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : JSON.stringify({
			"userId" : userId
		}),
		success : function(rtn) {
			if (rtn.code==0) {
				getRoleUserBindList();
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
 * 添加/编辑
 */
function addUser() {
	top.javaex.dialog({
		type : "dialog",
		id : "addUser",
		title : "添加用户",
		url : "${contextPath}/sys/role-user/page/unbind-list?roleId=" + roleId,
		width : "1000",
		height : "618"
	});
}
</script>
</html>
