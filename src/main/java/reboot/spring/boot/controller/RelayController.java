package reboot.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.boot.service.HttpClientService;

@RestController
@RequestMapping("/relay")
public class RelayController {

    @Autowired
    private HttpClientService httpClientService;

    @GetMapping("/test")
    public String test() {
        httpClientService.test();
        return "success";
    }

}
