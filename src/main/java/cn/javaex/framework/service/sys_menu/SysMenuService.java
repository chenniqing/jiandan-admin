package cn.javaex.framework.service.sys_menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javaex.framework.basic.error.MenuError;
import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.dao.sys_menu.ISysMenuDAO;
import cn.javaex.framework.model.dto.SysMenuDTO;
import cn.javaex.framework.model.dto.SysMenuSortDTO;
import cn.javaex.framework.model.entity.SysMenuEntity;
import cn.javaex.framework.model.vo.SysMenuVO;
import cn.javaex.framework.service.sys_role_menu_rel.SysRoleMenuRelService;

@Service
public class SysMenuService {
	
	@Autowired
	private ISysMenuDAO iSysMenuDAO;
	@Autowired
	private SysRoleMenuRelService sysRoleMenuRelService;
	
	/**
	 * 获取某个角色下的菜单列表（带回显）
	 * @param roleId 角色id
	 * @return
	 */
	public List<SysMenuVO> listMenuByRoleId(String roleId) {
		// 获取该角色的权限菜单
		List<String> menuIdList = sysRoleMenuRelService.listMenuIdByRoleId(roleId);
		Map<String, String> menuIdMap = new HashMap<String, String>(menuIdList.size());
		if (CollectionUtils.isNotEmpty(menuIdList)) {
			for (String menuId : menuIdList) {
				menuIdMap.put(menuId, menuId);
			}
		}
		
		// 1. 获取全部菜单
		List<SysMenuVO> allList = iSysMenuDAO.selectAllList();
		// 2. 定义一个map
		Map<String, SysMenuVO> itemMap = new HashMap<String, SysMenuVO>(allList.size());
		// 3. 定义一级菜单list
		List<SysMenuVO> rootMenuList = new ArrayList<SysMenuVO>();
		
		// 4. 遍历全部菜单list
		if (CollectionUtils.isNotEmpty(allList)) {
			// 4.1 第一次遍历，取出1级菜单
			for (SysMenuVO menu : allList) {
				// 设置map
				itemMap.put(menu.getId(), menu);
				
				if ("0".equals(menu.getParentId())) {
					rootMenuList.add(menu);
				}
			}
			// 4.2 第二次遍历，取出每个1级菜单下的子菜单
			for (SysMenuVO menu : allList) {
				if (!"0".equals(menu.getId())) {
					if (menuIdMap.get(menu.getId())!=null) {
						menu.setChecked(true);
					}
					SysMenuVO parent = itemMap.get(menu.getParentId());
					if (parent!=null) {
						if (menuIdMap.get(parent.getId())!=null) {
							parent.setOpen(true);
						}
						parent.getChildren().add(menu);
					}
				}
			}
		}
		
		return rootMenuList;
	}
	
	/**
	 * 新增
	 * @param sysMenuDTO
	 * @throws QingException 
	 */
	public void insert(SysMenuDTO sysMenuDTO) throws QingException {
		SysMenuEntity sysMenuEntity = new SysMenuEntity();
		BeanUtils.copyProperties(sysMenuDTO, sysMenuEntity);
		
		// 校验
		this.check(sysMenuEntity);
		
		// 查询目前最大排序
		int maxSort = iSysMenuDAO.selectMaxSortByParentId(sysMenuDTO.getParentId());
		sysMenuEntity.setSort(++maxSort);
		
		if (sysMenuDTO.getIsHidden() == null) {
			sysMenuEntity.setIsHidden(0);
		}
		sysMenuEntity.setIsSystem(0);
		iSysMenuDAO.insert(sysMenuEntity);
	}

	/**
	 * 更新
	 * @param id 
	 * @param sysMenuDTO
	 * @throws QingException 
	 */
	public void update(String id, SysMenuDTO sysMenuDTO) throws QingException {
		SysMenuEntity sysMenuEntity = new SysMenuEntity();
		BeanUtils.copyProperties(sysMenuDTO, sysMenuEntity);
		
		sysMenuEntity.setId(id);
		
		// 校验
		this.check(sysMenuEntity);
		
		iSysMenuDAO.updateById(sysMenuEntity);
	}
	
	/**
	 * 保存校验
	 * @param sysMenuEntity
	 * @throws QingException
	 */
	private void check(SysMenuEntity sysMenuEntity) throws QingException {
		// 1. 校验按钮权限标识是否重复
		if (StringUtils.isNotEmpty(sysMenuEntity.getPermCode())) {
			int count = iSysMenuDAO.selectCountByPermCode(sysMenuEntity.getPermCode(), sysMenuEntity.getId());
			if (count > 0) {
				throw new QingException(MenuError.MENU_1001);
			}
		}
		
		// 2. 校验合法性
		if ("导航".equals(sysMenuEntity.getType())) {
			if (StringUtils.isEmpty(sysMenuEntity.getUrl())) {
				throw new QingException(MenuError.MENU_1002);
			}
			int count = iSysMenuDAO.selectCountByUrl(sysMenuEntity.getUrl(), sysMenuEntity.getId());
			if (count>0) {
				throw new QingException(MenuError.MENU_1003);
			}
		}
		if ("目录".equals(sysMenuEntity.getType()) || "菜单".equals(sysMenuEntity.getType())) {
			if ("0".equals(sysMenuEntity.getParentId())) {
				throw new QingException(MenuError.MENU_1004);
			}
			
			SysMenuEntity parentMenu = iSysMenuDAO.selectById(sysMenuEntity.getParentId());
			if ("目录".equals(sysMenuEntity.getType()) && !"导航".equals(parentMenu.getType())) {
				throw new QingException(MenuError.MENU_1005);
			}
			if ("菜单".equals(sysMenuEntity.getType()) && StringUtils.isEmpty(sysMenuEntity.getUrl())) {
				throw new QingException(MenuError.MENU_1006);
			}
		}
		
		// 3. 校验路径
		if (StringUtils.isNotEmpty(sysMenuEntity.getUrl()) && !sysMenuEntity.getUrl().startsWith("/")) {
			sysMenuEntity.setUrl("/" + sysMenuEntity.getUrl());
		}
		if (StringUtils.isNotEmpty(sysMenuEntity.getPermCode()) && !sysMenuEntity.getPermCode().startsWith("/")) {
			sysMenuEntity.setPermCode("/" + sysMenuEntity.getPermCode());
		}
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public SysMenuEntity getById(String id) {
		return iSysMenuDAO.selectById(id);
	}
	
	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<SysMenuVO> listTree() {
		// 1. 获取全部菜单
		List<SysMenuVO> allList = iSysMenuDAO.selectAllList();
		// 2. 定义一个map
		Map<String, SysMenuVO> itemMap = new HashMap<String, SysMenuVO>(allList.size());
		// 3. 定义一级菜单list
		List<SysMenuVO> rootList = new ArrayList<SysMenuVO>();
		
		// 4. 遍历全部菜单list
		if (CollectionUtils.isNotEmpty(allList)) {
			// 4.1 第一次遍历，取出1级菜单
			for (SysMenuVO menu : allList) {
				// 设置map
				itemMap.put(menu.getId(), menu);
				
				if ("0".equals(menu.getParentId())) {
					rootList.add(menu);
				}
			}
			// 4.2 第二次遍历，取出子菜单
			for (SysMenuVO menu : allList) {
				if (!"0".equals(menu.getParentId())) {
					SysMenuVO parent = itemMap.get(menu.getParentId());
					if (parent != null) {
						parent.getChildren().add(menu);
					}
				}
			}
		}
		
		return rootList;
	}
	
	/**
	 * 查询所有菜单（排除按钮）
	 * @return
	 */
	public List<SysMenuVO> listMenuExclusionButton() {
		// 1. 获取全部菜单（排除按钮）
		List<SysMenuVO> menuList = iSysMenuDAO.selectMenuListExclusionButton();
		// 2. 定义一个map
		Map<String, SysMenuVO> itemMap = new HashMap<String, SysMenuVO>(menuList.size());
		// 3. 定义一级菜单list
		List<SysMenuVO> rootList = new ArrayList<SysMenuVO>();
		
		// 4. 遍历全部菜单list
		if (CollectionUtils.isNotEmpty(menuList)) {
			// 4.1 第一次遍历，取出1级菜单
			for (SysMenuVO menu : menuList) {
				// 设置map
				itemMap.put(menu.getId(), menu);
				
				if ("0".equals(menu.getParentId())) {
					rootList.add(menu);
				}
			}
			// 4.2 第二次遍历，取出每个1级菜单下的子菜单
			for (SysMenuVO menu : menuList) {
				if (!"0".equals(menu.getParentId())) {
					SysMenuVO parent = itemMap.get(menu.getParentId());
					if (parent!=null) {
						parent.getChildren().add(menu);
					}
				}
			}
		}
		
		return rootList;
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @throws QingException
	 */
	public void deleteById(String id) throws QingException {
		// 1. 删除角色-菜单的关联关系
		sysRoleMenuRelService.deleteByMenuId(id);
		
		// 2. 查询出所有子级和自己
		List<String> idList = iSysMenuDAO.selectIdListByRecursive(id);
		
		if (CollectionUtils.isNotEmpty(idList)) {
			iSysMenuDAO.deleteByIds(idList);
		}
	}

	/**
	 * 用户当前用户拥有权限的菜单
	 * @param userId
	 * @return
	 */
	public List<SysMenuVO> listByUserId(String userId) {
		// 1. 查询当前用户有权限的菜单
		List<SysMenuVO> userMenuList = iSysMenuDAO.selectListByUserId(userId);
		// 2. 定义一个map
		Map<String, SysMenuVO> itemMap = new HashMap<String, SysMenuVO>(userMenuList.size());
		// 3. 定义一级菜单list
		List<SysMenuVO> rootList = new ArrayList<SysMenuVO>();
		
		// 4. 遍历全部用户菜单list
		if (userMenuList!=null && userMenuList.isEmpty()==false) {
			// 4.1 第一次遍历，取出1级菜单
			for (SysMenuVO menu : userMenuList) {
				// 设置map
				itemMap.put(menu.getId(), menu);
				
				if ("0".equals(menu.getParentId())) {
					rootList.add(menu);
				}
			}
			// 4.2 第二次遍历，取出每个1级菜单下的子菜单
			for (SysMenuVO menu : userMenuList) {
				if (!"0".equals(menu.getParentId())) {
					SysMenuVO parent = itemMap.get(menu.getParentId());
					if (parent!=null) {
						parent.getChildren().add(menu);
					}
				}
			}
		}
		
		return rootList;
	}

	/**
	 * 保存排序
	 * @param menuList
	 */
	public void saveSort(List<SysMenuSortDTO> list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		
		for (SysMenuSortDTO sysMenuSortDTO : list) {
			SysMenuEntity sysMenuEntity = new SysMenuEntity();
			sysMenuEntity.setId(sysMenuSortDTO.getId());
			sysMenuEntity.setSort(sysMenuSortDTO.getSort());
			
			iSysMenuDAO.updateById(sysMenuEntity);
		}
	}

	/**
	 * 查询用户所有的按钮权限
	 * @param userId
	 * @return
	 */
	public List<String> listPermCodeByUserId(String userId) {
		return iSysMenuDAO.selectPermCodeListByUserId(userId);
	}

}