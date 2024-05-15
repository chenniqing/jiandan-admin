package cn.javaex.generator.model.dto;

import javax.validation.constraints.NotBlank;

/**
 * 代码生成-数据库表字段 上移、下移
 * 
 * @author 陈霓清
 */
public class SysGenTableColumnMoveDTO {
	@NotBlank(message = "up=上移；down=下移。该字段不能为空")
	private String action;		// up=上移；down=下移

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}
