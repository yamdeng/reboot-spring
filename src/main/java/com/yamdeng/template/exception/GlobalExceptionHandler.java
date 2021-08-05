package com.yamdeng.template.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ExceptionDto handleGlobalRuntimeException(HttpServletRequest request, Exception ex) {
        log.error("handleGlobalRuntimeException : " + ex);
        return new ExceptionDto(ex.getMessage(), ex.toString());
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ReqeustParameterException.class)
    public ExceptionDto handleClientBadParameterException(HttpServletRequest request, ReqeustParameterException ex) {
        log.error("handleClientBadParameterException : " + ex);
        return new ExceptionDto(ex.getMessage(), ex.toString(), ex.getErrorObjects());
    }

}