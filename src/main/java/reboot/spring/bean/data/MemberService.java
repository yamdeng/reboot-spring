package reboot.spring.bean.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import reboot.spring.bean.vo.Member;

@Transactional
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    public void multipleInsert(Member member) {
        memberDao.insert(member);
        memberDao.insert(member);
    }

}
