package cn.javaex.framework.config.druid;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;

@Configuration
public class DruidConfig {
	/**
	 * 将配置文件中spring.druid.*配置到DruidDataSource中
	 * @param statFilter
	 * @return
	 * @throws SQLException
	 */
	@ConfigurationProperties(prefix="spring.druid")
	@Bean(initMethod="init", destroyMethod="close")
	public DruidDataSource dataSource(Filter statFilter) throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(statFilter());
		dataSource.setProxyFilters(filters);
		return dataSource;
	}
	
	/**
	 * 配置慢连接过滤
	 * @return
	 */
	@Bean
	public Filter statFilter() {
		StatFilter filter = new StatFilter();
		filter.setSlowSqlMillis(5000);    // 慢SQL执行时间（单位：毫秒）
		filter.setLogSlowSql(true);       // 是否打印出慢日志
		filter.setMergeSql(true);         // 是否合并
		return filter;
	}
	
	/**
	 * 注册一个StatViewServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> DruidStatViewServle() {	
		// org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");
		
		// 添加初始化参数：initParams
		// 白名单：
//		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		
		// 登录查看信息的账号密码
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "123456");
		
		// 是否能够重置数据
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
}
