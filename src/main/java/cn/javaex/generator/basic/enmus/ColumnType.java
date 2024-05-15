package cn.javaex.generator.basic.enmus;

/**
 * 表字段类型
 * 
 * @author 陈霓清
 */
public enum ColumnType {
	BIGINT("bigint", "长整数（bigint）"),
	INT("int", "整数（int）"),
	MEDIUMINT("mediumint", "中等大小整数（mediumint）"),
	SMALLINT("smallint", "小整数（smallint）"),
	TINYINT("tinyint", "布尔型（tinyint）"),
	VARCHAR("varchar", "字符串（varchar）"),
	CHAR("char", "字符（char）"),
	DECIMAL("decimal", "BigDecimal（decimal）"),
	DOUBLE("double", "数值型（double）"),
	DATETIME("datetime", "日期时间（datetime）"),
	DATE("date", "日期（date）"),
	TEXT("text", "文本（text）"),
	TIMESTAMP("timestamp", "时间戳（timestamp）")
	;
	
	private String value;
	private String text;
	
	private ColumnType(String value, String text) {
		this.value = value;
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
