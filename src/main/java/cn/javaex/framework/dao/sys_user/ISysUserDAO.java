package cn.javaex.framework.dao.sys_user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.javaex.framework.basic.annotation.DataPermission;
import cn.javaex.framework.model.entity.SysUserEntity;
import cn.javaex.framework.model.query.SysUserQuery;
import cn.javaex.framework.model.vo.SysUserVO;
import cn.javaex.mybatisjj.mapper.CommonMapper;

public interface ISysUserDAO extends CommonMapper<SysUserEntity> {
	
	/**
	 * 查询列表
	 * @param query
	 * @return
	 */
	@DataPermission(deptAlias = "u.department_id", userAlias = "u.id")
	List<SysUserVO> selectList(SysUserQuery query);

	/**
	 * 查询指定角色下，绑定的用户
	 * @param query
	 * @return
	 */
	List<SysUserVO> selectBindList(SysUserQuery query);
	
	/**
	 * 查询指定角色下，未绑定的用户
	 * @param query
	 * @return
	 */
	List<SysUserVO> selectUnbindList(SysUserQuery query);

	/**
	 * 登录名唯一性校验
	 * @param username
	 * @param id
	 * @return
	 */
	int checkUnique(@Param("username") String username, @Param("id") String id);

}
