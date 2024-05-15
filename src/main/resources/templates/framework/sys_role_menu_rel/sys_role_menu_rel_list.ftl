<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>用户列表 - ${system_name}</title>
<#include "/common/style.ftl">
</head>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="content">
					<div id="tree" class="javaex-tree mt-20"></div>
				</div>
			</div>
		
			<div class="javaex-page-footer" style="text-align: left;">
				<input type="button" onclick="save()" class="javaex-btn blue" value="保存" />
			</div>
		</div>
	</div>
</body>
<script>
var roleId = "${roleId}";
var menuIds = new Array();

$(function() {
	getList();
});

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
		url : "${contextPath}/sys/role-menu/list/" + roleId,
		type : "GET",
		dataType : "json",
		success : function(rtn) {
			javaex.loading({
				mode : "manual",
				containerId : "table",
				type : "close"
			});
			
			if (rtn.code==0) {
				let list = rtn.data.list;
				javaex.tree({
					id : "tree",
					dataList : list,
					type : 1,				// 数据结构类型（1或2）
					isShowAllCheck : true,	// 是否显示全选
					checkbox : true,		// 是否显示复选框
					checkboxCallback : function(rtn) {
						menuIds = rtn.idArr;
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
	javaex.tip({
		content : "数据提交中，请稍候...",
		type : "submit"
	});
	
	$.ajax({
		url : "${contextPath}/sys/role-menu/save/" + roleId,
		type : "POST",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : JSON.stringify({
			"menuIds" : menuIds
		}),
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
</script>
</html>
