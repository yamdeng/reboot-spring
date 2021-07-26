package reboot.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.boot.util.HttpClientUtil;

@RestController
@RequestMapping("/relay")
public class RelayController {

    @Autowired
    private HttpClientUtil httpClientUtil;

    @GetMapping("/test")
    public String test() {
        httpClientUtil.test();
        return "success";
    }

}
