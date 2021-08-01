package reboot.spring.bean.data;

import org.springframework.beans.factory.annotation.Autowired;
import reboot.spring.bean.vo.Member;

import javax.transaction.Transactional;
import java.util.List;

//@Transactional("transactionManager")
@Transactional
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    public void insert(Member member) {
        memberDao.insert(member);
    }

    public void multipleInsert(Member member) {
        memberDao.insert(member);
        memberDao.insert(member);
    }

    public List<Member> selectAll() {
        return memberDao.selectAll();
    }

}
