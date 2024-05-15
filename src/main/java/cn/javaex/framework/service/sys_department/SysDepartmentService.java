package cn.javaex.framework.service.sys_department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.dao.sys_department.ISysDepartmentDAO;
import cn.javaex.framework.model.dto.SysDepartmentDTO;
import cn.javaex.framework.model.dto.SysDepartmentSortDTO;
import cn.javaex.framework.model.entity.SysDepartmentEntity;
import cn.javaex.framework.model.vo.SysDepartmentVO;

/**
 * 部门表
 * 
 * @author 陈霓清
 */
@Service
public class SysDepartmentService {
	
	@Autowired
	private ISysDepartmentDAO iSysDepartmentDAO;
	
	/**
	 * 查询列表
	 * @param sysDepartmentQuery
	 * @return
	 */
	public List<SysDepartmentVO> list() {
		// 1. 获取全部部门
		List<SysDepartmentVO> allList = iSysDepartmentDAO.selectAllList();
		// 2. 定义一个map
		Map<String, SysDepartmentVO> itemMap = new HashMap<String, SysDepartmentVO>(allList.size());
		// 3. 定义一级部门list
		List<SysDepartmentVO> rootList = new ArrayList<SysDepartmentVO>();
		
		// 4. 遍历全部部门list
		if (CollectionUtils.isNotEmpty(allList)) {
			// 4.1 第一次遍历，取出1级部门
			for (SysDepartmentVO menu : allList) {
				// 设置map
				itemMap.put(menu.getId(), menu);
				
				if ("0".equals(menu.getParentId())) {
					rootList.add(menu);
				}
			}
			// 4.2 第二次遍历，取出子部门
			for (SysDepartmentVO menu : allList) {
				if (!"0".equals(menu.getParentId())) {
					SysDepartmentVO parent = itemMap.get(menu.getParentId());
					if (parent != null) {
						parent.getChildren().add(menu);
					}
				}
			}
		}
		
		return rootList;
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public SysDepartmentEntity getById(String id) {
		return iSysDepartmentDAO.selectById(id);
	}
	
	/**
	 * 新增
	 * @param sysDepartmentDTO
	 * @throws QingException 
	 */
	public void insert(SysDepartmentDTO sysDepartmentDTO) throws QingException {
		SysDepartmentEntity sysDepartmentEntity = new SysDepartmentEntity();
		BeanUtils.copyProperties(sysDepartmentDTO, sysDepartmentEntity);
		
		// 查询目前最大排序
		int maxSort = iSysDepartmentDAO.selectMaxSortByParentId(sysDepartmentDTO.getParentId());
		sysDepartmentEntity.setSort(++maxSort);
		
		iSysDepartmentDAO.insert(sysDepartmentEntity);
	}
	
	/**
	 * 更新
	 * @param id
	 * @param sysDepartmentDTO
	 * @throws QingException 
	 */
	public void update(String id, SysDepartmentDTO sysDepartmentDTO) throws QingException {
		SysDepartmentEntity sysDepartmentEntity = new SysDepartmentEntity();
		BeanUtils.copyProperties(sysDepartmentDTO, sysDepartmentEntity);
		
		sysDepartmentEntity.setId(id);
		iSysDepartmentDAO.updateById(sysDepartmentEntity);
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 */
	public void deleteById(String id) {
		// 查询出所有子级和自己
		List<String> idList = iSysDepartmentDAO.selectIdListByRecursive(id);
		
		if (CollectionUtils.isNotEmpty(idList)) {
			iSysDepartmentDAO.deleteByIds(idList);
		}
	}

	/**
	 * 保存排序
	 * @param list
	 */
	public void saveSort(List<SysDepartmentSortDTO> list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		
		for (SysDepartmentSortDTO sysDepartmentSortDTO : list) {
			SysDepartmentEntity sysDepartmentEntity = new SysDepartmentEntity();
			sysDepartmentEntity.setId(sysDepartmentSortDTO.getId());
			sysDepartmentEntity.setSort(sysDepartmentSortDTO.getSort());
			
			iSysDepartmentDAO.updateById(sysDepartmentEntity);
		}
	}

	/**
	 * 查询出所有子级和自己
	 * @param departmentId
	 * @return
	 */
	public List<String> listIdsByRecursive(String departmentId) {
		return iSysDepartmentDAO.selectIdListByRecursive(departmentId);
	}
	
}
