package #ControllerPackage#;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import #ServicePackage#.#ServiceClassName#;
import #EntityPackage#.#EntityClassName#Entity;
import #VOPackage#.#EntityClassName#VO;
import #DTOPackage#.#EntityClassName#DTO;
import #QueryPackage#.#EntityClassName#Query;
import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.basic.response.Result;

/**
 * #TableComment#表
 * 
 * @author #Author#
 * @Date #Date#
 */
@RestController
@RequestMapping("/#TableNameURL#")
public class #ControllerClassName# {
	
	@Autowired
	private #ServiceClassName# #serviceClassName#;
	
	/**
	 * 查询列表
	 * @param #entityClassName#Query
	 * @return
	 */
	@GetMapping("/list")
	public Result list(#EntityClassName#Query #entityClassName#Query) {
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(#entityClassName#Query.getPageNum(), #entityClassName#Query.getPageSize());
		List<#EntityClassName#VO> list = #serviceClassName#.list(#entityClassName#Query);
		PageInfo<#EntityClassName#VO> pageInfo = new PageInfo<#EntityClassName#VO>(list);
		
		return Result.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 根据主键查询
	 * @param #primaryKeyField#
	 * @return
	 */
	@GetMapping("/get/{#primaryKeyField#}")
	public Result getBy#PrimaryKeyField#(@PathVariable(value="#primaryKeyField#") String #primaryKeyField#) {
		
		#EntityClassName#Entity #entityClassName#Entity = #serviceClassName#.getBy#PrimaryKeyField#(#primaryKeyField#);
		
		return Result.success().add("item", #entityClassName#Entity);
	}
	
	/**
	 * 新增#TableComment#
	 * @param #entityClassName#DTO
	 * @return
	 * @throws QingException 
	 */
	@PostMapping("/add")
	public Result add(#EntityClassName#DTO #entityClassName#DTO) throws QingException {
		
		#serviceClassName#.insert(#entityClassName#DTO);
		
		return Result.success();
	}
	
	/**
	 * 更新#TableComment#
	 * @param #primaryKeyField#
	 * @param #entityClassName#DTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/update/{#primaryKeyField#}")
	public Result update(
			@PathVariable(value="#primaryKeyField#") String #primaryKeyField#,
			#EntityClassName#DTO #entityClassName#DTO) throws QingException {
		
		#serviceClassName#.update(#primaryKeyField#, #entityClassName#DTO);
		
		return Result.success();
	}
	
	/**
	 * 根据主键删除
	 * @param #primaryKeyField#
	 * @return
	 */
	@PostMapping("/delete/{#primaryKeyField#}")
	public Result deleteBy#PrimaryKeyField#(@PathVariable(value="#primaryKeyField#") String #primaryKeyField#) throws QingException {
		
		#serviceClassName#.deleteBy#PrimaryKeyField#(#primaryKeyField#);
		
		return Result.success();
	}

}
