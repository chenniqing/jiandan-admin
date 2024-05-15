package cn.javaex.framework.model.query;

/**
 * 部门表
 * 
 * @author 陈霓清
 */
public class SysDepartmentQuery extends Query {
	private String departmentName;          // 部门名称
	private Integer isDeleted;              // 是否逻辑删除  0=正常；1=停用

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
