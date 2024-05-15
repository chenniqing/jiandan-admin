<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>未绑定角色用户列表 - ${system_name}</title>
<#include "/common/style.ftl">
</head>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-list-toolbar clear mt-10">
						<span class="fr javaex-search-group">
							<input type="text" class="javaex-text" id="keyword" placeholder="搜索用户账号 / 用户姓名" />
							<button id="search" class="javaex-btn blue" onclick="search()">查询</button>
						</span>
					</div>
					
					<div class="javaex-block">
						<table id="table" class="javaex-table td-c-1 vertical-line-white">
							<thead>
								<tr>
									<th class="javaex-table-num-col"></th>
									<th class="javaex-table-filter-col"><input type="checkbox" fill listen="listen-1"/> </th>
									<th>用户账号</th>
									<th>用户姓名</th>
								</tr>
							</thead>
							<tbody id="data">
								
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<div class="javaex-page-footer">
				<div class="javaex-page fl">
					<ul id="page" class="javaex-pagination"></ul>
				</div>
				
				<input type="button" id="save" onclick="save()" class="javaex-btn blue disabled" value="添加" disabled="disabled"/>
				<input type="button" onclick="closeDialog()" class="javaex-btn wathet" value="取消" />
			</div>
		</div>
	</div>
</body>
<script>
	var userIds = new Array();
	var roleId = "${roleId}";
	var param = {
		"keyword" : "",
		"pageNum" : 1,
		"pageSize" : 10
	};
	
	$(function() {
		getUnbindList();
	});
	
	function search() {
		param.keyword = $("#keyword").val();
		param.pageNum = 1;
		
		getUnbindList();
	}
	
	/**
	 * 加载列表数据
	 */
	function getUnbindList() {
		javaex.loading({
			mode : "manual",
			containerId : "table",
			type : "open"
		});
		
		$.ajax({
			url : "${contextPath}/sys/role-user/list/unbind/" + roleId,
			type : "GET",
			dataType : "json",
			data : param,
			success : function(rtn) {
				javaex.loading({
					mode : "manual",
					containerId : "table",
					type : "close"
				});
				
				if (rtn.code==0) {
					let pageInfo = rtn.data.pageInfo;
					let list = pageInfo.list;
					let html = '';
					
					if (list!=null && list.length>0) {
						$.each(list, function(i, data) {
							html += '<tr>';
							html += '	<td>'+(i+1)+'</td>';
							html += '	<td><input type="checkbox" fill name="userId" listen="listen-1-2" value="'+data.id+'"/> </td>';
							html += '	<td>'+data.username+'</td>';
							html += '	<td>'+data.nickname+'</td>';
							html += '</tr>';
						});
					} else {
						html = '<tr><td colspan="4" class="tc">暂无数据</td></tr>';
					}
					
					$("#data").empty();
					$("#data").append(html);
					javaex.render();
					
					let curPageNum = pageInfo.pageNum;
					let curPageSize = pageInfo.pageSize;
					let totalPages = pageInfo.pages;
					let totalNum = pageInfo.total;
					
					javaex.page({
						id : "page",
						pageNum : pageInfo.pageNum,        // 默认选中第几页
						pageSize : pageInfo.pageSize,      // 每页显示多少条
						totalPages : pageInfo.pages,       // 总页数
						totalNum : pageInfo.total,         // 总条数，不填时，不显示
						isShowSelect : true,               // 是否显示条数选择控件
						position : "left",
						callback : function(rtn) {
							param.pageNum = rtn.pageNum;
							param.pageSize = rtn.pageSize;
							
							getUnbindList();
						}
					});
				}
			}
		});
	}
	
	// 监听复选框选中事件
	javaex.listenCheckbox({
		callback : function() {
			userIds = [];
			
			$(':checkbox[name="userId"]:checked').each(function(i) {
				userIds.push($(this).val());
			});
			
			if (userIds.length==0) {
				$('#save').addClass("disabled");
				$('#save').attr("disabled", true);
			} else {
				$('#save').removeClass("disabled");
				$('#save').attr("disabled", false);
			}
		}
	});
	
	/**
	 * 保存
	 */
	function save() {
		javaex.tip({
			content : "数据提交中，请稍候...",
			type : "submit"
		});
		
		$.ajax({
			url : "${contextPath}/sys/role-user/add/user/" + roleId,
			type : "POST",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify({
				"userIds" : userIds
			}),
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
						let outerIframe = parent.document.getElementById("page");
						let outerDoc = outerIframe.contentDocument || outerIframe.contentWindow.document;
						let innerIframe = outerDoc.getElementsByTagName('iframe')[0]; // 如果它是唯一的iframe
						let innerDoc = innerIframe.contentDocument || innerIframe.contentWindow.document;
						innerIframe.contentWindow.searchBindUser();
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
		parent.javaex.close("addUser");
	}
</script>
</html>
