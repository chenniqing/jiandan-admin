package cn.javaex.generator.service.sys_gen_table;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cn.javaex.generator.basic.constant.GenConstant;
import cn.javaex.generator.model.entity.SysGenTableColumnEntity;
import cn.javaex.generator.model.entity.SysGenTableEntity;
import cn.javaex.htool.core.date.DateUtils;
import cn.javaex.htool.core.date.constant.DatePattern;
import cn.javaex.htool.core.io.FileUtils;
import cn.javaex.htool.core.io.IOUtils;
import cn.javaex.htool.core.string.StringUtils;

@Service
public class GenCodeService {
	
	/**
	 * 生成替换Map
	 * @param table
	 * @param tableColumnList
	 * @param packageName
	 * @param businessName 
	 * @param author 
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, String> genReplaceMap(SysGenTableEntity table,
			List<SysGenTableColumnEntity> tableColumnList, String packageName, String businessName, String author) throws ParseException {
		Map<String, String> replaceMap = new HashMap<String, String>();
		
		if (StringUtils.isNotEmpty(businessName)) {
			businessName = "." + businessName;
		}
		
		// 基础包路径  cn.javaex.packageName.
		String basePackage = GenConstant.DOMAIN_NAME +  "." + StringUtils.toUnderlineName(packageName) + ".";
		// 表描述
		Optional.ofNullable(table.getTableComment())
				        .map(comment -> comment.endsWith("表") ? StringUtils.removeLastChar(comment) : comment)
				        .ifPresent(comment -> replaceMap.put("#TableComment#", comment));
		replaceMap.putIfAbsent("#TableComment#", "");
		// 作者
		replaceMap.put("#Author#", StringUtils.isEmpty(author) ? System.getProperty("user.name") : author);
		// 创建时间
		replaceMap.put("#Date#", DateUtils.format(LocalDate.now(), DatePattern.yyyy年MM月dd日));
		// 实体对象包路径
		replaceMap.put("#EntityPackage#", basePackage + "model.entity" + businessName);
		// DTO类包路径
		replaceMap.put("#DTOPackage#", basePackage + "model.dto" + businessName);
		// VO对象包路径
		replaceMap.put("#VOPackage#", basePackage + "model.vo" + businessName);
		// Query类包路径
		replaceMap.put("#QueryPackage#", basePackage + "model.query" + businessName);
		// 实体类名
		String EntityClassName = StringUtils.toCapitalizeCamelCase(table.getTableName());
		replaceMap.put("#EntityClassName#", EntityClassName);
		// 实体对象名
		replaceMap.put("#entityClassName#", StringUtils.uncapitalize(EntityClassName));
		// Controller
		replaceMap.put("#ControllerPackage#", basePackage + "controller" + businessName + "." + table.getTableName());
		replaceMap.put("#ControllerClassName#", EntityClassName + "Controller");
		replaceMap.put("#ControllerPageClassName#", EntityClassName + "PageController");
		replaceMap.put("#pagePath#", (packageName + businessName).replace(".", "/"));
		// Service
		replaceMap.put("#ServicePackage#", basePackage + "service" + businessName + "." + table.getTableName());
		replaceMap.put("#ServiceClassName#", EntityClassName + "Service");
		replaceMap.put("#serviceClassName#", StringUtils.uncapitalize(EntityClassName + "Service"));
		// DAO
		replaceMap.put("#DAOPackage#", basePackage + "dao" + businessName + "." + table.getTableName());
		replaceMap.put("#DAOClassName#", "I" + EntityClassName + "DAO");
		replaceMap.put("#daoClassName#", StringUtils.uncapitalize("I" + EntityClassName + "DAO"));
		// 实体对应数据库表名
		replaceMap.put("#TableName#", table.getTableName());
		replaceMap.put("#TableNameURL#", table.getTableName().replace("_", "/"));
		// 主键名称
		for (SysGenTableColumnEntity tableColumn : tableColumnList) {
			if (tableColumn.getIsPrimaryKey()==1) {
				replaceMap.put("#PrimaryKeyField#", StringUtils.toCapitalizeCamelCase(tableColumn.getColumnName()));
				replaceMap.put("#primaryKeyField#", StringUtils.toCamelCase(tableColumn.getColumnName()));
				break;
			}
		}
		
		// import列表
		StringBuffer imports = new StringBuffer();
		imports.append(System.lineSeparator());
		StringBuffer importDTOs = new StringBuffer();
		importDTOs.append(System.lineSeparator());
		StringBuffer importVOs = new StringBuffer();
		importVOs.append(System.lineSeparator());
		// 实体的所有属性定义（成员变量）列表
		StringBuffer definitions = new StringBuffer();
		StringBuffer definitionVOs = new StringBuffer();
		StringBuffer definitionDTOs = new StringBuffer();
		StringBuffer definitionQuerys = new StringBuffer();
		// 实体的所有属性getter、setter方法
		StringBuffer getterSetters = new StringBuffer();
		StringBuffer getterSetterDTOs = new StringBuffer();
		StringBuffer getterSetterQuerys = new StringBuffer();
		
		// XML映射文件
		StringBuffer querys = new StringBuffer();
		
		// Freemarker
		// 表头
		StringBuffer tableHeaders = new StringBuffer();
		// 列
		StringBuffer tableCells = new StringBuffer();
		// 显示个数
		int tableHeaderCount = 2;
		// 编辑项
		StringBuffer editFormFields = new StringBuffer();
		// 查询参数
		StringBuffer queryParamInputs = new StringBuffer();
		StringBuffer queryParams = new StringBuffer();
		StringBuffer queryParamValues = new StringBuffer();
		StringBuffer queryParamValueNulls = new StringBuffer();
		
		// 实体类中，增加关联类型的属性 及其getter、setter方法
		for (SysGenTableColumnEntity tableColumn : tableColumnList) {
			// 成员变量类型
			String propertyType = tableColumn.getJavaType();
			// 成员变量
			String propertyName = StringUtils.toCamelCase(tableColumn.getColumnName());
			
			// 判断是否需要追加import
			if (propertyType.equals("Date")) {
				if (imports.toString().indexOf("java.util.Date") == -1) {
					imports.append("import java.util.Date;" + System.lineSeparator());
					importVOs.append("import java.util.Date;" + System.lineSeparator());
				}
			}
			else if (propertyType.equals("LocalDateTime")) {
				if (imports.toString().indexOf("java.time.LocalDateTime") == -1) {
					imports.append("import java.time.LocalDateTime;" + System.lineSeparator());
					importVOs.append("import java.time.LocalDateTime;" + System.lineSeparator());
				}
			}
			else if (propertyType.equals("LocalDate")) {
				if (imports.toString().indexOf("java.time.LocalDate") == -1) {
					imports.append("import java.time.LocalDate;" + System.lineSeparator());
					importVOs.append("import java.time.LocalDate;" + System.lineSeparator());
				}
			}
			else if (propertyType.equals("BigDecimal")) {
				if (imports.toString().indexOf("java.math.BigDecimal") == -1) {
					imports.append("import java.math.BigDecimal;" + System.lineSeparator());
					importVOs.append("import java.math.BigDecimal;" + System.lineSeparator());
				}
			}
			
			// 成员变量列表
			if (tableColumn.getIsPrimaryKey() == 1) {
				definitions.append("	@TableId(value = \"" + tableColumn.getColumnName() + "\")");
				definitions.append(System.lineSeparator());
				
				imports.append("import cn.javaex.mybatisjj.basic.annotation.TableId;" + System.lineSeparator());
			}
			definitions.append("	private " + propertyType + " " + propertyName + ";");
			definitionVOs.append("	private " + propertyType + " " + propertyName + ";");
			
			if (StringUtils.isNotEmpty(tableColumn.getColumnComment())) {
				if (("private " + propertyType + " " + propertyName + ";").length() >= 40) {
					definitions.append("  // " + tableColumn.getColumnComment());
					definitionVOs.append("  // " + tableColumn.getColumnComment());
				} else {
					for (int i=0; i<(40-("private " + propertyType + " " + propertyName + ";").length()); i++) {
						definitions.append(" ");
						definitionVOs.append(" ");
					}
					definitions.append("// " + tableColumn.getColumnComment());
					definitionVOs.append("// " + tableColumn.getColumnComment());
				}
			}
			definitions.append(System.lineSeparator());
			definitionVOs.append(System.lineSeparator());
			
			// DTO成员变量列表
			if (tableColumn.getIsPrimaryKey()==1 
					|| tableColumn.getColumnName().equals("create_time")
					|| tableColumn.getColumnName().equals("create_by")
					|| tableColumn.getColumnName().equals("update_time")
					|| tableColumn.getColumnName().equals("update_by")
					) {
				// 不做处理
			} else {
				definitionDTOs.append("	private " + propertyType + " " + propertyName + ";");
				if (StringUtils.isNotEmpty(tableColumn.getColumnComment())) {
					if (("private " + propertyType + " " + propertyName + ";").length() >= 40) {
						definitionDTOs.append("  // " + tableColumn.getColumnComment());
					} else {
						for (int i=0; i<(40-("private " + propertyType + " " + propertyName + ";").length()); i++) {
							definitionDTOs.append(" ");
						}
						definitionDTOs.append("// " + tableColumn.getColumnComment());
					}
				}
				definitionDTOs.append(System.lineSeparator());
				
				// 判断DTO是否需要追加import
				if (propertyType.equals("Date")) {
					if (importDTOs.toString().indexOf("java.util.Date") == -1) {
						importDTOs.append("import java.util.Date;" + System.lineSeparator());
					}
				}
				else if (propertyType.equals("LocalDateTime")) {
					if (importDTOs.toString().indexOf("java.time.LocalDateTime") == -1) {
						importDTOs.append("import java.time.LocalDateTime;" + System.lineSeparator());
					}
				}
				else if (propertyType.equals("LocalDate")) {
					if (importDTOs.toString().indexOf("java.time.LocalDate") == -1) {
						importDTOs.append("import java.time.LocalDate;" + System.lineSeparator());
					}
				}
				else if (propertyType.equals("BigDecimal")) {
					if (importDTOs.toString().indexOf("java.math.BigDecimal") == -1) {
						importDTOs.append("import java.math.BigDecimal;" + System.lineSeparator());
					}
				}
			}
			
			// Query成员变量列表
			if (tableColumn.getIsQuery() == 1) {
				definitionQuerys.append("	private " + propertyType + " " + propertyName + ";");
				if (StringUtils.isNotEmpty(tableColumn.getColumnComment())) {
					if (("private " + propertyType + " " + propertyName + ";").length() >= 40) {
						definitionQuerys.append("  // " + tableColumn.getColumnComment());
					} else {
						for (int i=0; i<(40-("private " + propertyType + " " + propertyName + ";").length()); i++) {
							definitionQuerys.append(" ");
						}
						definitionQuerys.append("// " + tableColumn.getColumnComment());
					}
				}
				definitionQuerys.append(System.lineSeparator());
			}
			
			
			/** 属性名，首字母大写，可用于拼getter、setter方法 */
			String propertyCapName = StringUtils.capitalize(propertyName);
			// getter、setter方法列表
			getterSetters.append("	/**" + System.lineSeparator());
			getterSetters.append("	 * 得到" + tableColumn.getColumnComment() + System.lineSeparator());
			getterSetters.append("	 * @return" + System.lineSeparator());
			getterSetters.append("	 */" + System.lineSeparator());
			getterSetters.append("	public " + propertyType + " get" + propertyCapName + "() {" + System.lineSeparator());
			getterSetters.append("		return " + propertyName + ";" + System.lineSeparator());
			getterSetters.append("	}" + System.lineSeparator());
			getterSetters.append("	/**" + System.lineSeparator());
			getterSetters.append("	 * 设置" + tableColumn.getColumnComment() + System.lineSeparator());
			getterSetters.append("	 * @param " + propertyName + System.lineSeparator());
			getterSetters.append("	 */"+System.lineSeparator());
			getterSetters.append("	public void set" + propertyCapName + "(" + propertyType + " " + propertyName + ") {" + System.lineSeparator());
			getterSetters.append("		this." + propertyName + " = " + propertyName + ";" + System.lineSeparator());
			getterSetters.append("	}" + System.lineSeparator());
			
			// DTO类代码
			if (tableColumn.getIsPrimaryKey()==1 
					|| tableColumn.getColumnName().equals("create_time")
					|| tableColumn.getColumnName().equals("create_by")
					|| tableColumn.getColumnName().equals("update_time")
					|| tableColumn.getColumnName().equals("update_by")
					) {
				// 不做处理
			} else {
				getterSetterDTOs.append("	/**" + System.lineSeparator());
				getterSetterDTOs.append("	 * 得到" + tableColumn.getColumnComment() + System.lineSeparator());
				getterSetterDTOs.append("	 * @return" + System.lineSeparator());
				getterSetterDTOs.append("	 */" + System.lineSeparator());
				getterSetterDTOs.append("	public " + propertyType + " get" + propertyCapName + "() {" + System.lineSeparator());
				getterSetterDTOs.append("		return " + propertyName + ";" + System.lineSeparator());
				getterSetterDTOs.append("	}" + System.lineSeparator());
				getterSetterDTOs.append("	/**" + System.lineSeparator());
				getterSetterDTOs.append("	 * 设置" + tableColumn.getColumnComment() + System.lineSeparator());
				getterSetterDTOs.append("	 * @param " + propertyName + System.lineSeparator());
				getterSetterDTOs.append("	 */"+System.lineSeparator());
				getterSetterDTOs.append("	public void set" + propertyCapName + "(" + propertyType + " " + propertyName + ") {" + System.lineSeparator());
				getterSetterDTOs.append("		this." + propertyName + " = " + propertyName + ";" + System.lineSeparator());
				getterSetterDTOs.append("	}" + System.lineSeparator());
				getterSetterDTOs.append(System.lineSeparator());
			}
			
			// Query类 getset
			if (tableColumn.getIsQuery() == 1) {
				getterSetterQuerys.append("	/**" + System.lineSeparator());
				getterSetterQuerys.append("	 * 得到" + tableColumn.getColumnComment() + System.lineSeparator());
				getterSetterQuerys.append("	 * @return" + System.lineSeparator());
				getterSetterQuerys.append("	 */" + System.lineSeparator());
				getterSetterQuerys.append("	public " + propertyType + " get" + propertyCapName + "() {" + System.lineSeparator());
				getterSetterQuerys.append("		return " + propertyName + ";" + System.lineSeparator());
				getterSetterQuerys.append("	}" + System.lineSeparator());
				getterSetterQuerys.append("	/**" + System.lineSeparator());
				getterSetterQuerys.append("	 * 设置" + tableColumn.getColumnComment() + System.lineSeparator());
				getterSetterQuerys.append("	 * @param " + propertyName + System.lineSeparator());
				getterSetterQuerys.append("	 */"+System.lineSeparator());
				getterSetterQuerys.append("	public void set" + propertyCapName + "(" + propertyType + " " + propertyName + ") {" + System.lineSeparator());
				getterSetterQuerys.append("		this." + propertyName + " = " + propertyName + ";" + System.lineSeparator());
				getterSetterQuerys.append("	}" + System.lineSeparator());
				getterSetterQuerys.append(System.lineSeparator());
			}
			
			// XML映射文件
			if (tableColumn.getIsQuery() == 1) {
				querys.append(System.lineSeparator());
				if ("String".equals(tableColumn.getJavaType())) {
					querys.append("		<if test=\"" + propertyName + "!=null and " + propertyName + "!=''\">");
				} else {
					querys.append("		<if test=\"" + propertyName + "!=null\">");
				}
				querys.append(System.lineSeparator());
				querys.append("			AND " + tableColumn.getColumnName() + " LIKE CONCAT('%', #{" + propertyName + "}, '%')");
				querys.append(System.lineSeparator());
				querys.append("		</if>");
			}
			
			// List列表页
			if (tableColumn.getIsPrimaryKey() == 1) {
				// 不处理
			} else {
				String thHeader = StringUtils.isEmpty(tableColumn.getColumnComment()) ? tableColumn.getColumnName() : tableColumn.getColumnComment();
				// 表头
				if (StringUtils.isNotEmpty(tableHeaders.toString())) {
					tableHeaders.append(System.lineSeparator());
				}
				tableHeaders.append("									<th>"+thHeader+"</th>");
				// 列
				if (StringUtils.isNotEmpty(tableCells.toString())) {
					tableCells.append(System.lineSeparator());
				}
				if (propertyType.equals("Date") || propertyType.equals("LocalDateTime")) {
					tableCells.append("						html += '	<td>'+javaex.dateFormat(data."+propertyName+", 'yyyy-MM-dd HH:mm:ss')+'</td>';");
				} else if (propertyType.equals("LocalDate")) {
					tableCells.append("						html += '	<td>'+javaex.dateFormat(data."+propertyName+", 'yyyy-MM-dd')+'</td>';");
				} else {
					if (tableColumn.getIsNotNull()==1) {
						tableCells.append("						html += '	<td>'+data."+propertyName+"+'</td>';");
					} else {
						tableCells.append("						html += '	<td>'+javaex.ifnull(data."+propertyName+")+'</td>';");
					}
				}
				
				// 显示个数
				tableHeaderCount++;
			}
			// 查询参数
			if (tableColumn.getIsQuery() == 1) {
				queryParamInputs.append(System.lineSeparator());
				queryParamInputs.append("							"+tableColumn.getColumnComment()+"：<input type=\"text\" class=\"javaex-text mr-10\" id=\""+propertyName+"\" placeholder=\"请输入"+tableColumn.getColumnComment()+"\" />");
			
				queryParams.append(System.lineSeparator());
				queryParams.append("	\""+propertyName+"\" : \"\",");
				
				queryParamValues.append(System.lineSeparator());
				queryParamValues.append("	param."+propertyName+" = \\$(\"#"+propertyName+"\").val();");
				
				queryParamValueNulls.append(System.lineSeparator());
				queryParamValueNulls.append("	\\$(\"#"+propertyName+"\").val('');");
			}
			
			// 新增、编辑页
			if (tableColumn.getIsPrimaryKey() == 1) {
				// 不处理
			} else {
				// 弹窗
				editFormFields.append(System.lineSeparator());
				editFormFields.append("						<!--"+tableColumn.getColumnComment()+"-->"+System.lineSeparator());
				editFormFields.append("						<div class=\"javaex-unit clear\">"+System.lineSeparator());
				if (tableColumn.getIsNotNull()==1) {
					editFormFields.append("							<div class=\"javaex-unit-left tl\"><p class=\"subtitle required\">"+tableColumn.getColumnComment()+"</p></div>"+System.lineSeparator());
				} else {
					editFormFields.append("							<div class=\"javaex-unit-left tl\"><p class=\"subtitle\">"+tableColumn.getColumnComment()+"</p></div>"+System.lineSeparator());
				}
				editFormFields.append("							<div class=\"javaex-unit-right\">"+System.lineSeparator());
				if (tableColumn.getIsNotNull()==1) {
					editFormFields.append("								<input type=\"text\" class=\"javaex-text\" placeholder=\"请输入"+tableColumn.getColumnComment()+"\" name=\""+propertyName+"\" data-type=\"required\" value=\"\" autocomplete=\"off\"/>"+System.lineSeparator());
				} else {
					editFormFields.append("								<input type=\"text\" class=\"javaex-text\" placeholder=\"请输入"+tableColumn.getColumnComment()+"\" name=\""+propertyName+"\" value=\"\" autocomplete=\"off\"/>"+System.lineSeparator());
				}
				editFormFields.append("							</div>"+System.lineSeparator());
				editFormFields.append("						</div>"+System.lineSeparator());
			}
		}
		
		// 替换内容
		replaceMap.put("#ImportPackageList#", imports.toString());
		replaceMap.put("#ImportPackageDTOList#", importDTOs.toString());
		replaceMap.put("#ImportPackageVOList#", importVOs.toString());
		replaceMap.put("#PropertyDefinitions#", definitions.toString());
		replaceMap.put("#PropertyDefinitionVOs#", definitionVOs.toString());
		replaceMap.put("#PropertyGetterSetters#", getterSetters.toString());
		
		replaceMap.put("#PropertyDefinitionDTOs#", definitionDTOs.toString());
		replaceMap.put("#PropertyGetterSetterDTOs#", getterSetterDTOs.toString());
		
		replaceMap.put("#PropertyDefinitionQuerys#", definitionQuerys.toString());
		replaceMap.put("#PropertyGetterSetterQuerys#", getterSetterQuerys.toString());
		
		// XML映射文件
		replaceMap.put("#Querys#", querys.toString());
		
		// 页面
		replaceMap.put("#TableHeaders#", tableHeaders.toString());
		replaceMap.put("#TableHeaderCount#", String.valueOf(tableHeaderCount));
		replaceMap.put("#TableCells#", tableCells.toString());
		replaceMap.put("#EditFormFields#", editFormFields.toString());
		// 查询参数
		replaceMap.put("#QueryParamInputs#", queryParamInputs.toString());
		replaceMap.put("#QueryParams#", queryParams.toString());
		replaceMap.put("#QueryParamValues#", queryParamValues.toString());
		replaceMap.put("#QueryParamValueNulls#", queryParamValueNulls.toString());
		
		return replaceMap;
	}
	
	/**
	 * 生成controller层java文件
	 * @param replaceMap
	 * @param genCodePath
	 * @throws IOException
	 */
	public void genController(Map<String, String> replaceMap, String genCodePath) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, "src/main/java");
		javaDir.mkdirs();
		
		// 2. 读取ControllerTemplate.java文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/ControllerTemplate.java");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3. 生成controller文件
		File fileDir = new File(javaDir, replaceMap.get("#ControllerPackage#").toString().replaceAll("\\.", "\\" + File.separator));
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#ControllerClassName#").toString() + ".java");
		FileUtils.write(classFile, content, "UTF-8", false);
	}
	
	/**
	 * 生成controller层java文件
	 * @param param
	 * @param table
	 * @param columnList
	 * @throws IOException
	 */
	public void genControllerPage(Map<String, String> replaceMap, String genCodePath) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, "src/main/java");
		javaDir.mkdirs();
		
		// 2. 读取ControllerPageTemplate.java文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/ControllerPageTemplate.java");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3. 生成controller文件
		File fileDir = new File(javaDir, replaceMap.get("#ControllerPackage#").toString().replaceAll("\\.", "\\" + File.separator));
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#ControllerPageClassName#").toString() + ".java");
		FileUtils.write(classFile, content, "UTF-8", false);
	}
	
	/**
	 * 生成service类
	 * @param replaceMap
	 * @param genCodePath
	 * @param columnList 
	 * @throws IOException
	 */
	public void genService(Map<String, String> replaceMap, String genCodePath, List<SysGenTableColumnEntity> columnList) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, "src/main/java");
		javaDir.mkdirs();
		
		// 字段唯一性校验
		StringBuffer uniquenessCheckInsert = new StringBuffer();
		StringBuffer uniquenessCheckUpdate = new StringBuffer();
		
		for (SysGenTableColumnEntity tableColumn : columnList) {
			if (tableColumn.getIsUnique()==1) {
				String EntityClassName = replaceMap.get("#EntityClassName#").toString();
				String propertyName = StringUtils.toCamelCase(tableColumn.getColumnName());
				String propertyCapName = StringUtils.capitalize(propertyName);
				
				uniquenessCheckInsert.append(System.lineSeparator());
				uniquenessCheckInsert.append("		// "+tableColumn.getColumnComment()+"唯一性校验");
				uniquenessCheckInsert.append(System.lineSeparator());
				uniquenessCheckInsert.append("		int count = " + StringUtils.uncapitalize("I" + EntityClassName + "DAO") + ".checkUnique("+StringUtils.uncapitalize(EntityClassName)+"DTO.get" + propertyCapName + "(), null);");
				uniquenessCheckInsert.append(System.lineSeparator());
				uniquenessCheckInsert.append("		if (count > 0) {");
				uniquenessCheckInsert.append(System.lineSeparator());
				uniquenessCheckInsert.append("			throw new QingException(\"" + tableColumn.getColumnComment() + "已存在\");");
				uniquenessCheckInsert.append(System.lineSeparator());
				uniquenessCheckInsert.append("		}");
				uniquenessCheckInsert.append(System.lineSeparator());
				
				uniquenessCheckUpdate.append(System.lineSeparator());
				uniquenessCheckUpdate.append("		// "+tableColumn.getColumnComment()+"唯一性校验");
				uniquenessCheckUpdate.append(System.lineSeparator());
				uniquenessCheckUpdate.append("		int count = " + StringUtils.uncapitalize("I" + EntityClassName + "DAO") + ".checkUnique("+StringUtils.uncapitalize(EntityClassName)+"DTO.get" + propertyCapName + "(), "+replaceMap.get("#primaryKeyField#").toString()+");");
				uniquenessCheckUpdate.append(System.lineSeparator());
				uniquenessCheckUpdate.append("		if (count > 0) {");
				uniquenessCheckUpdate.append(System.lineSeparator());
				uniquenessCheckUpdate.append("			throw new QingException(\"" + tableColumn.getColumnComment() + "已存在\");");
				uniquenessCheckUpdate.append(System.lineSeparator());
				uniquenessCheckUpdate.append("		}");
				uniquenessCheckUpdate.append(System.lineSeparator());
				
				break;
			}
		}
		replaceMap.put("#UniquenessCheckInsert#", uniquenessCheckInsert.toString());
		replaceMap.put("#UniquenessCheckUpdate#", uniquenessCheckUpdate.toString());
		
		// 2 读取ServiceTemplate.java文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/ServiceTemplate.java");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3 生成service文件
		File fileDir = new File(javaDir, replaceMap.get("#ServicePackage#").toString().replaceAll("\\.", "\\" + File.separator));
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#ServiceClassName#").toString() + ".java");
		FileUtils.write(classFile, content, "UTF-8", false);
	}

	/**
	 * 生成dao层java文件
	 * @param replaceMap
	 * @param genCodePath
	 * @param columnList 
	 * @throws IOException
	 */
	public void genDaoJava(Map<String, String> replaceMap, String genCodePath, List<SysGenTableColumnEntity> columnList) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, "src/main/java");
		javaDir.mkdirs();
		
		// 字段唯一性校验
		replaceMap.put("#ImportPackageParam#", "");
		StringBuffer uniquenessCheckDAO = new StringBuffer();
		for (SysGenTableColumnEntity tableColumn : columnList) {
			if (tableColumn.getIsUnique()==1) {
				String propertyName = StringUtils.toCamelCase(tableColumn.getColumnName());
				String primaryKeyField = replaceMap.get("#primaryKeyField#").toString();
				
				uniquenessCheckDAO.append(System.lineSeparator());
				uniquenessCheckDAO.append("	/**" + System.lineSeparator());
				uniquenessCheckDAO.append("	 * " + tableColumn.getColumnComment() + "唯一性校验" + System.lineSeparator());
				uniquenessCheckDAO.append("	 * @param " + primaryKeyField + System.lineSeparator());
				uniquenessCheckDAO.append("	 * @return" + System.lineSeparator());
				uniquenessCheckDAO.append("	 */" + System.lineSeparator());
				uniquenessCheckDAO.append("	int checkUnique(@Param(\"" + propertyName + "\") String " + propertyName + ", @Param(\""+primaryKeyField+"\") String "+primaryKeyField+");");
				uniquenessCheckDAO.append(System.lineSeparator());
				
				replaceMap.put("#ImportPackageParam#", System.lineSeparator() + "import org.apache.ibatis.annotations.Param;" + System.lineSeparator());
				
				break;
			}
		}
		replaceMap.put("#UniquenessCheckDAO#", uniquenessCheckDAO.toString());
		
		// 2 读取DAOTemplate.java文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/DAOTemplate.java");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3 生成service文件
		File fileDir = new File(javaDir, replaceMap.get("#DAOPackage#").toString().replaceAll("\\.", "\\" + File.separator));
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#DAOClassName#").toString() + ".java");
		FileUtils.write(classFile, content, "UTF-8", false);
	}
	
	/**
	 * 生成dao层xml文件
	 * @param replaceMap
	 * @param genCodePath
	 * @param columnList 
	 * @throws IOException
	 */
	public void genDaoXml(Map<String, String> replaceMap, String genCodePath, List<SysGenTableColumnEntity> columnList) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, "src/main/java");
		javaDir.mkdirs();
		
		// 字段唯一性校验
		StringBuffer uniquenessCheckXML = new StringBuffer();
		for (SysGenTableColumnEntity tableColumn : columnList) {
			if (tableColumn.getIsUnique()==1) {
				String propertyName = StringUtils.toCamelCase(tableColumn.getColumnName());
				String primaryKeyField = replaceMap.get("#primaryKeyField#").toString();
				
				// 主键
				String primaryKeyColumn = "";
				String primaryKeyJavaType = "";
				for (SysGenTableColumnEntity tableColumn2 : columnList) {
					if (tableColumn2.getIsPrimaryKey() == 1) {
						primaryKeyColumn = tableColumn2.getColumnName();
						primaryKeyJavaType = tableColumn2.getJavaType();
						break;
					}
				}
				
				uniquenessCheckXML.append(System.lineSeparator());
				uniquenessCheckXML.append("	<!-- "+tableColumn.getColumnComment()+"唯一性校验 -->" + System.lineSeparator());
				uniquenessCheckXML.append("	<select id=\"checkUnique\" resultType=\"int\">" + System.lineSeparator());
				uniquenessCheckXML.append("		SELECT" + System.lineSeparator());
				uniquenessCheckXML.append("			COUNT(*)" + System.lineSeparator());
				uniquenessCheckXML.append("		FROM" + System.lineSeparator());
				uniquenessCheckXML.append("			" + replaceMap.get("#TableName#").toString() + System.lineSeparator());
				uniquenessCheckXML.append("		WHERE" + System.lineSeparator());
				uniquenessCheckXML.append("			" + tableColumn.getColumnName() + " = #{" + propertyName + "}" + System.lineSeparator());
				if ("String".equals(primaryKeyJavaType)) {
					uniquenessCheckXML.append("		<if test=\""+primaryKeyField+"!=null and "+primaryKeyField+"!=''\">" + System.lineSeparator());
				} else {
					uniquenessCheckXML.append("		<if test=\""+primaryKeyField+"!=null\">" + System.lineSeparator());
				}
				uniquenessCheckXML.append("			AND "+primaryKeyColumn+" != #{"+primaryKeyField+"}" + System.lineSeparator());
				uniquenessCheckXML.append("		</if>" + System.lineSeparator());
				uniquenessCheckXML.append("	</select>" + System.lineSeparator());
				
				break;
			}
		}
		replaceMap.put("<!--#UniquenessCheckXML#-->", uniquenessCheckXML.toString());
		
		// 2 读取DAOMapperTemplate.xml文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/DAOMapperTemplate.xml");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3 生成service文件
		File fileDir = new File(javaDir, replaceMap.get("#DAOPackage#").toString().replaceAll("\\.", "\\" + File.separator));
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#DAOClassName#").toString() + ".xml");
		FileUtils.write(classFile, content, "UTF-8", false);
	}
	
	/**
	 * 生成Entity类文件
	 * @param replaceMap
	 * @param genCodePath
	 * @throws IOException
	 */
	public void genEntity(Map<String, String> replaceMap, String genCodePath) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, "src/main/java");
		javaDir.mkdirs();
		
		// 2 读取EntityTemplate.java文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/EntityTemplate.java");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3 生成service文件
		File fileDir = new File(javaDir, replaceMap.get("#EntityPackage#").toString().replaceAll("\\.", "\\" + File.separator));
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#EntityClassName#").toString() + "Entity.java");
		FileUtils.write(classFile, content, "UTF-8", false);
	}

	/**
	 * 生成DTO类代码
	 * @param replaceMap
	 * @param genCodePath
	 * @throws IOException
	 */
	public void genDTO(Map<String, String> replaceMap, String genCodePath) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, "src/main/java");
		javaDir.mkdirs();
		
		// 2 读取DTOTemplate.java文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/DTOTemplate.java");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3 生成service文件
		File fileDir = new File(javaDir, replaceMap.get("#DTOPackage#").toString().replaceAll("\\.", "\\" + File.separator));
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#EntityClassName#").toString() + "DTO.java");
		FileUtils.write(classFile, content, "UTF-8", false);
	}

	/**
	 * 生成VO类代码
	 * @param replaceMap
	 * @param genCodePath
	 * @throws IOException
	 */
	public void genVO(Map<String, String> replaceMap, String genCodePath) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, "src/main/java");
		javaDir.mkdirs();
		
		// 2 读取VOTemplate.java文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/VOTemplate.java");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3 生成service文件
		File fileDir = new File(javaDir, replaceMap.get("#VOPackage#").toString().replaceAll("\\.", "\\" + File.separator));
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#EntityClassName#").toString() + "VO.java");
		FileUtils.write(classFile, content, "UTF-8", false);
	}
	
	/**
	 * 生成Query类代码
	 * @param replaceMap
	 * @param genCodePath
	 * @throws IOException
	 */
	public void genQuery(Map<String, String> replaceMap, String genCodePath) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, "src/main/java");
		javaDir.mkdirs();
		
		// 2 读取QueryTemplate.java文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/QueryTemplate.java");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3 生成service文件
		File fileDir = new File(javaDir, replaceMap.get("#QueryPackage#").toString().replaceAll("\\.", "\\" + File.separator));
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#EntityClassName#").toString() + "Query.java");
		FileUtils.write(classFile, content, "UTF-8", false);
	}
	
	/**
	 * 列表页
	 * @param replaceMap
	 * @param genCodePath
	 * @param packageName
	 * @param businessName 
	 * @throws IOException
	 */
	public void genFreemarkerList(Map<String, String> replaceMap, String genCodePath, String packageName, String businessName) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, GenConstant.GEN_FREEMARKER);
		javaDir.mkdirs();
		
		// 2. 读取FreemarkerListTemplate.ftl文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/FreemarkerListTemplate.ftl");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3. 生成FreemarkerListTemplate.ftl文件
		if (StringUtils.isNotEmpty(businessName)) {
			businessName = "." + businessName;
		}
		File fileDir = new File(javaDir, (packageName + businessName).replaceAll("\\.", "\\" + File.separator) + File.separator + replaceMap.get("#TableName#").toString());
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#TableName#").toString() + "_list.ftl");
		FileUtils.write(classFile, content, "UTF-8", false);
	}

	/**
	 * 新增页
	 * @param replaceMap
	 * @param genCodePath
	 * @param packageName
	 * @param businessName 
	 * @throws IOException
	 */
	public void genFreemarkerAdd(Map<String, String> replaceMap, String genCodePath, String packageName, String businessName) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, GenConstant.GEN_FREEMARKER);
		javaDir.mkdirs();
		
		// 2. 读取FreemarkerAddTemplate.ftl文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/FreemarkerAddTemplate.ftl");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3. 生成FreemarkerListTemplate.ftl文件
		if (StringUtils.isNotEmpty(businessName)) {
			businessName = "." + businessName;
		}
		File fileDir = new File(javaDir, (packageName + businessName).replaceAll("\\.", "\\" + File.separator) + File.separator + replaceMap.get("#TableName#").toString());
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#TableName#").toString() + "_add.ftl");
		FileUtils.write(classFile, content, "UTF-8", false);
	}

	/**
	 * 编辑页
	 * @param replaceMap
	 * @param genCodePath
	 * @param packageName
	 * @param businessName
	 * @throws IOException
	 */
	public void genFreemarkerEdit(Map<String, String> replaceMap, String genCodePath, String packageName, String businessName) throws IOException {
		// 1. 生成的目录
		File genDir = new File(genCodePath);
		// java代码目录
		File javaDir = new File(genDir, GenConstant.GEN_FREEMARKER);
		javaDir.mkdirs();
		
		// 2. 读取FreemarkerEditTemplate.ftl文件内容
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("gen_code_template/FreemarkerEditTemplate.ftl");
		String content = IOUtils.toString(in, "UTF-8");
		for (String key : replaceMap.keySet()) {
			String value = replaceMap.get(key);
			content = content.replaceAll(key, value);
		}
		
		// 3. 生成FreemarkerListTemplate.ftl文件
		if (StringUtils.isNotEmpty(businessName)) {
			businessName = "." + businessName;
		}
		File fileDir = new File(javaDir, (packageName + businessName).replaceAll("\\.", "\\" + File.separator) + File.separator + replaceMap.get("#TableName#").toString());
		fileDir.mkdirs();
		File classFile = new File(fileDir, replaceMap.get("#TableName#").toString() + "_edit.ftl");
		FileUtils.write(classFile, content, "UTF-8", false);
	}
	
}