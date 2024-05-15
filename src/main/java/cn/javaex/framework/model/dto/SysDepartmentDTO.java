package cn.javaex.framework.model.dto;

/**
 * 部门表
 * 
 * @author 陈霓清
 */
public class SysDepartmentDTO {
	private String parentId;                // 父部门id  为0时，表示顶级部门
	private String departmentName;          // 部门名称
	private String departmentLeader;        // 部门负责人
	private String departmentPhone;         // 部门联系电话
	private String departmentEmail;         // 部门邮箱
	private Integer sort;                   // 显示顺序
	private Integer isDeleted;              // 是否逻辑删除  0=正常；1=停用

	/**
	 * 得到父部门id
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置父部门id
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 得到部门名称
	 * @return
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * 设置部门名称
	 * @param departmentName
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * 得到部门负责人
	 * @return
	 */
	public String getDepartmentLeader() {
		return departmentLeader;
	}
	/**
	 * 设置部门负责人
	 * @param departmentLeader
	 */
	public void setDepartmentLeader(String departmentLeader) {
		this.departmentLeader = departmentLeader;
	}

	/**
	 * 得到部门联系电话
	 * @return
	 */
	public String getDepartmentPhone() {
		return departmentPhone;
	}
	/**
	 * 设置部门联系电话
	 * @param departmentPhone
	 */
	public void setDepartmentPhone(String departmentPhone) {
		this.departmentPhone = departmentPhone;
	}

	/**
	 * 得到部门邮箱
	 * @return
	 */
	public String getDepartmentEmail() {
		return departmentEmail;
	}
	/**
	 * 设置部门邮箱
	 * @param departmentEmail
	 */
	public void setDepartmentEmail(String departmentEmail) {
		this.departmentEmail = departmentEmail;
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

	/**
	 * 得到是否逻辑删除
	 * @return
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置是否逻辑删除
	 * @param isDeleted
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}


}
