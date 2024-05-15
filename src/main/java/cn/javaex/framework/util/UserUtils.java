package cn.javaex.framework.util;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import cn.javaex.framework.basic.error.AccessError;
import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.model.entity.SysRoleEntity;
import cn.javaex.framework.model.entity.SysUserEntity;

/**
 * 用户工具类
 * 
 * @author 陈霓清
 */
public class UserUtils {
	
	/**
	 * 获取当前登录用户信息
	 * @return
	 * @throws QingException
	 */
	public static SysUserEntity getCurUser() throws QingException {
		Subject subject = SecurityUtils.getSubject();
		
		// 未认证，重新登录
		if (!subject.isAuthenticated()) {
			throw new QingException(AccessError.UN_LOGIN);
		}
		
		return (SysUserEntity) subject.getPrincipal();
	}
	
	/**
	 * 判断当前用户是否拥有某一角色
	 * @param roleCode
	 * @return
	 * @throws QingException
	 */
	public static boolean hasRole(String roleCode) throws QingException {
		List<SysRoleEntity> roleList = getCurUser().getRoleList();
		if (CollectionUtils.isEmpty(roleList)) {
			return false;
		}
		
		List<String> roleCodeList = roleList.stream()
										.map(SysRoleEntity::getRoleCode)
										.collect(Collectors.toList());
		
		return roleCodeList.contains(roleCode);
	}
	
}
