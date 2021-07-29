package reboot.spring.boot;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reboot.spring.boot.domain.Member;
import reboot.spring.boot.repository.MemberRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void createMember() {
//        Member member = new Member("yamdeng", "yamdeng@gmail.com", 37);
        Member member =  Member.builder().name("yamdeng").email("yamdeng@gmail.com").age(37).build();
        Member savedMember = memberRepository.save(member);
        Optional<Member> searchMemberOptional= memberRepository.findById(savedMember.getId());
        if(searchMemberOptional.isPresent()) {
            Member searchMember = searchMemberOptional.get();
            assertEquals("yamdeng", searchMember.getName());
        }
    }

}
