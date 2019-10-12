package com.revature.proj2.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Revature Proj2: Sortify
 * Package: Aspect
 * LoggingAspect.java
 * Aspect
 * Purpose: Aspect for handling logging cross-cut concern
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/08/2019
 */

@Aspect
@Component
public class LoggingAspect {
	//Private variables
	private static final Logger LOG = LogManager.getLogger(LoggingAspect.class);
	
	//Pointcuts
	//Repository, Service, Web Layers
	@Pointcut("within(com.revature.proj2.repository..*)"
			+ " || within(com.revature.proj2.service..*)"
			+ " || within(com.revatuer.proj2.web..*)")
	public void applicationPackagePointcut() {}
	
	//Injections
	@Before("applicationPackagePointcut()")
	public void logBeforeApplicationPackage(JoinPoint jp) {
		LOG.info("In the " + jp.getSignature().getDeclaringTypeName() + ", " 
				+ jp.getSignature().getName() + " method will be called");
	}
	@After("applicationPackagePointcut()")
	public void logAfterApplicationPackage(JoinPoint jp) {
		LOG.info("In the " + jp.getSignature().getDeclaringTypeName() + ", "
				+ jp.getSignature().getName() + " method was called");
	}
	
	@AfterReturning(pointcut="applicationPackagePointcut()", returning="returnObj")
	public void logAfterReturningApplicationPackage(JoinPoint jp, Object returnObj) {
		LOG.info("In the " + jp.getSignature().getDeclaringTypeName() + ", " 
				+ jp.getSignature().getName() + " method just returned\n" + returnObj);
	}
	
	@AfterThrowing(pointcut="applicationPackagePointcut()", throwing="e")
	public void logAfterThrowingApplicationPackage(JoinPoint jp, Throwable e) {
		LOG.info("In the " + jp.getSignature().getDeclaringTypeName() + ", " 
				+ jp.getSignature().getName() + " method just threw a(n) " + e);
	}
}
