package cn.javaex.framework.controller.sys_department;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 部门表
 * 
 * @author 陈霓清
 */
@Controller
@RequestMapping("/sys/department")
public class SysDepartmentPageController {
	
	/**
	 * 跳转到列表页
	 * @param map
	 * @return
	 */
	@GetMapping("/page/list")
	public String list(ModelMap map) {
		return "framework/sys_department/sys_department_list";
	}
	
	/**
	 * 跳转到添加页
	 * @param map
	 * @param parentId
	 * @return
	 */
	@GetMapping("/page/add")
	public String add(ModelMap map,
			@RequestParam(required=false, value="parentId") String parentId) {
		
		map.put("parentId", parentId);
		
		return "framework/sys_department/sys_department_add";
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
		
		return "framework/sys_department/sys_department_edit";
	}

}
