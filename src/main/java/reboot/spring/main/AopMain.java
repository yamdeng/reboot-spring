package reboot.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reboot.spring.bean.FirstDao;
import reboot.spring.bean.FirstRepository;
import reboot.spring.bean.IFirstRepository;
import reboot.spring.bean.IFirstService;
import reboot.spring.bean.aop.ExceptionBean;
import reboot.spring.config.AopConfig;
import reboot.spring.config.AopConfigClass;

public class AopMain {

    public static void main(String[] args) {
//        timecheckAop();
//        exceptionCheckAop();
        proxyTargetClass();
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

    private static void proxyTargetClass() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(AopConfig.class);
        FirstDao firstDao1 = ctx.getBean("firstDao", FirstDao.class);
        firstDao1.list();
        System.out.println("firstDao1 class : " + firstDao1.getClass());

        AnnotationConfigApplicationContext proxyTargetClassCtx =
            new AnnotationConfigApplicationContext(AopConfigClass.class);

        FirstDao firstDao2 = ctx.getBean("firstDao", FirstDao.class);
        firstDao2.list();
        System.out.println("firstDao2 class : " + firstDao2.getClass());
    }

}
