package cn.javaex.framework.config.xss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter
public class XssRequestFilter implements Filter {

	/**
	 * 白名单
	 */
	public List<String> whiteList = new ArrayList<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		/**
		 * 示例
		 */
//		whiteList.add("/food/guide/add");
//		whiteList.add("/food/guide/update/.*");    // /food/guide/update/{id}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (isWhiteList(req)) {
			chain.doFilter(request, response);
			return;
		}
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}

	private boolean isWhiteList(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		for (String pattern : whiteList) {
			if (Pattern.matches(pattern, servletPath)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {

	}
}
