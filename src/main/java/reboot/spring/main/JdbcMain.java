package reboot.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reboot.spring.bean.data.ConnectionCheck;
import reboot.spring.config.DataConfig;

public class JdbcMain {

    public static void main(String[] args) {
        dataSourceTest();
    }

    private static void dataSourceTest() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(DataConfig.class);

        ConnectionCheck connectionCheck = ctx.getBean("connectionCheck", ConnectionCheck.class);
        connectionCheck.connection();
        connectionCheck.connectionH2();

        ctx.close();
    }

}
