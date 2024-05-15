package cn.javaex.generator.model.query;

import cn.javaex.framework.model.query.Query;

/**
 * 代码生成-数据库表
 * 
 * @author 陈霓清
 */
public class SysGenTableQuery extends Query {
	private String tableName;
	private String tableComment;
	private String[] sorts;
	private String sortStr;
	
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

}
