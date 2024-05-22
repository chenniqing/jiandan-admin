package cn.javaex.framework.service.sys_user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javaex.framework.basic.error.SysError;
import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.dao.sys_user.ISysUserDAO;
import cn.javaex.framework.model.dto.SysUserDTO;
import cn.javaex.framework.model.entity.SysRoleEntity;
import cn.javaex.framework.model.entity.SysRoleUserRelEntity;
import cn.javaex.framework.model.entity.SysUserEntity;
import cn.javaex.framework.model.query.SysUserQuery;
import cn.javaex.framework.model.vo.SysUserVO;
import cn.javaex.framework.service.sys_role.SysRoleService;
import cn.javaex.framework.service.sys_role_user_rel.SysRoleUserRelService;

@Service
public class SysUserService {
	
	@Autowired
	private ISysUserDAO iSysUserDAO;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleUserRelService sysRoleUserRelService;
	
	/**
	 * 查询列表
	 * @param sysUserQuery
	 * @return
	 */
	public List<SysUserVO> list(SysUserQuery sysUserQuery) {
		List<SysUserVO> list = iSysUserDAO.selectList(sysUserQuery);
		
		for (SysUserVO sysUserVo : list) {
			List<SysRoleEntity> roleList = sysRoleService.listByByUserId(sysUserVo.getId());
			
			List<String> roleNameList = Optional.ofNullable(roleList)
											.orElseGet(Collections::emptyList)
											.stream()
											.map(SysRoleEntity::getRoleName)
											.collect(Collectors.toList());
			
			sysUserVo.setRoleNameList(roleNameList);
		}
		
		return list;
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public SysUserEntity getById(String id) {
		return iSysUserDAO.selectById(id);
	}
	
	/**
	 * 新增
	 * @param sysUserDTO
	 * @throws QingException 
	 */
	public void insert(SysUserDTO sysUserDTO) throws QingException {
		// 1. 登录名唯一性校验
		this.checkUnique(sysUserDTO, null);
		
		// 2. 保存用户信息
		SysUserEntity sysUserEntity = new SysUserEntity();
		BeanUtils.copyProperties(sysUserDTO, sysUserEntity);
		iSysUserDAO.insert(sysUserEntity);
		
		// 3. 保存用户角色
		if (sysUserDTO.getRoleIds()!=null && sysUserDTO.getRoleIds().length>0) {
			List<SysRoleUserRelEntity> sysRoleUserRelList = new ArrayList<SysRoleUserRelEntity>();
			
			for (String roleId : sysUserDTO.getRoleIds()) {
				SysRoleUserRelEntity sysRoleUserRelEntity = new SysRoleUserRelEntity();
				sysRoleUserRelEntity.setRoleId(roleId);
				sysRoleUserRelEntity.setUserId(sysUserEntity.getId());
				sysRoleUserRelList.add(sysRoleUserRelEntity);
			}
			
			sysRoleUserRelService.batchInsert(sysRoleUserRelList);
		}
	}
	
	/**
	 * 更新
	 * @param id
	 * @param sysUserDTO
	 * @throws QingException 
	 */
	public void update(String id, SysUserDTO sysUserDTO) throws QingException {
		// 1. 登录名唯一性校验
		this.checkUnique(sysUserDTO, id);
		
		// 2. 保存用户信息
		SysUserEntity sysUserEntity = new SysUserEntity();
		BeanUtils.copyProperties(sysUserDTO, sysUserEntity);
		
		sysUserEntity.setId(id);
		iSysUserDAO.updateById(sysUserEntity);
		
		// 3. 保存用户角色
		// 3.1 先删除已有角色
		sysRoleUserRelService.deleteByUserId(sysUserEntity.getId());
		// 3.2 批量保存角色
		if (sysUserDTO.getRoleIds()!=null && sysUserDTO.getRoleIds().length>0) {
			List<SysRoleUserRelEntity> sysRoleUserRelList = new ArrayList<SysRoleUserRelEntity>();
			
			for (String roleId : sysUserDTO.getRoleIds()) {
				SysRoleUserRelEntity sysRoleUserRelEntity = new SysRoleUserRelEntity();
				sysRoleUserRelEntity.setRoleId(roleId);
				sysRoleUserRelEntity.setUserId(sysUserEntity.getId());
				sysRoleUserRelList.add(sysRoleUserRelEntity);
			}
			
			sysRoleUserRelService.batchInsert(sysRoleUserRelList);
		}
	}
	
	/**
	 * 登录名唯一性校验
	 * @param sysUserDTO
	 * @param id
	 */
	private void checkUnique(SysUserDTO sysUserDTO, String id) {
		int count = iSysUserDAO.checkUnique(sysUserDTO.getUsername(), id);
		if (count > 0) {
			throw new QingException("用户名已存在");
		}
	}
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @throws QingException 
	 */
	public SysUserEntity login(String username, String password) throws QingException {
		// 获取Subject单例对象
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			// 1. 登录验证
			subject.login(token);
			
			// 2. 取出用户信息
			SysUserEntity sysUserEntity = (SysUserEntity) subject.getPrincipal();
			
			// 3. 查询用户的所有角色
			List<SysRoleEntity> roleList = sysRoleService.listByByUserId(sysUserEntity.getId());
			sysUserEntity.setRoleList(roleList);
			
			return sysUserEntity;
		} catch (IncorrectCredentialsException ice) {
			// 捕获密码错误异常
			throw new QingException("用户名或密码错误");
		} catch (UnknownAccountException uae) {
			// 捕获未知用户名异常
			throw new QingException("用户名或密码错误");
		} catch (LockedAccountException lae) {
			// 账号锁定
			throw new QingException("该账号已被封禁");
		} catch (ConcurrentAccessException cae) {
			// 一个用户多次登录异常
			throw new QingException("已登录");
		} catch (Exception e) {
			// 未知异常
			throw new QingException(SysError.SYS_ERROR);
		}
	}

	/**
	 * 查询指定角色下，绑定的用户
	 * @param query
	 * @return
	 */
	public List<SysUserVO> listBind(SysUserQuery query) {
		return iSysUserDAO.selectBindList(query);
	}
	
	/**
	 * 查询指定角色下，未绑定的用户
	 * @param query
	 * @return
	 */
	public List<SysUserVO> listUnbind(SysUserQuery query) {
		return iSysUserDAO.selectUnbindList(query);
	}

	/**
	 * 根据username查询用户信息
	 * @param username
	 * @return
	 * @throws QingException 
	 */
	public SysUserEntity selectByUsername(String username) throws QingException {
		List<SysUserEntity> userList = iSysUserDAO.selectListByColumn("username", username);
		if (CollectionUtils.isEmpty(userList)) {
			return null;
		}
		
		return userList.get(0);
	}

	/**
	 * 更新密码
	 * @param id
	 * @param password
	 */
	public void updatePassword(String id, String password) {
		SysUserEntity sysUserEntity = new SysUserEntity();
		sysUserEntity.setId(id);
		sysUserEntity.setPassword(password);
		iSysUserDAO.updateById(sysUserEntity);
	}

}
