<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>用户列表 - ${system_name}</title>
<#include "/common/style.ftl">
</head>
<style>
span.javaex-tree-icon {
    display: inline-block;
    vertical-align: top;
    width: 20px;
    line-height: 34px;
    font-size: 16px;
    text-align: center;
    cursor: pointer;
}
.javaex-tree li a {
    display: inline-block;
    cursor: pointer;
    text-decoration: none;
    vertical-align: initial;
    font-size: 18px;
    height: 34px;
    line-height: 34px;
    margin-left: 4px;
}
.javaex-tree span.icon-caret-right, .javaex-tree span.icon-caret-down {
    font-size: 22px;
}
</style>
<body>
	<div class="javaex-page-wrap">
		<div class="javaex-page-content">
			<div class="javaex-page-header">
				<div class="javaex-breadcrumb">
					<span>系统管理</span>
					<span class="divider">/</span>
					<span class="active">用户管理</span>
				</div>
			</div>
			
			<div class="javaex-page-body">
				<div class="content">
					<div class="javaex-grid spacing-20">
						<div id="departmentList" class="javaex-grid-2">
							<div id="tree" class="javaex-tree"></div>
						</div>
						<div class="javaex-grid-10">
							<div class="javaex-search-wrapper">
								<div class="javaex-search-line">
									<div class="javaex-search-control">
										登录账号：<input type="text" class="javaex-text" id="username" placeholder="请输入登录账号" />
									</div>
									<div class="javaex-search-control">
										昵称：<input type="text" class="javaex-text" id="nickname" placeholder="请输入用户昵称" />
									</div>
									<div class="javaex-search-control">
										手机号：<input type="text" class="javaex-text" id="phone" placeholder="请输入手机号" />
									</div>
									<div class="javaex-search-control">
										邮箱：<input type="text" class="javaex-text" id="email" placeholder="请输入邮箱" />
									</div>
									<!-- 确认查询按钮 -->
									<div class="javaex-search-control javaex-search-submit">
										<button class="javaex-btn red" onclick="reset()">重置</button>
										<button class="javaex-btn primary" onclick="search()">确认查询</button>
									</div>
								</div>
								<div class="javaex-search-line">
									<div class="javaex-search-control">
										创建时间：<input type="text" id="beginTime" class="javaex-date" style="width: 140px;" value="" readonly/>
										 ~ 
										<input type="text" id="endTime" class="javaex-date" style="width: 140px;" value="" readonly/>
									</div>
									<div class="javaex-search-control">
										用户性别：<select id="sex">
											<option value="">所有</option>
											<option value="0">男</option>
											<option value="1">女</option>
											<option value="2">未知</option>
										</select>
									</div>
									<div class="javaex-search-control">
										用户状态：<select id="status">
											<option value="">所有</option>
											<option value="0">正常</option>
											<option value="1">停用</option>
										</select>
									</div>
								</div>
							</div>
							
							<div class="javaex-list-toolbar clear">
								<span class="fl">
									<button class="javaex-btn blue" onclick="add()">新增</button>
								</span>
							</div>
							
							<div class="javaex-block">
								<table id="table" class="javaex-table td-c-1 vertical-line-white">
									<thead>
										<tr>
											<th class="javaex-table-num-col"></th>
											<th>登录账号</th>
											<th>用户昵称</th>
											<th>所属部门</th>
											<th>手机号</th>
											<th>创建时间</th>
											<th>用户角色</th>
											<th>状态</th>
											<th style="width:80px;">操作</th>
										</tr>
									</thead>
									<tbody id="data">
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			
			<div class="javaex-page-footer">
				<div class="javaex-page">
					<ul id="page" class="javaex-pagination"></ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
var param = {
	"departmentId" : "",
	"beginTime" : "",
	"endTime" : "",
	"username" : "",
	"nickname" : "",
	"phone" : "",
	"email" : "",
	"sex" : "",
	"status" : "",
	"pageNum" : 1,
	"pageSize" : 10
};

javaex.date({
	id : "beginTime",		// 承载日期组件的id
	monthNum : 1,		// 1代表选择单日日期
	time : "",	// 选择的日期
	callback : function(rtn) {
		
	}
});
javaex.date({
	id : "endTime",		// 承载日期组件的id
	monthNum : 1,		// 1代表选择单日日期
	time : "",	// 选择的日期
	callback : function(rtn) {
		
	}
});
javaex.select({
	id : "sex"
});
javaex.select({
	id : "status"
});

$(function() {
	getDepartmentList();
	getUserList();
});



/**
 * 加载列表数据
 */
function getDepartmentList() {
	javaex.loading({
		mode : "manual",
		containerId : "departmentList",
		type : "open"
	});
	
	$.ajax({
		url : "${contextPath}/sys/department/list-all",
		type : "GET",
		dataType : "json",
		success : function(rtn) {
			javaex.loading({
				mode : "manual",
				containerId : "departmentList",
				type : "close"
			});
			
			if (rtn.code==0) {
				let list = rtn.data.list;
				
				let newList = javaex.deepReplaceKeyName(list, ['departmentName'], ['name']);
				
				javaex.tree({
					id : "tree",
					dataList : newList,
					type : 1,				// 数据结构类型（1或2）
					isShowAllCheck : false,	// 是否显示全选
					checkbox : false,		// 是否显示复选框
					aCallback : function(rtn) {
						param.departmentId = rtn.id;
						param.pageNum = 1;
						
						getUserList();
					}
				});
			}
		}
	});
}

//搜索
function search() {
	param.beginTime = $("#beginTime").val();
	param.endTime = $("#endTime").val();
	param.username = $("#username").val();
	param.nickname = $("#nickname").val();
	param.phone = $("#phone").val();
	param.email = $("#email").val();
	param.sex = $("#sex").val();
	param.status = $("#status").val();
	param.pageNum = 1;
	
	delete param['sorts'];
	// 将所有列的排序标识初始化
	javaex.initTable("table");
	
	getUserList();
}
// 重置
function reset() {
	$("#beginTime").val('');
	$("#endTime").val('');
	$("#username").val('');
	$("#nickname").val('');
	$("#phone").val('');
	$("#email").val('');
	$("#sex").val('');
	$("#status").val('');
	javaex.select({
		id : "sex"
	});
	javaex.select({
		id : "status"
	});
	
	search();
}

/**
 * 加载列表数据
 */
function getUserList() {
	javaex.loading({
		mode : "manual",
		containerId : "table",
		type : "open"
	});
	
	$.ajax({
		url : "${contextPath}/sys/user/list",
		type : "GET",
		dataType : "json",
		traditional : true,
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
						html += '	<td>'+data.username+'</td>';
						html += '	<td>'+data.nickname+'</td>';
						html += '	<td>'+javaex.ifnull(data.departmentName)+'</td>';
						html += '	<td>'+javaex.ifnull(data.phone)+'</td>';
						html += '	<td>'+javaex.dateFormat(data.createTime, "yyyy-MM-dd HH:mm:ss")+'</td>';
						html += '	<td>'+data.roleNameList+'</td>';
						if (data.status==0) {
							html += '<td><font class="green">正常</font></td>';
						} else {
							html += '<td><font class="red">禁止登录</font></td>';
						}
						html += '	<td>';
						html += '		<a href="javascript:;" class="blue" onclick="edit(\''+data.id+'\')">编辑</a>';
						html += '	</td>';
						html += '</tr>';
					});
				} else {
					html = '<tr><td colspan="9" class="tc">暂无数据</td></tr>';
				}
				
				$("#data").html(html);
				
				javaex.table({
					id : "table",
					isClose : true,
					sort : {
						"2" : "username",
						"6" : "create_time",
						"8" : "status"
					},
					sortCallback : function(rtn) {
						param.sorts = rtn.sortArr;
						param.pageNum = 1;
						
						getUserList();
					}
				});
				
				javaex.page({
					id : "page",
					pageNum : pageInfo.pageNum,        // 默认选中第几页
					pageSize : pageInfo.pageSize,      // 每页显示多少条
					totalPages : pageInfo.pages,       // 总页数
					totalNum : pageInfo.total,         // 总条数，不填时，不显示
					isShowSelect : true,   // 是否显示条数选择控件
					position : "left",
					callback : function(rtn) {
						param.pageNum = rtn.pageNum;
						param.pageSize = rtn.pageSize;
						
						getUserList();
					}
				});
			}
		}
	});
}

/**
 * 添加
 */
function add() {
	top.javaex.dialog({
		type : "dialog",
		title : "添加",
		url : "${contextPath}/sys/user/page/add",
		width : "1000",
		height : "618"
	});
}

/**
 * 编辑
 */
function edit(id) {
	top.javaex.dialog({
		type : "dialog",
		title : "编辑",
		url : "${contextPath}/sys/user/page/edit?id="+id,
		width : "1000",
		height : "618"
	});
}
</script>
</html>
