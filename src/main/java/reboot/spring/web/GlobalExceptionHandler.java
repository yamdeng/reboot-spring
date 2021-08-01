package reboot.spring.web;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public String handleGlobalException(HttpServletRequest request, RuntimeException ex) {
        log.info("handleGlobalException : " + ex.getMessage());
        return "error/error-global";
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String handleGlobalRuntimeException(HttpServletRequest request, Exception ex) {
        log.info("handleGlobalRuntimeException : " + ex.getMessage());
        return "exception error";
    }

}