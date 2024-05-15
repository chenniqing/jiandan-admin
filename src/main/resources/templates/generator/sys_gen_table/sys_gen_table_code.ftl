<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>代码生成 - ${system_name}</title>
<#include "/common/style.ftl">
</head>
<style>
.javaex-unit-right span.qz {
    display: inline-block;
    width: 65px;
    vertical-align: middle;
    margin-right: 5px;
}
</style>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="javaex-main">
					<form id="form">
						<p class="javaex-alert" warning>为防止手贱，不提供代码生成到指定路径功能。请手动将自动下载的代码解压缩并覆盖到项目中。</p><br/>
						<input type="hidden" name="tableIds" value="${idstrs}"/>
						
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">项目包名</p></div>
							<div class="javaex-unit-right">
								<span class="qz">cn.javaex.</span><span><input type="text" class="javaex-text" style="width: calc(100% - 70px);" id="packageName" name="packageName" value="" placeholder="支持xx.xx这样的多级。例如：cn.javaex.abc.student，就填abc.student" data-type="required" /></span>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">业务包名</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="businessName" value="" placeholder="例如：cn.javaex.comprehensive.controller.campus_clinic，那就填campus_clinic" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">创作人</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="author" value="" placeholder="@author xxx，如果不填，则会默认使用系统账户名字" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">代码生成</p></div>
							<div class="javaex-unit-right">
								<p><input type="checkbox" fill listen="listen-1"/>全选</p>
								<ul class="javaex-equal-4 clear">
									<li><input type="checkbox" fill listen="listen-1-2" name="codeTypes" value="controller" />Controller</li>
									<li><input type="checkbox" fill listen="listen-1-2" name="codeTypes" value="service" />Service</li>
									<li><input type="checkbox" fill listen="listen-1-2" name="codeTypes" value="dao" />Dao</li>
								</ul>
								<ul class="javaex-equal-4 clear">
									<li><input type="checkbox" fill listen="listen-1-2" name="codeTypes" value="entity" />Entity</li>
									<li><input type="checkbox" fill listen="listen-1-2" name="codeTypes" value="dto" />DTO</li>
									<li><input type="checkbox" fill listen="listen-1-2" name="codeTypes" value="vo" />VO</li>
									<li><input type="checkbox" fill listen="listen-1-2" name="codeTypes" value="query" />Query</li>
									<li><input type="checkbox" fill listen="listen-1-2" name="codeTypes" value="freemarker" />Freemarker</li>
								</ul>
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="javaex-page-footer">
				<input type="button" onclick="genCode()" class="javaex-btn blue" value="生成" />
				<input type="button" onclick="closeDialog()" class="javaex-btn wathet" value="关闭" />
			</div>
		</div>
	</div>
</body>
<script>
	/**
	 * 生成代码
	 */
	function genCode() {
		if (!javaexVerify()) {
			return;
		}
		
		let codeTypes = javaex.getCheckboxVal("codeTypes");
		if (codeTypes==null || codeTypes.length==0) {
			javaex.tip({
				content : "最少选中一种文件类型才能生成代码",
				type : "error"
			});
			return;
		}
		
		javaex.tip({
			content : "代码生成中，请稍候...",
			type : "submit"
		});
		
		$.ajax({
			url : "${contextPath}/sys/gen/table/gen-code",
			type : "POST",
			dataType : "json",
			traditional : true,
			data : $("#form").serialize(),
			success : function(rtn) {
				if (rtn.code==0) {
					javaex.tip({
						content : "代码已生成，正在下载...",
						type : "success"
					});
					
					// 下载压缩包
					const uuid = rtn.data.uuid;
					window.location.href = "${contextPath}/sys/gen/table/download-file/" + uuid;
					
					setTimeout(function() {
						parent.javaex.close();
					}, 2000);
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