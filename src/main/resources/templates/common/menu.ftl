<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>${system_name}</title>
<#include "/common/style.ftl">
</head>

<body>
	<div class="javaex-navbar">
		<div class="javaex-container-fluid clear">
			<!--logo名称-->
			<div class="javaex-logo">简单管理系统</div>
			
			<!--左侧导航-->
			<ul class="javaex-nav fl">
				<#list menuList as nav>
					<li <#if nav.url==navUrl>class="active"</#if>><a href="${contextPath}${nav.url}">${nav.name}</a></li>
				</#list>
			</ul>
			
			<!--右侧-->
			<ul class="javaex-nav fr">
				<li>
					<a href="javascript:;">欢迎您，${curUser.nickname}</a>
					<ul class="javaex-nav-dropdown-menu" style="right: 10px;">
						<li><a href="javascript:;" onclick="changePassword()">修改密码</a></li>
						<li><a href="${contextPath}/logout">退出当前账号</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	
	<div class="javaex-main-container">
		<!--左侧菜单-->
		<div class="javaex-aside javaex-aside-fixed">
			<h1><span class="javaex-nav-name">${navName}</span></h1>
			<div id="javaex-toc" class="javaex-toc">
				<div class="javaex-menu-container">
					<div id="menu" class="javaex-menu">
						<ul>
							<#list menuList as nav>
								<#if nav.url==navUrl>
									<#list nav.children as entity>
										<#if entity.type=='目录'>
											<li class="javaex-menu-item">
												<a href="javascript:;">${entity.name}<i class="icon-angle-down"></i></a>
												<ul>
													<#list entity.children as menu>
														<li <#if menu.url==activeUrl>class="hover"</#if>>
															<a href="javascript:page('${contextPath}${menu.url}');">${menu.name}</a>
														</li>
													</#list>
												</ul>
											</li>
										<#else>
											<li class="javaex-menu-item <#if entity.url==activeUrl>hover</#if>">
												<a href="javascript:page('${contextPath}${entity.url}');">${entity.name}</a>
											</li>
										</#if>
									</#list>
								</#if>
							</#list>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<!--iframe载入内容-->
		<div class="javaex-markdown">
			<iframe id="page" src="${contextPath}${activeUrl}"></iframe>
		</div>
	</div>
</body>
<script>
	javaex.loading();
	
	var hightUrl = "${contextPath}${activeUrl}";
	
	javaex.menu({
		id : "menu",
		isAutoSelected : true,
		key : "",
		url : hightUrl
	});
	
	$(function() {
		// 设置左侧菜单高度
		setMenuHeight();
	});
	
	/**
	 * 设置左侧菜单高度
	 */
	function setMenuHeight() {
		var height = document.documentElement.clientHeight - $("#javaex-toc").offset().top;
		height = height - 10;
		$("#javaex-toc").css("height", height+"px");
	}
	
	/**
	 * 控制页面载入
	 */
	function page(url) {
		$("#page").attr("src", url);
	}
	
	/**
	 * 修改密码
	 */
	function changePassword() {
		top.javaex.dialog({
			type : "dialog",
			title : "修改密码",
			url : "${contextPath}/sys/user/page/change-password",
			width : "1000",
			height : "618"
		});
	}
</script>
</html>
