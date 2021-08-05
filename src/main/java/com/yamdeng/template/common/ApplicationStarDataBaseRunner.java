package com.yamdeng.template.common;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(2)
public class ApplicationStarDataBaseRunner implements CommandLineRunner {

    @Value("${app.logo}")
    private String appLogo;
    @Value("${app.data.run-ddl-script}")
    private Boolean isRunSqlScript;
    @Value("${app.datasource.use-multiple}")
    private Boolean isUseMultipleDataSource;
    @Value("${app.messagesource.use-db}")
    private Boolean useDbMessageSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Qualifier("secondJdbcTemplate")
    @Autowired(required = false)
    private JdbcTemplate secondJdbcTemplate;

    @Autowired
    private MessageSource messageSource;

    public void run(String... args) {
    	log.info("========== database init start ==========");
        log.info("isRunSqlScript : " + isRunSqlScript);
        if(isRunSqlScript) {
            initScript();
        }
        if(useDbMessageSource) {
            DBMessageSource dbMessageSource = (DBMessageSource) messageSource;
            dbMessageSource.refreshMessage();
        }
        log.info("========== database init end ==========");
    }

    private void initScript() {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql/ddl.sql"));
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql/init.sql"));
            if(isUseMultipleDataSource && secondJdbcTemplate != null) {
                Connection secondConnection = secondJdbcTemplate.getDataSource().getConnection();
                ScriptUtils.executeSqlScript(secondConnection, new ClassPathResource("sql/ddl.sql"));
                ScriptUtils.executeSqlScript(secondConnection, new ClassPathResource("sql/init.sql"));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    
}
