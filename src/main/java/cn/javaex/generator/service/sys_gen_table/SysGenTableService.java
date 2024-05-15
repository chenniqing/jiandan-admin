package cn.javaex.generator.service.sys_gen_table;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.model.entity.SysUserEntity;
import cn.javaex.framework.service.sys_user.SysUserService;
import cn.javaex.generator.basic.enmus.DriverType;
import cn.javaex.generator.dao.sys_gen_table.ISysGenTableDAO;
import cn.javaex.generator.model.dto.SysGenTableCodeDTO;
import cn.javaex.generator.model.dto.SysGenTableColumnDTO;
import cn.javaex.generator.model.dto.SysGenTableDTO;
import cn.javaex.generator.model.entity.SysGenTableColumnEntity;
import cn.javaex.generator.model.entity.SysGenTableEntity;
import cn.javaex.generator.model.query.SysGenTableQuery;
import cn.javaex.generator.model.vo.SysGenTableVO;
import cn.javaex.generator.service.sys_gen_table_column.SysGenTableColumnService;

/**
 * 代码生成-数据库表
 * 
 * @author 陈霓清
 */
@Service
public class SysGenTableService {
	
	@Value("${spring.druid.url}")
	private String databaseUrl;

	@Value("${spring.druid.username}")
	private String databaseUsername;

	@Value("${spring.druid.password}")
	private String databasePassword;
	
	@Autowired
	private ISysGenTableDAO iSysGenTableDAO;
	@Autowired
	private SysGenTableColumnService sysGenTableColumnService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private GenCodeService genCodeService;
	
	/**
	 * 查询列表
	 * @param sysGenTableQuery
	 * @return
	 */
	public List<SysGenTableVO> list(SysGenTableQuery sysGenTableQuery) {
		List<SysGenTableVO> list = iSysGenTableDAO.list(sysGenTableQuery);
		
		for (SysGenTableVO sysGenTableVo : list) {
			SysUserEntity createUser = sysUserService.getById(sysGenTableVo.getCreateBy());
			sysGenTableVo.setCreateUsername(createUser != null ? createUser.getUsername() : null);
			
			SysUserEntity updateUser = sysUserService.getById(sysGenTableVo.getUpdateBy());
			sysGenTableVo.setUpdateUsername(updateUser != null ? updateUser.getUsername() : null);
		}
		
		return list;
	}
	
	/**
	 * 根据主键查询信息
	 * @param id
	 * @return
	 */
	public SysGenTableEntity getById(String id) {
		return iSysGenTableDAO.selectById(id);
	}
	
	/**
	 * 新增
	 * @param sysGenTableDTO
	 * @throws QingException 
	 */
	public void insert(SysGenTableDTO sysGenTableDTO) throws QingException {
		// 唯一性校验
		int count = iSysGenTableDAO.checkUnique(sysGenTableDTO.getTableName(), null);
		if (count > 0) {
			throw new QingException("表名已存在");
		}
		
		SysGenTableEntity sysGenTableEntity = new SysGenTableEntity();
		BeanUtils.copyProperties(sysGenTableDTO, sysGenTableEntity);
		
		iSysGenTableDAO.insert(sysGenTableEntity);
		
		// 自动生成 id、create_time、create_by、update_time、update_by 字段
		String tableId = sysGenTableEntity.getId();
		
		// id
		SysGenTableColumnDTO sysGenTableColumnDTO = new SysGenTableColumnDTO();
		sysGenTableColumnDTO.setTableId(tableId);
		sysGenTableColumnDTO.setColumnName("id");
		sysGenTableColumnDTO.setColumnComment("主键");
		sysGenTableColumnDTO.setColumnType("bigint");
		sysGenTableColumnDTO.setLength(20);
		sysGenTableColumnDTO.setIsPrimaryKey(1);
		sysGenTableColumnDTO.setIsNotNull(1);
		sysGenTableColumnService.insert(sysGenTableColumnDTO);
		
		// create_time
		sysGenTableColumnDTO = new SysGenTableColumnDTO();
		sysGenTableColumnDTO.setTableId(tableId);
		sysGenTableColumnDTO.setColumnName("create_time");
		sysGenTableColumnDTO.setColumnComment("创建时间");
		sysGenTableColumnDTO.setColumnType("datetime");
		sysGenTableColumnDTO.setLength(0);
		sysGenTableColumnService.insert(sysGenTableColumnDTO);
		
		// create_by
		sysGenTableColumnDTO = new SysGenTableColumnDTO();
		sysGenTableColumnDTO.setTableId(tableId);
		sysGenTableColumnDTO.setColumnName("create_by");
		sysGenTableColumnDTO.setColumnComment("创建人ID");
		sysGenTableColumnDTO.setColumnType("bigint");
		sysGenTableColumnDTO.setLength(20);
		sysGenTableColumnService.insert(sysGenTableColumnDTO);
		
		// update_time
		sysGenTableColumnDTO = new SysGenTableColumnDTO();
		sysGenTableColumnDTO.setTableId(tableId);
		sysGenTableColumnDTO.setColumnName("update_time");
		sysGenTableColumnDTO.setColumnComment("更新时间");
		sysGenTableColumnDTO.setColumnType("datetime");
		sysGenTableColumnDTO.setLength(0);
		sysGenTableColumnService.insert(sysGenTableColumnDTO);
		
		// update_by
		sysGenTableColumnDTO = new SysGenTableColumnDTO();
		sysGenTableColumnDTO.setTableId(tableId);
		sysGenTableColumnDTO.setColumnName("update_by");
		sysGenTableColumnDTO.setColumnComment("更新人ID");
		sysGenTableColumnDTO.setColumnType("bigint");
		sysGenTableColumnDTO.setLength(20);
		sysGenTableColumnService.insert(sysGenTableColumnDTO);
	}
	
	/**
	 * 更新
	 * @param id 
	 * @param sysGenTableDTO
	 * @throws QingException 
	 */
	public void update(String id, SysGenTableDTO sysGenTableDTO) throws QingException {
		// 唯一性校验
		int count = iSysGenTableDAO.checkUnique(sysGenTableDTO.getTableName(), id);
		if (count > 0) {
			throw new QingException("表名已存在");
		}

		SysGenTableEntity sysGenTableEntity = new SysGenTableEntity();
		BeanUtils.copyProperties(sysGenTableDTO, sysGenTableEntity);
		
		sysGenTableEntity.setId(id);
		iSysGenTableDAO.updateById(sysGenTableEntity);
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 */
	public void deleteById(String id) {
		// 1. 删除表字段
		sysGenTableColumnService.deleteByTableId(id);
		
		// 2. 删除表
		iSysGenTableDAO.deleteById(id);
	}

	/**
	 * 查看建表sql语句
	 * @param ids
	 * @return
	 */
	public String viewSql(String[] ids) {
		StringBuffer sql = new StringBuffer();
		
		for (String tableId : ids) {
			SysGenTableEntity sysGenTableEntity = iSysGenTableDAO.selectById(tableId);
			List<SysGenTableColumnEntity> sysGenTableColumnList = sysGenTableColumnService.listByTableId(tableId);
			
			// 获取创建表的sql语句
			String createTableSql = this.getCreateTableSql(sysGenTableEntity, sysGenTableColumnList);
			
			sql.append("-- " + sysGenTableEntity.getTableName() + (sysGenTableEntity.getTableComment()==null ? "" : " : " + sysGenTableEntity.getTableComment()));
			sql.append(System.lineSeparator());
			sql.append("DROP TABLE IF EXISTS `" + sysGenTableEntity.getTableName() + "`;");
			sql.append(System.lineSeparator());
			sql.append(createTableSql);
			sql.append(System.lineSeparator());
			sql.append(System.lineSeparator());
		}
		
		return sql.toString();
	}

	/**
	 * 获取创建表的sql语句
	 * @param sysGenTableEntity
	 * @param sysGenTableColumnList
	 * @return
	 */
	private String getCreateTableSql(SysGenTableEntity sysGenTableEntity,
			List<SysGenTableColumnEntity> sysGenTableColumnList) {
		// 1. 定义主键变量
		String PRIMARY_KEY = "";
		
		// 2. 构建字符串
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE TABLE `" + sysGenTableEntity.getTableName() + "` (");
		for (SysGenTableColumnEntity sysGenTableColumnEntity : sysGenTableColumnList) {
			sb.append(System.lineSeparator());
			// 判断该字段是否已废弃（0：启用， 1：停用）
			if (sysGenTableColumnEntity.getIsDeleted() == 0) {
				// 判断该字段是不是主键
				// 主键
				if (sysGenTableColumnEntity.getIsPrimaryKey()==1) {
					if ("".equals(PRIMARY_KEY)) {
						PRIMARY_KEY = "`" + sysGenTableColumnEntity.getColumnName() + "`";
					} else {
						PRIMARY_KEY += ",`" + sysGenTableColumnEntity.getColumnName() + "`";
					}
					
					switch (sysGenTableColumnEntity.getColumnType()) {
						case "bigint":
							sb.append("  `" + sysGenTableColumnEntity.getColumnName() + "` bigint(20) NOT NULL AUTO_INCREMENT");
							break;
						case "int":
							sb.append("  `" + sysGenTableColumnEntity.getColumnName() + "` int(11) NOT NULL AUTO_INCREMENT");
							break;
						case "mediumint":
							sb.append("  `" + sysGenTableColumnEntity.getColumnName() + "` mediumint(8) NOT NULL AUTO_INCREMENT");
							break;
						case "smallint":
							sb.append("  `"+ sysGenTableColumnEntity.getColumnName() + "` smallint(6) NOT NULL AUTO_INCREMENT");
							break;
						case "char":
							sb.append("  `" + sysGenTableColumnEntity.getColumnName() + "` char(" + sysGenTableColumnEntity.getLength() + ") NOT NULL");
							break;
						default:
							sb.append("  `" + sysGenTableColumnEntity.getColumnName() + "` varchar(" + sysGenTableColumnEntity.getLength() + ") NOT NULL");
							break;
					}
					
					// 是否有注释
					if (StringUtils.isNotBlank(sysGenTableColumnEntity.getColumnComment())) {
						sb.append(" COMMENT '" + sysGenTableColumnEntity.getColumnComment() + "'");
					}
					sb.append(",");
				}
				// 非主键
				else {
					sb.append("  `" + sysGenTableColumnEntity.getColumnName() + "`");
					sb.append(sysGenTableColumnService.appendColumnStr(sysGenTableColumnEntity));
					sb.append(",");
				}
			}
		}
		
		// 2.1 判断是否存在主键
		if (!"".equals(PRIMARY_KEY)) {
			sb.append(System.lineSeparator());
			sb.append("  PRIMARY KEY (" + PRIMARY_KEY + ") ");
		} else {
			// 删除最后一个,
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(System.lineSeparator());
		sb.append(")");
		
		// 是否有注释
		if (StringUtils.isNotBlank(sysGenTableEntity.getTableComment())) {
			sb.append(" COMMENT '" + sysGenTableEntity.getTableComment() + "';");
		}
		
		return sb.toString();
	}

	/**
	 * 从数据库导出表
	 * @param sysGenTableQuery
	 * @return
	 */
	public List<SysGenTableVO> listFromDatabase(SysGenTableQuery sysGenTableQuery) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DriverType.MYSQL.getValue());
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		// 查询指定项目下已存在的表名字
		List<SysGenTableVO> existingTableList = this.list(sysGenTableQuery);
		List<String> existingTableNameList = existingTableList.stream()
													.map(SysGenTableVO::getTableName)
													.collect(Collectors.toList());
		String[] existingTableNames = existingTableNameList.toArray(new String[existingTableNameList.size()]);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append(" 	TABLE_NAME, ");
		sb.append(" 	TABLE_COMMENT ");
		sb.append(" FROM ");
		sb.append(" 	information_schema.TABLES ");
		sb.append(" WHERE ");
		sb.append(" 	TABLE_SCHEMA = (SELECT DATABASE()) ");
		if (CollectionUtils.isNotEmpty(existingTableNameList)) {
			sb.append(" AND TABLE_NAME NOT IN ( ");
			sb.append("'" + StringUtils.join(existingTableNames, "','") + "'");
			sb.append(" ) ");
		}
		if (StringUtils.isNotEmpty(sysGenTableQuery.getKeyword())) {
			sb.append(" AND (table_name LIKE '%" + sysGenTableQuery.getKeyword() + "%' OR table_comment LIKE '%" + sysGenTableQuery.getKeyword() + "%') ");
		}
		
		RowMapper<SysGenTableVO> rowMapper = new BeanPropertyRowMapper<SysGenTableVO>(SysGenTableVO.class);
		List<SysGenTableVO> list = jdbcTemplate.query(sb.toString(), rowMapper);
		
		return list;
	}

	/**
	 * 从数据库导出表
	 * @param tableNames
	 */
	public void doSynchronyFromDatabase(String[] tableNames) {
		if (tableNames==null || tableNames.length==0) {
			return;
		}
		
		// 1. 连接数据库
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DriverType.MYSQL.getValue());
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		// 遍历所选的表
		for (String tableName : tableNames) {
			// 2. 判断所选表在系统中是否已存在（防止因系统反应慢而造成的误选）
			int tableNameColumn = iSysGenTableDAO.selectCountByColumn("table_name", tableName);
			if (tableNameColumn > 0) {
				continue;
			}
			
			// 3. 查询表的字段信息
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT ");
			sb.append(" 	COLUMN_NAME, ");
			sb.append(" 	COLUMN_COMMENT, ");
			sb.append(" 	DATA_TYPE, ");
			sb.append(" 	COLUMN_TYPE, ");
			sb.append(" 	COLUMN_KEY, ");
			sb.append(" 	IS_NULLABLE, ");
			sb.append(" 	COLUMN_DEFAULT ");
			sb.append(" FROM ");
			sb.append(" 	information_schema.COLUMNS ");
			sb.append(" WHERE ");
			sb.append(" 	TABLE_SCHEMA = (SELECT DATABASE()) ");
			sb.append(" AND TABLE_NAME = '" + tableName + "' ");
			sb.append(" ORDER BY ORDINAL_POSITION ");
			
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
			
			if (list!=null && list.isEmpty()==false) {
				// 4. 插入表 cms_table
				String tableComment = jdbcTemplate.queryForObject("SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = (SELECT DATABASE()) AND TABLE_NAME = '"+tableName+"'", String.class);
				
				SysGenTableDTO sysGenTableDTO = new SysGenTableDTO();
				sysGenTableDTO.setTableName(tableName);
				sysGenTableDTO.setTableComment(tableComment);
				sysGenTableDTO.setIsDeleted(0);
				String tableId = this.insertForSynchrony(sysGenTableDTO);
				
				// 5. 遍历数据库中表的字段属性
				for (Map<String, Object> map : list) {
					SysGenTableColumnDTO sysGenTableColumnDTO = new SysGenTableColumnDTO();
					sysGenTableColumnDTO.setTableId(tableId);
					sysGenTableColumnDTO.setColumnName(map.get("COLUMN_NAME").toString());  // 字段名
					sysGenTableColumnDTO.setColumnComment((String) map.get("COLUMN_COMMENT"));    // 字段表述
					sysGenTableColumnDTO.setColumnType(map.get("DATA_TYPE").toString());          // 字段类型
					
					// 长度和小数点
					String columnType = map.get("COLUMN_TYPE").toString();
					if (columnType.indexOf("(")==-1) {
						sysGenTableColumnDTO.setLength(0);
						sysGenTableColumnDTO.setPoint(0);
					} else {
						// 获取()内的内容
						columnType = columnType.substring(columnType.indexOf("(") + 1, columnType.length() - 1);
						if (columnType.indexOf(",")==-1) {
							sysGenTableColumnDTO.setLength(Integer.valueOf(columnType));
							sysGenTableColumnDTO.setPoint(0);
						} else {
							// decimal类型
							String[] arr = columnType.split(",");
							sysGenTableColumnDTO.setLength(Integer.valueOf(arr[0]));
							sysGenTableColumnDTO.setPoint(Integer.valueOf(arr[1]));
						}
					}
					// 主键
					if (map.get("COLUMN_KEY")!=null && "PRI".equals(map.get("COLUMN_KEY").toString().toUpperCase())) {
						sysGenTableColumnDTO.setIsPrimaryKey(1);
					} else {
						sysGenTableColumnDTO.setIsPrimaryKey(0);
					}
					// 是否不为空
					if (map.get("IS_NULLABLE")!=null && "NO".equals(map.get("IS_NULLABLE").toString().toUpperCase())) {
						sysGenTableColumnDTO.setIsNotNull(1);
					} else {
						sysGenTableColumnDTO.setIsNotNull(0);
					}
					// 默认值
					if (map.get("COLUMN_DEFAULT")!=null && !map.get("COLUMN_DEFAULT").toString().equals("NULL")) {
						sysGenTableColumnDTO.setDefaultValue(map.get("COLUMN_DEFAULT").toString());
					}
					
					// 设置java类型
					sysGenTableColumnService.setJavaType(sysGenTableColumnDTO);
					
					sysGenTableColumnService.insert(sysGenTableColumnDTO);
				}
			}
		}
	}

	/**
	 * 从数据库导出专用
	 * @param sysGenTableDTO
	 * @return
	 */
	private String insertForSynchrony(SysGenTableDTO sysGenTableDTO) {
		SysGenTableEntity sysGenTableEntity = new SysGenTableEntity();
		BeanUtils.copyProperties(sysGenTableDTO, sysGenTableEntity);
		
		iSysGenTableDAO.insert(sysGenTableEntity);
		
		return sysGenTableEntity.getId();
	}

	/**
	 * 同步到数据库
	 * @param ids
	 * @param synchronyType
	 */
	public void synchronyToDatabase(String[] ids, Integer synchronyType) {
		if (ids==null || ids.length==0) {
			return;
		}
		
		// 1. 连接数据库
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DriverType.MYSQL.getValue());
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		// 2. 遍历选择的表
		for (String tableId : ids) {
			SysGenTableEntity sysGenTableEntity = iSysGenTableDAO.selectById(tableId);
			// 禁止操作系统内置表
//			if (sysGenTableEntity.getTableName().startsWith("sys_")) {
//				throw new QingException("无法同步系统内置表");
//			}
			
			List<SysGenTableColumnEntity> sysGenTableColumnList = sysGenTableColumnService.listByTableId(tableId);
			if (CollectionUtils.isEmpty(sysGenTableColumnList)) {
				continue;
			}
			
			// 3. 校验表在数据库中是否已存在
			String sql = "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = (SELECT DATABASE()) AND table_name = '" + sysGenTableEntity.getTableName() + "'";
			int count = jdbcTemplate.queryForObject(sql, Integer.class);
			
			// 4. 普通同步
			if (synchronyType == 1) {
				if (count==0) {
					// 4.1 直接创建表
					String createTableSql = this.getCreateTableSql(sysGenTableEntity, sysGenTableColumnList);
					jdbcTemplate.execute(createTableSql);
				} else {
					// 4.1 获取该表在数据库中的所有字段
					sql = "SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = (SELECT DATABASE()) AND TABLE_NAME = '" + sysGenTableEntity.getTableName() + "'";
					List<String> dbColumnList = jdbcTemplate.queryForList(sql, String.class);
					
					// 4.2 遍历用户自定义的字段
					for (SysGenTableColumnEntity sysGenTableColumnEntity : sysGenTableColumnList) {
						// 4.3 判断该字段是否已废弃（0：启用， 1：停用）
						if (sysGenTableColumnEntity.getIsDeleted() == 0) {
							// 4.4 判断字段是否已存在
							if (dbColumnList.contains(sysGenTableColumnEntity.getColumnName()) == false) {
								StringBuffer sb = new StringBuffer();
								sb.append(" ALTER TABLE `" + sysGenTableEntity.getTableName() + "` ADD `" + sysGenTableColumnEntity.getColumnName() + "` ");
								sb.append(sysGenTableColumnService.appendColumnStr(sysGenTableColumnEntity));
								sb.append(";");
								
								// 5. 执行SQL语句
								jdbcTemplate.execute(sb.toString());
							}
						}
					}
				}
			}
			// 4. 强制同步
			else if (synchronyType == 2) {
				// 4.1 先删除表
				jdbcTemplate.execute("DROP TABLE IF EXISTS `" + sysGenTableEntity.getTableName() + "`;");
				// 4.2 再创建表
				String createTableSql = this.getCreateTableSql(sysGenTableEntity, sysGenTableColumnList);
				jdbcTemplate.execute(createTableSql);
			}
		}
	}

	/**
	 * 代码生成
	 * @param sysGenTableCodeDTO
	 * @param genCodePath
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void genCode(SysGenTableCodeDTO sysGenTableCodeDTO, String genCodePath) throws IOException, ParseException {
		for (String tableId : sysGenTableCodeDTO.getTableIds()) {
			SysGenTableEntity sysGenTableEntity = iSysGenTableDAO.selectById(tableId);
			List<SysGenTableColumnEntity> sysGenTableColumnList = sysGenTableColumnService.listByTableId(tableId);
			
			for (String codeType : sysGenTableCodeDTO.getCodeTypes()) {
				Map<String, String> replaceMap = genCodeService.genReplaceMap(sysGenTableEntity, sysGenTableColumnList, sysGenTableCodeDTO.getPackageName(), sysGenTableCodeDTO.getBusinessName(), sysGenTableCodeDTO.getAuthor());
				
				switch (codeType) {
				case "controller":
					genCodeService.genController(replaceMap, genCodePath);
					genCodeService.genControllerPage(replaceMap, genCodePath);
					break;
				case "service":
					genCodeService.genService(replaceMap, genCodePath, sysGenTableColumnList);
					break;
				case "dao":
					genCodeService.genDaoJava(replaceMap, genCodePath, sysGenTableColumnList);
					genCodeService.genDaoXml(replaceMap, genCodePath, sysGenTableColumnList);
					break;
				case "entity":
					genCodeService.genEntity(replaceMap, genCodePath);
					break;
				case "dto":
					genCodeService.genDTO(replaceMap, genCodePath);
					break;
				case "vo":
					genCodeService.genVO(replaceMap, genCodePath);
					break;
				case "query":
					genCodeService.genQuery(replaceMap, genCodePath);
					break;
				case "freemarker":
					genCodeService.genFreemarkerList(replaceMap, genCodePath, sysGenTableCodeDTO.getPackageName(), sysGenTableCodeDTO.getBusinessName());
					genCodeService.genFreemarkerAdd(replaceMap, genCodePath, sysGenTableCodeDTO.getPackageName(), sysGenTableCodeDTO.getBusinessName());
					genCodeService.genFreemarkerEdit(replaceMap, genCodePath, sysGenTableCodeDTO.getPackageName(), sysGenTableCodeDTO.getBusinessName());
					break;
				default:
					break;
				}
			}
		}
	}

}
