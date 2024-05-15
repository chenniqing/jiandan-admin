package cn.javaex.framework.config.xss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
//		whiteList.add("/sys/login");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (isWhiteList(req, resp)) {
			chain.doFilter(request, response);
			return;
		}
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}

	private boolean isWhiteList(HttpServletRequest request, HttpServletResponse response) {
		for (int i = 0; i < whiteList.size(); i++) {
			String servletPath = request.getServletPath();
			if (whiteList.get(i).equals(servletPath)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {

	}

}
