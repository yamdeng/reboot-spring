package reboot.spring.bean.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

// @Order(99)
@Aspect
public class AfterLogAspect {

    @Pointcut("execution(public * reboot.spring.bean..*(..))")
    private void afterLog() {
    }

    @After("afterLog()")
    public void successLog(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("after log : " + className + "." + joinPoint.getSignature().getName());
    }

}
