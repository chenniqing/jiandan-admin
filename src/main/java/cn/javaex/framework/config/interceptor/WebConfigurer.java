package cn.javaex.framework.config.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置磁盘路径和虚拟路径的映射
 * 
 * @author 陈霓清
 */
@Component
public class WebConfigurer implements WebMvcConfigurer {
	
	@Value("${upload.path}")
	private String uploadPath;    // 文件上传目录名
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String projectPath = System.getProperty("user.dir");
		
		// 和页面有关的静态目录都放在项目的static目录下
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		// 文件上传到磁盘上的虚拟路径映射
		registry.addResourceHandler("/" + uploadPath + "/**").addResourceLocations("file:///" + projectPath + "/" + uploadPath + "/");
	}
	
	/**
	 * 默认首页（跳转到指定请求）
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/login");
	}
}
