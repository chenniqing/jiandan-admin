package cn.javaex.framework.model.vo;

import java.time.LocalDateTime;

/**
 * 数据字典表
 * 
 * @author 陈霓清
 */
public class SysDictDataVO {
	private String id;                      // 主键
	private LocalDateTime createTime;       // 创建时间
	private String createBy;                // 创建人ID
	private LocalDateTime updateTime;       // 更新时间
	private String updateBy;                // 更新人ID
	private String dictCode;                // 字典编码
	private String dictCodeComment;         // 字典编码描述
	private String dictValue;               // 字典键值
	private String dictText;                // 字典文本
	private Integer sort;                   // 显示顺序
	private Integer isDefault;              // 是否默认值  0=不是；1=是
	private String remark;                  // 备注
	private Integer isDeleted;              // 是否逻辑删除  0=不是；1=是

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
	 * 得到字典编码
	 * @return
	 */
	public String getDictCode() {
		return dictCode;
	}
	/**
	 * 设置字典编码
	 * @param dictCode
	 */
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	/**
	 * 得到字典编码描述
	 * @return
	 */
	public String getDictCodeComment() {
		return dictCodeComment;
	}
	/**
	 * 设置字典编码描述
	 * @param dictCodeComment
	 */
	public void setDictCodeComment(String dictCodeComment) {
		this.dictCodeComment = dictCodeComment;
	}
	/**
	 * 得到字典键值
	 * @return
	 */
	public String getDictValue() {
		return dictValue;
	}
	/**
	 * 设置字典键值
	 * @param dictValue
	 */
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	/**
	 * 得到字典文本
	 * @return
	 */
	public String getDictText() {
		return dictText;
	}
	/**
	 * 设置字典文本
	 * @param dictText
	 */
	public void setDictText(String dictText) {
		this.dictText = dictText;
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
	 * 得到是否默认值
	 * @return
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
	/**
	 * 设置是否默认值
	 * @param isDefault
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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
