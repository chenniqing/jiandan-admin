package cn.javaex.framework.dao.sys_dict_data;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.javaex.framework.model.entity.SysDictDataEntity;
import cn.javaex.framework.model.query.SysDictDataQuery;
import cn.javaex.framework.model.vo.SysDictDataVO;
import cn.javaex.mybatisjj.mapper.CommonMapper;

/**
 * 数据字典表
 * 
 * @author 陈霓清
 */
public interface ISysDictDataDAO extends CommonMapper<SysDictDataEntity> {
	
	/**
	 * 查询列表
	 * @param sysDictDataQuery
	 * @return
	 */
	List<SysDictDataVO> list(SysDictDataQuery sysDictDataQuery);

	/**
	 * 查询已有的编码列表
	 * @return
	 */
	List<SysDictDataVO> selectDictCodeList();

	/**
	 * 将同编码的其他字典默认值设定取消
	 * @param dictCode
	 */
	void updateDefault0ByDictCode(String dictCode);

	/**
	 * 根据字典编码查询选项
	 * @param dictCode
	 * @return
	 */
	List<SysDictDataVO> selectListByDictCode(String dictCode);

	/**
	 * 字典键值唯一性校验
	 * @param dictCode
	 * @param dictValue
	 * @param id
	 * @return
	 */
	int checkUnique(@Param("dictCode") String dictCode, @Param("dictValue") String dictValue, @Param("id") String id);

}
