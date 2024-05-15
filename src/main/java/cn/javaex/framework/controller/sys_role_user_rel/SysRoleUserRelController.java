package cn.javaex.framework.controller.sys_role_user_rel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.basic.response.Result;
import cn.javaex.framework.model.dto.SysRoleUserDTO;
import cn.javaex.framework.model.query.SysUserQuery;
import cn.javaex.framework.model.vo.SysUserVO;
import cn.javaex.framework.service.sys_role_user_rel.SysRoleUserRelService;
import cn.javaex.framework.service.sys_user.SysUserService;

/**
 * 角色-用户关联关系
 * 
 * @author 陈霓清
 */
@RestController
@RequestMapping("/sys/role-user")
public class SysRoleUserRelController {

	@Autowired
	private SysRoleUserRelService sysRoleUserRelService;
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 角色绑定的用户列表
	 * @param roleId
	 * @param sysUserQuery
	 * @return
	 */
	@GetMapping("/list/bind/{roleId}")
	public Result bindList(@PathVariable(value="roleId") String roleId, SysUserQuery sysUserQuery) {
		sysUserQuery.setRoleId(roleId);
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(sysUserQuery.getPageNum(), sysUserQuery.getPageSize());
		List<SysUserVO> list = sysUserService.listBind(sysUserQuery);
		PageInfo<SysUserVO> pageInfo = new PageInfo<SysUserVO>(list);
		
		return Result.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 角色未绑定的用户列表
	 * @param roleId
	 * @param sysUserQuery
	 * @return
	 */
	@GetMapping("/list/unbind/{roleId}")
	public Result unbindList(@PathVariable(value="roleId") String roleId, SysUserQuery sysUserQuery) {
		sysUserQuery.setRoleId(roleId);
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(sysUserQuery.getPageNum(), sysUserQuery.getPageSize());
		List<SysUserVO> list = sysUserService.listUnbind(sysUserQuery);
		PageInfo<SysUserVO> pageInfo = new PageInfo<SysUserVO>(list);
		
		return Result.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 向角色添加用户
	 * @param roleId
	 * @param sysRoleUserDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/add/user/{roleId}")
	public Result addUser(@PathVariable(value="roleId") String roleId, @RequestBody SysRoleUserDTO sysRoleUserDTO) throws QingException {
		
		sysRoleUserRelService.addUser(roleId, sysRoleUserDTO.getUserIds());
		
		return Result.success();
	}
	
	/**
	 * 移除用户
	 * @param roleId 角色id
	 * @param sysRoleUserDTO
	 * @return
	 */
	@PostMapping("/delete/user/{roleId}")
	public Result deleteUser(@PathVariable(value="roleId") String roleId, @RequestBody SysRoleUserDTO sysRoleUserDTO) {
		
		sysRoleUserRelService.deleteUser(roleId, sysRoleUserDTO.getUserId());
		
		return Result.success();
	}
	
	/**
	 * 批量移除用户
	 * @param roleId 角色id
	 * @param sysRoleUserDTO
	 * @return
	 */
	@PostMapping("/batch-delete/user/{roleId}")
	public Result batchDeleteUser(@PathVariable(value="roleId") String roleId, @RequestBody SysRoleUserDTO sysRoleUserDTO) {
		
		sysRoleUserRelService.batchDeleteUser(roleId, sysRoleUserDTO.getUserIds());
		
		return Result.success();
	}
}
