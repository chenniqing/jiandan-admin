package cn.javaex.framework.controller.sys_role;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sys/role")
public class SysRolePageController {

	/**
	 * 查询所有角色
	 * @param map
	 * @return
	 */
	@GetMapping("/page/list")
	public String list(ModelMap map) {
		return "framework/sys_role/sys_role_list";
	}
	
	/**
	 * 跳转角色新增页面
	 * @return
	 */
	@GetMapping("/page/add")
	public String add() {
		return "framework/sys_role/sys_role_add";
	}
	
	/**
	 * 跳转角色编辑页面
	 * @param map
	 * @param id 角色id
	 * @return
	 */
	@GetMapping("/page/edit")
	public String edit(ModelMap map,
			@RequestParam(required=false, value="id") String id) {
		
		map.put("id", id);
		
		return "framework/sys_role/sys_role_edit";
	}
	
}
