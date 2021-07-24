package reboot.spring.main;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reboot.spring.bean.FirstServiceImpl;
import reboot.spring.bean.IFirstRepository;
import reboot.spring.bean.IFirstService;
import reboot.spring.bean.vo.FirstVo;
import reboot.spring.config.BeanConfig1;
import reboot.spring.config.BeanConfig2;
import reboot.spring.config.ParentBeanConfig;

public class BeanLoadMain {

    public static void main(String[] args) {
        manualBeanLoad();
        autoBeanLoad();
        importUseBeanLoad();
    }

    private static void manualBeanLoad() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(BeanConfig1.class);
        FirstServiceImpl fileServiceImpl1 = ctx.getBean("firstServiceImpl", FirstServiceImpl.class);
        System.out.println("list : " + fileServiceImpl1.list());

        // prototype 빈 확인
        FirstServiceImpl fileServiceImpl2 = ctx.getBean("firstServiceImpl", FirstServiceImpl.class);
        FirstVo firstVo1 = ctx.getBean("firstVo", FirstVo.class);
        FirstVo firstVo2 = ctx.getBean("firstVo", FirstVo.class);
        System.out.println("fileServiceImpl == fileServiceImpl2 : " + (fileServiceImpl1 == fileServiceImpl2)); // true
        System.out.println("firstVo1 == firstVo2 : " + (firstVo1 == firstVo2)); // false
    }

    private static void autoBeanLoad() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(BeanConfig2.class);
        IFirstRepository firstRepository = ctx.getBean("firstRepository", IFirstRepository.class);
        List<FirstVo> list1 = firstRepository.list();
        System.out.println("list : " + firstRepository.list());

        IFirstService firstService = ctx.getBean("firstService", IFirstService.class);
        List<FirstVo> list2 = firstService.list();
        System.out.println("list1 == list2 : " + (list1 == list2)); // true
    }

    private static void importUseBeanLoad() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(ParentBeanConfig.class);
        FirstServiceImpl fileServiceImpl = ctx.getBean("firstServiceImpl", FirstServiceImpl.class);
        System.out.println("list : " + fileServiceImpl.list());
    }

}
