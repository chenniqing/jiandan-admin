package cn.javaex.generator.controller.sys_gen_table_column;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.basic.response.Result;
import cn.javaex.generator.basic.enmus.JavaType;
import cn.javaex.generator.model.dto.SysGenTableColumnDTO;
import cn.javaex.generator.model.dto.SysGenTableColumnMoveDTO;
import cn.javaex.generator.model.entity.SysGenTableColumnEntity;
import cn.javaex.generator.model.entity.SysGenTableEntity;
import cn.javaex.generator.model.query.SysGenTableColumnQuery;
import cn.javaex.generator.model.vo.SysGenTableColumnVO;
import cn.javaex.generator.service.sys_gen_table.SysGenTableService;
import cn.javaex.generator.service.sys_gen_table_column.SysGenTableColumnService;

/**
 * 代码生成-数据库表字段
 * 
 * @author 陈霓清
 */
@RestController
@RequestMapping("/sys/gen/table/column")
public class SysGenTableColumnController {
	
	@Autowired
	private SysGenTableColumnService sysGenTableColumnService;
	@Autowired
	private SysGenTableService sysGenTableService;
	
	/**
	 * 查询列表
	 * @param sysGenTableColumnQuery
	 * @return
	 */
	@GetMapping("/list")
	public Result list(SysGenTableColumnQuery sysGenTableColumnQuery) {
		List<SysGenTableColumnVO> list = sysGenTableColumnService.list(sysGenTableColumnQuery);
		
		// java类型
		List<String> javaTypeList = new ArrayList<String>();
		for (JavaType e : JavaType.values()) {
			javaTypeList.add(e.toString());
		}
		
		return Result.success()
				.add("list", list)
				.add("javaTypeList", javaTypeList)
				;
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Result getById(@PathVariable(value="id") String id) {
		SysGenTableColumnEntity sysGenTableColumnEntity = sysGenTableColumnService.getById(id);
		
		return Result.success().add("item", sysGenTableColumnEntity);
	}
	
	/**
	 * 新增
	 * @param sysGenTableColumnDTO
	 * @return
	 * @throws QingException 
	 */
	@PostMapping("/add")
	public Result add(@Valid @RequestBody SysGenTableColumnDTO sysGenTableColumnDTO) throws QingException {
		sysGenTableColumnService.insert(sysGenTableColumnDTO);
		
		return Result.success();
	}
	
	/**
	 * 更新
	 * @param id
	 * @param sysGenTableColumnDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/update/{id}")
	public Result update(
			@PathVariable(value="id") String id,
			@Valid @RequestBody SysGenTableColumnDTO sysGenTableColumnDTO) throws QingException {
		
		sysGenTableColumnService.update(id, sysGenTableColumnDTO);
		
		return Result.success();
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public Result deleteById(@PathVariable(value="id") String id) {
		sysGenTableColumnService.deleteById(id);
		
		return Result.success();
	}

	/**
	 * 上移、下移
	 * @param id
	 * @param sysGenTableColumnMoveDTO
	 * @return
	 */
	@PostMapping("/move/{id}")
	public Result move(@PathVariable(value="id") String id,
			@Valid @RequestBody SysGenTableColumnMoveDTO sysGenTableColumnMoveDTO) {
		
		sysGenTableColumnService.move(id, sysGenTableColumnMoveDTO);
		
		return Result.success();
	}
	
	/**
	 * 查看添加字段sql语句
	 * @param idstrs
	 * @return
	 */
	@GetMapping("/view-sql")
	public Result viewSql(@RequestParam(value="idstrs") String idstrs) {
		String[] ids = idstrs.split(",");
		
		StringBuffer sql = new StringBuffer();
		
		for (String id : ids) {
			SysGenTableColumnEntity sysGenTableColumnEntity = sysGenTableColumnService.getById(id);
			SysGenTableEntity sysGenTableEntity = sysGenTableService.getById(sysGenTableColumnEntity.getTableId());
			
			StringBuffer sb = new StringBuffer();
			
			sb.append("ALTER TABLE `" + sysGenTableEntity.getTableName() + "` ADD `" + sysGenTableColumnEntity.getColumnName() + "` ");
			sb.append(sysGenTableColumnService.appendColumnStr(sysGenTableColumnEntity));
			sb.append(";");
			
			sql.append(sb.toString());
			sql.append("\r\n");
		}
		
		return Result.success().add("sql", sql.toString());
	}
	
}
