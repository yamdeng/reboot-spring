package com.yamdeng.template.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yamdeng.template.common.LogMode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ApiLogInterceptor implements HandlerInterceptor {

    @Value("${app.logo.mode:prod}")
    private LogMode logMode;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        log.info("ApiLogInterceptor preHandle : " + handler.toString());
        if(LogMode.DEV.equals(logMode)) {
            log.info(request.getRequestURL().toString() + " parameter display start");
            Map<String, String[]> getParameterMap = request.getParameterMap();
            for(String key : getParameterMap.keySet()) {
                log.info("[" + key + "] : " + Arrays.toString(getParameterMap.get(key)));
            }
            log.info(request.getRequestURL().toString() + " parameter display end");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        log.info("ApiLogInterceptor postHandle : " + handler.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
        log.info("ApiLogInterceptor afterCompletion : " + handler.toString());
        if(LogMode.DEV.equals(logMode)) {
            log.info(request.getRequestURL().toString() + " response display start");
            Collection<String> headerNames = response.getHeaderNames();
            for(String headerValue : headerNames) {
                log.info("[" + headerValue + "] : " + response.getHeader(headerValue));
            }
            log.info(request.getRequestURL().toString() + " response display end");
        }
        if(ex != null) {
            log.info("ApiLogInterceptor afterCompletion exception : " + ex.getMessage());
        }
    }
}
