<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>编辑按钮 - ${system_name}</title>
<#include "/common/style.ftl">
</head>

<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="javaex-main">
					<form id="form">
						<input type="hidden" name="id" value=""/>
						
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">父级菜单</p></div>
							<div class="javaex-unit-right">
								<select id="parentId" name="parentId" disabled="disabled">
									<option value="0">== 无 ==</option>
									<#list list as nav>
										<option value="${nav.id}">${nav.name}</option>
										<#list nav.children as catalogue>
											<option value="${catalogue.id}">&nbsp;&nbsp;&nbsp;&nbsp;${catalogue.name}</option>
											<#list catalogue.children as menu>
												<option value="${menu.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.name}</option>
												
											</#list>
										</#list>
									</#list>
								</select>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">菜单类型</p></div>
							<div class="javaex-unit-right">
								<ul class="javaex-equal-6 clear">
									<li><input type="radio" fill name="type" value="按钮" checked/>按钮</li>
								</ul>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">按钮名称</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="name" value="" data-type="required" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">权限标识</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="permCode" value="" data-type="required" placeholder="请求路径的格式。例如：/sys/menu/edit" autocomplete="off" />
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
	
	$(function() {
		getData();
	});
	
	/**
	 * 加载表单数据
	 */
	function getData() {
		$.ajax({
			url : "${contextPath}/sys/menu/get/" + id,
			type : "GET",
			dataType : "json",
			success : function(rtn) {
				if (rtn.code==0) {
					let data = rtn.data.item;
					
// 					$.ajax({
// 						url : "${contextPath}/sys/menu/get/" + data.parentId,
// 						type : "GET",
// 						dataType : "json",
// 						success : function(rtn2) {
// 							if (rtn2.code==0) {
// 								var parent = rtn2.data.item;
// 								var html = '<option value="'+parent.id+'" selected>'+parent.name+'</option>';
// 								$("#parentId").empty();
// 								$("#parentId").append(html);
// 								javaex.select({
// 									id : "parentId"
// 								});
// 							}
// 						}
// 					});
					
					javaex.form({
						id : "form",
						formData : data,
						callback : function() {
							javaex.select({
								id : "parentId"
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
		
		$("#parentId").attr("disabled", false);
		
		javaex.tip({
			content : "数据提交中，请稍候...",
			type : "submit"
		});
		
		$.ajax({
			url : "${contextPath}/sys/menu/update/" + id,
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