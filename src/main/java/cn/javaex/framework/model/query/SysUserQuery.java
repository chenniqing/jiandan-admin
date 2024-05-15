package cn.javaex.framework.model.query;

import java.util.List;

/**
 * 用户表
 * 
 * @author 陈霓清
 */
public class SysUserQuery extends Query {
	private String username;                // 用户名（登录账号）
	private String nickname;                // 用户昵称
	private String phone;                   // 手机号
	private String email;                   // 邮箱
	private Integer sex;                    // 性别（0=男；1=女；2=未知）
	private Integer status;                 // 用户状态（0=正常；1=停用）
	private String beginTime;               // 开始时间
	private String endTime;                 // 结束时间
	private String roleId;                  // 角色ID
	private String[] sorts;
	private String sortStr;
	private String departmentId;            // 部门ID
	private List<String> departmentIdList;  // 部门ID集合
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public List<String> getDepartmentIdList() {
		return departmentIdList;
	}
	public void setDepartmentIdList(List<String> departmentIdList) {
		this.departmentIdList = departmentIdList;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String[] getSorts() {
		return sorts;
	}
	public void setSorts(String[] sorts) {
		this.sorts = sorts;
	}
	public String getSortStr() {
		return sortStr;
	}
	public void setSortStr(String sortStr) {
		this.sortStr = sortStr;
	}
	
}
