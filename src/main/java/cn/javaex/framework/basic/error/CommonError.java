package cn.javaex.framework.basic.error;

/**
 * 枚举类型错误的接口
 * 
 * @author 陈霓清
 */
public interface CommonError {
	/**
	 * 获取错误码
	 * @return
	 */
	int getCode();
	
	/**
	 * 获取错误信息
	 * @return
	 */
	String getMessage();
}
