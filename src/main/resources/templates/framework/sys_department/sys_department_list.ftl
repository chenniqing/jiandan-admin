<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>部门表 - ${system_name}</title>
<#include "/common/style.ftl">
</head>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-header">
				<div class="javaex-breadcrumb">
					<span>系统管理</span>
					<span class="divider">/</span>
					<span class="active">部门管理</span>
				</div>
			</div>
			
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-list-toolbar clear">
						<span class="fl">
							<button class="javaex-btn blue" onclick="add()">新增</button>
							<button class="javaex-btn wathet" onclick="saveSort()">更新排序</button>
							
							<label class="ml-20"><input type="checkbox" id="launch-retract" fill name="checkbox" checked/>展开/收起</label>
						</span>
					</div>
					
					<div class="javaex-block">
						<table id="table" class="javaex-table td-c-1 vertical-line-white">
							<thead>
								<tr>
									<th class="javaex-table-filter-col"><input type="radio" fill name="radio" value=""/> </th>
									<th style="width:120px;">显示顺序</th>
									<th>部门名称</th>
									<th>负责人</th>
									<th>联系电话</th>
									<th>邮箱</th>
									<th>状态</th>
									<th>更新时间</th>
									<th style="width:250px;">操作</th>
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
$(function() {
	getList();
});

function search() {
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
		url : "${contextPath}/sys/department/list-all",
		type : "GET",
		dataType : "json",
		success : function(rtn) {
			javaex.loading({
				mode : "manual",
				containerId : "table",
				type : "close"
			});
			
			if (rtn.code==0) {
				$('#launch-retract').attr('checked', true);
				
				let list = rtn.data.list;
				let html = '';
				
				if (list!=null && list.length>0) {
					html = traverseDepartments(list);
				} else {
					html = '<tr><td colspan="9" class="tc">暂无数据</td></tr>';
				}
				
				$("#data").html(html);
				javaex.render();
				
				javaex.table({
					id : "table",
					tree : 3,
					isClose : false
				});
			}
		}
	});
}

/**
 * 定义递归函数来遍历子级
 */
function traverseDepartments(departments) {
	let html = '';
	
    for (const department of departments) {
        html += '<tr id="tree_'+department.id+'" parentId="tree_'+department.parentId+'">';
        html += '<td><input type="radio" fill name="radio" value="'+department.id+'"/> </td>';
		html += '<td class="input-td"><input type="hidden" name="id" value="'+department.id+'" /><input type="text" class="javaex-text" name="sort" data-type="positive_int" error-msg="必须输入正整数" value="'+department.sort+'" autocomplete="off" /></td>';
		html += '<td>'+department.departmentName+'</td>';
		html += '<td>'+javaex.ifnull(department.departmentLeader)+'</td>';
		html += '<td>'+javaex.ifnull(department.departmentPhone)+'</td>';
		html += '<td>'+javaex.ifnull(department.departmentEmail)+'</td>';
		if (department.isDeleted==1) {
			html += '<td><font class="red">停用</font></td>';
		} else {
			html += '<td><font class="green">启用</font></td>';
		}
		html += '<td>'+javaex.dateFormat(department.updateTime, 'yyyy-MM-dd HH:mm:ss')+'</td>';
		html += '<td>';
		html += '	<a href="javascript:;" class="blue mr-10" onclick="edit(\''+department.id+'\')">编辑</a>';
		html += '	<a href="javascript:;" class="red mr-10" onclick="del(this, \''+department.id+'\')">删除</a>';
		html += '</td>';
		html += '</tr>';
		
        // 如果当前部门有子部门，则递归调用函数
        if (department.children && department.children.length > 0) {
        	html += traverseDepartments(department.children);
        }
    }
    
    return html;
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
		url : "${contextPath}/sys/department/delete/" + id,
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
	let parentId = javaex.getRadioVal("radio");
	
	top.javaex.dialog({
		type : "dialog",
		title : "添加",
		url : "${contextPath}/sys/department/page/add?parentId=" + parentId,
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
		url : "${contextPath}/sys/department/page/edit?id=" + id,
		width : "1000",
		height : "618"
	});
}

/**
 * 更新排序
 */
var idSortPairs = [];
function saveSort() {
	if (!javaexVerify()) {
		return;
	}
	
	idSortPairs = []; // 清空数组
    
    $('input[name="id"]').each(function(index) {
        // 获取id和相对应的sort值
        let id = $(this).val();
        let sort = $('input[name="sort"]').eq(index).val();
 
        // 将id和sort封装成对象
        idSortPairs.push({
            id: id,
            sort: sort
        });
    });
	
	javaex.tip({
		content : "数据提交中，请稍候...",
		type : "submit"
	});
	
	$.ajax({
		url : "${contextPath}/sys/department/sort",
		type : "POST",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data: JSON.stringify(idSortPairs),
		success : function(rtn) {
			if (rtn.code==0) {
				javaex.tip({
					content : rtn.message,
					type : "success"
				});
				
				getList();
			} else {
				javaex.tip({
					content : rtn.message,
					type : "error"
				});
			}
		}
	});
}

javaex.listenCheckbox({
	callback : function() {
		if ($('#launch-retract').is(':checked')) {
			$("#table .icon-caret-right").each(function() {
				$(this).click();
			});
		} else {
			$("#table .icon-caret-down").each(function() {
				if ($(this).closest("tr").is(":visible")) {
					$(this).click();
				}
			});
		}
	}
});
</script>
</html>
