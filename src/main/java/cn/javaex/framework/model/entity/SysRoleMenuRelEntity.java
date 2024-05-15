package cn.javaex.framework.model.entity;

import cn.javaex.mybatisjj.basic.annotation.TableName;

/**
 * 角色菜单关联表
 * 
 * @author 陈霓清
 */
@TableName("sys_role_menu_rel")
public class SysRoleMenuRelEntity {
	private String roleId;		// 角色id
	private String menuId;		// 菜单id

	/**
	 * 得到角色id
	 * @return
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * 设置角色id
	 * @param roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 得到菜单id
	 * @return
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * 设置菜单id
	 * @param menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
