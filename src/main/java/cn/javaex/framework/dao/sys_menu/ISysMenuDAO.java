package cn.javaex.framework.dao.sys_menu;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.javaex.framework.model.entity.SysMenuEntity;
import cn.javaex.framework.model.vo.SysMenuVO;
import cn.javaex.mybatisjj.mapper.CommonMapper;

public interface ISysMenuDAO extends CommonMapper<SysMenuEntity> {

	/**
	 * 获取所有菜单
	 * @return
	 */
	List<SysMenuVO> selectAllList();
	
	/**
	 * 获取所有菜单（排除按钮）
	 * @return
	 */
	List<SysMenuVO> selectMenuListExclusionButton();
	
	/**
	 * 查询当前菜单级别下最大排序
	 * @param parentId
	 * @return
	 */
	int selectMaxSortByParentId(String parentId);

	/**
	 * 查询当前用户有权限的菜单
	 * @param userId 用户id
	 * @return
	 */
	List<SysMenuVO> selectListByUserId(@Param("userId") String userId);

	/**
	 * 查询所有菜单路径
	 * @return
	 */
	Set<String> selectMenuUrlList();

	/**
	 * 查询所有权限标识
	 * @return
	 */
	Set<String> selectPermCodeList();
	
	/**
	 * 校验按钮权限标识是否重复
	 * @param permCode 按钮权限标识
	 * @param id 主键
	 * @return
	 */
	int selectCountByPermCode(@Param("permCode") String permCode, @Param("id") String id);

	/**
	 * 查询用户的所有按钮权限
	 * @param userId 用户id
	 * @return
	 */
	List<String> selectPermCodeListByUserId(@Param("userId") String userId);

	/**
	 * 校验导航标识是否重复
	 * @param url
	 * @param id
	 * @return
	 */
	int selectCountByUrl(@Param("url") String url, @Param("id") String id);

	/**
	 * 查询出所有子级和自己
	 * @param id
	 * @return
	 */
	List<String> selectIdListByRecursive(String id);

}