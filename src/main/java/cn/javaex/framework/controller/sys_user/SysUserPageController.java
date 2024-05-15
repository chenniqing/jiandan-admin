package cn.javaex.framework.controller.sys_user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户表
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserPageController {
	
	/**
	 * 跳转到列表页
	 * @param map
	 * @return
	 */
	@GetMapping("/page/list")
	public String list(ModelMap map) {
		return "framework/sys_user/sys_user_list";
	}
	
	/**
	 * 跳转到添加页
	 * @param map
	 * @param id
	 * @return
	 */
	@GetMapping("/page/add")
	public String add() {
		return "framework/sys_user/sys_user_add";
	}
	
	/**
	 * 跳转到编辑页
	 * @param map
	 * @param id
	 * @return
	 */
	@GetMapping("/page/edit")
	public String edit(ModelMap map,
			@RequestParam(required=false, value="id") String id) {
		
		map.put("id", id);
		
		return "framework/sys_user/sys_user_edit";
	}
	
	/**
	 * 跳转到修改密码页
	 * @return
	 */
	@GetMapping("/page/change-password")
	public String changePassword() {
		return "framework/sys_user/sys_user_change_password";
	}
	
}
