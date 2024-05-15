package cn.javaex.generator.model.dto;

/**
 * 代码生成-数据库表
 * 
 * @author 陈霓清
 */
public class SysGenTableCodeDTO {
	private String[] tableIds;     // 表ID集合
	private String packageName;    // 包名
	private String businessName;   // 业务包名
	private String author;         // 创作人
	private String[] codeTypes;    // 生成的代码类型集合
	
	public String[] getTableIds() {
		return tableIds;
	}
	public void setTableIds(String[] tableIds) {
		this.tableIds = tableIds;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String[] getCodeTypes() {
		return codeTypes;
	}
	public void setCodeTypes(String[] codeTypes) {
		this.codeTypes = codeTypes;
	}
	
}
