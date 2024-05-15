package cn.javaex.framework.config.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.javaex.framework.basic.exception.QingException;

/**
 * 统一日志管理
 * 
 * 陈霓清
 */
@Aspect
@Component
public class LogAspect {

	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
	
	// 控制层包路径
	@Pointcut("execution(* cn.javaex..*Controller*.*(..))")
	public void aopLog() {
		
	}

	/**
	 * 前置通知：在目标方法开始之前执行的通知
	 * @param joinPoint
	 */
	@Before("aopLog()")
	public void before(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL().toString();
		String methodName = joinPoint.getSignature().getName();
		log.info(url + ": " + methodName + "()==========>正常开始");
	}

	/**
	 * 返回通知：在目标方法正常结束时，才执行的通知
	 * @param joinPoint
	 */
	@AfterReturning("aopLog()")
	public void afterReturning(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL().toString();
		String methodName = joinPoint.getSignature().getName();
		log.info(url + ": " + methodName + "()==========>正常终了");
	}
	
	/**
	 * 异常通知：在目标方法出现异常时才执行的通知
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(pointcut="aopLog()", throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL().toString();
		String methodName = joinPoint.getSignature().getName();
		log.error("请求路径：" + url);
		log.error("请求方法：" + methodName + "()==========>异常终了");
		
		if (ex instanceof QingException) {
			log.error("异常原因：" + ((QingException)ex).getMessage());
		} else {
			log.error("异常原因：" + ex);
		}
	}
}
