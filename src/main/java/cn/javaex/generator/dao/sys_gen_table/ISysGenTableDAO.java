package cn.javaex.generator.dao.sys_gen_table;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.javaex.generator.model.entity.SysGenTableEntity;
import cn.javaex.generator.model.query.SysGenTableQuery;
import cn.javaex.generator.model.vo.SysGenTableVO;
import cn.javaex.mybatisjj.mapper.CommonMapper;

/**
 * 代码生成-数据库表
 * 
 * @author 陈霓清
 */
public interface ISysGenTableDAO extends CommonMapper<SysGenTableEntity> {
	
	/**
	 * 查询列表
	 * @param query
	 * @return
	 */
	List<SysGenTableVO> list(SysGenTableQuery query);

	/**
	 * 唯一性校验
	 * @param tableName
	 * @param id
	 * @return
	 */
	int checkUnique(@Param("tableName") String tableName, @Param("id") String id);

}
