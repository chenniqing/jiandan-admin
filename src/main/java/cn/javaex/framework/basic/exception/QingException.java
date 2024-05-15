package cn.javaex.framework.basic.exception;

import cn.javaex.framework.basic.constant.SysConstant;
import cn.javaex.framework.basic.error.CommonError;

/**
 * 系统自定义异常处理类，针对预期的异常，需要在程序中抛出此类的异常
 * 
 * @author 陈霓清
 */
public class QingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;
	private String message;
	
	/**
	 * 抛出普通的自定义异常（返回的code为1）
	 * @param message
	 */
	public QingException(String message) {
		this.code = SysConstant.ERROR_CODE;
		this.message = message;
	}
	
	/**
	 * 抛出特殊的自定义异常（返回的code需要定义）
	 * @param message
	 */
	public QingException(CommonError error) {
		this.code = error.getCode();
		this.message = error.getMessage();
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}