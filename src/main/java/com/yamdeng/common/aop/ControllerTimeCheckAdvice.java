package com.yamdeng.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ControllerTimeCheckAdvice {

    final static Logger logger = LoggerFactory.getLogger(ControllerTimeCheckAdvice.class);

    @Pointcut("execution(public * com.yamdeng..controller..*(..))")
    private void publicTarget() {
    }

    @Around("publicTarget()")
    public Object checkTimeToController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = sig.getName();
            logger.info(className + "." + methodName + " call time : " + (finish - start));
        }
    }

}
