package reboot.spring.bean.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import reboot.spring.bean.vo.Member;

@Transactional("transactionManagerMysql")
public class MemberMysqlService {

    @Autowired
    private MemberDaoMySql memberDaoMySql;

    public void multipleInsert(Member member) {
        memberDaoMySql.insert(member);
        memberDaoMySql.insert(member);
    }

}
