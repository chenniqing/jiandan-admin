package cn.javaex.framework.dao.sys_role_user_rel;

import org.apache.ibatis.annotations.Param;

import cn.javaex.framework.model.entity.SysRoleUserRelEntity;
import cn.javaex.mybatisjj.mapper.CommonMapper;

public interface ISysRoleUserRelDAO extends CommonMapper<SysRoleUserRelEntity> {

	/**
	 * 批量移除用户
	 * @param roleId
	 * @param userIdArr
	 * @return
	 */
	int batchDeleteUser(@Param("roleId") String roleId, @Param("userIdArr") String[] userIdArr);
	
	/**
	 * 移除用户
	 * @param roleId
	 * @param userId
	 */
	int deleteByRoleIdAndUserId(@Param("roleId") String roleId, @Param("userId") String userId);

}