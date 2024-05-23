package cn.javaex.framework.config.shiro;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson2.JSON;

import cn.javaex.framework.basic.error.AccessError;

/**
 * 自定义未授权请求
 * 
 * @author 陈霓清
 */
@Controller
public class Unauthorize {

	/**
	 * 判断是否是ajax请求
	 * @param request
	 * @return
	 */
	boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}
	
	/**
	 * 没有权限
	 * @param map
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/403")
	public String error403(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (isAjax(request)) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("code", AccessError.NO_ACCESS.getCode());
			responseMap.put("message", AccessError.NO_ACCESS.getMessage());
			String json = JSON.toJSONString(responseMap);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			if (response.isCommitted() == false) {
				response.getWriter().write(json);
				response.getWriter().flush();
			}
			return null;
		}
		
		return "error/403";
	}

	@RequestMapping("/404")
	public String error404(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "error/404";
	}
	
}
