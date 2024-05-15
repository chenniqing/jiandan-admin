package cn.javaex.framework.controller.sys_menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.javaex.framework.model.vo.SysMenuVO;
import cn.javaex.framework.service.sys_menu.SysMenuService;

/**
 * 菜单表
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuPageController {

	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 跳转到列表页
	 * @param map
	 * @return
	 */
	@GetMapping("/page/list")
	public String list(ModelMap map) {
		return "framework/sys_menu/sys_menu_list";
	}
	
	/**
	 * 跳转到新增菜单页
	 * @param map
	 * @param parentId
	 * @return
	 */
	@GetMapping("/page/add-menu")
	public String addMenu(ModelMap map,
			@RequestParam(required=false, value="parentId") String parentId) {
		// 查询菜单（排除按钮）
		List<SysMenuVO> list = sysMenuService.listMenuExclusionButton();
		map.put("list", list);
		
		map.put("parentId", parentId);
		
		return "framework/sys_menu/sys_menu_add_menu";
	}
	
	/**
	 * 跳转到编辑菜单页
	 * @param map
	 * @param id
	 * @return
	 */
	@GetMapping("/page/edit-menu")
	public String editMenu(ModelMap map,
			@RequestParam(required=false, value="id") String id) {
		// 查询菜单（排除按钮）
		List<SysMenuVO> list = sysMenuService.listMenuExclusionButton();
		map.put("list", list);
		
		map.put("id", id);
		
		return "framework/sys_menu/sys_menu_edit_menu";
	}
	
	/**
	 * 跳转到新增按钮权限页
	 * @param map
	 * @param id
	 * @param parentId
	 * @return
	 */
	@GetMapping("/page/add-button")
	public String addButton(ModelMap map,
			@RequestParam(required=false, value="parentId") String parentId) {
		// 查询菜单（排除按钮）
		List<SysMenuVO> list = sysMenuService.listMenuExclusionButton();
		map.put("list", list);
		
		map.put("parentId", parentId);
		
		return "framework/sys_menu/sys_menu_add_button";
	}
	
	/**
	 * 跳转到编辑按钮权限页
	 * @param map
	 * @param id
	 * @return
	 */
	@GetMapping("/page/edit-button")
	public String editButton(ModelMap map,
			@RequestParam(required=false, value="id") String id) {
		// 查询菜单（排除按钮）
		List<SysMenuVO> list = sysMenuService.listMenuExclusionButton();
		map.put("list", list);
		
		map.put("id", id);
		
		return "framework/sys_menu/sys_menu_edit_button";
	}

}
