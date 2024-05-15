package cn.javaex.framework.basic.response;

import java.util.HashMap;
import java.util.Map;

import cn.javaex.framework.basic.constant.SysConstant;
import cn.javaex.framework.basic.error.CommonError;
import cn.javaex.framework.basic.error.SysError;

/**
 * 通用的返回json数据格式的类
 * 
 * @author 陈霓清
 */
public class Result {

	/**
	 * 状态码
	 *     0：成功
	 *    -1：未知异常（服务器故障或程序有bug）
	 *     1：普通自定义异常
	 *     其他：重要异常
	 */
	private int code;
	/**提示信息*/
	private String message;
	/**用户要返回给浏览器的数据*/
	private Map<String, Object> data = new HashMap<String, Object>();
	
	public static Result success() {
		Result result = new Result();
		result.setCode(SysError.SUCCESS.getCode());
		result.setMessage(SysError.SUCCESS.getMessage());
		return result;
	}
	
	public static Result success(String message) {
		Result result = new Result();
		result.setCode(SysError.SUCCESS.getCode());
		if (message==null || message.length()==0) {
			result.setMessage(SysError.SUCCESS.getMessage());
		}  else {
			result.setMessage(message);
		}
		return result;
	}
	
	public static Result error(String message) {
		Result result = new Result();
		result.setCode(SysConstant.ERROR_CODE);
		result.setMessage(message);
		return result;
	}
	
	public static Result error(CommonError error) {
		Result result = new Result();
		result.setCode(error.getCode());
		result.setMessage(error.getMessage());
		return result;
	}
	
	/**
	 * 链式操作返回信息
	 * @param key
	 * @param value
	 * @return
	 */
	public Result add(String key, Object value) {
		this.getData().put(key, value);
		return this;
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
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}