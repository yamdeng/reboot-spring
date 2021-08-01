package reboot.spring.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
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

    @Bean
    public MemberService memberService() {
        return new MemberService();
    }

    @Bean
    public MemberMysqlService memberMysqlService() {
        return new MemberMysqlService();
    }

    @Bean
    public MemberGlobalService memberGlobalService() {
        return new MemberGlobalService();
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager userTransactionManager() throws Exception {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setTransactionTimeout(300);
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean
    public JtaTransactionManager transactionManager() throws Exception {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager());
        jtaTransactionManager.setUserTransaction(userTransactionManager());
        return jtaTransactionManager;
    }

}
