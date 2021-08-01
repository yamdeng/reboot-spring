package reboot.spring.bean.data;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import reboot.spring.bean.vo.Member;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class MemberDaoMySql {

    private String dbmsName;

    private JdbcTemplate jdbcTemplate;

    public MemberDaoMySql(JdbcTemplate jdbcTemplate, String dbmsName) {
        this.jdbcTemplate = jdbcTemplate;
        this.dbmsName = dbmsName;
    }

    public Optional<Member> selectByEmail(String email) {
        Member member = jdbcTemplate.queryForObject(
                "select * from MEMBER_TEST where EMAIL = ?",
                new RowMapper<Member>() {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Member member = new Member(
                                rs.getString("EMAIL"),
                                rs.getString("PASSWORD"),
                                rs.getString("NAME"),
                                rs.getTimestamp("REGDATE").toLocalDateTime());
                        member.setId(rs.getLong("ID"));
                        return member;
                    }
                }, email);
        return Optional.ofNullable(member);
    }

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                // 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into MEMBER_TEST (EMAIL, PASSWORD, NAME, REGDATE) " +
                                "values (?, ?, ?, ?)",
                        new String[]{"ID"});
                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setTimestamp(4,
                        Timestamp.valueOf(member.getRegDate()));
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }

    public void update(Member member) {
        jdbcTemplate.update(
                "update MEMBER_TEST set NAME = ?, PASSWORD = ? where ID = ?",
                member.getName(), member.getPassword(), member.getId());
    }

    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("select * from MEMBER_TEST",
                (ResultSet rs, int rowNum) -> {
                    Member member = new Member(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getTimestamp("REGDATE").toLocalDateTime());
                    member.setId(rs.getLong("ID"));
                    return member;
                });
        return results;
    }

    public void delete(String email) {
        try {
            jdbcTemplate.update(
                    "delete FROM MEMBER_TEST where EMAIL = ?", email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initScript() {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql/" + dbmsName + "-init.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteQuery() {
        delete("yamdeng11@gmail.com");
    }


}
