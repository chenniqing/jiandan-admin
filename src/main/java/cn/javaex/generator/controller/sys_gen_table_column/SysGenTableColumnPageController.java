package cn.javaex.generator.controller.sys_gen_table_column;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.javaex.generator.basic.enmus.ColumnType;

/**
 * 代码生成-数据库表字段
 * 
 * @author 陈霓清
 */
@Controller
@RequestMapping("/sys/gen/table/column")
public class SysGenTableColumnPageController {
	
	/**
	 * 跳转到列表页
	 * @param map
	 * @param tableId
	 * @param tableName
	 * @return
	 */
	@GetMapping("/page/list")
	public String list(ModelMap map,
			@RequestParam(value="tableId") String tableId,
			@RequestParam(value="tableName") String tableName) {
		
		map.put("tableId", tableId);
		map.put("tableName", tableName);
		
		return "generator/sys_gen_table_column/sys_gen_table_column_list";
	}
	
	/**
	 * 跳转到新增页
	 * @param map
	 * @param tableId
	 * @return
	 */
	@GetMapping("/page/add")
	public String add(ModelMap map,
			@RequestParam(value="tableId") String tableId) {
		// 字段类型
		List<Map<String, String>> typeList = Arrays.stream(ColumnType.values())
		            .map(opt -> {
		                Map<String, String> typeMap = new HashMap<>();
		                typeMap.put("value", opt.getValue());
		                typeMap.put("text", opt.getText());
		                return typeMap;
		            })
		            .collect(Collectors.toList());
 
        map.put("typeList", typeList);
		map.put("tableId", tableId);
		
		return "generator/sys_gen_table_column/sys_gen_table_column_add";
	}
	
	/**
	 * 跳转到编辑页
	 * @param map
	 * @param id
	 * @return
	 */
	@GetMapping("/page/edit")
	public String edit(ModelMap map, @RequestParam(value="id") String id) {
		// 字段类型
		List<Map<String, String>> typeList = Arrays.stream(ColumnType.values())
		            .map(opt -> {
		                Map<String, String> typeMap = new HashMap<>();
		                typeMap.put("value", opt.getValue());
		                typeMap.put("text", opt.getText());
		                return typeMap;
		            })
		            .collect(Collectors.toList());

		map.put("typeList", typeList);
		map.put("id", id);
		
		return "generator/sys_gen_table_column/sys_gen_table_column_edit";
	}
	
	/**
	 * 查看添加字段sql语句
	 * @param map
	 * @param idstrs
	 * @return
	 */
	@GetMapping("/page/view-sql")
	public String viewSql(ModelMap map,
			@RequestParam(value="idstrs") String idstrs) {
		
		map.put("idstrs", idstrs);
		
		return "generator/sys_gen_table_column/sys_gen_table_column_sql";
	}

}
