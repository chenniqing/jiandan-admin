package #ServicePackage#;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.javaex.framework.basic.exception.QingException;
import #DAOPackage#.#DAOClassName#;
import #EntityPackage#.#EntityClassName#Entity;
import #VOPackage#.#EntityClassName#VO;
import #DTOPackage#.#EntityClassName#DTO;
import #QueryPackage#.#EntityClassName#Query;

/**
 * #TableComment#表
 * 
 * @author #Author#
 * @Date #Date#
 */
@Service
public class #ServiceClassName# {
	
	@Autowired
	private #DAOClassName# #daoClassName#;
	
	/**
	 * 查询列表
	 * @param #entityClassName#Query
	 * @return
	 */
	public List<#EntityClassName#VO> list(#EntityClassName#Query #entityClassName#Query) {
		return #daoClassName#.selectList(#entityClassName#Query);
	}
	
	/**
	 * 根据主键查询
	 * @param #primaryKeyField#
	 * @return
	 */
	public #EntityClassName#Entity getBy#PrimaryKeyField#(String #primaryKeyField#) {
		return #daoClassName#.selectById(#primaryKeyField#);
	}
	
	/**
	 * 新增
	 * @param #entityClassName#DTO
	 * @throws QingException 
	 */
	public void insert(#EntityClassName#DTO #entityClassName#DTO) throws QingException {#UniquenessCheckInsert#
		#EntityClassName#Entity #entityClassName#Entity = new #EntityClassName#Entity();
		BeanUtils.copyProperties(#entityClassName#DTO, #entityClassName#Entity);
		
		#daoClassName#.insert(#entityClassName#Entity);
	}
	
	/**
	 * 更新
	 * @param #primaryKeyField#
	 * @param #entityClassName#DTO
	 * @throws QingException 
	 */
	public void update(String #primaryKeyField#, #EntityClassName#DTO #entityClassName#DTO) throws QingException {#UniquenessCheckUpdate#
		#EntityClassName#Entity #entityClassName#Entity = new #EntityClassName#Entity();
		BeanUtils.copyProperties(#entityClassName#DTO, #entityClassName#Entity);
		
		#entityClassName#Entity.set#PrimaryKeyField#(#primaryKeyField#);
		#daoClassName#.updateById(#entityClassName#Entity);
	}
	
	/**
	 * 根据主键删除
	 * @param #primaryKeyField#
	 */
	public void deleteBy#PrimaryKeyField#(String #primaryKeyField#) {
		#daoClassName#.deleteById(#primaryKeyField#);
	}
	
}
