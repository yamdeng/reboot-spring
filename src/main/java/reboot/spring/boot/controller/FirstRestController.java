package reboot.spring.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/first")
@RestController
public class FirstRestController {

    @GetMapping("/test")
    public String test() {
        log.info("=== /first/test call ===");
        return "test";
    }

}
