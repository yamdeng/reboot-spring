package reboot.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import reboot.spring.bean.data.*;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@Import({DataSourceConfig.class})
public class DataConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataSource dataSourceToH2;

    @Autowired
    private DataSource dataSourceToMysql;

    @Bean
    public ConnectionCheck connectionCheck() {
        return new ConnectionCheck();
    }

    @Bean(initMethod = "initScript", destroyMethod = "deleteQuery")
    public MemberDao memberDao() {
        String dbmsName = "h2";
//        String dbmsName = "mysql";
        JdbcTemplate jdbcTemplate = null;
        if (dbmsName.equals("h2")) {
            jdbcTemplate = new JdbcTemplate(dataSourceToH2);
        } else {
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return new MemberDao(jdbcTemplate, dbmsName);
    }

    @Bean(initMethod = "initScript", destroyMethod = "deleteQuery")
    public MemberDaoMySql memberDaoMysql() {
        String dbmsName = "mysql";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return new MemberDaoMySql(jdbcTemplate, dbmsName);
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        String dbmsName = "h2";
        if (dbmsName.equals("h2")) {
            tm.setDataSource(dataSourceToH2);
        } else {
            tm.setDataSource(dataSource);
        }
        return tm;
    }

    @Bean("transactionManagerMysql")
    public PlatformTransactionManager transactionManagerMysql() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource);
        return tm;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService();
    }

    @Bean
    public MemberMysqlService memberMysqlService() {
        return new MemberMysqlService();
    }

}
