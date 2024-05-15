package cn.javaex.generator.dao.sys_gen_table_column;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.javaex.generator.model.entity.SysGenTableColumnEntity;
import cn.javaex.generator.model.query.SysGenTableColumnQuery;
import cn.javaex.generator.model.vo.SysGenTableColumnVO;
import cn.javaex.mybatisjj.mapper.CommonMapper;

/**
 * 代码生成-数据库表字段
 * 
 * @author 陈霓清
 */
public interface ISysGenTableColumnDAO extends CommonMapper<SysGenTableColumnEntity> {
	
	/**
	 * 查询列表
	 * @param sysGenTableColumnQuery
	 * @return
	 */
	List<SysGenTableColumnVO> list(SysGenTableColumnQuery sysGenTableColumnQuery);

	/**
	 * 查询上一条记录
	 * @param tableId
	 * @param sort
	 * @return
	 */
	SysGenTableColumnEntity selectLastBySort(@Param("tableId") String tableId, @Param("sort") int sort);

	/**
	 * 查询下一条记录
	 * @param tableId
	 * @param sort
	 * @return
	 */
	SysGenTableColumnEntity selectNextBySort(@Param("tableId") String tableId, @Param("sort") int sort);

	/**
	 * 唯一性校验
	 * @param tableId
	 * @param columnName
	 * @param id
	 * @return
	 */
	int checkUnique(@Param("tableId") String tableId, @Param("columnName") String columnName, @Param("id") String id);

	/**
	 * 取消所有唯一性字段校验
	 * @param tableId
	 */
	int updateIsUnique0ByTableId(String tableId);

	/**
	 * 查询目前最大排序
	 * @param tableId
	 * @return
	 */
	int selectMaxSortByTableId(String tableId);
	
}
