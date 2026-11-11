package com.craft.spring_boot_crud;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect = the extra behaviour (logging) that we add to the menu CRUD methods
 * WITHOUT writing any logging code inside MenuItemController.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut = WHERE the aspect applies: every method of MenuItemController
    @Pointcut("execution(* com.craft.spring_boot_crud.MenuItemController.*(..))")
    public void menuMethods() {
    }

    // Advice 1: runs BEFORE the method
    @Before("menuMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("[BEFORE] {} called with arguments {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    // Advice 2: runs AFTER the method returns normally
    @AfterReturning(pointcut = "menuMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AFTER RETURNING] {} returned {}",
                joinPoint.getSignature().getName(), result);
    }

    // Advice 3: runs AFTER the method throws an exception
    @AfterThrowing(pointcut = "menuMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.error("[AFTER THROWING] {} failed: {}",
                joinPoint.getSignature().getName(), ex.getMessage());
    }

    // Advice 4: wraps the method and measures how long it takes
    @Around("menuMethods()")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed(); // run the real method
        } finally {
            long time = System.currentTimeMillis() - start;
            log.info("[AROUND] {} took {} ms",
                    joinPoint.getSignature().getName(), time);
        }
    }
}
