package reboot.spring.config;

import com.atomikos.jdbc.AtomikosNonXADataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class DataSourceConfig {

    @Lazy
    @Bean(initMethod = "init")
    public javax.sql.DataSource dataSource() throws Exception {
        AtomikosNonXADataSourceBean dataSource = new AtomikosNonXADataSourceBean();
        dataSource.setBorrowConnectionTimeout(2000);
        dataSource.setMaxPoolSize(10);
        dataSource.setLocalTransactionMode(true);
        dataSource.setUniqueResourceName("dataSource");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.111.211:3306/chat?characterEncoding=utf8&useSSL=false");
        dataSource.setUser("yamdeng");
        dataSource.setPassword("dB1234**");
        return dataSource;
    }

    @Lazy
    @Bean(initMethod = "init")
    public javax.sql.DataSource dataSourceToH2() throws Exception {
        AtomikosNonXADataSourceBean dataSource = new AtomikosNonXADataSourceBean();
        dataSource.setBorrowConnectionTimeout(2000);
        dataSource.setMaxPoolSize(10);
        dataSource.setLocalTransactionMode(true);
        dataSource.setUniqueResourceName("dataSourceToH2");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUser("sa");
//        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUrl("jdbc:h2:file:C:\\h2data\\testdb2");
        return dataSource;
    }

}
