<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新增角色 - ${system_name}</title>
<#include "/common/style.ftl">
</head>

<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="javaex-main">
					<form id="form">
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">角色名称</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="roleName" placeholder="请输入角色名称" value="" data-type="required" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">角色标识</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="roleCode" placeholder="请输入角色标识" value="" data-type="required" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">数据权限</p></div>
							<div class="javaex-unit-right">
								<select id="dataScope" name="dataScope">
									<option value="">请选择</option>
									<option value="1">全部数据权限</option>
									<option value="2">本部门及以下数据权限</option>
									<option value="3">本部门数据权限</option>
									<option value="4">仅自己数据权限</option>
								</select>
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
javaex.select({
	id : "dataScope"
});

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
		url : "${contextPath}/sys/role/add",
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