package reboot.spring.boot.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reboot.spring.boot.domain.Member;
import reboot.spring.boot.domain.QMember;
import reboot.spring.boot.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JPAQueryFactory query;

    public List<Member> list() {
        return memberRepository.findAll();
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> getDetail(Long id) {
        return memberRepository.findById(id);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> testQueryDsl() {
        QMember qMember = QMember.member;
        List <Member> list = query
            .select(qMember)
            .from(qMember).fetch();
        return list;
    }

}