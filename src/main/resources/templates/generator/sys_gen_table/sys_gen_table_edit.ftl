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

						<!--表名-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">表名</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" placeholder="请输入表名" name="tableName" data-type="required" value="" autocomplete="off"/>
							</div>
						</div>

						<!--表中文名-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">表中文名</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" placeholder="请输入表中文名" name="tableComment" value="" autocomplete="off"/>
							</div>
						</div>

						<!--备注-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">备注</p></div>
							<div class="javaex-unit-right">
								<textarea class="javaex-desc" placeholder="请输入备注" name="remark"></textarea>
							</div>
						</div>

						<!--状态-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">状态</p></div>
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
		getData();
	});

	/**
	 * 加载表单数据
	 */
	function getData() {
		$.ajax({
			url : "${contextPath}/sys/gen/table/get/" + id,
			type : "GET",
			dataType : "json",
			success : function(rtn) {
				if (rtn.code==0) {
					let data = rtn.data.item;
					
					javaex.form({
						id : "form",
						formData : data
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
		
		javaex.tip({
			content : "数据提交中，请稍候...",
			type : "submit"
		});
		
		$.ajax({
			url : "${contextPath}/sys/gen/table/update/" + id,
			type : "POST",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			data : javaex.serializeToJsonString($("#form").serialize()),
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