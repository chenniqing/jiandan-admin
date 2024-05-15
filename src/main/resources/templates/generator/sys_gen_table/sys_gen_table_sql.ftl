<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>表创建sql语句 - ${system_name}</title>
<#include "/common/style.ftl">
<link href="${contextPath}/assets/plugin/javaex-5.5.1/pc/lib/highlight/highlight.css" rel="stylesheet" />
<script src="${contextPath}/assets/plugin/javaex-5.5.1/pc/lib/highlight/highlight.min.js"></script>
<script src="${contextPath}/assets/plugin/javaex-5.5.1/pc/lib/highlight/highlightjs-line-numbers.min.js"></script>
<script src="${contextPath}/assets/plugin/javaex-5.5.1/pc/lib/clipboard/clipboard.min.js"></script>
</head>

<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="javaex-main" style="padding-bottom: 0;">
					<pre><code id="sql"></code></pre>
				</div>
			</div>
			
			<div class="javaex-page-footer">
				<input type="button" onclick="closeDialog()" class="javaex-btn wathet" value="关闭" />
			</div>
		</div>
	</div>
</body>
<script>
var idstrs = "${idstrs}";

$(function() {
	getData();
});

/**
 * 加载表单数据
 */
function getData() {
	$.ajax({
		url : "${contextPath}/sys/gen/table/view-sql",
		type : "GET",
		dataType : "json",
		data : {
			"idstrs" : idstrs
		},
		success : function(rtn) {
			if (rtn.code==0) {
				let sql = rtn.data.sql;
				$("#sql").html(sql);
				
				hljs.initHighlighting();
				
				setTimeout(function() {
					$("code").each(function(i, block) {
						hljs.lineNumbersBlock(block);
					});
				}, 500);
				
				javaex.copy();
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