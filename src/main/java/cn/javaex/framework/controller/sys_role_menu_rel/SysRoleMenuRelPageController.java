package cn.javaex.framework.controller.sys_role_menu_rel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 角色菜单关系表
 * 
 * @author 陈霓清
 */
@Controller
@RequestMapping("/sys/role-menu")
public class SysRoleMenuRelPageController {
	
	/**
	 * 跳转到角色-菜单配置页面
	 * @param map
	 * @param roleId
	 * @return
	 */
	@GetMapping("/page/list")
	public String list(ModelMap map,
			@RequestParam(value="roleId") String roleId) {
		
		map.put("roleId", roleId);
		
		return "framework/sys_role_menu_rel/sys_role_menu_rel_list";
	}
	
}
