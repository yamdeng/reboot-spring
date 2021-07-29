package reboot.spring.boot;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = BootApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:/test.properties")
public class ControllerWithDBTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void apiTest() throws Exception {
        String result = "test";
        mockMvc.perform(get("/first/test"))
            .andExpect(status().isOk())
            .andExpect(content().string(result));
    }

    @Test
    public void platformLicenseTest() throws Exception {
        mockMvc.perform(get("/platform/license"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.version", is(notNullValue())))
            .andExpect(jsonPath("$.version", is("spring version : 5.3.9, application version : 0.1")));
    }

    @Test
    public void membersApiTest() throws Exception {

        mockMvc.perform(get("/api/members"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]").exists())
            .andExpect(jsonPath("$[0].name").value("안용성"));

    }

}
