package cn.javaex.framework.dao.sys_role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.javaex.framework.model.entity.SysRoleEntity;
import cn.javaex.framework.model.query.SysRoleQuery;
import cn.javaex.framework.model.vo.SysRoleVO;
import cn.javaex.mybatisjj.mapper.CommonMapper;

public interface ISysRoleDAO extends CommonMapper<SysRoleEntity> {

	/**
	 * 查询角色列表
	 * @param sysRoleQuery 
	 * @return
	 */
	List<SysRoleVO> selectList(SysRoleQuery sysRoleQuery);

	/**
	 * 查询目前最大排序
	 * @return
	 */
	int selectMaxSort();

	/**
	 * 角色标识唯一性校验
	 * @param roleCode 角色标识
	 * @param id 主键
	 * @return
	 */
	int checkUnique(@Param("roleCode") String roleCode, @Param("id") String id);

	/**
	 * 查询菜单需要的角色
	 * @param menuUrl
	 * @return
	 */
	List<String> selectRoleCodeListByMenuUrl(@Param("url") String url);

	/**
	 * 查询上一条记录
	 * @param sort
	 * @return
	 */
	SysRoleEntity selectLastBySort(Integer sort);

	/**
	 * 查询下一条记录
	 * @param sort
	 * @return
	 */
	SysRoleEntity selectNextBySort(Integer sort);

	/**
	 * 根据用户ID查询用户所有角色
	 * @param userId
	 * @return
	 */
	List<SysRoleEntity> selectRoleListByUserId(String userId);
	
}