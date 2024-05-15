package cn.javaex.framework.controller.sys_role_user_rel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 角色用户关系表
 * 
 * @author 陈霓清
 */
@Controller
@RequestMapping("/sys/role-user")
public class SysRoleUserRelPageController {

	/**
	 * 跳转到角色-用户配置页面
	 * @param map
	 * @param roleId
	 * @return
	 */
	@GetMapping("/page/bind-list")
	public String bindList(ModelMap map,
			@RequestParam(value="roleId") String roleId) {
		
		map.put("roleId", roleId);
		
		return "framework/sys_role_user_rel/role_user_rel_bind_list";
	}
	
	/**
	 * 角色未绑定的用户列表
	 * @param map
	 * @param roleId
	 * @return
	 */
	@GetMapping("/page/unbind-list")
	public String unbindList(ModelMap map,
			@RequestParam(value="roleId") String roleId) {
		
		map.put("roleId", roleId);
		
		return "framework/sys_role_user_rel/role_user_rel_unbind_list";
	}
	
}
