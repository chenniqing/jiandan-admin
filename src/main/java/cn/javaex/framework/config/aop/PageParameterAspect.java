package cn.javaex.framework.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.javaex.framework.model.query.Query;

/**
 * 数据库分页拦截器，每页最大查询条数为100条
 * 
 * @author 陈霓清
 */
@Aspect
@Component
@Order(1)
public class PageParameterAspect {

	private static final int MAX_PAGE_SIZE = 100;

	@Before("execution(* cn.javaex..*Controller*.*(..))")
	public void before(JoinPoint joinPoint) {
		Object[] arguments = joinPoint.getArgs();
		for (Object arg : arguments) {
			if (arg instanceof Query) { // 检查参数是否是Query的实例或其子类实例
				Query query = (Query) arg;
				if (query.getPageSize()!=null && query.getPageSize()>MAX_PAGE_SIZE) {
					query.setPageSize(MAX_PAGE_SIZE); // 修改pageSize
				}
			}
		}
	}
}
