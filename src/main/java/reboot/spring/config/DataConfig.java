package reboot.spring.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import reboot.spring.bean.data.ConnectionCheck;
import reboot.spring.bean.data.MemberDao;

@Configuration
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
//        String dbmsName = "h2";
        String dbmsName = "mysql";
        JdbcTemplate jdbcTemplate = null;
        if(dbmsName.equals("h2")) {
            jdbcTemplate = new JdbcTemplate(dataSourceToH2);
        } else {
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return new MemberDao(jdbcTemplate, dbmsName);
    }

}
