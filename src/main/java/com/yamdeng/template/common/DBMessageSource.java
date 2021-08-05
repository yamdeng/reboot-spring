package com.yamdeng.template.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;

import java.text.MessageFormat;
import java.util.Locale;

public class DBMessageSource extends AbstractMessageSource {

    // @Autowired
    // private IMessageService messageService;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String lang = locale.getLanguage();
        // String message = messageService.getMessage(code, lang);
        String message = "";
        return createMessageFormat(message, locale);
    }

}