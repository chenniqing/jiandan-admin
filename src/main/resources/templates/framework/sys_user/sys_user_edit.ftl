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
						<input type="hidden" id="id" name="id" value="${id}"/>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">登录账号</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" id="username" name="username" value="" placeholder="请输入登录账号" data-type="required" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">登录密码</p></div>
							<div class="javaex-unit-right">
								<input type="password" class="javaex-text" id="password" name="password" value="" placeholder="编辑时，留空表示不修改密码" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">用户昵称</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" id="nickname" name="nickname" value="" placeholder="请输入用户昵称" data-type="required" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-grid spacing-20">
							<div class="javaex-grid-6">
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle">手机号</p></div>
									<div class="javaex-unit-right">
										<input type="text" class="javaex-text" id="phone" name="phone" value="" placeholder="请输入手机号" data-type="null|phone" error-msg="手机号格式错误" autocomplete="off" />
									</div>
								</div>
							</div>
							<div class="javaex-grid-6">
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle">邮箱</p></div>
									<div class="javaex-unit-right">
										<input type="text" class="javaex-text" id="email" name="email" value="" placeholder="请输入邮箱" data-type="null|email" error-msg="邮箱格式错误" autocomplete="off" />
									</div>
								</div>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">用户性别</p></div>
							<div class="javaex-unit-right">
								<label><input type="radio" fill name="sex" value="0" checked />男</label>
								<label class="ml-20"><input type="radio" fill name="sex" value="1" />女</label>
								<label class="ml-20"><input type="radio" fill name="sex" value="2" />未知</label>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">所属部门</p></div>
							<div class="javaex-unit-right">
								<select id="departmentId" name="departmentId">
									<option value="0">== 无 ==</option>
								</select>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">用户角色</p></div>
							<div class="javaex-unit-right">
								<select id="roles" style="width:100%;">
									
								</select>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">状态</p></div>
							<div class="javaex-unit-right">
								<label><input type="radio" fill name="status" value="0" checked />正常</label>
								<label class="ml-20"><input type="radio" fill name="status" value="1" />停用</label>
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="javaex-page-footer">
				<input type="button" onclick="save()" class="javaex-btn blue" value="保存" />
				<input type="button" onclick="closeDialog()" class="javaex-btn wathet" value="取消" />
			</div>
		</div>
	</div>
</body>
<script>
var id = "${id}";
var roles = null;
var roleIds = new Array();

$(function() {
	getDepartmentList();
	getRoles();
	getData();
});

/**
 * 加载部门列表
 */
function getDepartmentList() {
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
				$("#departmentId").append(html);
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
 * 加载角色列表
 */
function getRoles() {
	$.ajax({
		url : "${contextPath}/sys/role/list-all",
		type : "GET",
		dataType : "json",
		async : false,
		success : function(rtn) {
			if (rtn.code==0) {
				$("#username").val("");
				$("#password").val("");
				$("#nickname").val("");
				
				roles = rtn.data.list;
			}
		}
	});
}

/**
 * 加载表单数据
 */
function getData() {
	$.ajax({
		url : "${contextPath}/sys/user/get/" + id,
		type : "GET",
		dataType : "json",
		async : false,
		success : function(rtn) {
			if (rtn.code==0) {
				let data = rtn.data.item;
				let roleIdList = rtn.data.roleIdList;
				
				javaex.form({
					id : "form",
					formData : data,
					callback : function() {
						$("#username").attr("readonly", true);
						$("#username").addClass("readonly");
						
						const transformedRoles = roles.map(role => {
						    const transformedRole = {
								value: role.id,
								text: role.roleName
						    };
						 
						    // 检查id是否在selectedIds数组中，如果是，追加selected=true
						    if (roleIdList.includes(role.id)) {
								transformedRole.selected = true;
						    }
						 
						    return transformedRole;
						});

						javaex.select({
							id : "roles",
							mode : "checkbox",
							isInit : true,
							dataList : transformedRoles,
							callback: function (rtn) {
								roleIds = rtn.val;
							}
						});
						
						$("#departmentId").val(data.departmentId);
						javaex.select({
							id : "departmentId",
							isSearch : true
						});
					}
				});
			}
		}
	});
}

/**
 * 保存
 */
function save() {
	if (!javaexVerify()) {
		return;
	}
	
	if (!id && !$("#password").val()) {
		addErrorMsg("password", "密码不能为空");
		return;
	}
	
	javaex.tip({
		content : "数据提交中，请稍候...",
		type : "submit"
	});
	
	$.ajax({
		url : "${contextPath}/sys/user/update/" + id,
		type : "POST",
		dataType : "json",
		traditional : true,
		data : {
			"username" : $("#username").val(),
			"password" : $("#password").val(),
			"nickname" : $("#nickname").val(),
			"phone" : $("#phone").val(),
			"email" : $("#email").val(),
			"departmentId" : $("#departmentId").val(),
			"sex" : $(':radio[name="sex"]:checked').val(),
			"status" : $(':radio[name="status"]:checked').val(),
			"roleIds" : roleIds
		},
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