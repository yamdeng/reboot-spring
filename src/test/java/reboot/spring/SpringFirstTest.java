package reboot.spring;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reboot.spring.config.FirstSpringConfig;

public class SpringFirstTest {

    @Test
    public void firstTest() {
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
        assertEquals(true, isBeanSame);
    }

}
