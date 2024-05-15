package cn.javaex.framework.dao.sys_department;

import java.util.List;

import cn.javaex.framework.model.entity.SysDepartmentEntity;
import cn.javaex.framework.model.vo.SysDepartmentVO;
import cn.javaex.mybatisjj.mapper.CommonMapper;

/**
 * 部门表
 * 
 * @author 陈霓清
 */
public interface ISysDepartmentDAO extends CommonMapper<SysDepartmentEntity> {

	/**
	 * 查询所有部门
	 * @return
	 */
	List<SysDepartmentVO> selectAllList();

	/**
	 * 查询当前部门级别下最大排序
	 * @param parentId
	 * @return
	 */
	int selectMaxSortByParentId(String parentId);

	/**
	 * 查询出所有子级和自己
	 * @param id
	 * @return
	 */
	List<String> selectIdListByRecursive(String id);

}
