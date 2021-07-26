package reboot.spring.boot.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reboot.spring.boot.vo.MemberVo;

@Controller
@RequestMapping("/view/th")
public class ThymeleafController {

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/model")
    public String modelPage(Model model) {
        model.addAttribute("name", "yamdeng");
        model.addAttribute("email", "yamdeng@gmail.com");
        Map<String, Object> memberMap = new HashMap<>();
        memberMap.put("name", "yamdeng2");
        memberMap.put("email", "yamdeng2@gmail.com");
        MemberVo memberVo = new MemberVo();
        memberVo.setName("yamdeng3");
        memberVo.setEmail("yamdeng3@gmail.com");
        model.addAttribute("memberMap", memberMap);
        model.addAttribute("memberVo", memberVo);
        return "model";
    }

}
