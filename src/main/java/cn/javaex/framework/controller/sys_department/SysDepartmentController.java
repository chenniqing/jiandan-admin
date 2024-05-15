package cn.javaex.framework.controller.sys_department;

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
import cn.javaex.framework.model.dto.SysDepartmentDTO;
import cn.javaex.framework.model.dto.SysDepartmentSortDTO;
import cn.javaex.framework.model.entity.SysDepartmentEntity;
import cn.javaex.framework.model.vo.SysDepartmentVO;
import cn.javaex.framework.service.sys_department.SysDepartmentService;

/**
 * 部门表
 * 
 * @author 陈霓清
 */
@RestController
@RequestMapping("/sys/department")
public class SysDepartmentController {
	
	@Autowired
	private SysDepartmentService sysDepartmentService;
	
	/**
	 * 查询列表
	 * @return
	 */
	@GetMapping("/list-all")
	public Result listAll() {
		
		List<SysDepartmentVO> list = sysDepartmentService.list();
		
		return Result.success().add("list", list);
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Result getById(@PathVariable(value="id") String id) {
		
		SysDepartmentEntity sysDepartmentEntity = sysDepartmentService.getById(id);
		
		return Result.success().add("item", sysDepartmentEntity);
	}
	
	/**
	 * 新增部门
	 * @param sysDepartmentDTO
	 * @return
	 * @throws QingException 
	 */
	@PostMapping("/add")
	public Result add(SysDepartmentDTO sysDepartmentDTO) throws QingException {
		
		sysDepartmentService.insert(sysDepartmentDTO);
		
		return Result.success();
	}
	
	/**
	 * 更新部门
	 * @param id
	 * @param sysDepartmentDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/update/{id}")
	public Result update(
			@PathVariable(value="id") String id,
			SysDepartmentDTO sysDepartmentDTO) throws QingException {
		
		sysDepartmentService.update(id, sysDepartmentDTO);
		
		return Result.success();
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public Result deleteById(@PathVariable(value="id") String id) throws QingException {
		
		sysDepartmentService.deleteById(id);
		
		return Result.success();
	}

	/**
	 * 保存排序
	 * @param list
	 * @return
	 */
	@PostMapping("/sort")
	public Result sort(@RequestBody List<SysDepartmentSortDTO> list) {
		
		sysDepartmentService.saveSort(list);
		
		return Result.success();
	}
	
}
