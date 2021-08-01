package reboot.spring.bean.data;

import org.springframework.beans.factory.annotation.Autowired;
import reboot.spring.bean.vo.Member;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class MemberGlobalService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberMysqlService memberMysqlService;

    public List<Member> selectAll() {
        return memberService.selectAll();
    }

    public void globalInsert(Member member) {
        memberService.insert(member);
        memberMysqlService.multipleInsert(member);
    }

}
