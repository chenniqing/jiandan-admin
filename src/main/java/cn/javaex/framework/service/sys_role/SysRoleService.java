package cn.javaex.framework.service.sys_role;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javaex.framework.basic.error.RoleError;
import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.dao.sys_role.ISysRoleDAO;
import cn.javaex.framework.model.dto.SysRoleDTO;
import cn.javaex.framework.model.entity.SysRoleEntity;
import cn.javaex.framework.model.query.SysRoleQuery;
import cn.javaex.framework.model.vo.SysRoleVO;
import cn.javaex.framework.service.sys_role_menu_rel.SysRoleMenuRelService;

@Service
public class SysRoleService {
	
	@Autowired
	private ISysRoleDAO iSysRoleDAO;
	@Autowired
	private SysRoleMenuRelService sysRoleMenuService;
	
	/**
	 * 查询角色列表
	 * @param sysRoleQuery 
	 * @return
	 */
	public List<SysRoleVO> list(SysRoleQuery sysRoleQuery) {
		return iSysRoleDAO.selectList(sysRoleQuery);
	}

	/**
	 * 根据主键查询角色
	 * @param id 角色id
	 * @return
	 */
	public SysRoleEntity getById(String id) {
		return iSysRoleDAO.selectById(id);
	}

	/**
	 * 新增
	 * @param sysUserDTO
	 * @throws QingException 
	 */
	public void insert(SysRoleDTO sysUserDTO) throws QingException {
		SysRoleEntity sysRoleEntity = new SysRoleEntity();
		BeanUtils.copyProperties(sysUserDTO, sysRoleEntity);
		
		// 角色标识唯一性校验
		this.checkUnique(sysRoleEntity, null);
		
		// 查询目前最大排序
		int maxSort = iSysRoleDAO.selectMaxSort();
		sysRoleEntity.setSort(++maxSort);
		
		iSysRoleDAO.insert(sysRoleEntity);
	}

	/**
	 * 更新
	 * @param id 
	 * @param sysUserDTO
	 * @throws QingException 
	 */
	public void update(String id, SysRoleDTO sysUserDTO) throws QingException {
		SysRoleEntity sysRoleEntity = new SysRoleEntity();
		BeanUtils.copyProperties(sysUserDTO, sysRoleEntity);
		
		// 角色标识唯一性校验
		this.checkUnique(sysRoleEntity, id);
		
		sysRoleEntity.setId(id);
		iSysRoleDAO.updateById(sysRoleEntity);
	}
	
	/**
	 * 角色标识唯一性校验
	 * @param sysRoleEntity
	 * @param id 
	 * @throws QingException 
	 */
	private void checkUnique(SysRoleEntity sysRoleEntity, String id) throws QingException {
		int count = iSysRoleDAO.checkUnique(sysRoleEntity.getRoleCode(), id);
		if (count > 0) {
			throw new QingException(RoleError.ROLE_1101);
		}
	}

	/**
	 * 删除角色
	 * @param id 角色id
	 */
	public void deleteById(String id) {
		// 1. 删除角色-菜单的分配
		sysRoleMenuService.deleteByRoleId(id);
		
		// 2. 删除角色
		iSysRoleDAO.deleteById(id);
	}

	/**
	 * 上移下移
	 * @param id
	 * @param action
	 */
	public synchronized void move(String id, String action) {
		// 1. 查询当前记录的信息
		SysRoleEntity curRole = iSysRoleDAO.selectById(id);
		SysRoleEntity entity = null;
		int tempSort = curRole.getSort();
		
		// 2. 上移下移判断
		// 上移
		if ("up".equals(action)) {
			// 2.1 查询上一条记录
			entity = iSysRoleDAO.selectLastBySort(curRole.getSort());
		}
		// 下移
		else if ("down".equals(action)) {
			// 2.1 查询下一条记录
			entity = iSysRoleDAO.selectNextBySort(curRole.getSort());
		}
		
		// 2.2 跳过本身就是第一或最后一条记录的
		if (entity==null) {
			return;
		}
		
		// 2.3 排序sort互换
		curRole.setSort(entity.getSort());
		entity.setSort(tempSort);
		
		// 3. 更新
		iSysRoleDAO.updateById(curRole);
		iSysRoleDAO.updateById(entity);
	}

	/**
	 * 根据角色code查询实体
	 * @param roleCode
	 * @return
	 */
	public SysRoleEntity getByRoleCode(String roleCode) {
		List<SysRoleEntity> list = iSysRoleDAO.selectListByColumn("role_code", roleCode);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	/**
	 * 根据用户ID查询用户所有角色
	 * @param userId
	 * @return
	 */
	public List<SysRoleEntity> listByByUserId(String userId) {
		return iSysRoleDAO.selectRoleListByUserId(userId);
	}

}