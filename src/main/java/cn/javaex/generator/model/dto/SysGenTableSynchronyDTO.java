package cn.javaex.generator.model.dto;

import javax.validation.constraints.NotNull;

/**
 * 同步到数据库
 * 
 * @author 陈霓清
 */
public class SysGenTableSynchronyDTO {
	@NotNull(message = "至少选择一张表")
	private String[] ids;
	@NotNull(message = "同步类型不能为空")
	private Integer synchronyType;
	
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public Integer getSynchronyType() {
		return synchronyType;
	}
	public void setSynchronyType(Integer synchronyType) {
		this.synchronyType = synchronyType;
	}
	
}
