package reboot.spring.boot.config;

import com.zaxxer.hikari.HikariConfig;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DataSourceConfig {

//    spring.datasource.url=jdbc:h2:file:C:/h2data/groupware2
//    spring.jpa.hibernate.ddl-auto=update
//    spring.datasource.driver-class-name=org.h2.Driver
//    spring.datasource.username=sa
//    spring.datasource.password=

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setPoolName("rebootSpring");
        hikariConfig.setLeakDetectionThreshold(100);
        return null;
    }

}
