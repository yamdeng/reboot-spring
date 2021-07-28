package reboot.spring.boot;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
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
		List<String> beanNameList = Arrays.stream(ctx.getBeanFactory().getBeanDefinitionNames()).sorted().collect(
			Collectors.toList());
		for(String beanName : beanNameList) {
			log.info("loaded beanName : " + beanName);
		}
	}

}
