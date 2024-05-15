package cn.javaex.framework.config.aop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.javaex.framework.basic.annotation.DataPermission;
import cn.javaex.framework.basic.constant.DataScopeConstant;
import cn.javaex.framework.config.mybatisjj.DataPermissionFilter;
import cn.javaex.framework.config.mybatisjj.UserDataPermissionInterceptor;
import cn.javaex.framework.model.entity.SysRoleEntity;
import cn.javaex.framework.model.entity.SysUserEntity;
import cn.javaex.framework.service.sys_department.SysDepartmentService;
import cn.javaex.framework.util.UserUtils;

/**
 * 数据权限处理
 * 
 * 陈霓清
 */
@Aspect
@Component
public class DataPermissionAspect {

	@Autowired
	private SysDepartmentService sysDepartmentService;
	
	@Before("@annotation(dataPermission)")
	public void doBefore(JoinPoint joinPoint, DataPermission dataPermission) {
		SysUserEntity curUser = UserUtils.getCurUser();
		List<Integer> dataScopeList = curUser.getRoleList().stream()
										.map(SysRoleEntity::getDataScope)
										.collect(Collectors.toList());
		
		// 在查询之前设置数据权限过滤条件
		DataPermissionFilter filter = new DataPermissionFilter();
		filter.setDeptAlias(dataPermission.deptAlias());
		filter.setUserAlias(dataPermission.userAlias());
		
		// 全部数据权限
		if (dataScopeList.contains(DataScopeConstant.ONE)) {
			filter.setDataScope(DataScopeConstant.ONE);
		}
		// 本部门及以下数据权限
		else if (dataScopeList.contains(DataScopeConstant.TWO)) {
			List<String> departmentIdList = sysDepartmentService.listIdsByRecursive(curUser.getDepartmentId());
			filter.setDepartmentIdList(departmentIdList);
			
			filter.setDataScope(DataScopeConstant.TWO);
		}
		// 本部门数据权限
		else if (dataScopeList.contains(DataScopeConstant.THREE)) {
			List<String> departmentIdList = new ArrayList<String>();
			departmentIdList.add(curUser.getDepartmentId());
			filter.setDepartmentIdList(departmentIdList);
			
			filter.setDataScope(DataScopeConstant.THREE);
		}
		// 仅自己数据权限
		else if (dataScopeList.contains(DataScopeConstant.FOUR)) {
			filter.setUserId(curUser.getId());
			
			filter.setDataScope(DataScopeConstant.FOUR);
		}
		
		UserDataPermissionInterceptor.setPermissionFilter(filter);
	}
	
	@After("@annotation(dataPermission)")
	public void doAfter(JoinPoint joinPoint, DataPermission dataPermission) {
		// 清除数据权限过滤条件
		UserDataPermissionInterceptor.clearPermissionFilter();
	}

}
