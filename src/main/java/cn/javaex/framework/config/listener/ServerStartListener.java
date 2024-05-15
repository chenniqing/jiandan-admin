package cn.javaex.framework.config.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Value;

import cn.javaex.framework.basic.constant.SysConstant;

/**
 * 监听器
 * 
 * @author 陈霓清
 */
@WebListener
public class ServerStartListener implements ServletContextListener {

	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	/**
	 * web应用对象初始化的时候会被监听到
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// 设置系统名称
		ServletContext application = sce.getServletContext();
		application.setAttribute("system_name", SysConstant.SYSTEM_NAME);
		
		// 项目路径前缀
		application.setAttribute("contextPath", contextPath);
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
