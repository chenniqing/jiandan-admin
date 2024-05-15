package cn.javaex.framework.model.entity;

import cn.javaex.mybatisjj.basic.annotation.TableId;
import java.time.LocalDateTime;

import cn.javaex.mybatisjj.basic.annotation.TableName;

/**
 * 角色表
 * 
 * @author 陈霓清
 */
@TableName("sys_role")
public class SysRoleEntity {
	@TableId(value = "id")
	private String id;                      // 主键
	private LocalDateTime createTime;       // 创建时间
	private String createBy;                // 创建人ID
	private LocalDateTime updateTime;       // 更新时间
	private String updateBy;                // 更新人ID
	private String roleName;                // 角色名称
	private String roleCode;                // 角色标识
	private Integer dataScope;              // 数据范围（1：全部数据权限；2：本部门及以下数据权限；3：本部门数据权限；4：仅自己数据权限）
	private Integer sort;                   // 显示顺序

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
	 * 得到角色名称
	 * @return
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 设置角色名称
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 得到角色标识
	 * @return
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * 设置角色标识
	 * @param roleCode
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * 得到数据范围（1：全部数据权限；2：本部门及以下数据权限；3：本部门数据权限；4：仅自己数据权限）
	 * @return
	 */
	public Integer getDataScope() {
		return dataScope;
	}
	/**
	 * 设置数据范围（1：全部数据权限；2：本部门及以下数据权限；3：本部门数据权限；4：仅自己数据权限）
	 * @param dataScope
	 */
	public void setDataScope(Integer dataScope) {
		this.dataScope = dataScope;
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

}
