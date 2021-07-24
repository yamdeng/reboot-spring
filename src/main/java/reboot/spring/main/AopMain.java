package reboot.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reboot.spring.bean.FirstDao;
import reboot.spring.bean.FirstRepository;
import reboot.spring.bean.IFirstRepository;
import reboot.spring.bean.IFirstService;
import reboot.spring.bean.aop.ExceptionBean;
import reboot.spring.config.AopConfig;

public class AopMain {

    public static void main(String[] args) {
        timecheckAop();
        exceptionCheckAop();
    }

    private static void timecheckAop() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(AopConfig.class);
        IFirstRepository firstRepository = ctx.getBean(IFirstRepository.class);
        IFirstService firstService = ctx.getBean(IFirstService.class);
        firstService.list();
        firstRepository.list();
        ctx.close();
    }

    private static void exceptionCheckAop() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(AopConfig.class);
        ExceptionBean exceptionBean = ctx.getBean(ExceptionBean.class);
        try {
            exceptionBean.handleAopExampleException(true);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        ctx.close();
    }

}
