package cn.javaex.framework.service.sys_dict_data;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.dao.sys_dict_data.ISysDictDataDAO;
import cn.javaex.framework.model.entity.SysDictDataEntity;
import cn.javaex.framework.model.vo.SysDictDataVO;
import cn.javaex.framework.model.dto.SysDictDataDTO;
import cn.javaex.framework.model.query.SysDictDataQuery;

/**
 * 数据字典表
 * 
 * @author 陈霓清
 */
@Service
public class SysDictDataService {
	
	@Autowired
	private ISysDictDataDAO iSysDictDataDAO;
	
	/**
	 * 查询列表
	 * @param sysDictDataQuery
	 * @return
	 */
	public List<SysDictDataVO> list(SysDictDataQuery sysDictDataQuery) {
		return iSysDictDataDAO.list(sysDictDataQuery);
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public SysDictDataEntity getById(String id) {
		return iSysDictDataDAO.selectById(id);
	}
	
	/**
	 * 新增
	 * @param sysDictDataDTO
	 * @throws QingException 
	 */
	public void insert(SysDictDataDTO sysDictDataDTO) throws QingException {
		// 唯一性校验
		this.checkUnique(sysDictDataDTO, null);
		
		SysDictDataEntity sysDictDataEntity = new SysDictDataEntity();
		BeanUtils.copyProperties(sysDictDataDTO, sysDictDataEntity);
		
		// 设为默认值
		if (sysDictDataEntity.getIsDefault() == 1) {
			// 将同编码的其他字典默认值设定取消
			iSysDictDataDAO.updateDefault0ByDictCode(sysDictDataEntity.getDictCode());
		}
		
		iSysDictDataDAO.insert(sysDictDataEntity);
	}
	
	/**
	 * 更新
	 * @param id
	 * @param sysDictDataDTO
	 * @throws QingException 
	 */
	public void update(String id, SysDictDataDTO sysDictDataDTO) throws QingException {
		// 唯一性校验
		this.checkUnique(sysDictDataDTO, id);
		
		SysDictDataEntity sysDictDataEntity = new SysDictDataEntity();
		BeanUtils.copyProperties(sysDictDataDTO, sysDictDataEntity);
		
		// 设为默认值
		if (sysDictDataEntity.getIsDefault() == 1) {
			// 将同编码的其他字典默认值设定取消
			iSysDictDataDAO.updateDefault0ByDictCode(sysDictDataEntity.getDictCode());
		}
		
		sysDictDataEntity.setId(id);
		iSysDictDataDAO.updateById(sysDictDataEntity);
	}
	
	/**
	 * 唯一性校验
	 * @param sysDictDataDTO
	 * @param id
	 */
	private void checkUnique(SysDictDataDTO sysDictDataDTO, String id) {
		// 字典键值唯一性校验
		int count = iSysDictDataDAO.checkUnique(sysDictDataDTO.getDictCode(), sysDictDataDTO.getDictValue(), id);
		if (count > 0) {
			throw new QingException("同一类型下，字典键值已存在");
		}
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 */
	public void deleteById(String id) {
		iSysDictDataDAO.deleteById(id);
	}

	/**
	 * 查询已有的编码列表
	 * @return
	 */
	public List<SysDictDataVO> listAllDictCode() {
		return iSysDictDataDAO.selectDictCodeList();
	}

	/**
	 * 根据字典编码查询选项
	 * @param dictCode
	 * @return
	 */
	public List<SysDictDataVO> listByDictCode(String dictCode) {
		return iSysDictDataDAO.selectListByDictCode(dictCode);
	}
	
}
