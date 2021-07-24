package reboot.spring.main;

import java.util.Iterator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reboot.spring.bean.auto.AutoBean;
import reboot.spring.bean.auto.AutoService;
import reboot.spring.bean.auto.ManualBean;
import reboot.spring.config.ComponentScanConfig;

public class ComponentScanMain {

    public static void main(String[] args) {
        loadBeanByComponentScan();
        checkLoadBeanList();
    }

    private static void loadBeanByComponentScan() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        AutoService autoService = ctx.getBean(AutoService.class);
        String beanName = autoService.getBeanName();
        System.out.println("beanName : " + beanName);
        ctx.close();
    }

    private static void checkLoadBeanList() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        AutoBean autoBean = ctx.getBean("autoBean", AutoBean.class);
        Iterator<String> beanNameIterator = ctx.getBeanFactory().getBeanNamesIterator();
        while(beanNameIterator.hasNext()) {
            String beanName = beanNameIterator.next();
            System.out.println("beanName : " + beanName);
        }

        // 수동으로 빈 등록하기
        ctx.registerBean(ManualBean.class, new ManualBean());
        beanNameIterator = ctx.getBeanFactory().getBeanNamesIterator();
        while(beanNameIterator.hasNext()) {
            String beanName = beanNameIterator.next();
            System.out.println("after beanName : " + beanName);
        }
        ctx.close();
    }

}
