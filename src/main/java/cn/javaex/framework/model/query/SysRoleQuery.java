package cn.javaex.framework.model.query;

/**
 * 角色表
 * 
 * @author 陈霓清
 */
public class SysRoleQuery extends Query {
	private String roleCode;         // 角色标识
	private String roleName;         // 角色名称
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
