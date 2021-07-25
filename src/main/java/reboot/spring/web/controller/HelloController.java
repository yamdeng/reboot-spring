package reboot.spring.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.bean.vo.Member;
import reboot.spring.bean.vo.Member2;
import reboot.spring.web.validator.MemberValidator;

@Slf4j
@RestController
public class HelloController {

    private static class HelloException extends RuntimeException {

        private HelloException(String message) {
            super(message);
        }

    }

    private static class HelloException2 extends RuntimeException {

        private HelloException2(String message) {
            super(message);
        }

    }

    @Autowired
    MessageSource messageSource;

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.addValidators(new MemberValidator());
//    }

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

    @PostMapping("/member2")
    @ResponseBody
    public String member2(@Valid @RequestBody Member2 member2, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(errorInfo -> {
                log.info("errorInfo.toString() : " + errorInfo.toString());
            });
            return "fail";
        }
        return "success";
    }

    @PostMapping("/member3")
    @ResponseBody
    public String member3(@RequestBody @Valid Member member, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(errorInfo -> {
                log.info("errorInfo.toString() : " + errorInfo.toString());
            });
            return "fail";
        }
        return "success";
    }

    @GetMapping("/exception")
    @ResponseBody
    public String exception() {
        if (true) {
            throw new RuntimeException("manual exception!");
        }
        return "success";
    }

    @GetMapping("/exception/hello")
    @ResponseBody
    public String exceptionHello() {
        if (true) {
            throw new HelloException("hello exception!");
        }
        return "success";
    }

    @GetMapping("/exception/hello2")
    @ResponseBody
    public String exceptionHello2() {
        if (true) {
            throw new HelloException2("hello2 exception!");
        }
        return "success";
    }

    @GetMapping("/exception/runtime")
    @ResponseBody
    public String exceptionRuntime() {
        if (true) {
            throw new RuntimeException("runtime exception!");
        }
        return "success";
    }

    @GetMapping("/exception/checked")
    @ResponseBody
    public String exceptionChecked() throws Exception {
        if (true) {
            throw new Exception("checked exception!");
        }
        return "success";
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HelloException.class)
    @ResponseBody
    public String handleSomeException(HttpServletRequest request, HelloException ex) {
        return "hello error";
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HelloException2.class)
    @ResponseBody
    public ResponseEntity<?> handleSomeException2(HttpServletRequest request, HelloException2 ex) {
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String handleException(HttpServletRequest request, RuntimeException ex) {
        return "runtime error";
    }


}
