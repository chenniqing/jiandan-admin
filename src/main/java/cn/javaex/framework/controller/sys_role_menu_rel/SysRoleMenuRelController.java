package cn.javaex.framework.controller.sys_role_menu_rel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.javaex.framework.basic.response.Result;
import cn.javaex.framework.model.dto.SysRoleMenuDTO;
import cn.javaex.framework.model.vo.SysMenuVO;
import cn.javaex.framework.service.sys_menu.SysMenuService;
import cn.javaex.framework.service.sys_role_menu_rel.SysRoleMenuRelService;

/**
 * 角色-菜单关联关系
 * 
 * @author 陈霓清
 */
@RestController
@RequestMapping("/sys/role-menu")
public class SysRoleMenuRelController {

	@Autowired
	private SysRoleMenuRelService sysRoleMenuRelService;
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 获取某个角色下的菜单列表
	 * @param roleId
	 * @return
	 */
	@GetMapping("/list/{roleId}")
	public Result list(@PathVariable(value="roleId") String roleId) {
		
		List<SysMenuVO> list = sysMenuService.listMenuByRoleId(roleId);
		
		return Result.success().add("list", list);
	}
	
	/**
	 * 保存角色菜单的分配
	 * @param roleId
	 * @param sysRoleMenuDTO
	 * @return
	 */
	@PostMapping("/save/{roleId}")
	public Result save(@PathVariable(value="roleId") String roleId, @RequestBody SysRoleMenuDTO sysRoleMenuDTO) {
		
		sysRoleMenuRelService.save(roleId, sysRoleMenuDTO.getMenuIds());
		
		return Result.success();
	}
	
}
