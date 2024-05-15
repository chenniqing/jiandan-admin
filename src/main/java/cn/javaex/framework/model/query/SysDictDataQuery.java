package cn.javaex.framework.model.query;

/**
 * 数据字典表
 * 
 * @author 陈霓清
 */
public class SysDictDataQuery extends Query {
	private String dictCode;                // 字典编码
	private String dictCodeComment;         // 字典编码描述
	private String dictValue;               // 字典键值
	private String dictText;                // 字典文本
	private Integer isDeleted;              // 是否逻辑删除  0=不是；1=是

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
