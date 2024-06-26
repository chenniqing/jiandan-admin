package cn.javaex.framework.model.dto;

/**
 * 用户表修改密码
 * 
 * @author 陈霓清
 */
public class SysUserChangePasswordDTO {
	private String oldPassword;            // 旧密码
	private String newPassword;            // 新密码
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
