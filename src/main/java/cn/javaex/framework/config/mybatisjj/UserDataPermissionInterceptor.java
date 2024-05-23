package cn.javaex.framework.config.mybatisjj;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.javaex.framework.basic.constant.DataScopeConstant;
import cn.javaex.mybatisjj.config.interceptor.BeforeModifiedSqlInterceptor;

/**
 * 数据权限拦截器
 * 
 * @author 陈霓清
 */
@Component
public class UserDataPermissionInterceptor implements BeforeModifiedSqlInterceptor {
	
	// 条件占位符
	private static final String CONDITIONS_PLACEHOLDER = "{CONDITIONS}";
	
	// 使用ThreadLocal来保持每个线程的权限过滤信息
	private static final ThreadLocal<DataPermissionFilter> permissionFilterHolder = new ThreadLocal<>();

	/**
	 * 设置权限过滤条件
	 * @param filter
	 */
	public static void setPermissionFilter(DataPermissionFilter filter) {
		permissionFilterHolder.set(filter);
	}
	
	/**
	 * 清除权限过滤条件
	 */
	public static void clearPermissionFilter() {
		permissionFilterHolder.remove();
	}
	
	@Override
	public String modifiedSQL(String originalSql) {
		// 没有添加数据权限DataPermission注解的SQL方法，不处理
		DataPermissionFilter filter = permissionFilterHolder.get();
		if (filter == null) {
			return originalSql;
		}
		
		// 构建条件SQL
		String conditionSql = this.getConditionSQL(filter);
		if (StringUtils.isEmpty(conditionSql)) {
			if (originalSql.contains(CONDITIONS_PLACEHOLDER)) {
				return originalSql.replace(CONDITIONS_PLACEHOLDER, "");
			}
			return originalSql;
		}
		
		// 判断是否有条件占位符
		// 在占位符位置插入自定义条件
		if (originalSql.contains(CONDITIONS_PLACEHOLDER)) {
			return originalSql.replace(CONDITIONS_PLACEHOLDER, " AND " + conditionSql + " ");
		}
		
		// 没有条件占位符的情况下，自动插入条件
		return this.addConditionToSQL(originalSql, conditionSql);
	}

	/**
	 * 构建条件SQL
	 * @param filter
	 * @return
	 */
	private String getConditionSQL(DataPermissionFilter filter) {
		StringBuffer sb = new StringBuffer();
		
		List<String> departmentIdList = filter.getDepartmentIdList();
		int dataScope = filter.getDataScope();
		
		switch (dataScope) {
		case DataScopeConstant.ONE:      // 全部数据权限
			// 不处理
			break;
		case DataScopeConstant.TWO:      // 本部门及以下数据权限
			if (CollectionUtils.isEmpty(departmentIdList)) {
				sb.append(" 1 = 2 ");    // 没有部门时，禁止操作数据
			} else {
				String departmentIdString = departmentIdList.stream()
						.map(id -> "'" + id + "'")
						.collect(Collectors.joining(", "));
				sb.append(" " + filter.getDeptAlias() + " IN (" + departmentIdString + ") ");
			}
			break;
		case DataScopeConstant.THREE:    // 本部门数据权限
			if (CollectionUtils.isEmpty(departmentIdList)) {
				sb.append(" 1 = 2 ");    // 没有部门时，禁止操作数据
			} else {
				sb.append(" " + filter.getDeptAlias() + " = '" + departmentIdList.get(0) + "' ");
			}
			break;
		case DataScopeConstant.FOUR:    // 仅自己数据权限
			sb.append(" " + filter.getUserAlias() + " = '" + filter.getUserId() + "' ");
			break;
		default:
			break;
		}
		
		return sb.toString();
	}

	/**
	 * 添加条件SQL
	 * @param sql
	 * @param condition
	 */
	private String addConditionToSQL(String sql, String condition) {
		int whereIndex = this.findLastWhereIndex(sql);
		if (whereIndex == -1) {
			return sql.trim() + " WHERE " + condition;  // 如果找不到WHERE，那么添加它
		} else {
			// 在最后的WHERE后面插入条件
			return new StringBuilder(sql)
					.insert(whereIndex + 5, " (" + condition + ") AND")  // +5 是为了跳过 "WHERE" 这五个字符
					.toString();
		}
	}

	/**
	 * 寻找最后一个WHERE关键词
	 * @param sql
	 * @return
	 */
	private int findLastWhereIndex(String sql) {
		// 跟踪括号以确定在哪个层级上我们正在解析
		int bracketLevel = 0;
		// 用于确定是否在文字字符串内部，例如在单引号内
		boolean inString = false;
		// 检查多行注释
		boolean inMultiLineComment = false;
		// 检查单行注释
		boolean inSingleLineComment = false;
		// 记录最后一个WHERE的位置
		int lastWherePos = -1;

		for (int i = 0; i < sql.length(); i++) {
			char ch = sql.charAt(i);
			char nextChar = (i + 1 < sql.length()) ? sql.charAt(i + 1) : '\0';

			// 离开注释状态
			if (inSingleLineComment && (ch == '\n' || ch == '\r')) {
				inSingleLineComment = false;
			} else if (inMultiLineComment && ch == '*' && nextChar == '/') {
				inMultiLineComment = false;
				i++; // 跳过 '*'
				continue;
			}

			// 跳过字符串和注释中的所有内容
			if (inString || inSingleLineComment || inMultiLineComment) {
				continue;
			}

			// 进入注释状态
			if (ch == '-' && nextChar == '-') {
				inSingleLineComment = true;
			} else if (ch == '/' && nextChar == '*') {
				inMultiLineComment = true;
			}

			// 进入退出字符串状态
			if (ch == '\'') {
				inString = !inString;
			}

			if (!inString) {
				// 进入退出括号状态
				if (ch == '(') {
					bracketLevel++;
				} else if (ch == ')') {
					bracketLevel--;
				}

				// 如果是在最顶层，并且找到了"WHERE"关键词
				if (bracketLevel == 0 && i + 5 < sql.length() &&
					sql.substring(i, i + 5).equalsIgnoreCase("WHERE")) {
					lastWherePos = i; // 记录最后一个WHERE的位置
					i += 4;
				}
			}
		}
 
		return lastWherePos;
	}
	
}
