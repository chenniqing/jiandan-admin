package cn.javaex.framework.config.shiro;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import cn.javaex.framework.dao.sys_menu.ISysMenuDAO;
import cn.javaex.framework.dao.sys_role.ISysRoleDAO;
import cn.javaex.framework.util.SpringContextUtils;

/**
 * shiro过滤器配置
 * 
 * @author 陈霓清
 */
public class FilterChainDefinition {
	
	@Autowired
	private EhCacheManager cacheManager;
	@Autowired
	private ISysMenuDAO iSysMenuDAO;
	@Autowired
	private ISysRoleDAO iSysRoleDAO;
	
	/**
	 * 自定义 FilterChainDefinition
	 * @return
	 */
	public Map<String, String> buildFilterChainDefinitionMap() {
		/**
		 * shiro内置过滤器，可以实现权限相关的拦截器
		 * 
		 * 常用的过滤器：
		 *    anon: 无需认证（登录）可以访问
		 *    authc: 必须认证才可以访问
		 *    user: 如果使用rememberMe功能可以直接访问
		 *    perms: 该资源必须得到资源权限才可以访问
		 *    roles: 该资源必须得到角色权限才可以访问
		 */
		Map<String, String> filterMap = new LinkedHashMap<>();
		filterMap.put("/static/**", "anon");
		filterMap.put("/assets/**", "anon");
		filterMap.put("/upload/**", "anon");
		filterMap.put("/error", "anon");
		filterMap.put("/logout", "anon");
		filterMap.put("/login/**", "anon");
		
		// 查询菜单需要的角色
		Set<String> allMenuUrlSet = iSysMenuDAO.selectMenuUrlList();
		for (String menuUrl : allMenuUrlSet) {
			List<String> roleList = iSysRoleDAO.selectRoleCodeListByMenuUrl(menuUrl);
			if (roleList!=null && roleList.isEmpty()==false) {
				String[] arr = roleList.toArray(new String[roleList.size()]);
				String roles = "roles[" + Arrays.toString(arr).replace("[", "\"").replace("]", "\"") + "]";
				filterMap.put(menuUrl, roles);
			}
		}
		
		// 查询按钮需要的权限
		Set<String> permCodes = iSysMenuDAO.selectPermCodeList();
		if (permCodes!=null && permCodes.isEmpty()==false) {
			for (String permCode : permCodes) {
				String perms = "perms[" + permCode + "]";
				filterMap.put(permCode + "/**", perms);
			}
		}
		
		filterMap.put("/**", "authc");
		
		return filterMap;
	}
	
	/**
	 * 更新权限
	 */
	public void updatePermission() {
		try {
			ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextUtils.getBean(ShiroFilterFactoryBean.class);
			
			// 1. 更新权限设定
			AbstractShiroFilter shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
			
			// 获取过滤管理器
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
			DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
			// 清空初始权限配置
			manager.getFilterChains().clear();
			// 重新获取资源
			Map<String, String> chains = buildFilterChainDefinitionMap();
			for (Map.Entry<String, String> entry : chains.entrySet()) {
				String url = entry.getKey();
				String chainDefinition = entry.getValue();
				manager.createChain(url, chainDefinition);
			}
			
			// 2. 清除用户授权缓存
			this.clearAuthorizationInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 清除所有用户的授权缓存
	 */
	public void clearAuthorizationInfo() {
		cacheManager.getCache("cn.javaex.framework.config.shiro.Realm.authorizationCache").clear();
	}
	
}
