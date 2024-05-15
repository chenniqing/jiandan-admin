package cn.javaex.framework.model.dto;

/**
 * 用户表
 * 
 * @author 陈霓清
 */
public class SysUserDTO {
	private String username;                // 用户名（登录账号）
	private String password;                // 密码
	private String nickname;                // 用户昵称
	private String phone;                   // 手机号
	private String email;                   // 邮箱
	private Integer sex;                    // 性别（0=男；1=女；2=未知）
	private String avatar;                  // 头像
	private String departmentId;            // 部门ID
	private Integer status;                 // 用户状态（0=正常；1=停用）
	private String lastLoginIp;             // 最后登录IP
	private String remark;                  // 备注
	private String[] roleIds;	// 角色ID数组


	/**
	 * 得到用户名（登录账号）
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置用户名（登录账号）
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 得到密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 得到用户昵称
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置用户昵称
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 得到手机号
	 * @return
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置手机号
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 得到邮箱
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置邮箱
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 得到性别（0=男；1=女；2=未知）
	 * @return
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置性别（0=男；1=女；2=未知）
	 * @param sex
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 得到头像
	 * @return
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * 设置头像
	 * @param avatar
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 得到部门ID
	 * @return
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * 设置部门ID
	 * @param departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 得到用户状态（0=正常；1=停用）
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置用户状态（0=正常；1=停用）
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 得到最后登录IP
	 * @return
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	/**
	 * 设置最后登录IP
	 * @param lastLoginIp
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * 得到备注
	 * @return
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

}
