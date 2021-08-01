package reboot.spring.bean.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TimeCheckAspect {

    @Pointcut("execution(public * reboot.spring.bean..*(..))")
    private void timecheckTarget() {
    }

    //    @Around("timecheckTarget()")
    @Around("reboot.spring.bean.aop.CommonPointcut.rootPointcut()")
    public Object checkTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = sig.getName();
            System.out.println(className + "." + methodName + " call time : " + (finish - start));
        }
    }

}
