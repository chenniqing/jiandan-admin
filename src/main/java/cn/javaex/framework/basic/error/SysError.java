package cn.javaex.framework.basic.error;

/**
 * 系统错误配置
 * 
 * @author 陈霓清
 */
public enum SysError implements CommonError {
	/**操作成功*/
	SUCCESS(0, "操作成功"),
	/**系统繁忙，请稍后重试*/
	SYS_ERROR(-1, "系统繁忙，请稍后重试")
	;
	
	private final int code;
	private final String message;
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	SysError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
