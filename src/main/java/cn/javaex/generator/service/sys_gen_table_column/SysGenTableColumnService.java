package cn.javaex.generator.service.sys_gen_table_column;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.model.entity.SysUserEntity;
import cn.javaex.framework.service.sys_user.SysUserService;
import cn.javaex.generator.basic.enmus.JavaType;
import cn.javaex.generator.dao.sys_gen_table_column.ISysGenTableColumnDAO;
import cn.javaex.generator.model.dto.SysGenTableColumnDTO;
import cn.javaex.generator.model.dto.SysGenTableColumnMoveDTO;
import cn.javaex.generator.model.entity.SysGenTableColumnEntity;
import cn.javaex.generator.model.query.SysGenTableColumnQuery;
import cn.javaex.generator.model.vo.SysGenTableColumnVO;

/**
 * 代码生成-数据库表字段
 * 
 * @author 陈霓清
 */
@Service
public class SysGenTableColumnService {
	
	@Autowired
	private ISysGenTableColumnDAO iSysGenTableColumnDAO;
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 查询列表
	 * @param sysGenTableColumnQuery
	 * @return
	 */
	public List<SysGenTableColumnVO> list(SysGenTableColumnQuery sysGenTableColumnQuery) {
		List<SysGenTableColumnVO> list = iSysGenTableColumnDAO.list(sysGenTableColumnQuery);
		
		for (SysGenTableColumnVO sysGenTableColumnVO : list) {
			SysUserEntity createUser = sysUserService.getById(sysGenTableColumnVO.getCreateBy());
			sysGenTableColumnVO.setCreateUsername(createUser != null ? createUser.getUsername() : null);
			
			SysUserEntity updateUser = sysUserService.getById(sysGenTableColumnVO.getUpdateBy());
			sysGenTableColumnVO.setUpdateUsername(updateUser != null ? updateUser.getUsername() : null);
		}
		
		return list;
	}
	
	/**
	 * 根据主键查询信息
	 * @param id
	 * @return
	 */
	public SysGenTableColumnEntity getById(String id) {
		return iSysGenTableColumnDAO.selectById(id);
	}
	
	/**
	 * 新增
	 * @param sysGenTableColumnDTO
	 * @throws QingException 
	 */
	public void insert(SysGenTableColumnDTO sysGenTableColumnDTO) throws QingException {
		// 参数校验和设置JavaType
		this.checkAndSetJavaType(sysGenTableColumnDTO, null);
		
		SysGenTableColumnEntity sysGenTableColumnEntity = new SysGenTableColumnEntity();
		BeanUtils.copyProperties(sysGenTableColumnDTO, sysGenTableColumnEntity);
		
		// 查询目前最大排序
		int maxSort = iSysGenTableColumnDAO.selectMaxSortByTableId(sysGenTableColumnDTO.getTableId());
		sysGenTableColumnEntity.setSort(++maxSort);
		
		sysGenTableColumnEntity.setIsQuery(0);
		sysGenTableColumnEntity.setIsUnique(0);
		sysGenTableColumnEntity.setIsDeleted(0);
		iSysGenTableColumnDAO.insert(sysGenTableColumnEntity);
	}
	
	/**
	 * 更新
	 * @param id 
	 * @param sysGenTableColumnDTO
	 * @throws QingException 
	 */
	public void update(String id, SysGenTableColumnDTO sysGenTableColumnDTO) throws QingException {
		// 参数校验和设置JavaType
		this.checkAndSetJavaType(sysGenTableColumnDTO, id);
		
		// 唯一性校验字段的设置
		// 若当前字段设置为唯一性校验，则先将所有字段取消唯一性校验
		if (sysGenTableColumnDTO.getIsUnique()!=null && sysGenTableColumnDTO.getIsUnique()==1) {
			SysGenTableColumnEntity sysGenTableColumnEntity = iSysGenTableColumnDAO.selectById(id);
			iSysGenTableColumnDAO.updateIsUnique0ByTableId(sysGenTableColumnEntity.getTableId());
		}
		
		SysGenTableColumnEntity sysGenTableColumnEntity = new SysGenTableColumnEntity();
		BeanUtils.copyProperties(sysGenTableColumnDTO, sysGenTableColumnEntity);
		
		sysGenTableColumnEntity.setId(id);
		
		iSysGenTableColumnDAO.updateById(sysGenTableColumnEntity);
	}
	
	/**
	 * 参数校验和设置JavaType
	 * @param sysGenTableColumnDTO
	 * @param id
	 */
	private void checkAndSetJavaType(SysGenTableColumnDTO sysGenTableColumnDTO, String id) {
		// 唯一性校验
		int count = iSysGenTableColumnDAO.checkUnique(sysGenTableColumnDTO.getTableId(), sysGenTableColumnDTO.getColumnName(), id);
		if (count > 0) {
			throw new QingException("字段名已存在");
		}
		
		// 生成java类型
		this.setJavaType(sysGenTableColumnDTO);
		
		// 主键和不为空的设置
		if (sysGenTableColumnDTO.getIsPrimaryKey()==null) {
			sysGenTableColumnDTO.setIsPrimaryKey(0);
		}
		if (sysGenTableColumnDTO.getIsNotNull()==null) {
			sysGenTableColumnDTO.setIsNotNull(0);
		}
	}

	/**
	 * 根据主键删除
	 * @param id
	 */
	public void deleteById(String id) {
		iSysGenTableColumnDAO.deleteById(id);
	}

	/**
	 * 上移、下移
	 * @param id
	 * @param sysGenTableColumnMoveDTO
	 */
	public void move(String id, SysGenTableColumnMoveDTO sysGenTableColumnMoveDTO) {
		// 1. 查询当前记录的信息
		SysGenTableColumnEntity curSysGenTableColumnEntity = iSysGenTableColumnDAO.selectById(id);
		
		SysGenTableColumnEntity sysGenTableColumnEntity = null;
		int tempSort = curSysGenTableColumnEntity.getSort();
		
		// 2. 上移下移判断
		// 上移
		if ("up".equals(sysGenTableColumnMoveDTO.getAction())) {
			// 2.1 查询上一条记录
			sysGenTableColumnEntity = iSysGenTableColumnDAO.selectLastBySort(curSysGenTableColumnEntity.getTableId(), curSysGenTableColumnEntity.getSort());
		}
		// 下移
		else if ("down".equals(sysGenTableColumnMoveDTO.getAction())) {
			// 2.1 查询下一条记录
			sysGenTableColumnEntity = iSysGenTableColumnDAO.selectNextBySort(curSysGenTableColumnEntity.getTableId(), curSysGenTableColumnEntity.getSort());
		} else {
			throw new QingException("up=上移；down=下移。");
		}
		
		// 2.2 跳过本身就是第一或最后一条记录的
		if (sysGenTableColumnEntity==null) {
			return;
		}
		
		// 2.3 排序sort互换
		curSysGenTableColumnEntity.setSort(sysGenTableColumnEntity.getSort());
		sysGenTableColumnEntity.setSort(tempSort);
		
		// 3. 更新
		iSysGenTableColumnDAO.updateById(curSysGenTableColumnEntity);
		iSysGenTableColumnDAO.updateById(sysGenTableColumnEntity);
	}

	/**
	 * 根据表ID删除
	 * @param tableId
	 */
	public void deleteByTableId(String tableId) {
		iSysGenTableColumnDAO.deleteByColumn("table_id", tableId);
	}

	/**
	 * 追加字段字符串
	 * @param sysGenTableColumnEntity
	 * @return
	 */
	public String appendColumnStr(SysGenTableColumnEntity sysGenTableColumnEntity) {
		StringBuffer sb = new StringBuffer();
		// 类型
		String type = sysGenTableColumnEntity.getColumnType();
		
		switch (type) {
			case "bigint":
				sb.append(" bigint(" + sysGenTableColumnEntity.getLength() + ") ");
				break;
			case "int":
				sb.append(" int(" + sysGenTableColumnEntity.getLength() + ") ");
				break;
			case "mediumint":
				sb.append(" mediumint(" + sysGenTableColumnEntity.getLength() + ") ");
				break;
			case "smallint":
				sb.append(" smallint(" + sysGenTableColumnEntity.getLength() + ") ");
				break;
			case "tinyint":
				sb.append(" tinyint(1) ");
				break;
			case "varchar":
				sb.append(" varchar(" + sysGenTableColumnEntity.getLength() + ") ");
				break;
			case "char":
				sb.append(" char(" + sysGenTableColumnEntity.getLength() + ") ");
				break;
			case "decimal":
				sb.append(" decimal(" + sysGenTableColumnEntity.getLength() + ", " + sysGenTableColumnEntity.getPoint() + ") ");
				break;
			case "double":
				sb.append(" double ");
				break;
			case "datetime":
				sb.append(" datetime ");
				break;
			case "date":
				sb.append(" date ");
				break;
			case "text":
				sb.append(" text ");
				break;
			default:
				break;
		}
		
		// 是否不为空
		if (sysGenTableColumnEntity.getIsNotNull()==1) {
			sb.append(" NOT NULL ");
		}
		// 是否有默认值
		if (StringUtils.isNotBlank(sysGenTableColumnEntity.getDefaultValue())) {
			sb.append(" DEFAULT '" + sysGenTableColumnEntity.getDefaultValue() + "' ");
		} else {
			if (sysGenTableColumnEntity.getIsNotNull()==0) {
				sb.append(" DEFAULT NULL ");
			}
		}
		// 是否有注释
		if (StringUtils.isNotBlank(sysGenTableColumnEntity.getColumnComment())) {
			sb.append(" COMMENT '" + sysGenTableColumnEntity.getColumnComment() + "'");
		}
		
		return sb.toString();
	}

	/**
	 * 设置java类型
	 * @param sysGenTableColumnDTO
	 */
	public void setJavaType(SysGenTableColumnDTO sysGenTableColumnDTO) {
		if (sysGenTableColumnDTO.getJavaType()==null && sysGenTableColumnDTO.getColumnName()!=null && sysGenTableColumnDTO.getColumnType()!=null) {
			String type = sysGenTableColumnDTO.getColumnType();
			
			if (sysGenTableColumnDTO.getColumnName().toLowerCase().endsWith("id") || sysGenTableColumnDTO.getColumnName().toLowerCase().endsWith("by")) {
				sysGenTableColumnDTO.setJavaType(JavaType.String.toString());
			}
			else if (type.equals("bigint")) {
				sysGenTableColumnDTO.setJavaType(JavaType.Long.toString());
			}
			else if (type.equals("int") || type.equals("smallint") || type.equals("tinyint")) {
				sysGenTableColumnDTO.setJavaType(JavaType.Integer.toString());
			}
			else if (type.equals("double")) {
				sysGenTableColumnDTO.setJavaType(JavaType.Double.toString());
			}
			else if (type.equals("datetime")) {
				sysGenTableColumnDTO.setJavaType(JavaType.LocalDateTime.toString());
			}
			else if (type.equals("date")) {
				sysGenTableColumnDTO.setJavaType(JavaType.LocalDate.toString());
			}
			else if (type.equals("decimal")) {
				sysGenTableColumnDTO.setJavaType(JavaType.BigDecimal.toString());
			}
			else {
				sysGenTableColumnDTO.setJavaType(JavaType.String.toString());
			}
		}
	}

	/**
	 * 根据tableId查询列表
	 * @param tableId
	 * @return
	 */
	public List<SysGenTableColumnEntity> listByTableId(String tableId) {
		List<SysGenTableColumnEntity> sysGenTableColumnEntityList = iSysGenTableColumnDAO.selectListByColumn("table_id", tableId);
		
		// sysGenTableColumnList 按照 sort 排序
		Collections.sort(sysGenTableColumnEntityList, new Comparator<SysGenTableColumnEntity>() {
			@Override
			public int compare(SysGenTableColumnEntity o1, SysGenTableColumnEntity o2) {
				return Integer.compare(o1.getSort(), o2.getSort());
			}
		});
		
		return sysGenTableColumnEntityList;
	}
	
}
