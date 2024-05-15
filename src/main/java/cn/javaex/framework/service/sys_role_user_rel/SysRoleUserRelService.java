package cn.javaex.framework.service.sys_role_user_rel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javaex.framework.config.shiro.FilterChainDefinition;
import cn.javaex.framework.dao.sys_role_user_rel.ISysRoleUserRelDAO;
import cn.javaex.framework.model.entity.SysRoleUserRelEntity;

@Service
public class SysRoleUserRelService {
	
	@Autowired
	private ISysRoleUserRelDAO iSysRoleUserRelDAO;
	@Autowired
	private FilterChainDefinition filterChainDefinition;
	
	/**
	 * 查询用户的所有角色code
	 * @param userId
	 * @return
	 */
	public List<String> listRoleIdByUserId(String userId) {
		List<SysRoleUserRelEntity> list = iSysRoleUserRelDAO.selectListByColumn("user_id", userId);
		
        return list.stream()
        		.map(SysRoleUserRelEntity::getRoleId)
        		.collect(Collectors.toList());
	}
	
	/**
	 * 向角色添加用户
	 * @param roleId 角色id
	 * @param userIds 用户id数组
	 */
	public void addUser(String roleId, String[] userIds) {
		// 1. 删除（防止重复数据）
		iSysRoleUserRelDAO.batchDeleteUser(roleId, userIds);
		
		// 2. 重新插入
		if (userIds!=null && userIds.length>0) {
			List<SysRoleUserRelEntity> sysRoleUserRelList = new ArrayList<SysRoleUserRelEntity>();
			
			for (String userId : userIds) {
				SysRoleUserRelEntity sysRoleUserRelEntity = new SysRoleUserRelEntity();
				sysRoleUserRelEntity.setRoleId(roleId);
				sysRoleUserRelEntity.setUserId(userId);
				
				sysRoleUserRelList.add(sysRoleUserRelEntity);
			}
			
			iSysRoleUserRelDAO.batchInsert(sysRoleUserRelList);
		}
	}
	
	/**
	 * 新增
	 * @param roleId
	 * @param userId
	 */
	public void insert(String roleId, String userId) {
		SysRoleUserRelEntity entity = new SysRoleUserRelEntity();
		entity.setRoleId(roleId);
		entity.setUserId(userId);
		
		iSysRoleUserRelDAO.insert(entity);
	}
	
	/**
	 * 移除用户
	 * @param roleId 角色id
	 * @param userId 用户id
	 */
	public void deleteUser(String roleId, String userId) {
		// 1. 移除用户
		iSysRoleUserRelDAO.deleteByRoleIdAndUserId(roleId, userId);
		
		// 2. 清除用户授权缓存
		filterChainDefinition.clearAuthorizationInfo();
	}

	/**
	 * 批量移除用户
	 * @param roleId 角色id
	 * @param userIds 用户id数组
	 */
	public void batchDeleteUser(String roleId, String[] userIds) {
		// 1. 移除用户
		iSysRoleUserRelDAO.batchDeleteUser(roleId, userIds);
		
		// 2. 清除用户授权缓存
		filterChainDefinition.clearAuthorizationInfo();
	}

	/**
	 * 删除指定用户的所有角色
	 * @param userId
	 */
	public void deleteByUserId(String userId) {
		iSysRoleUserRelDAO.deleteByColumn("user_id", userId);
	}

	/**
	 * 批量插入
	 * @param sysRoleUserRelList
	 */
	public void batchInsert(List<SysRoleUserRelEntity> sysRoleUserRelList) {
		iSysRoleUserRelDAO.batchInsert(sysRoleUserRelList);
	}

}