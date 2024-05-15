package cn.javaex.framework.model.entity;

import cn.javaex.mybatisjj.basic.annotation.TableName;

/**
 * 角色用户关联表
 * 
 * @author 陈霓清
 */
@TableName("sys_role_user_rel")
public class SysRoleUserRelEntity {
	private String roleId;		// 角色id
	private String userId;		// 用户id

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
	 * 得到用户id
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置用户id
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
