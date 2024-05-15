package cn.javaex.generator.model.vo;

import java.time.LocalDateTime;

/**
 * 代码生成-数据库表字段
 * 
 * @author 陈霓清
 */
public class SysGenTableColumnVO {
	private String id;		// 主键
	private LocalDateTime createTime;		// 创建时间
	private String createBy;		// 创建人ID
	private LocalDateTime updateTime;		// 更新时间
	private String updateBy;		// 更新人ID
	private String tableId;		// 表ID
	private String columnName;		// 字段名
	private String columnComment;		// 字段描述
	private String columnType;		// 字段类型
	private Integer length;		// 字段长度
	private Integer point;		// 小数点
	private Integer isPrimaryKey;		// 该字段是否是主键
	private Integer isNotNull;		// 是否不为NULL
	private String defaultValue;		// 字段默认值
	private String remark;		// 备注
	private String javaType;		// java类型
	private Integer isQuery;		// 是否查询字段
	private Integer isUnique;		// 是否唯一不重复的
	private Integer sort;		// 显示排序
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
	 * 得到表ID
	 * @return
	 */
	public String getTableId() {
		return tableId;
	}
	/**
	 * 设置表ID
	 * @param tableId
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 * 得到字段名
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * 设置字段名
	 * @param columnName
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * 得到表中文名
	 * @return
	 */
	public String getColumnComment() {
		return columnComment;
	}
	/**
	 * 设置表中文名
	 * @param columnComment
	 */
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	/**
	 * 得到字段类型
	 * @return
	 */
	public String getColumnType() {
		return columnType;
	}
	/**
	 * 设置字段类型
	 * @param columnType
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	/**
	 * 得到字段长度
	 * @return
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * 设置字段长度
	 * @param length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * 得到小数点
	 * @return
	 */
	public Integer getPoint() {
		return point;
	}
	/**
	 * 设置小数点
	 * @param point
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}

	/**
	 * 得到该字段是否是主键
	 * @return
	 */
	public Integer getIsPrimaryKey() {
		return isPrimaryKey;
	}
	/**
	 * 设置该字段是否是主键
	 * @param isPrimaryKey
	 */
	public void setIsPrimaryKey(Integer isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	/**
	 * 得到是否不为NULL
	 * @return
	 */
	public Integer getIsNotNull() {
		return isNotNull;
	}
	/**
	 * 设置是否不为NULL
	 * @param isNotNull
	 */
	public void setIsNotNull(Integer isNotNull) {
		this.isNotNull = isNotNull;
	}

	/**
	 * 得到字段默认值
	 * @return
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	/**
	 * 设置字段默认值
	 * @param defaultValue
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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
	 * 得到java类型
	 * @return
	 */
	public String getJavaType() {
		return javaType;
	}
	/**
	 * 设置java类型
	 * @param javaType
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	/**
	 * 得到是否查询字段
	 * @return
	 */
	public Integer getIsQuery() {
		return isQuery;
	}
	/**
	 * 设置是否查询字段
	 * @param isQuery
	 */
	public void setIsQuery(Integer isQuery) {
		this.isQuery = isQuery;
	}

	/**
	 * 得到是否唯一不重复的
	 * @return
	 */
	public Integer getIsUnique() {
		return isUnique;
	}
	/**
	 * 设置是否唯一不重复的
	 * @param isUnique
	 */
	public void setIsUnique(Integer isUnique) {
		this.isUnique = isUnique;
	}

	/**
	 * 得到显示排序
	 * @return
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置显示排序
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
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
