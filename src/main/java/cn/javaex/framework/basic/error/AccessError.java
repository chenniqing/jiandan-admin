package cn.javaex.framework.basic.error;

/**
 * 权限相关错误
 * 
 * @author 陈霓清
 */
public enum AccessError implements CommonError {
	/**未登录*/
	UN_LOGIN(401, "未登录"),
	/**权限不足*/
	NO_ACCESS(403, "权限不足")
	;
	
	private final int code;
	private final String message;
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	AccessError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
