package reboot.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reboot.spring.boot.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
