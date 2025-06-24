package com.bishal.gms.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitor {
	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitor.class);
	
	@Around("execution(* com.bishal.gms.controller.GroceryController.addProduct(..))")
	public Object logmethodcall(ProceedingJoinPoint jp) throws Throwable {
		long start = System.currentTimeMillis();
		
		Object object =  jp.proceed();
		
		long end = System.currentTimeMillis();
		
		LOGGER.info("Time taken for executing the method "+(end-start)+"ms");
		
		return object;
	}
}
