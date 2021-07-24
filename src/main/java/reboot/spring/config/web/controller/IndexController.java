package reboot.spring.config.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model,
        @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/hihi")
    public String hihi(Model model,
        @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("name", name);
        return "hihi";
    }

}
