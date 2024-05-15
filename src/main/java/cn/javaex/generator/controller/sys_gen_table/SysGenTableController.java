package cn.javaex.generator.controller.sys_gen_table;

import java.io.File;
import java.io.IOException;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.basic.response.Result;
import cn.javaex.generator.model.dto.SysGenTableCodeDTO;
import cn.javaex.generator.model.dto.SysGenTableDTO;
import cn.javaex.generator.model.dto.SysGenTableExportDTO;
import cn.javaex.generator.model.dto.SysGenTableSynchronyDTO;
import cn.javaex.generator.model.entity.SysGenTableEntity;
import cn.javaex.generator.model.query.SysGenTableQuery;
import cn.javaex.generator.model.vo.SysGenTableVO;
import cn.javaex.generator.service.sys_gen_table.SysGenTableService;
import cn.javaex.htool.core.id.IdUtils;
import cn.javaex.htool.core.io.FileUtils;
import cn.javaex.htool.core.io.ZipUtils;

/**
 * 代码生成-数据库表
 * 
 * @author 陈霓清
 */
@RestController
@RequestMapping("/sys/gen/table")
public class SysGenTableController {
	
	@Autowired
	private SysGenTableService sysGenTableService;
	
	/**
	 * 查询列表
	 * @param sysGenTableQuery
	 * @return
	 */
	@GetMapping("/list")
	public Result list(SysGenTableQuery sysGenTableQuery) {
		if (sysGenTableQuery.getSorts()!=null && sysGenTableQuery.getSorts().length>0) {
			StringBuffer sb = new StringBuffer();
			sb.append(" ORDER BY ");
			for (String sort : sysGenTableQuery.getSorts()) {
				String[] arr = sort.split("=");
				sb.append(arr[0] + " " + arr[1] + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			
			sysGenTableQuery.setSortStr(sb.toString());
		}
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(sysGenTableQuery.getPageNum(), sysGenTableQuery.getPageSize());
		List<SysGenTableVO> list = sysGenTableService.list(sysGenTableQuery);
		PageInfo<SysGenTableVO> pageInfo = new PageInfo<SysGenTableVO>(list);
		
		return Result.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Result getById(@PathVariable(value="id") String id) {
		
		SysGenTableEntity sysGenTableEntity = sysGenTableService.getById(id);
		
		return Result.success().add("item", sysGenTableEntity);
	}
	
	/**
	 * 新增
	 * @param sysGenTableDTO
	 * @return
	 * @throws QingException 
	 */
	@PostMapping("/add")
	public Result add(@Valid @RequestBody SysGenTableDTO sysGenTableDTO) throws QingException {
		
		sysGenTableService.insert(sysGenTableDTO);
		
		return Result.success();
	}
	
	/**
	 * 更新
	 * @param id
	 * @param sysGenTableDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/update/{id}")
	public Result update(
			@PathVariable(value="id") String id,
			@Valid @RequestBody SysGenTableDTO sysGenTableDTO) throws QingException {
		
		sysGenTableService.update(id, sysGenTableDTO);
		
		return Result.success();
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public Result deleteById(@PathVariable(value="id") String id) {
		
		sysGenTableService.deleteById(id);
		
		return Result.success();
	}
	
	/**
	 * 查看建表sql语句
	 * @param idstrs
	 * @return
	 */
	@GetMapping("/view-sql")
	public Result viewSql(@RequestParam(value="idstrs") String idstrs) {
		String[] ids = idstrs.split(",");
		String sql = sysGenTableService.viewSql(ids);
		
		return Result.success().add("sql", sql);
	}
	
	/**
	 * 从数据库导出表
	 * @param sysGenTableQuery
	 * @return
	 * @throws QingException
	 */
	@GetMapping("/list-from-db")
	public Result listFromDatabase(SysGenTableQuery sysGenTableQuery) throws QingException {
		
		List<SysGenTableVO> list = sysGenTableService.listFromDatabase(sysGenTableQuery);
		
		return Result.success().add("list", list);
	}
	
	/**
	 * 从数据库导出表
	 * @param sysGenTableExportDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/synchrony-from-db")
	public Result synchronyFromDatabase(@Valid @RequestBody SysGenTableExportDTO sysGenTableExportDTO) throws Exception {
		
		sysGenTableService.doSynchronyFromDatabase(sysGenTableExportDTO.getTableNames());
		
		return Result.success();
	}
	
	/**
	 * 同步到数据库
	 * @param sysGenTableSynchronyDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/synchrony-to-db")
	public Result synchronyToDatabase(@Valid @RequestBody SysGenTableSynchronyDTO sysGenTableSynchronyDTO) throws QingException {
		
		sysGenTableService.synchronyToDatabase(sysGenTableSynchronyDTO.getIds(), sysGenTableSynchronyDTO.getSynchronyType());
		
		return Result.success();
	}
	
	/**
	 * 代码生成
	 * @param idArr
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/gen-code")
	public Result genCode(SysGenTableCodeDTO sysGenTableCodeDTO) throws Exception {
		String uuid = IdUtils.getUUID();
		String projectPath = System.getProperty("user.dir");
		String genCodePath = projectPath + File.separator + "gen_code" + File.separator + uuid;
		
		// 代码生成
		sysGenTableService.genCode(sysGenTableCodeDTO, genCodePath);
		
		// 打成压缩包
		String zipPath = genCodePath + File.separator + "代码.zip";
		ZipUtils.zip(genCodePath, zipPath, false);
		
		return Result.success().add("uuid", uuid);
	}
	
	/**
	 * 下载生成的代码
	 * @param uuid
	 * @throws IOException
	 */
	@GetMapping("/download-file/{uuid}")
	public void downloadFile(@PathVariable(value="uuid") String uuid) throws IOException {
		String projectPath = System.getProperty("user.dir");
		projectPath = projectPath + File.separator + "gen_code" + File.separator + uuid;
		String zipPath = projectPath + File.separator + "代码.zip";
		
		FileUtils.downloadFile(zipPath);
	}
	
}
