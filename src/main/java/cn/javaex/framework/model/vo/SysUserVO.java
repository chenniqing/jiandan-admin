package cn.javaex.framework.model.vo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户表
 * 
 * @author 陈霓清
 */
public class SysUserVO {
	private String id;                      // 主键
	private LocalDateTime createTime;       // 创建时间
	private String createBy;                // 创建人ID
	private LocalDateTime updateTime;       // 更新时间
	private String updateBy;                // 更新人ID
	private String username;                // 用户名（登录账号）
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
	
	private String departmentName;          // 部门名称
	private List<String> roleNameList;      // 用户具有的角色名称集合
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<String> getRoleNameList() {
		return roleNameList;
	}
	public void setRoleNameList(List<String> roleNameList) {
		this.roleNameList = roleNameList;
	}
	
}
