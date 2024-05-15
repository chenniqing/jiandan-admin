package cn.javaex.framework.service.sys_role_menu_rel;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javaex.framework.config.shiro.FilterChainDefinition;
import cn.javaex.framework.dao.sys_role_menu_rel.ISysRoleMenuRelDAO;
import cn.javaex.framework.model.entity.SysRoleMenuRelEntity;

@Service
public class SysRoleMenuRelService {
	
	@Autowired
	private ISysRoleMenuRelDAO iSysRoleMenuRelDAO;
	@Autowired
	private FilterChainDefinition filterChainDefinition;
	
	/**
	 * 保存角色菜单的分配
	 * @param roleId
	 * @param menuIds
	 */
	public void save(String roleId, String[] menuIds) {
		// 1. 删除该角色的菜单
		iSysRoleMenuRelDAO.deleteByColumn("role_id", roleId);
		
		// 2. 重新插入角色菜单
		if (menuIds!=null && menuIds.length>0) {
			for (int i=0; i<menuIds.length; i++) {
				SysRoleMenuRelEntity roleMenu = new SysRoleMenuRelEntity();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(menuIds[i]);
				
				iSysRoleMenuRelDAO.insert(roleMenu);
			}
		}
		
		// 3. 更新权限设定
		filterChainDefinition.updatePermission();
	}

	/**
	 * 删除角色-菜单的关联关系
	 * @param roleId
	 */
	public void deleteByRoleId(String roleId) {
		iSysRoleMenuRelDAO.deleteByColumn("role_id", roleId);
	}

	/**
	 * 删除角色-菜单的关联关系
	 * @param menuId
	 */
	public void deleteByMenuId(String menuId) {
		iSysRoleMenuRelDAO.deleteByColumn("menu_id", menuId);
	}

	/**
	 * 获取指定角色的权限菜单
	 * @param roleId
	 * @return
	 */
	public List<String> listMenuIdByRoleId(String roleId) {
		List<SysRoleMenuRelEntity> list = iSysRoleMenuRelDAO.selectListByColumn("role_id", roleId);
		
		return list.stream()
				.map(SysRoleMenuRelEntity::getMenuId)
				.collect(Collectors.toList());
	}
	
}