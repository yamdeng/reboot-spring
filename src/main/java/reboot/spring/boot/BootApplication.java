package reboot.spring.boot;

import java.util.Iterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(BootApplication.class, args);
		Iterator<String> beanNameIterator = ctx.getBeanFactory().getBeanNamesIterator();
		while(beanNameIterator.hasNext()) {
			String beanName = beanNameIterator.next();
			log.info("loaded beanName : " + beanName);
		}
	}

}
