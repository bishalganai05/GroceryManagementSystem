package com.bishal.gms.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	//aspect  - @Aspect -> for logging
	//advice - (when) -> @Before method is called
	//pointcut -> the expression -> "execution(* com.bishal.gms.controller.GroceryController.*(..))"
	//joinpoint ->get hold of specific method
	
	
	
	@Before("execution(* com.bishal.gms.controller.GroceryController.addProduct(..))")
	public void logmethodcall(JoinPoint jp) {
		LOGGER.info("Log Method Called before "+jp.getSignature().getName());
	}
	
	@AfterReturning("execution(* com.bishal.gms.controller.GroceryController.addProduct(..))")
	public void logmethodcallAfterReturning(JoinPoint jp) {
		LOGGER.info("Log Method Called after successful returning "+jp.getSignature().getName());
	}
	
	@AfterThrowing("execution(* com.bishal.gms.controller.GroceryController.addProduct(..))")
	public void logmethodcallAfterError(JoinPoint jp) {
		LOGGER.info("Log Method Called after error "+jp.getSignature().getName());
	}
	
	@After("execution(* com.bishal.gms.controller.GroceryController.addProduct(..))")
	public void logmethodcallAfter(JoinPoint jp) {
		LOGGER.info("Log Method Called after finally "+jp.getSignature().getName());
	}
}
