package com.yamdeng.template.common;

import com.yamdeng.template.util.WebUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class DefaultMessageSourceService implements MessageSourceService {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, WebUtil.getCurrentRequestLocale());
    }

    public String getMessage(String code, String defaultMessage) {
        return messageSource.getMessage(code, null, defaultMessage, WebUtil.getCurrentRequestLocale());
    };

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, WebUtil.getCurrentRequestLocale());
    }

    public String getMessage(String code, Object[] args, String defaultMessage) {
        return messageSource.getMessage(code, args, defaultMessage, WebUtil.getCurrentRequestLocale());
    }
    
}
