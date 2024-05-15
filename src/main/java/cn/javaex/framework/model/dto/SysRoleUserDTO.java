package cn.javaex.framework.model.dto;

/**
 * 角色用户关联
 * 
 * @author 陈霓清
 */
public class SysRoleUserDTO {
	private String userId;		// 用户id
	private String[] userIds;	// 用户id数组
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String[] getUserIds() {
		return userIds;
	}
	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}
	
}
