package reboot.spring.bean.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Order(2)
@Aspect
public class SuccessLogAspect {

    @Pointcut("execution(public * reboot.spring.bean..*(..))")
    private void afterLog() {
    }

    @Order(10)
    @AfterReturning("afterLog()")
    public void successLog(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("success log 1 : " + className + "." + joinPoint.getSignature().getName());
    }

}
