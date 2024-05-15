package cn.javaex.framework.config.shiro;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.javaex.framework.model.entity.SysRoleEntity;
import cn.javaex.framework.model.entity.SysUserEntity;
import cn.javaex.framework.service.sys_menu.SysMenuService;
import cn.javaex.framework.service.sys_role.SysRoleService;
import cn.javaex.framework.service.sys_user.SysUserService;

/**
 * 自定义realm
 * 
 * @author 陈霓清
 */
public class Realm extends AuthorizingRealm {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 权限授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		SysUserEntity sysUserEntity = (SysUserEntity) principals.getPrimaryPrincipal();
		
		// 查询用户的所有角色
		List<SysRoleEntity> roleList = sysRoleService.listByByUserId(sysUserEntity.getId());
		List<String> roleCodeList = roleList.stream()
										.map(SysRoleEntity::getRoleCode)
										.collect(Collectors.toList());
		info.addRoles(roleCodeList);
		
		// 查询用户所有的按钮权限
		List<String> permCodeSet = sysMenuService.listPermCodeByUserId(sysUserEntity.getId());
		if (permCodeSet!=null && permCodeSet.isEmpty()==false) {
			info.addStringPermissions(permCodeSet);
		}
		
		return info;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String username = (String)authenticationToken.getPrincipal();
		
		SysUserEntity user = sysUserService.selectByUsername(username);
		if (user==null) {
			throw new UnknownAccountException();
		} else {
			user.setUsername(username);
		}
		
		// 校验是否允许登录
		if (user.getStatus()==1) {
			throw new LockedAccountException();
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		
		return info;
	}

}
