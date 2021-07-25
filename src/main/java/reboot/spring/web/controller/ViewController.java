package reboot.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/view/detail")
    public String detail() {
        return "thymeleaf/detail";
    }

    @GetMapping("/view/list")
    public String list() {
        return "thymeleaf/list";
    }

    @GetMapping("/view/form")
    public String form() {
        return "thymeleaf/form";
    }

}
