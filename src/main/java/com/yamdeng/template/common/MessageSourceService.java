package com.yamdeng.template.common;

public interface MessageSourceService {

    String getMessage(String code);

    String getMessage(String code, String defaultMessage);

    String getMessage(String code, Object[] args);

    String getMessage(String code, Object[] args, String defaultMessage);
    
}