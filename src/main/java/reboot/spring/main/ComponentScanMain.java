package reboot.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reboot.spring.bean.auto.AutoService;
import reboot.spring.config.ComponentScanConfig;

public class ComponentScanMain {

    private static void loadBeanByComponentScan() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        AutoService autoService = ctx.getBean(AutoService.class);
        String beanName = autoService.getBeanName();
        System.out.println("beanName : " + beanName);
        ctx.close();
    }

    public static void main(String[] args) {
        loadBeanByComponentScan();
    }

}
