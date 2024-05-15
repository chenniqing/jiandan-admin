<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>编辑菜单 - ${system_name}</title>
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
							<div class="javaex-unit-left tl"><p class="subtitle required">父级节点</p></div>
							<div class="javaex-unit-right">
								<select id="parentId" name="parentId">
									<option value="0">== 无 ==</option>
									<#list list as nav>
										<option value="${nav.id}">${nav.name}</option>
										<#list nav.children as entity>
											<#if entity.type=='目录'>
												<option value="${entity.id}">&nbsp;&nbsp;&nbsp;&nbsp;${entity.name}</option>
											</#if>
										</#list>
									</#list>
								</select>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">菜单类型</p></div>
							<div class="javaex-unit-right">
								<ul class="javaex-equal-6 clear">
									<li><input type="radio" fill name="type" value="导航" />导航</li>
									<li><input type="radio" fill name="type" value="目录" />目录</li>
									<li><input type="radio" fill name="type" value="菜单" />菜单</li>
								</ul>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">菜单名称</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="name" value="" data-type="required" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">菜单链接</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="url" value="" placeholder="类型为导航时，填写唯一的导航标识即可" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">隐藏菜单</p></div>
							<div class="javaex-unit-right">
								<input type="checkbox" id="isHiddenCheckbox" name="isHiddenCheckbox" style="vertical-align: middle;" switch onchange="isHiddenChange(this)"/>
								<input type="hidden" id="isHidden" name="isHidden" >
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
	getEntity();
});

$(':radio[name="type"]').change(function() {
	format($(this).val());
});

function isHiddenChange(obj) {
	if ($(obj).is(":checked")) {
		$('#isHidden').val("1");
	} else {
		$('#isHidden').val("0");
	}
}

/**
 * 格式化数据
 */
function format(type) {
	if (type=="目录") {
		// 禁用菜单地址栏
		$('input[name="url"]').attr("readonly", true);
		$('input[name="url"]').addClass("readonly");
	} else {
		// 放开菜单地址栏
		$('input[name="url"]').attr("readonly", false);
		$('input[name="url"]').removeClass("readonly");
	}
}

/**
 * 加载表单数据
 */
function getEntity() {
	$.ajax({
		url : "${contextPath}/sys/menu/get/" + id,
		type : "GET",
		dataType : "json",
		success : function(rtn) {
			if (rtn.code==0) {
				let data = rtn.data.item;
				
				javaex.form({
					id : "form",
					formData : data,
					callback : function() {
						if (data.isHidden == 1) {
							$('#isHiddenCheckbox').prop('checked', true);
						}
						
						javaex.select({
							id : "parentId"
						});
						
						format(javaex.getRadioVal("type"));
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