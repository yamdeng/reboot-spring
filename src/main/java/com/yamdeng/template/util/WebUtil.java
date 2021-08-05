package com.yamdeng.template.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yamdeng.template.exception.ApplicationException;

import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

public class WebUtil {

    public static String getClientIp() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = req.getRemoteAddr();
        }
        return ip;
    }

    public static Locale getCurrentRequestLocale() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return RequestContextUtils.getLocale(request);
    }

    public static String getBodyToJsonString(HttpServletRequest request) {
        String resultString = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                StreamUtils.copy(inputStream, output);
                resultString = new String(output.toByteArray(), "UTF-8");
            }
        } catch (IOException e) {
            throw new ApplicationException("Body Parser Error", e);
        }
        return resultString;
    }

    public static void deleteCookie(String cookieName) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static void addCookie(String cookieName, String cookieValue) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(3600 * 365);
        response.addCookie(cookie);
    }

}