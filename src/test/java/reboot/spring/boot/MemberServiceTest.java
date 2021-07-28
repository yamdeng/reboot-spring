package reboot.spring.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reboot.spring.boot.domain.Member;
import reboot.spring.boot.repository.MemberRepository;
import reboot.spring.boot.service.MemberService;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void createMember() {
        Member member = new Member("yamdeng", "yamdeng@gmail.com", 37);
        Member savedMember = memberService.save(member);
        Optional<Member> searchMemberOptional= memberService.getDetail(savedMember.getId());
        if(searchMemberOptional.isPresent()) {
            Member searchMember = searchMemberOptional.get();
            assertEquals("yamdeng", searchMember.getName());
        }
    }

}
