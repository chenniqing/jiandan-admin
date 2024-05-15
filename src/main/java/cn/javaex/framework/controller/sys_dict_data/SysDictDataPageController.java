package cn.javaex.framework.controller.sys_dict_data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 数据字典表
 * 
 * @author 陈霓清
 */
@Controller
@RequestMapping("/sys/dict/data")
public class SysDictDataPageController {
	
	/**
	 * 跳转到列表页
	 * @param map
	 * @return
	 */
	@GetMapping("/page/list")
	public String list(ModelMap map) {
		return "framework/sys_dict_data/sys_dict_data_list";
	}
	
	/**
	 * 跳转到添加页
	 * @param map
	 * @return
	 */
	@GetMapping("/page/add")
	public String add() {
		return "framework/sys_dict_data/sys_dict_data_add";
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
		
		return "framework/sys_dict_data/sys_dict_data_edit";
	}

}
