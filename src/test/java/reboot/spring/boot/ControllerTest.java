package reboot.spring.boot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import reboot.spring.boot.controller.FirstRestController;
import reboot.spring.boot.controller.PlatformController;

@WebMvcTest(controllers = {FirstRestController.class, PlatformController.class})
@TestPropertySource("classpath:/test.properties")
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void apiTest() throws Exception {
        String result = "test";
        mockMvc.perform(get("/first/test"))
            .andExpect(status().isOk())
            .andExpect(content().string(result));
    }

    // 에러 web config외에 다른 Configuration 어노테이션을 인식하지 못함
    @Test
    public void platformLicenseTest() throws Exception {
        mockMvc.perform(get("/platform/license"))
            .andExpect(status().isOk());
    }

}
