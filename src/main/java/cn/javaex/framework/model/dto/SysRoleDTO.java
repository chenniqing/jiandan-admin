package cn.javaex.framework.model.dto;

/**
 * 角色表
 * 
 * @author 陈霓清
 */
public class SysRoleDTO {
	private String roleName;                // 角色名称
	private String roleCode;                // 角色标识
	private Integer dataScope;              // 数据范围（1：全部数据权限；2：本部门及以下数据权限；3：本部门数据权限；4：仅自己数据权限）
	private Integer sort;                   // 显示顺序

	/**
	 * 得到角色名称
	 * @return
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 设置角色名称
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 得到角色标识
	 * @return
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * 设置角色标识
	 * @param roleCode
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * 得到数据范围（1：全部数据权限；2：本部门及以下数据权限；3：本部门数据权限；4：仅自己数据权限）
	 * @return
	 */
	public Integer getDataScope() {
		return dataScope;
	}
	/**
	 * 设置数据范围（1：全部数据权限；2：本部门及以下数据权限；3：本部门数据权限；4：仅自己数据权限）
	 * @param dataScope
	 */
	public void setDataScope(Integer dataScope) {
		this.dataScope = dataScope;
	}

	/**
	 * 得到显示顺序
	 * @return
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置显示顺序
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
