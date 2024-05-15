package cn.javaex.framework.controller.sys_dict_data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.javaex.framework.service.sys_dict_data.SysDictDataService;
import cn.javaex.framework.model.entity.SysDictDataEntity;
import cn.javaex.framework.model.vo.SysDictDataVO;
import cn.javaex.framework.model.dto.SysDictDataDTO;
import cn.javaex.framework.model.query.SysDictDataQuery;
import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.basic.response.Result;

/**
 * 数据字典表
 * 
 * @author 陈霓清
 */
@RestController
@RequestMapping("/sys/dict/data")
public class SysDictDataController {
	
	@Autowired
	private SysDictDataService sysDictDataService;
	
	/**
	 * 根据字典编码查询选项
	 * @param dictCode
	 * @return
	 */
	@GetMapping("/list/{dictCode}")
	public Result listByDictCode(@PathVariable(value="dictCode") String dictCode) {
		
		List<SysDictDataVO> list = sysDictDataService.listByDictCode(dictCode);
		
		return Result.success().add("list", list);
	}
	
	/**
	 * 查询已有的编码列表
	 * @return
	 */
	@GetMapping("/list-all/dict-code")
	public Result listAllDictCode() {
		
		List<SysDictDataVO> list = sysDictDataService.listAllDictCode();
		
		return Result.success().add("list", list);
	}
	
	/**
	 * 查询列表
	 * @param sysDictDataQuery
	 * @return
	 */
	@GetMapping("/list")
	public Result list(SysDictDataQuery sysDictDataQuery) {
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(sysDictDataQuery.getPageNum(), sysDictDataQuery.getPageSize());
		List<SysDictDataVO> list = sysDictDataService.list(sysDictDataQuery);
		PageInfo<SysDictDataVO> pageInfo = new PageInfo<SysDictDataVO>(list);
		
		return Result.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Result getById(@PathVariable(value="id") String id) {
		
		SysDictDataEntity sysDictDataEntity = sysDictDataService.getById(id);
		
		return Result.success().add("item", sysDictDataEntity);
	}
	
	/**
	 * 新增数据字典
	 * @param sysDictDataDTO
	 * @return
	 * @throws QingException 
	 */
	@PostMapping("/add")
	public Result add(SysDictDataDTO sysDictDataDTO) throws QingException {
		
		sysDictDataService.insert(sysDictDataDTO);
		
		return Result.success();
	}
	
	/**
	 * 更新数据字典
	 * @param id
	 * @param sysDictDataDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/update/{id}")
	public Result update(
			@PathVariable(value="id") String id,
			SysDictDataDTO sysDictDataDTO) throws QingException {
		
		sysDictDataService.update(id, sysDictDataDTO);
		
		return Result.success();
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public Result deleteById(@PathVariable(value="id") String id) throws QingException {
		
		sysDictDataService.deleteById(id);
		
		return Result.success();
	}

}
