package reboot.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reboot.spring.bean.auto.AutoService;
import reboot.spring.bean.auto.ManualBean;
import reboot.spring.bean.auto.exclude.ExcludeBean;
import reboot.spring.config.ComponentScanConfig;

import java.util.Iterator;

public class ComponentScanMain {

    public static void main(String[] args) {
        loadBeanByComponentScan();
        checkLoadBeanList();
        excludeComponentScan();
    }

    private static void loadBeanByComponentScan() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        AutoService autoService = ctx.getBean(AutoService.class);
        String beanName = autoService.getBeanName();
        System.out.println("loadBeanByComponentScan beanName : " + beanName);
        ctx.close();
    }

    private static void checkLoadBeanList() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        Iterator<String> beanNameIterator = ctx.getBeanFactory().getBeanNamesIterator();
        while (beanNameIterator.hasNext()) {
            String beanName = beanNameIterator.next();
            System.out.println("checkLoadBeanList beanName : " + beanName);
        }

        // 수동으로 빈 등록하기
        ctx.registerBean(ManualBean.class, new ManualBean());
        beanNameIterator = ctx.getBeanFactory().getBeanNamesIterator();
        while (beanNameIterator.hasNext()) {
            String beanName = beanNameIterator.next();
            System.out.println("after checkLoadBeanList beanName : " + beanName);
        }
        ctx.close();
    }

    private static void excludeComponentScan() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        ExcludeBean bean = ctx.getBean(ExcludeBean.class); // 에러 : reboot.spring.bean.auto.exclude 패키지는 스캔에서 제외
        String beanName = bean.getBeanName();
        System.out.println("excludeComponentScan beanName : " + beanName);
        ctx.close();
    }

}
