package #ControllerPackage#;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * #TableComment#表
 * 
 * @author #Author#
 * @Date #Date#
 */
@Controller
@RequestMapping("/#TableNameURL#")
public class #ControllerPageClassName# {
	
	/**
	 * 跳转到列表页
	 * @param map
	 * @return
	 */
	@GetMapping("/page/list")
	public String list(ModelMap map) {
		return "#pagePath#/#TableName#/#TableName#_list";
	}
	
	/**
	 * 跳转到添加页
	 * @param map
	 * @return
	 */
	@GetMapping("/page/add")
	public String add() {
		return "#pagePath#/#TableName#/#TableName#_add";
	}
	
	/**
	 * 跳转到编辑页
	 * @param map
	 * @param #primaryKeyField#
	 * @return
	 */
	@GetMapping("/page/edit")
	public String edit(ModelMap map,
			@RequestParam(required=false, value="#primaryKeyField#") String #primaryKeyField#) {
		
		map.put("#primaryKeyField#", #primaryKeyField#);
		
		return "#pagePath#/#TableName#/#TableName#_edit";
	}

}
