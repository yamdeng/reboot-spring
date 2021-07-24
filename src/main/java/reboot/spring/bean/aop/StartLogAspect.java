package reboot.spring.bean.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class StartLogAspect {

    @Pointcut("execution(public * reboot.spring.bean..*(..))")
    private void beforeLog() {
    }

    @Before("reboot.spring.bean.aop.StartLogAspect.beforeLog()")
    public void startLog(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("start log : " + className + "." + joinPoint.getSignature().getName());
    }

}
