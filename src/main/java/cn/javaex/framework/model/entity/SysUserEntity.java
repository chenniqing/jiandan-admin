package cn.javaex.framework.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import cn.javaex.mybatisjj.basic.annotation.ExcludeTableColumn;
import cn.javaex.mybatisjj.basic.annotation.TableName;

/**
 * 用户表
 * 
 * @author 陈霓清
 */
@TableName("sys_user")
public class SysUserEntity {
	private String id;                      // 主键
	private LocalDateTime createTime;       // 创建时间
	private String createBy;                // 创建人ID
	private LocalDateTime updateTime;       // 更新时间
	private String updateBy;                // 更新人ID
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
	private LocalDateTime lastLoginDate;    // 最后登录时间
	private String remark;                  // 备注

	@ExcludeTableColumn
	private List<SysRoleEntity> roleList;
	
	public List<SysRoleEntity> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<SysRoleEntity> roleList) {
		this.roleList = roleList;
	}
	/**
	 * 得到主键
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置主键
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 得到创建时间
	 * @return
	 */
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	/**
	 * 得到创建人ID
	 * @return
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置创建人ID
	 * @param createBy
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 得到更新时间
	 * @return
	 */
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 得到更新人ID
	 * @return
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置更新人ID
	 * @param updateBy
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

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
	 * 得到最后登录时间
	 * @return
	 */
	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}
	/**
	 * 设置最后登录时间
	 * @param lastLoginDate
	 */
	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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

}
