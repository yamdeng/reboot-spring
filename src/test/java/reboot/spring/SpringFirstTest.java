package reboot.spring;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reboot.spring.config.FirstSpringConfig;
import reboot.spring.config.FirstSpringConfig.FirstBean;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FirstSpringConfig.class})
public class SpringFirstTest {

    @Autowired
    public FirstBean firstBean;

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

    @Test
    public void firstBeanTest() {
        assertEquals("yamdeng", firstBean.getName());
    }

}
