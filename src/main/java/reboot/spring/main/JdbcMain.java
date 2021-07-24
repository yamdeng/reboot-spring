package reboot.spring.main;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import reboot.spring.bean.data.ConnectionCheck;
import reboot.spring.bean.data.MemberDao;
import reboot.spring.bean.data.MemberService;
import reboot.spring.bean.vo.Member;
import reboot.spring.config.DataConfig;

@Slf4j
public class JdbcMain {

    public static void main(String[] args) {
        dataSourceTest();
        crudTest();
        transactionTest();
    }

    private static void dataSourceTest() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(DataConfig.class);

        ConnectionCheck connectionCheck = ctx.getBean("connectionCheck", ConnectionCheck.class);
        connectionCheck.connection();
        connectionCheck.connectionH2();

        ctx.close();
    }

    private static void crudTest() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(DataConfig.class);

        MemberDao memberDao = ctx.getBean("memberDao", MemberDao.class);
        Member newMember = new Member("yamdeng11@gmail.com", "1234", "yamdeng11");

        // create
        memberDao.insert(newMember);

        // 1건 read
        Optional<Member> optionalMember = memberDao.selectByEmail("yamdeng@gmail.com");
        if(optionalMember.isPresent()) {
            Member member = optionalMember.get();
            log.info("selectByEmail result : " + member);
            // update
            member.setName("yamdeng" + UUID.randomUUID().toString());
            memberDao.update(member);
        }

        // 전체 조회
        List<Member> list = memberDao.selectAll();
        list.forEach(member -> {
            System.out.println("member : " + member);
        });

        ctx.close();
    }

    private static void transactionTest() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(DataConfig.class);

        MemberService memberService = ctx.getBean("memberService", MemberService.class);
        Member newMember = new Member("yamdeng12@gmail.com", "1234", "yamdeng11");
        try {
            memberService.multipleInsert(newMember);
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionName = e.getClass().getName();
            log.info("exception name : " + exceptionName);
            if(e instanceof DuplicateKeyException) {
                log.info("handle duplication key");
            }
        }

        ctx.close();
    }

}
