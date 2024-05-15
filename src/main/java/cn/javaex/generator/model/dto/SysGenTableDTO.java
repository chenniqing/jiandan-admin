package cn.javaex.generator.model.dto;

import javax.validation.constraints.NotBlank;

/**
 * 代码生成-数据库表
 * 
 * @author 陈霓清
 */
public class SysGenTableDTO {
	@NotBlank(message = "表名不能为空")
	private String tableName;		// 表名
	private String tableComment;		// 表描述
	private String remark;		// 备注
	private Integer isDeleted;		// 是否逻辑删除（0=否；1=是）

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
