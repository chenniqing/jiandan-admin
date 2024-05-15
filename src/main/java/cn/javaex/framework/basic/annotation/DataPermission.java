package cn.javaex.framework.basic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限注解
 * 
 * @author 陈霓清
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {
	
	/**
	 * 部门别名
	 * @return
	 */
	String deptAlias() default "";
	
	/**
	 * 用户别名
	 * @return
	 */
	String userAlias() default "";
	
}
