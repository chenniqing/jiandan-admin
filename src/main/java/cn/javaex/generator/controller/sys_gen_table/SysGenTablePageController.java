package cn.javaex.generator.controller.sys_gen_table;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 代码生成-数据库表
 * 
 * @author 陈霓清
 */
@Controller
@RequestMapping("/sys/gen/table")
public class SysGenTablePageController {
	
	/**
	 * 跳转查询列表页面
	 * @return
	 */
	@GetMapping("/page/list")
	public String list() {
		return "generator/sys_gen_table/sys_gen_table_list";
	}
	
	/**
	 * 跳转到新增页面
	 * @param map
	 * @return
	 */
	@GetMapping("/page/add")
	public String add() {
		return "generator/sys_gen_table/sys_gen_table_add";
	}
	
	/**
	 * 跳转到编辑页面
	 * @param map
	 * @param id
	 * @return
	 */
	@GetMapping("/page/edit")
	public String edit(ModelMap map, @RequestParam(value="id") String id) {
		map.put("id", id);
		
		return "generator/sys_gen_table/sys_gen_table_edit";
	}

	/**
	 * 跳转到查看建表sql语句页面
	 * @param map
	 * @param idstrs
	 * @return
	 */
	@GetMapping("/page/view-sql")
	public String viewSql(ModelMap map,
			@RequestParam(value="idstrs") String idstrs) {
		
		map.put("idstrs", idstrs);
		
		return "generator/sys_gen_table/sys_gen_table_sql";
	}
	
	/**
	 * 跳转到从数据库导出表页面
	 * @return
	 */
	@GetMapping("/page/get-table-from-db")
	public String getTableFromDatabase() {
		return "generator/sys_gen_table/sys_gen_table_list_from_db";
	}
	
	/**
	 * 跳转到代码生成页面
	 * @param map
	 * @param idstrs
	 * @return
	 */
	@GetMapping("/page/gen-code")
	public String genCode(ModelMap map,
			@RequestParam(value="idstrs") String idstrs) {
		
		map.put("idstrs", idstrs);
		
		return "generator/sys_gen_table/sys_gen_table_code";
	}
	
}
