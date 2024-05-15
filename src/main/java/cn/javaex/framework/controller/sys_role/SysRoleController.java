package cn.javaex.framework.controller.sys_role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.basic.response.Result;
import cn.javaex.framework.model.dto.SysRoleDTO;
import cn.javaex.framework.model.dto.SysRoleMoveDTO;
import cn.javaex.framework.model.entity.SysRoleEntity;
import cn.javaex.framework.model.query.SysRoleQuery;
import cn.javaex.framework.model.vo.SysRoleVO;
import cn.javaex.framework.service.sys_role.SysRoleService;

/**
 * 角色表
 * 
 * @author 陈霓清
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 查询列表
	 * @param sysRoleQuery
	 * @return
	 */
	@GetMapping("/list-all")
	public Result listAll(SysRoleQuery sysRoleQuery) {
		
		List<SysRoleVO> list = sysRoleService.list(sysRoleQuery);
		
		return Result.success().add("list", list);
	}
	
	/**
	 * 查询列表
	 * @param sysRoleQuery
	 * @return
	 */
	@GetMapping("/list")
	public Result list(SysRoleQuery sysRoleQuery) {
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(sysRoleQuery.getPageNum(), sysRoleQuery.getPageSize());
		List<SysRoleVO> list = sysRoleService.list(sysRoleQuery);
		PageInfo<SysRoleVO> pageInfo = new PageInfo<SysRoleVO>(list);
		
		return Result.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 根据主键获取信息
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Result getById(@PathVariable(value="id") String id) {
		SysRoleEntity sysRoleEntity = sysRoleService.getById(id);
		
		return Result.success().add("entity", sysRoleEntity);
	}
	
	/**
	 * 新增角色
	 * @param sysRoleDTO
	 * @return
	 * @throws QingException 
	 */
	@PostMapping("/add")
	public Result add(SysRoleDTO sysRoleDTO) throws QingException {
		
		sysRoleService.insert(sysRoleDTO);
		
		return Result.success();
	}
	
	/**
	 * 更新角色
	 * @param id
	 * @param sysRoleDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/update/{id}")
	public Result update(
			@PathVariable(value="id") String id,
			SysRoleDTO sysRoleDTO) throws QingException {
		
		sysRoleService.update(id, sysRoleDTO);
		
		return Result.success();
	}
	
	/**
	 * 上移下移
	 * @param id
	 * @param dto
	 * @return
	 */
	@PostMapping("/move/{id}")
	public Result move(
			@PathVariable(value="id") String id,
			@RequestBody SysRoleMoveDTO dto) {
		
		sysRoleService.move(id, dto.getAction());
		
		return Result.success();
	}
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public Result delete(@PathVariable(value="id") String id) throws QingException {
		sysRoleService.deleteById(id);
		
		return Result.success();
	}
}
