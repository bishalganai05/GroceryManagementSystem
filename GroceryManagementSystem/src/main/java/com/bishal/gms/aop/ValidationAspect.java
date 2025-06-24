package com.bishal.gms.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);
	
	@Around("execution(* com.bishal.gms.controller.GroceryController.getProductById(..)) && args(productID)")
	public Object validation(ProceedingJoinPoint jp,int productID) throws Throwable {
		if(productID<0) {
			LOGGER.info("Product id is negative, updating it");
			productID *= -1;
		}
		Object object =  jp.proceed(new Object[] {productID});
		
		return object;
	}
}
