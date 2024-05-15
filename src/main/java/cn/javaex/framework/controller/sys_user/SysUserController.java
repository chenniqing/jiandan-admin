package cn.javaex.framework.controller.sys_user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.basic.response.Result;
import cn.javaex.framework.model.dto.SysUserChangePasswordDTO;
import cn.javaex.framework.model.dto.SysUserDTO;
import cn.javaex.framework.model.entity.SysUserEntity;
import cn.javaex.framework.model.query.SysUserQuery;
import cn.javaex.framework.model.vo.SysUserVO;
import cn.javaex.framework.service.sys_department.SysDepartmentService;
import cn.javaex.framework.service.sys_role_user_rel.SysRoleUserRelService;
import cn.javaex.framework.service.sys_user.SysUserService;
import cn.javaex.framework.util.UserUtils;
import cn.javaex.htool.crypto.digest.DigestUtils;

/**
 * 用户表
 * 
 * @author 陈霓清
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleUserRelService sysRoleUserService;
	@Autowired
	private SysDepartmentService sysDepartmentService;
	
	/**
	 * 查询列表
	 * @param sysUserQuery
	 * @return
	 */
	@GetMapping("/list")
	public Result list(SysUserQuery sysUserQuery) {
		if (StringUtils.isNotEmpty(sysUserQuery.getDepartmentId())) {
			// 查询出所有子级和自己
			List<String> departmentIdList = sysDepartmentService.listIdsByRecursive(sysUserQuery.getDepartmentId());
			sysUserQuery.setDepartmentIdList(departmentIdList);
		}
		
		if (sysUserQuery.getSorts()!=null && sysUserQuery.getSorts().length>0) {
			StringBuffer sb = new StringBuffer();
			sb.append(" ORDER BY ");
			for (String sort : sysUserQuery.getSorts()) {
				String[] arr = sort.split("=");
				sb.append(arr[0] + " " + arr[1] + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			
			sysUserQuery.setSortStr(sb.toString());
		}
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(sysUserQuery.getPageNum(), sysUserQuery.getPageSize());
		List<SysUserVO> list = sysUserService.list(sysUserQuery);
		PageInfo<SysUserVO> pageInfo = new PageInfo<SysUserVO>(list);
		
		return Result.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Result getById(@PathVariable(value="id") String id) {
		SysUserEntity sysUserEntity = sysUserService.getById(id);
		sysUserEntity.setPassword(null);
		
		// 查询用户的所有角色Id集合
		List<String> roleIdList = sysRoleUserService.listRoleIdByUserId(id);
		
		return Result.success()
				.add("item", sysUserEntity)
				.add("roleIdList", roleIdList)
				;
	}
	
	/**
	 * 新增
	 * @param sysUserDTO
	 * @return
	 * @throws QingException 
	 */
	@PostMapping("/add")
	public Result add(SysUserDTO sysUserDTO) throws QingException {
		if (StringUtils.isNotBlank(sysUserDTO.getPassword())) {
			sysUserDTO.setPassword(DigestUtils.md5(sysUserDTO.getPassword()));
		}
		
		sysUserService.insert(sysUserDTO);
		
		return Result.success();
	}
	
	/**
	 * 更新
	 * @param id
	 * @param sysUserDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/update/{id}")
	public Result update(@PathVariable(value="id") String id, SysUserDTO sysUserDTO) throws QingException {
		if (StringUtils.isNotBlank(sysUserDTO.getPassword())) {
			sysUserDTO.setPassword(DigestUtils.md5(sysUserDTO.getPassword()));
		} else {
			sysUserDTO.setPassword(null);
		}
		
		sysUserService.update(id, sysUserDTO);
		
		return Result.success();
	}
	
	/**
	 * 修改密码
	 * @param sysUserChangePasswordDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/update/password")
	public Result updatePassword(SysUserChangePasswordDTO sysUserChangePasswordDTO) throws QingException {
		SysUserEntity curUser = UserUtils.getCurUser();
		
		if (StringUtils.isBlank(sysUserChangePasswordDTO.getOldPassword())) {
			return Result.error("请输入旧密码");
		}
		if (!curUser.getPassword().equals(DigestUtils.md5(sysUserChangePasswordDTO.getOldPassword()))) {
			return Result.error("旧密码错误");
		}
		
		if (StringUtils.isBlank(sysUserChangePasswordDTO.getNewPassword())) {
			return Result.error("请输入新密码");
		}
		
		sysUserService.updatePassword(curUser.getId(), DigestUtils.md5(sysUserChangePasswordDTO.getNewPassword()));
		
		return Result.success();
	}
	
}
