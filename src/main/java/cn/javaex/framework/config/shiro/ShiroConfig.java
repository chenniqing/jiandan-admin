package cn.javaex.framework.config.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置类
 * 
 * @author 陈霓清
 */
@Configuration
public class ShiroConfig {
	
	/**
	 * 自定义Realm
	 */
	@Bean
	public Realm realm() {
		return new Realm();
	}
	
	/**
	 * 缓存管理器
	 * @return
	 */
	@Bean
	public EhCacheManager cacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
		return cacheManager;
	}
	
	/**
	 * sessionDAO
	 * @return
	 */
	@Bean
	public MemorySessionDAO sessionDAO() {
		return new MemorySessionDAO();
	}
	
	/**
	 * sessionManager
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager sessionManager(@Qualifier("sessionDAO") MemorySessionDAO sessionDAO) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(sessionDAO);
		sessionManager.setSessionIdUrlRewritingEnabled(false);    // 去掉URL中的JSESSIONID
		return sessionManager;
	}
	
	/**
	 * securityManager安全管理器
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(
			@Qualifier("realm") Realm realm,
			@Qualifier("cacheManager") EhCacheManager cacheManager,
			@Qualifier("sessionManager") DefaultWebSessionManager sessionManager) {
		
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);                    // 自定义realm
		securityManager.setCacheManager(cacheManager);      // 缓存管理器
		securityManager.setSessionManager(sessionManager);  // session管理器
		return securityManager;
	}
	
	/**
	 * 过滤器配置
	 * @return
	 */
	@Bean
	public FilterChainDefinition filterChainDefinition() {
		return new FilterChainDefinition();
	}
	
	/**
	 * shiro的web过滤器
	 * @param securityManager
	 * @param filterChainDefinition
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager,
			@Qualifier("filterChainDefinition") FilterChainDefinition filterChainDefinition) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		
		// 设置securityManager安全管理器
		shiroFilter.setSecurityManager(securityManager);
		// 设置登录请求
		shiroFilter.setLoginUrl("/login");
		// 设置未授权请求
		shiroFilter.setUnauthorizedUrl("/403");
		// 设置过滤器
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinition.buildFilterChainDefinitionMap());
		
		// 覆写角色拦截过滤器，满足其中一个角色即通过验证
		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("roles", new RolesAuthorizationFilter());
		shiroFilter.setFilters(filters);
		
		return shiroFilter;
	}
	
}
