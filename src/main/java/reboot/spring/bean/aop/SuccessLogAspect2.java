package reboot.spring.bean.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Order(1)
@Aspect
public class SuccessLogAspect2 {

    @Pointcut("execution(public * reboot.spring.bean..*(..))")
    private void afterLog() {
    }

    @AfterReturning("afterLog()")
    public void successLog(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("success log 2 : " + className + "." + joinPoint.getSignature().getName());
    }

}
