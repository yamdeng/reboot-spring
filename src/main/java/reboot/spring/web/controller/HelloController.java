package reboot.spring.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.bean.vo.Member;
import reboot.spring.web.validator.MemberValidator;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    MessageSource messageSource;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello2";
    }

    @GetMapping("/message")
    @ResponseBody
    public Map<String, String> message() {
        Map<String, String> resultMap = new HashMap<>();
        String welcome = messageSource.getMessage("welcome", null, Locale.getDefault());
        String hi = messageSource.getMessage("hi", null, Locale.getDefault());
        String bye = messageSource.getMessage("bye", null, Locale.getDefault());
        String ok = messageSource.getMessage("label.ok", null, Locale.getDefault());
        String cancel = messageSource.getMessage("label.cancel", null, Locale.getDefault());
        String buttonOk = messageSource.getMessage("button.ok", null, Locale.getDefault());
        String buttonCancel = messageSource.getMessage("button.cancel", null, Locale.getDefault());
        resultMap.put("welcome", welcome);
        resultMap.put("hi", hi);
        resultMap.put("bye", bye);
        resultMap.put("ok", ok);
        resultMap.put("cancel", cancel);
        resultMap.put("buttonOk", buttonOk);
        resultMap.put("buttonCancel", buttonCancel);
        return resultMap;
    }

    @PostMapping("/member")
    @ResponseBody
    public String member(@RequestBody Member member, Errors errors) {
        new MemberValidator().validate(member, errors);
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(errorInfo -> {
                log.info("errorInfo.toString() : " + errorInfo.toString());
            });
            return "fail";
        }
        return "success";
    }

}
