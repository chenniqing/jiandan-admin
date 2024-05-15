package cn.javaex.framework.basic.error;

/**
 * 角色相关错误
 * 
 * @author 陈霓清
 */
public enum RoleError implements CommonError {
	/**角色标识重复*/
	ROLE_1101(1101, "角色标识重复")
	;
	
	private final int code;
	private final String message;
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	RoleError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
