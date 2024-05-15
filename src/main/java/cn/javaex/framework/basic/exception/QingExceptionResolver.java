package cn.javaex.framework.basic.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson2.JSON;

import cn.javaex.framework.basic.error.SysError;

/**
 * 全局异常拦截器
 *
 * @author 陈霓清
 */
@ControllerAdvice
public class QingExceptionResolver implements HandlerExceptionResolver {
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		// 1. 打印错误信息到控制台
		ex.printStackTrace();
		
		// 2. 定义一个错误信息
		int code = -1;
		String message = SysError.SYS_ERROR.getMessage();
		// 判断该错误是否是预期的错误
		if (ex instanceof QingException) {
			code = ((QingException)ex).getCode();
			message = ((QingException)ex).getMessage();
		} else if (ex instanceof DataIntegrityViolationException) {
			message = "违反数据完整性约束（例如，唯一性约束、非空约束、外键约束等）";
		}
		
		// 3. 判断请求类型
		HandlerMethod handMethod = (HandlerMethod)handler;
		RestController restController = handMethod.getMethod().getDeclaringClass().getAnnotation(RestController.class);
		ResponseBody responseBody = handMethod.getMethod().getAnnotation(ResponseBody.class);
		
		// 3.1 返回json错误数据
		if (restController!=null || responseBody!=null) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("code", code);
			responseMap.put("message", message);
			String json = JSON.toJSONString(responseMap);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			try {
				if (response.isCommitted() == false) {
					response.getWriter().write(json);
					response.getWriter().flush();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			// 返回一个空的ModelAndView表示已经手动生成响应
			return new ModelAndView();
		}
		
		// 4.  如果是跳转页面的请求，则跳转到错误页面
		// 页面转发（跳转至错误页面）
		ModelAndView modelAndView = new ModelAndView();
		// 将错误信息传到页面
		modelAndView.addObject("message", message);
		// 指向错误页面
		modelAndView.setViewName("error/error");
		
		return modelAndView;
	}
}