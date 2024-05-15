package cn.javaex.framework.controller.sys_menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.basic.response.Result;
import cn.javaex.framework.model.dto.SysMenuDTO;
import cn.javaex.framework.model.dto.SysMenuSortDTO;
import cn.javaex.framework.model.entity.SysMenuEntity;
import cn.javaex.framework.model.vo.SysMenuVO;
import cn.javaex.framework.service.sys_menu.SysMenuService;

/**
 * 菜单表
 * 
 * @author 陈霓清
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 查询列表
	 * @return
	 */
	@GetMapping("/list-all")
	public Result listAll() {
		
		List<SysMenuVO> list = sysMenuService.listTree();
		
		return Result.success().add("list", list);
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Result getById(@PathVariable(value="id") String id) {
		
		SysMenuEntity sysMenuEntity = sysMenuService.getById(id);
		
		return Result.success().add("item", sysMenuEntity);
	}
	
	/**
	 * 新增
	 * @param sysMenuDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/add")
	public Result add(SysMenuDTO sysMenuDTO) throws QingException {
		
		sysMenuService.insert(sysMenuDTO);
		
		return Result.success();
	}
	
	/**
	 * 更新
	 * @param id
	 * @param sysMenuDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/update/{id}")
	public Result update(
			@PathVariable(value="id") String id,
			SysMenuDTO sysMenuDTO) throws QingException {
		
		sysMenuService.update(id, sysMenuDTO);
		
		return Result.success();
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/delete/{id}")
	public Result delete(@PathVariable(value="id") String id) throws QingException {
		
		sysMenuService.deleteById(id);
		
		return Result.success();
	}
	
	/**
	 * 保存排序
	 * @param list
	 * @return
	 */
	@PostMapping("/sort")
	public Result sort(@RequestBody List<SysMenuSortDTO> list) {
		
		sysMenuService.saveSort(list);
		
		return Result.success();
	}

}
