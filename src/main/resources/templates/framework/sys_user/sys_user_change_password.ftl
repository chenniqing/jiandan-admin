<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新增 - ${system_name}</title>
<#include "/common/style.ftl">
</head>

<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="javaex-main">
					<form id="form">
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">登录账号</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text readonly" name="username" readonly value="${curUser.username}" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">旧密码</p></div>
							<div class="javaex-unit-right">
								<input type="password" class="javaex-text" name="oldPassword" value="" placeholder="请输入登录密码" data-type="required" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">新密码</p></div>
							<div class="javaex-unit-right">
								<input type="password" class="javaex-text" name="newPassword" value="" placeholder="请输入新密码" data-type="required" autocomplete="off" />
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
		url : "${contextPath}/sys/user/update/password",
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