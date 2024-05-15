<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>主页 - ${system_name}</title>
<#include "/common/style.ftl">
<style>
.box {
    width: 100%;
    padding-top: 20px;
    position: relative;
    background-color: #fff;
    border: 1px solid #EBEBEB;
}
ul li, ol li {
	margin-bottom:12px;
    font-size: 16px;
}
ul li span {
	font-weight: 700;
	margin-right:6px;
}
.javaex-block .javaex-banner {
    font-size: 20px;
}
</style>
</head>

<body>
	<div style="padding:20px;">
		<blockquote class="javaex-quote">
			介绍：基于SpringBoot2的权限管理系统。 核心技术采用Spring、MyBatis、Shiro没有任何其它重度依赖。直接运行即可用。
		</blockquote>
		
		<div class="javaex-grid spacing-20">
			<div class="javaex-grid-6">
				<div class="javaex-block has-border">
					<div class="javaex-banner"><span class="javaex-block-fixed"></span>技术选型</div>
					<div class="javaex-main">
						<ul>
							<li><span>JDK：</span>1.8</li>
							<li><span>数据库：</span>MySQL 8</li>
							<li>
								<span>后端：</span>
								<ul>
									<li class="ml-20"><span>核心框架：</span>springboot 2.1.3.RELEASE</li>
									<li class="ml-20"><span>安全框架：</span>shiro</li>
									<li class="ml-20"><span>数据库操作：</span>mybatis + pagehelper + mybatisjj（自研）</li>
									<li class="ml-20"><span>数据库连接池：</span>druid</li>
									<li class="ml-20"><span>工具类：</span>fastjson + htool（自研）</li>
									<li class="ml-20"><span>导入导出：</span>officejj（自研）</li>
								</ul>
							</li>
							<li>
								<span>前端：</span>
								<ul>
									<li class="ml-20"><span>模板引擎：</span>FreeMarker</li>
									<li class="ml-20"><span>UI库：</span>jQuery + javaex（自研）</li>
								</ul>
							</li>
							<li><span>文档地址：</span><a href="https://doc.javaex.cn/jiandan-admin" class="javaex-link primary" target="_blank">https://doc.javaex.cn/jiandan-admin</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="javaex-grid-6">
				<div class="javaex-block has-border">
					<div class="javaex-banner"><span class="javaex-block-fixed"></span>开发优势</div>
						<div class="javaex-main-0">
							<ol>
								<li>不用写任何注解即可实现菜单和按钮的控制权限</li>
								<li>仅需一个注解即可实现数据权限</li>
								<li>FreeMarker模板引擎的语法贴近JSP，更适合后端快速开发</li>
								<li>轮子全都自己造，文档齐全，代码简洁，更容易上手</li>
							</ol>
						</div>
					</div>
			</div>
		</div>
		<div class="javaex-grid spacing-20">
			<div class="javaex-grid-4">
				<div class="box" style="overflow:hidden;">
					<i class="javaex-subscript red">联系信息</i>
					<div class="javaex-main">
						官网：<a href="https://www.javaex.cn" class="javaex-link primary" target="_blank">https://www.javaex.cn</a>
						<br/><br/>
						QQ群：587243028
						<br/><br/>
						mybatisjj文档地址：<a href="https://doc.javaex.cn/mybatisjj-mysql" class="javaex-link primary" target="_blank">https://doc.javaex.cn/mybatisjj-mysql</a>
						<br/><br/>
						htool文档地址：<a href="https://doc.javaex.cn/htool" class="javaex-link primary" target="_blank">https://doc.javaex.cn/htool</a>
						<br/><br/>
						officejj文档地址：<a href="https://doc.javaex.cn/officejj" class="javaex-link primary" target="_blank">https://doc.javaex.cn/officejj</a>
						<br/><br/>
						javaex文档地址：<a href="https://javaex.javaex.cn/doc-5.x/index.html" class="javaex-link primary" target="_blank">https://javaex.javaex.cn/doc-5.x/index.html</a>
					</div>
				</div>
			</div>
			<div class="javaex-grid-4">
				<div class="box" style="overflow:hidden;">
					<i class="javaex-subscript green">捐助</i>
					<div class="javaex-main tc">
						<p class="javaex-alert" success>请作者喝一杯卡布奇诺</p><br/>
						<div class="javaex-image" style="width: 300px; height: 300px; margin: 0 auto;">
							<img class="javaex-img" src="/assets/images/weixin.png" fit="cover" />
						</div>
					</div>
				</div>
			</div>
			<div class="javaex-grid-4">
				<div class="box" style="overflow:hidden;">
					<i class="javaex-subscript blue">更新日志</i>
					<div class="javaex-main">
						<ul>
							<li class="ml-20"><span>v 1.0.0：</span>2024-05-15</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
</script>
</html>