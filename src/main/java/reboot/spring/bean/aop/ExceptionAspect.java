package reboot.spring.bean.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExceptionAspect {

    @Pointcut("execution(public * reboot.spring.bean..*(..))")
    private void exceptionPointCut() {
    }

    @AfterThrowing(value = "exceptionPointCut()", throwing="ex")
    public void afterThrowing(JoinPoint joinPoint, RuntimeException ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("afterThrowing : " + className + "." + joinPoint.getSignature().getName());
        System.out.println("afterThrowing Exception message : " + ex.getMessage());
    }

    @AfterThrowing(value = "execution(public * reboot.spring.bean..*(..))", throwing="ex")
    public void afterThrowingByAopExceptionException(JoinPoint joinPoint, AopExampleException ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("afterThrowing : " + className + "." + joinPoint.getSignature().getName());
        System.out.println("afterThrowing Aop Exception message : " + ex.getMessage());
    }


}
