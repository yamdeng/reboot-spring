package reboot.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public javax.sql.DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( "com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.111.211:3306/chat?characterEncoding=utf8&useSSL=false");
        dataSource.setUsername("yamdeng");
        dataSource.setPassword("dB1234**");
        return dataSource;
    }

    @Bean
    public javax.sql.DataSource dataSourceToH2() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
//        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUrl("jdbc:h2:file:C:\\h2data\\testdb2");
        return dataSource;
    }

    @Bean
    public javax.sql.DataSource dataSourceToMysql() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName( "com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.111.211:3306/chat?characterEncoding=utf8&useSSL=false");
        dataSource.setUsername("yamdeng");
        dataSource.setPassword("dB1234**");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(10);
        dataSource.setTestWhileIdle(true);
        dataSource.setMinEvictableIdleTimeMillis(60000 * 3);
        dataSource.setTimeBetweenEvictionRunsMillis(10 * 1000);
        return dataSource;
    }

}
