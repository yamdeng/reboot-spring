package reboot.spring.config.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

}
