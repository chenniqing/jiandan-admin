<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>后台登录 - 简单后台管理系统</title>
<#include "/common/style.ftl">
<style>
body {
	display: flex;
	display: -webkit-flex;
	min-height: 100vh;
	flex-direction: column;
	background: linear-gradient(160deg,transparent 45%,#fff 0,#fff 60%,transparent 0),linear-gradient(-160deg,transparent 45%,#f4f6f9 0,transparent 60%),linear-gradient(180deg,rgba(216,221,230,.5),#fff);
	background-size: 100% 100%;
	background-repeat: no-repeat;
	background-size: 100% 1600px;
}
@media screen and (min-width: 1540px) {
	body {
		background-size: 100% 1600px;
	}
}
.account-wrap {
	width: 100%;
	flex: 1;
}
.account-container {
	width: 360px;
	margin: 0 auto;
}
.account-content {
	width: 100%;
	margin: 140px auto 0 0;
	position: relative;
}
.account-tip-content {
	width: 100%;
	margin-top: 20px;
	margin-bottom: 24px;
	position: relative;
}
.account-tip-content .account-title {
	font-size: 24px;
	color: #262626;
	font-weight: 500;
}
.account-login input.javaex-text {
	width: 100%;
}
.password-wrap {
	margin-top: 20px;
}
.btn-submit {
	margin-top: 24px;
	border-radius: 4px;
}
.footer-wrap {
	position: relative;
	margin-bottom: 40px;
	text-align: center;
	font-size: 14px;
	color: #383838;
}
.account-login input.text, .account-login .javaex-btn {
	height: 36px;
	line-height: 36px;
}
</style>
</head>

<body>
	<div class="account-wrap">
		<form id="form">
			<div class="account-container">
				<div class="account-content">
					<div class="account-tip-content">
						<p class="account-title">简单后台管理系统</p>
						<p id="loginreg-error-tip" class="javaex-error-info vh" style="position: absolute; left: 10px;"></p>
					</div>
					
					<div class="account-login">
						<div class="username-wrap">
							<input type="text" class="javaex-text original" name="username" data-type="required" placeholder="请输入账号" />
						</div>
						<div class="password-wrap">
							<input type="password" class="javaex-text original" id="password" name="password" onkeypress="return onKeyPress(event)" data-type="required" placeholder="请输入密码" />
						</div>
						<a href="javascript:;" onClick="doLogin()" class="javaex-btn blue full btn-submit">登录</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="footer-wrap">
		<p>2024 © 简单后台管理系统 All Rights Reserved <a href="https://www.javaex.cn" target="_blank" class="javaex-link primary" style="margin-top: -2px;">https://www.javaex.cn</a></p>
	</div>
</body>
<script>
	function onKeyPress(e) {
		var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
		
		if (keyCode==13) {
			doLogin();
			return false;
		}
		return true;
	}

	function doLogin() {
		if (!javaexVerify()) {
			return;
		}
		
		$("#login").addClass("disable");
		$("#login").val("登录中...");
		
		$.ajax({
			url: "${contextPath}/login/submit",
			type : "POST",
			dataType : "json",
			data: $("#form").serialize(),
			success : function(rtn) {
				if (rtn.code==0) {
					top.window.location.href = "${contextPath}/index";
				} else {
					$("#login").removeClass("disable");
					$("#login").val("登录");
					
					$("#loginreg-error-tip").text(rtn.message);
					$("#loginreg-error-tip").removeClass("vh");
				}
			}
		});
	}
	
	$(document).on('focus', 'input[type="text"], input[type="password"]', function() {
		$("#loginreg-error-tip").text("");
		$("#loginreg-error-tip").addClass("vh");
	});
</script>
</html>
