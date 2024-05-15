<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>菜单列表 - ${system_name}</title>
<#include "/common/style.ftl">
<style>
.input-td {
	padding: 5px 16px;
}
</style>
</head>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-header">
				<div class="javaex-breadcrumb">
					<span>系统管理</span>
					<span class="divider">/</span>
					<span class="active">菜单管理</span>
				</div>
			</div>
			
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-list-toolbar clear">
						<span class="fl">
							<button class="javaex-btn blue" onclick="addMenu()">新增菜单</button>
							<button class="javaex-btn wathet" onclick="saveSort()">更新排序</button>
							
							<label class="ml-20"><input type="checkbox" id="launch-retract" fill name="checkbox" />展开/收起</label>
						</span>
					</div>
					
					<div class="javaex-block">
						<table id="table" class="javaex-table td-c-1 vertical-line-white">
							<thead>
								<tr>
									<th class="javaex-table-filter-col"><input type="radio" fill name="radio" value=""/> </th>
									<th style="width:120px;">显示顺序</th>
									<th>菜单名称</th>
									<th>菜单链接</th>
									<th>页面按钮权限</th>
									<th>是否隐藏</th>
									<th style="width:80px;">类型</th>
									<th style="width:250px;">操作</th>
								</tr>
							</thead>
							<tbody id="data">
								
							</tbody>
						</table>
					</div>
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
		url : "${contextPath}/sys/menu/list-all",
		type : "GET",
		dataType : "json",
		success : function(rtn) {
			javaex.loading({
				mode : "manual",
				containerId : "table",
				type : "close"
			});
			
			if (rtn.code==0) {
				$('#launch-retract').attr('checked', false);
				
				let list = rtn.data.list;
				let html = '';
				
				if (list!=null && list.length>0) {
					$.each(list, function(i, nav) {
						html += '<tr id="tree_'+nav.id+'" parentId="tree_'+nav.parentId+'">';
						html += '	<td><input type="radio" fill name="radio" value="'+nav.id+'"/> </td>';
						html += '	<td class="input-td"><input type="hidden" name="id" value="'+nav.id+'" /><input type="text" class="javaex-text" name="sort" data-type="positive_int" error-msg="必须输入正整数" value="'+nav.sort+'" autocomplete="off" /></td>';
						html += '	<td>'+nav.name+'</td>';
						html += '	<td>'+javaex.ifnull(nav.url)+'</td>';
						html += '	<td>'+javaex.ifnull(nav.permCode)+'</td>';
						if (javaex.ifnull(nav.isHidden, 0) == 1) {
							html += '<td><font class="red">隐藏</font></td>';
						} else {
							html += '<td></td>';
						}
						html += '	<td><button class="javaex-btn red empty mini">导航</button></td>';
						html += '	<td>';
						if (nav.isSystem!=1) {
							html += '	<a href="javascript:;" class="blue mr-10" onclick="editMenu(\''+nav.id+'\')">编辑</a>';
							html += '	<a href="javascript:;" class="red mr-10" onclick="delMenu(this, \''+nav.id+'\')">删除</a>';
						}
						html += '	</td>';
						html += '</tr>';
						
						let children = nav.children;
						if (children!=null && children.length>0) {
							$.each(children, function(j, entity) {
								html += '<tr id="tree_'+entity.id+'" parentId="tree_'+entity.parentId+'">';
								if (entity.type=='目录') {
									html += '<td><input type="radio" fill name="radio" value="'+entity.id+'"/> </td>';
								} else {
									html += '<td></td>';
								}
								html += '	<td class="input-td" style="padding-left:30px;"><input type="hidden" name="id" value="'+entity.id+'" /><input type="text" class="javaex-text" name="sort" data-type="positive_int" error-msg="必须输入正整数" value="'+entity.sort+'" autocomplete="off" /></td>';
								html += '	<td>'+javaex.ifnull(entity.name)+'</td>';
								html += '	<td>'+javaex.ifnull(entity.url)+'</td>';
								html += '	<td>'+javaex.ifnull(entity.permCode)+'</td>';
								if (javaex.ifnull(entity.isHidden, 0) == 1) {
									html += '<td><font class="red">隐藏</font></td>';
								} else {
									html += '<td></td>';
								}
								if (entity.type=='目录') {
									html += '	<td><button class="javaex-btn yellow empty mini">'+entity.type+'</button></td>';
								} else {
									html += '	<td><button class="javaex-btn blue empty mini">'+entity.type+'</button></td>';
								}
								html += '	<td>';
								if (entity.isSystem!=1) {
									html += '	<a href="javascript:;" class="blue mr-10" onclick="editMenu(\''+entity.id+'\')">编辑</a>';
									html += '	<a href="javascript:;" class="red mr-10" onclick="delMenu(this, \''+entity.id+'\')">删除</a>';
								}
								if (entity.type=='菜单') {
									html += '	<a href="javascript:;" class="green mr-10" onclick="addButton(\''+entity.id+'\')">添加页面权限</a>';
								}
								html += '	</td>';
								html += '</tr>';
								
								if (entity.type=='目录') {
									let menuList = entity.children;
									if (menuList!=null && menuList.length>0) {
										$.each(menuList, function(k, menu) {
											html += '<tr id="tree_'+menu.id+'" parentId="tree_'+menu.parentId+'">';
											html += '	<td></td>';
											html += '	<td class="input-td" style="padding-left:40px;"><input type="hidden" name="id" value="'+menu.id+'" /><input type="text" class="javaex-text" name="sort" data-type="positive_int" error-msg="必须输入正整数" value="'+menu.sort+'" autocomplete="off" /></td>';
											html += '	<td>'+menu.name+'</td>';
											html += '	<td>'+javaex.ifnull(menu.url)+'</td>';
											html += '	<td>'+javaex.ifnull(menu.permCode)+'</td>';
											if (javaex.ifnull(menu.isHidden, 0) == 1) {
												html += '<td><font class="red">隐藏</font></td>';
											} else {
												html += '<td></td>';
											}
											html += '	<td><button class="javaex-btn blue empty mini">'+menu.type+'</button></td>';
											html += '	<td>';
											if (menu.isSystem!=1) {
												html += '	<a href="javascript:;" class="blue mr-10" onclick="editMenu(\''+menu.id+'\')">编辑</a>';
												html += '	<a href="javascript:;" class="red mr-10" onclick="delMenu(this, \''+menu.id+'\')">删除</a>';
											}
											html += '	<a href="javascript:;" class="green mr-10" onclick="addButton(\''+menu.id+'\')">添加页面权限</a>';
											html += '	</td>';
											html += '</tr>';
											
											let btnList = menu.children;
											if (btnList!=null && btnList.length>0) {
												$.each(btnList, function(l, btn) {
													html += '<tr id="tree_'+btn.id+'" parentId="tree_'+btn.parentId+'">';
													html += '	<td></td>';
													html += '	<td class="input-td" style="padding-left:60px;"><input type="hidden" name="id" value="'+btn.id+'" /><input type="text" class="javaex-text" name="sort" data-type="positive_int" error-msg="必须输入正整数" value="'+btn.sort+'" autocomplete="off" /></td>';
													html += '	<td>'+btn.name+'</td>';
													html += '	<td>'+javaex.ifnull(btn.url)+'</td>';
													html += '	<td>'+javaex.ifnull(btn.permCode)+'</td>';
													if (javaex.ifnull(btn.isHidden, 0) == 1) {
														html += '<td><font class="red">隐藏</font></td>';
													} else {
														html += '<td></td>';
													}
													html += '	<td><button class="javaex-btn green empty mini">'+btn.type+'</button></td>';
													html += '	<td>';
													if (btn.isSystem!=1) {
														html += '	<a href="javascript:;" class="blue" onclick="editButton(\''+btn.id+'\')">编辑</a>';
														html += '	<a href="javascript:;" class="red mr-10" onclick="delMenu(this, \''+btn.id+'\')">删除</a>';
													}
													html += '	</td>';
													html += '</tr>';
												});
											}
										});
									}
								}
								else {
									let btnList = entity.children;
									if (btnList!=null && btnList.length>0) {
										$.each(btnList, function(l, btn) {
											html += '<tr id="tree_'+btn.id+'" parentId="tree_'+btn.parentId+'">';
											html += '	<td></td>';
											html += '	<td class="input-td" style="padding-left:40px;"><input type="hidden" name="id" value="'+btn.id+'" /><input type="text" class="javaex-text" name="sort" data-type="positive_int" error-msg="必须输入正整数" value="'+btn.sort+'" autocomplete="off" /></td>';
											html += '	<td>'+btn.name+'</td>';
											html += '	<td>'+javaex.ifnull(btn.url)+'</td>';
											html += '	<td>'+javaex.ifnull(btn.permCode)+'</td>';
											if (javaex.ifnull(btn.isHidden, 0) == 1) {
												html += '<td><font class="red">隐藏</font></td>';
											} else {
												html += '<td></td>';
											}
											html += '	<td><button class="javaex-btn green empty mini">'+btn.type+'</button></td>';
											html += '	<td>';
											if (btn.isSystem!=1) {
												html += '	<a href="javascript:;" class="blue mr-10" onclick="editButton(\''+btn.id+'\')">编辑</a>';
												html += '	<a href="javascript:;" class="red mr-10" onclick="delMenu(this, \''+btn.id+'\')">删除</a>';
											}
											html += '	</td>';
											html += '</tr>';
										});
									}
								}
							});
						}
					});
				} else {
					html = '<tr><td colspan="8" class="tc">暂无数据</td></tr>';
				}
				
				$("#data").html(html);
				javaex.render();
				
				javaex.table({
					id : "table",
					tree : 3,
					isClose : true
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
 * 新增菜单
 */
function addMenu() {
	let parentId = javaex.getRadioVal("radio");
	
	top.javaex.dialog({
		type : "dialog",
		title : "新增菜单",
		url : "${contextPath}/sys/menu/page/add-menu?parentId=" + parentId,
		width : "1000",
		height : "618"
	});
}

/**
 * 编辑菜单
 */
function editMenu(id) {
	let parentId = javaex.getRadioVal("radio");
	
	top.javaex.dialog({
		type : "dialog",
		title : "编辑菜单",
		url : "${contextPath}/sys/menu/page/edit-menu?id=" + id,
		width : "1000",
		height : "618"
	});
}

/**
 * 添加按钮
 */
function addButton(parentId) {
	top.javaex.dialog({
		type : "dialog",
		title : "添加页面按钮",
		url : "${contextPath}/sys/menu/page/add-button?parentId=" + parentId,
		width : "1000",
		height : "618"
	});
}
/**
 * 编辑按钮
 */
function editButton(id) {
	top.javaex.dialog({
		type : "dialog",
		title : "编辑页面按钮",
		url : "${contextPath}/sys/menu/page/edit-button?id=" + id,
		width : "1000",
		height : "618"
	});
}

/**
 * 删除菜单
 */
function delMenu(obj, id) {
	javaex.deleteDialog(obj, {
		content : "确定要删除么？",
		confirm : function() {
			javaex.tip({
				content : "菜单删除中，请稍候...",
				type : "submit"
			});
			
			$.ajax({
				url : "${contextPath}/sys/menu/delete/" + id,
				type : "POST",
				dataType : "json",
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
		url : "${contextPath}/sys/menu/sort",
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
