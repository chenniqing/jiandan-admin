package cn.javaex.generator.model.dto;

/**
 * 从数据库导出表
 * 
 * @author 陈霓清
 */
public class SysGenTableExportDTO {
	private String[] tableNames;

	public String[] getTableNames() {
		return tableNames;
	}

	public void setTableNames(String[] tableNames) {
		this.tableNames = tableNames;
	}

}
