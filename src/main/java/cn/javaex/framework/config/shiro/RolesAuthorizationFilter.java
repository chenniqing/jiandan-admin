package cn.javaex.framework.config.shiro;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 覆写角色拦截过滤器，满足其中一个角色即通过验证
 * 
 * @author 陈霓清
 */
public class RolesAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;
		
		// 未指定角色，因此没有要检查的内容-允许访问
		if (rolesArray==null || rolesArray.length==0) {
			return true;
		}
		
		List<String> roles = CollectionUtils.asList(rolesArray);
		boolean[] hasRoles = subject.hasRoles(roles);
		for (boolean hasRole : hasRoles) {
			if (hasRole) {
				return true;
			}
		}
		return false;
	}

}
