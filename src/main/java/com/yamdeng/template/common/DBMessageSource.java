package com.yamdeng.template.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.yamdeng.template.dto.MessageDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

public class DBMessageSource extends AbstractMessageSource {

    @Value("${app.messagesource.locale-kinds}")
    private String localeKinds;
    String[] locales = null;

    @Value("${app.messagesource.default-locale}")
    private String defaultLocale;

    private LocalDateTime messageMapLastModified;

    private Map<String, List<MessageDto>> messageMap = new HashMap<String, List<MessageDto>>();

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @PostConstruct
    private void init() {
        locales = StringUtils.split(localeKinds, ",");
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String lang = getLangToDefaultLocale(locale.getLanguage());
        String message = getMessageToMessageMap(code, lang);
        // String message = getMessageToDB(code, lang);
        return createMessageFormat(message, locale);
    }

    private List<MessageDto> getListByLang(String lang) {
        List<MessageDto> results = jdbcTemplate.query("select * from APP_MESSAGE where LANG = ?",
            (ResultSet rs, int rowNum) -> {
                MessageDto member = new MessageDto(
                        rs.getString("CODE"),
                        rs.getString("LANG"),
                        rs.getString("MESSAGE"));
                return member;
            }, lang);
        return results;
    }

    @Scheduled(fixedRateString ="${app.messagesource.refresh-ms}", initialDelayString = "${app.messagesource.refresh-ms}")
    public void refreshMessage() {
        Map<String, List<MessageDto>> newMessageMap = new HashMap<String, List<MessageDto>>();
        for(String lang : locales) {
            List<MessageDto> list = getListByLang(lang);
            newMessageMap.put(lang, list);
        }
        synchronized (this.messageMap) {
            this.messageMap.clear();
            this.messageMap = newMessageMap;
            this.messageMapLastModified = LocalDateTime.now();
        }
    }

    private String getLangToDefaultLocale(String lang) {
        if(localeKinds.contains(lang)) {
            return lang;
        } else {
            return defaultLocale;
        } 
    }

    private String getMessageToMessageMap(String code, String lang) {
        List<MessageDto> list = messageMap.get(lang);
        Optional<MessageDto> optionalDto =  list.stream().filter(messageDto -> {
            return messageDto.getCode().equals(code);
        }).findAny();
        if(optionalDto.isPresent()) {
            return optionalDto.get().getMessage();
        } else {
            return code;
        }
    }

    public String getMessageToDB(String code, String lang) {
        MessageDto member = jdbcTemplate.queryForObject(
                "select * from APP_MESSAGE where CODE = ? AND LANG = ?",
                new RowMapper<MessageDto>() {
                    @Override
                    public MessageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        MessageDto member = new MessageDto(
                                rs.getString("CODE"),
                                rs.getString("LANG"),
                                rs.getString("MESSAGE"));
                        return member;
                    }
                }, code, lang);
        if(member != null) {
            return member.getMessage();
        } else {
            return code;
        }
    }

    public LocalDateTime getMessageMapLastModified() {
        return messageMapLastModified;
    }
}
