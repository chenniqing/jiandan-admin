package cn.javaex.generator.model.vo;

import java.time.LocalDateTime;

/**
 * 代码生成-数据库表
 * 
 * @author 陈霓清
 */
public class SysGenTableVO {
	private String id;		// 主键
	private LocalDateTime createTime;		// 创建时间
	private String createBy;		// 创建人ID
	private LocalDateTime updateTime;		// 更新时间
	private String updateBy;		// 更新人ID
	private String tableName;		// 表名
	private String tableComment;		// 表描述
	private String remark;		// 备注
	private Integer isDeleted;		// 是否逻辑删除（0=否；1=是）

	private String createUsername;
	private String updateUsername;
	
	public String getCreateUsername() {
		return createUsername;
	}
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	public String getUpdateUsername() {
		return updateUsername;
	}
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
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
	 * 得到表名
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * 设置表名
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 得到表中文名
	 * @return
	 */
	public String getTableComment() {
		return tableComment;
	}
	/**
	 * 设置表中文名
	 * @param tableComment
	 */
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
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

	/**
	 * 得到是否逻辑删除（0=否；1=是）
	 * @return
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置是否逻辑删除（0=否；1=是）
	 * @param isDeleted
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
