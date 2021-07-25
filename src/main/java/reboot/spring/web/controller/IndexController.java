package reboot.spring.web.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jdk.jshell.spi.ExecutionControl.RunException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import reboot.spring.bean.vo.Member;

@Slf4j
@Controller
public class IndexController {

    private static class IndexException extends RuntimeException {

        private IndexException(String message) {
            super(message);
        }

    }

    private static class IndexException2 extends RuntimeException {

        private IndexException2(String message) {
            super(message);
        }

    }

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

    @GetMapping("/client")
    public String clientError() {
        if(true) {
            throw new IndexException("client error");
        }
        return "form";
    }

    @GetMapping("/server")
    public String serverError() {
        if(true) {
            throw new IndexException2("server error");
        }
        return "form";
    }

    @GetMapping("/checked")
    public String checkedError() throws Exception {
        if(true) {
            throw new Exception("checked exception error");
        }
        return "form";
    }

    @GetMapping("/runtime")
    public String runtimeError() {
        if(true) {
            throw new RuntimeException("runtime exception error");
        }
        return "form";
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IndexException.class)
    public String handleClientException(HttpServletRequest request, IndexException ex) {
        log.info("handleClientException : " + ex.getMessage());
        return "error/error-client";
    }

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IndexException2.class)
    public String handleServerException(HttpServletRequest request, IndexException2 ex) {
        log.info("handleServerException : " + ex.getMessage());
        return "error/error-server";
    }

}
