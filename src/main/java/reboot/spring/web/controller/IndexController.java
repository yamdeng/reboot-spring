package reboot.spring.web.controller;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reboot.spring.bean.vo.Member;

@Slf4j
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
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "enabled", defaultValue = "false") Boolean enabled) {
        model.addAttribute("name", name);
        model.addAttribute("enabled", enabled);
        return "hihi";
    }

    @GetMapping("/form")
    public String form(@RequestParam Map<String, String> params) {
        log.info("name : " + params.get("name") + ", id : " + params.get("id"));
        return "form";
    }

//    @PostMapping("/form-submit")
//    public String submitForm(Member member) {
//        log.info("form-submit member : " + member);
//        return "form-submit";
//    }

    @PostMapping("/form-submit")
    public String submitForm(@ModelAttribute("memberData") Member member) {
        log.info("form-submit member : " + member);
        return "form-submit";
    }

}
