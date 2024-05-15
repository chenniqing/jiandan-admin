package cn.javaex.framework.config.mybatisjj;

import java.util.List;

/**
 * 数据权限封装类
 * 
 * @author 陈霓清
 */
public class DataPermissionFilter {
	private String deptAlias;                // 部门别名，例如： d.department_id
	private String userAlias;                // 用户别名，例如： u.user_id
	private List<String> departmentIdList;
	private String userId;
	private int dataScope;                   // 数据范围（1：全部数据权限 2：本部门及以下数据权限 3：本部门数据权限 4：仅自己数据权限）
	
	public String getDeptAlias() {
		return deptAlias;
	}
	public void setDeptAlias(String deptAlias) {
		this.deptAlias = deptAlias;
	}
	public String getUserAlias() {
		return userAlias;
	}
	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	public List<String> getDepartmentIdList() {
		return departmentIdList;
	}
	public void setDepartmentIdList(List<String> departmentIdList) {
		this.departmentIdList = departmentIdList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getDataScope() {
		return dataScope;
	}
	public void setDataScope(int dataScope) {
		this.dataScope = dataScope;
	}
	
}
