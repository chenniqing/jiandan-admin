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
						<input type="hidden" name="id" value="${id}"/>

						<div class="javaex-grid spacing-20">
							<div class="javaex-grid-6">
								<!--字典编码-->
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle required">字典编码</p></div>
									<div class="javaex-unit-right">
										<input type="text" class="javaex-text" placeholder="请输入字典编码。示例：sex" id="dictCode" name="dictCode" data-type="required" value="" autocomplete="off"/>
									</div>
								</div>
							</div>
							<div class="javaex-grid-6">
								<!--字典编码描述-->
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle required">字典描述</p></div>
									<div class="javaex-unit-right">
										<input type="text" class="javaex-text" placeholder="请输入字典编码描述。示例：用户性别" id="dictCodeComment" name="dictCodeComment" value="" autocomplete="off"/>
									</div>
								</div>
							</div>
						</div>
						
						<div class="javaex-grid spacing-20">
							<div class="javaex-grid-6">
								<!--字典键值-->
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle required">字典键值</p></div>
									<div class="javaex-unit-right">
										<input type="text" class="javaex-text" placeholder="请输入字典键值。示例：0" name="dictValue" data-type="required" value="" autocomplete="off"/>
									</div>
								</div>
							</div>
							<div class="javaex-grid-6">
								<!--字典文本-->
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle required">字典文本</p></div>
									<div class="javaex-unit-right">
										<input type="text" class="javaex-text" placeholder="请输入字典文本。示例：男" name="dictText" data-type="required" value="" autocomplete="off"/>
									</div>
								</div>
							</div>
						</div>

						<!--显示顺序-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">显示顺序</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" placeholder="请输入显示顺序" name="sort" data-type="positive_int" error-msg="只能填写正整数" value="" autocomplete="off"/>
							</div>
						</div>

						<!--是否默认值-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">是否默认</p></div>
							<div class="javaex-unit-right">
								<ul class="javaex-equal-12 clear">
									<li><input type="radio" fill name="isDefault" value="1" checked/>是</li>
									<li><input type="radio" fill name="isDefault" value="0" />否</li>
								</ul>
							</div>
						</div>

						<!--字典状态-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">字典状态</p></div>
							<div class="javaex-unit-right">
								<ul class="javaex-equal-12 clear">
									<li><input type="radio" fill name="isDeleted" value="0" checked/>启用</li>
									<li><input type="radio" fill name="isDeleted" value="1" />停用</li>
								</ul>
							</div>
						</div>

						<!--备注-->
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">备注</p></div>
							<div class="javaex-unit-right">
								<textarea class="javaex-desc" name="remark" placeholder="请输入备注"></textarea>
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
		url : "${contextPath}/sys/dict/data/get/" + id,
		type : "GET",
		dataType : "json",
		success : function(rtn) {
			if (rtn.code==0) {
				let data = rtn.data.item;
				
				javaex.form({
					id : "form",
					formData : data,
					callback: function() {
						$("#dictCode").attr("readonly", true);
						$("#dictCode").addClass("readonly");
						
						$("#dictCodeComment").attr("readonly", true);
						$("#dictCodeComment").addClass("readonly");
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
		url : "${contextPath}/sys/dict/data/update/" + id,
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