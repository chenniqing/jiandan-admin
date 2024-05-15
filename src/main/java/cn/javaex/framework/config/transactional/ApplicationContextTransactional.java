package cn.javaex.framework.config.transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 事务管理
 * 
 * @author 陈霓清
 */
@Aspect
@Configuration
public class ApplicationContextTransactional {
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	// 事务方法超时时间设置，单位：秒
	private static final int TX_METHOD_TIMEOUT = 10;
	// AOP切面的切点表达式
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* *..*Service*.*(..))";
	
	/**
	 * 增强(事务)的属性的配置
	 * @return
	 */
	@Bean
	public TransactionInterceptor txAdvice() {
		NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();
		
		RuleBasedTransactionAttribute requiredAttr = new RuleBasedTransactionAttribute();
		requiredAttr.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//		requiredAttr.setTimeout(TX_METHOD_TIMEOUT);
		requiredAttr.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		
		// propagation="SUPPORTS" , readOnly="true" 配置
		RuleBasedTransactionAttribute supportsAttr = new RuleBasedTransactionAttribute();
		supportsAttr.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
		supportsAttr.setReadOnly(true);
		
		// tx:method
		Map<String , TransactionAttribute> txMethod = new HashMap<String , TransactionAttribute>();
		// 回滚
		txMethod.put("save*", requiredAttr);
		txMethod.put("add*", requiredAttr);
		txMethod.put("insert*", requiredAttr);
		txMethod.put("update*", requiredAttr);
		txMethod.put("delete*", requiredAttr);
		txMethod.put("batch*", requiredAttr);
		txMethod.put("do*", requiredAttr);
		// 只读
		txMethod.put("select*", supportsAttr);
		txMethod.put("get*", supportsAttr);
		txMethod.put("query*", supportsAttr);
		txMethod.put("list*", supportsAttr);
		txMethod.put("find*", supportsAttr);
		
		txAttributeSource.setNameMap(txMethod);
		TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager , txAttributeSource);
		
		return txAdvice;
	}

	/**
	 * AOP配置定义切面
	 * @return
	 */
	@Bean
	public Advisor txAdviceAdvisor() {
		AspectJExpressionPointcut pointcut= new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut , txAdvice());
	}
}
