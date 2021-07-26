package reboot.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import reboot.spring.config.WebConfig;
import reboot.spring.config.WebConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
    @ContextConfiguration(classes = WebConfig.class),
    @ContextConfiguration(classes = WebConfiguration.class)
})
public class SpringWebTest {

    @Autowired
    protected WebApplicationContext webAppContext;

    @Test
    public void firstWebTest() {
        assertEquals(true, true);
    }


}
