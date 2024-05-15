package #DAOPackage#;

import java.util.List;
#ImportPackageParam#
import #EntityPackage#.#EntityClassName#Entity;
import #QueryPackage#.#EntityClassName#Query;
import #VOPackage#.#EntityClassName#VO;
import cn.javaex.mybatisjj.mapper.CommonMapper;

/**
 * #TableComment#表
 * 
 * @author #Author#
 * @Date #Date#
 */
public interface #DAOClassName# extends CommonMapper<#EntityClassName#Entity> {
	
	/**
	 * 查询列表
	 * @param #entityClassName#Query
	 * @return
	 */
	List<#EntityClassName#VO> selectList(#EntityClassName#Query #entityClassName#Query);
#UniquenessCheckDAO#
}
