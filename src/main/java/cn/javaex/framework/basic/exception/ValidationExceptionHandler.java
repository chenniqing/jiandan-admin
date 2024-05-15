package cn.javaex.framework.basic.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 传参校验全局异常处理器
 * 
 * @author 陈霓清
 */
@ControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();

		StringBuilder errorMessage = new StringBuilder();
		bindingResult.getFieldErrors().forEach(error -> {
			errorMessage.append(error.getDefaultMessage()).append("。");
		});

		Map<String, Object> errMap = new HashMap<String, Object>();
		errMap.put("code", 400);
		errMap.put("message", errorMessage.toString());
		errMap.put("data", null);
		
		return ResponseEntity.badRequest().body(errMap);
	}

}
