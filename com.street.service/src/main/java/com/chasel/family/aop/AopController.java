package com.chasel.family.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//描述切面类
@Aspect
@Configuration
public class AopController {
	private static Logger log = LoggerFactory.getLogger(AopController.class);
	/*
	 * 定义一个切入点
	 */
	@Pointcut("execution(public * com.chasel.family.controller.*.*(..))")
	public void webLog() {
	}

	// 环绕通知,环绕增强，相当于MethodInterceptor
	@Around("webLog()")
	public Object arround(ProceedingJoinPoint pjp) {
		long startTime = System.currentTimeMillis();
		String method = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
		try {
			Object o = pjp.proceed();
			log.info(method + " 运行时间： " + (System.currentTimeMillis() - startTime) + "ms\n");
			return o;
		} catch (Throwable e) {
			log.error(method + "has an error: {}" + e.getMessage());
			return null;
		}
	}

}
