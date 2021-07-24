package reboot.spring.bean.data;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ConnectionCheck {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataSource dataSourceToH2;

    @Autowired
    private DataSource dataSourceToMysql;

    public void connection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                }
        }
    }

    public void connectionH2() {
        Connection conn = null;
        try {
            conn = dataSourceToH2.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                }
        }
    }

    public void connectionMysql() {
        Connection conn = null;
        try {
            conn = dataSourceToMysql.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                }
        }
    }


}
