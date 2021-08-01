package reboot.spring.bean.data;

import org.springframework.beans.factory.annotation.Autowired;
import reboot.spring.bean.vo.Member;

import javax.transaction.Transactional;
import java.util.List;

//@Transactional("transactionManagerMysql")
@Transactional
public class MemberMysqlService {

    @Autowired
    private MemberDaoMySql memberDaoMySql;

    public void insert(Member member) {
        memberDaoMySql.insert(member);
    }

    public void multipleInsert(Member member) {
        memberDaoMySql.insert(member);
        memberDaoMySql.insert(member);
    }

    public List<Member> selectAll() {
        return memberDaoMySql.selectAll();
    }

}
