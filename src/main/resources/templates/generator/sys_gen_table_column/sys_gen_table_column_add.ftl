<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新增 - ${system_name}</title>
<#include "/common/style.ftl">
</head>

<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="javaex-main">
					<form id="form">
						<input type="hidden" name="tableId" value="${tableId}"/>
						
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle required">字段名</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="columnName" value="" data-type="required" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">字段描述</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="columnComment" value="" autocomplete="off" />
							</div>
						</div>
						
						<div class="javaex-grid spacing-20">
							<div class="javaex-grid-4">
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle required">字段类型</p></div>
									<div class="javaex-unit-right">
										<select id="columnType" name="columnType" data-type="required">
											<option value="">请选择</option>
											<#list typeList as item>
												<option value="${item.value}">${item.text}</option>
											</#list>
										</select>
									</div>
								</div>
							</div>
							<div class="javaex-grid-4">
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle required">字段长度</p></div>
									<div class="javaex-unit-right">
										<input type="text" class="javaex-text" id="length" name="length" value="" data-type="nonnegative_int" error-msg="请填写正整数或0" autocomplete="off" />
									</div>
								</div>
							</div>
							<div class="javaex-grid-4">
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle">小数点</p></div>
									<div class="javaex-unit-right">
										<input type="text" class="javaex-text" id="point" name="point" value="" data-type="null|nonnegative_int" error-msg="有值时，请填写正整数" autocomplete="off" />
									</div>
								</div>
							</div>
						</div>
						<div class="javaex-grid spacing-20">
							<div class="javaex-grid-4">
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle">主键约束</p></div>
									<div class="javaex-unit-right">
										<input type="checkbox" fill name="isPrimaryKey" value="1"/>设为主键
									</div>
								</div>
							</div>
							<div class="javaex-grid-4">
								<div class="javaex-unit clear">
									<div class="javaex-unit-left tl"><p class="subtitle">不许为空</p></div>
									<div class="javaex-unit-right">
										<input type="checkbox" fill name="isNotNull" value="1"/>不是null
									</div>
								</div>
							</div>
							<div class="javaex-grid-4">
								 
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">默认值</p></div>
							<div class="javaex-unit-right">
								<input type="text" class="javaex-text" name="defaultValue" value="" autocomplete="off" />
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">字段备注</p></div>
							<div class="javaex-unit-right">
								<textarea class="javaex-desc" name="remark"></textarea>
							</div>
						</div>
						<div class="javaex-unit clear">
							<div class="javaex-unit-left tl"><p class="subtitle">字段状态</p></div>
							<div class="javaex-unit-right">
								<ul class="javaex-equal-8 clear">
									<li><input type="radio" fill name="isDeleted" value="0" checked/>启用</li>
									<li><input type="radio" fill name="isDeleted" value="1" />废弃</li>
								</ul>
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
	/**
	 * 保存
	 */
	function save() {
		if (!javaexVerify()) {
			return;
		}
		
		$(':checkbox[name="isNotNull"]').attr("disabled", false);
		
		javaex.tip({
			content : "数据提交中，请稍候...",
			type : "submit"
		});
		
		$.ajax({
			url : "${contextPath}/sys/gen/table/column/add",
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
	
	javaex.select({
		id : "columnType",
		isInit : false,
		maxNum : 10,
		callback: function (rtn) {
			let selectValue = rtn.val;
			let length = 0;
			
			switch (selectValue) {
				case "bigint":
					length = 20;
					break;
				case "int":
					length = 11;
					break;
				case "mediumint":
					length = 8;
					break;
				case "smallint":
					length = 6;
					break;
				case "tinyint":
					length = 1;
					break;
				case "varchar":
					length = 50;
					break;
				case "char":
					length = 32;
					break;
				case "decimal":
					length = 10;
					break;
				case "double":
					length = 0;
					break;
				case "datetime":
					length = 0;
					break;
				case "date":
					length = 0;
					break;
				case "text":
					length = 0;
					break;
				default:
					break;
			}
			$("#length").val(length);
			$("#length").focus();
			
			// 是否禁用小数点
			if (selectValue=="decimal") {
				$("#point").val("2");
				$("#point").attr("disabled", false);
			} else {
				$("#point").val("");
				$("#point").attr("disabled", true);
			}
		}
	});
	
	$(':checkbox[name="isPrimaryKey"]').change(function() {
		setStatusByIsPrimaryKey();
	});
	function setStatusByIsPrimaryKey() {
		if ($(':checkbox[name="isPrimaryKey"]').is(":checked")) {
			$(':checkbox[name="isNotNull"]').attr("checked", true);
			$(':checkbox[name="isNotNull"]').attr("disabled", true);
			
			$("#defaultValue").attr("readonly", true);
			$("#defaultValue").addClass("readonly");
		} else {
			$(':checkbox[name="isNotNull"]').attr("disabled", false);
			
			$("#defaultValue").attr("readonly", false);
			$("#defaultValue").removeClass("readonly");
		}
	}
</script>
</html>