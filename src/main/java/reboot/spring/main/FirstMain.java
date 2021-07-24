package reboot.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import reboot.spring.bean.FirstBean;
import reboot.spring.config.FirstSpringConfig;

public class FirstMain {

    private static void loadSpringConfigXmlMethod() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        FirstBean firstBean1 = ctx.getBean("firstBean", FirstBean.class);
        FirstBean firstBean2 = ctx.getBean("firstBean", FirstBean.class);
        System.out.println("firstBean1(xml) : " + firstBean1);
        System.out.println("firstBean2(xml) : " + firstBean2);
        boolean isBeanSame = false;
        if(firstBean1 == firstBean2) {
            isBeanSame = true;
        }
        System.out.println("isBeanSame : " + isBeanSame);
        ctx.close();
    }

    private static void loadSpringConfigAnnotationMethod() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(FirstSpringConfig.class);
        FirstSpringConfig.FirstBean firstBean1 = ctx.getBean("firstBean", FirstSpringConfig.FirstBean.class);
        FirstSpringConfig.FirstBean firstBean2 = ctx.getBean("firstBean", FirstSpringConfig.FirstBean.class);
        System.out.println("firstBean1 : " + firstBean1);
        System.out.println("firstBean2 : " + firstBean2);
        boolean isBeanSame = false;
        if(firstBean1 == firstBean2) {
            isBeanSame = true;
        }
        System.out.println("isBeanSame : " + isBeanSame);
        ctx.close();
    }

    public static void main(String[] args) {
        loadSpringConfigAnnotationMethod();;
        loadSpringConfigXmlMethod();
    }

}
