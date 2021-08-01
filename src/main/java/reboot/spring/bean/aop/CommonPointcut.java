package reboot.spring.bean.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {

    //    @Pointcut("execution(public * reboot.spring.bean..*(..)) " +
//            "&& execution(public * reboot.spring.web.validator..*(..))")
    @Pointcut("execution(public * reboot.spring.bean..*(..)) " +
            "|| execution(public * reboot.spring.web.validator..*(..))")
    public void rootPointcut() {
    }

}
