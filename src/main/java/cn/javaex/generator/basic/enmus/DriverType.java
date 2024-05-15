package cn.javaex.generator.basic.enmus;

/**
 * 数据库驱动
 * 
 * @author 陈霓清
 */
public enum DriverType {
	MYSQL("mysql", "com.mysql.cj.jdbc.Driver")
	;
	
	private String key;
	private String value;
	
	private DriverType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
