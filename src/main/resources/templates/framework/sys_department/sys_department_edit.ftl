<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>编辑 - ${system_name}</title>
<#include "/common/style.ftl">
</head>

<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="javaex-main">
					<form id="form">
						<input type="hidden" name="id" value=""/>
						
						<!--父部门id-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">上级部门</p></div>
							<div class="javaex-unit-right">
								<select id="parentId" name="parentId">
									<option value="0">== 无 ==</option>
								</select>
							</div>
						</div>

						<!--部门名称-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">部门名称</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" placeholder="请输入部门名称" name="departmentName" data-type="required" value="" autocomplete="off"/>
							</div>
						</div>

						<!--部门负责人-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">负责人</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" placeholder="请输入部门负责人" name="departmentLeader" value="" autocomplete="off"/>
							</div>
						</div>

						<!--部门联系电话-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">联系电话</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" placeholder="请输入部门联系电话" name="departmentPhone" value="" autocomplete="off"/>
							</div>
						</div>

						<!--部门邮箱-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">邮箱</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" placeholder="请输入部门邮箱" name="departmentEmail" value="" autocomplete="off"/>
							</div>
						</div>

						<!--是否逻辑删除-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">状态</p></div>
							<div class="javaex-unit-right">
								<label><input type="radio" fill name="isDeleted" value="0" checked/>启用</label>
								<label class="ml-20"><input type="radio" fill name="isDeleted" value="1" />停用</label>
							</div>
						</div>

					</form>
				</div>
			</div>
			
			<div class="javaex-page-footer">
				<input type="button" onclick="save()" class="javaex-btn blue" value="保存" />
				<input type="button" onclick="closeDialog()" class="javaex-btn wathet" value="关闭" />
			</div>
		</div>
	</div>
</body>
<script>
var id = "${id}";

$(function() {
	getList();
	
	getData();
});

/**
 * 加载表单数据
 */
function getData() {
	$.ajax({
		url : "${contextPath}/sys/department/get/" + id,
		type : "GET",
		dataType : "json",
		success : function(rtn) {
			if (rtn.code==0) {
				let data = rtn.data.item;
				
				javaex.form({
					id : "form",
					formData : data,
					callback: function() {
						javaex.select({
							id : "parentId",
							isSearch : true
						});
					}
				});
			}
		}
	});
}

/**
 * 加载列表数据
 */
function getList() {
	$.ajax({
		url : "${contextPath}/sys/department/list-all",
		type : "GET",
		dataType : "json",
		async : false,
		success : function(rtn) {
			if (rtn.code==0) {
				let list = rtn.data.list;
				let html = '';
				
				if (list!=null && list.length>0) {
					html = traverseDepartments(list);
				}
				
				$("#parentId").append(html);
			}
		}
	});
}

/**
 * 定义递归函数来遍历子级
 */
function traverseDepartments(departments, level = 0) {
	let html = '';
	// 根据层级添加相应数量的&nbsp;
    const spaces = '&nbsp;'.repeat(level * 4);
    
    for (const department of departments) {
        html += '<option value="'+department.id+'">'+spaces+department.departmentName+'</option>';
		
        // 如果当前部门有子部门，则递归调用函数
        if (department.children && department.children.length > 0) {
        	html += traverseDepartments(department.children, level + 1);
        }
    }
    
    return html;
}

/**
 * 保存
 */
function save() {
	if (!javaexVerify()) {
		return;
	}
	
	javaex.tip({
		content : "数据提交中，请稍候...",
		type : "submit"
	});
	
	$.ajax({
		url : "${contextPath}/sys/department/update/" + id,
		type : "POST",
		dataType : "json",
		data : $("#form").serialize(),
		success : function(rtn) {
			if (rtn.code==0) {
				javaex.tip({
					content : rtn.message,
					type : "success"
				});
				
				setTimeout(function() {
					parent.javaex.close();
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