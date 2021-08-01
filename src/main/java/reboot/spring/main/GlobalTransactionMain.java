package reboot.spring.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import reboot.spring.bean.data.MemberGlobalService;
import reboot.spring.bean.vo.Member;
import reboot.spring.config.DataConfig;

@Slf4j
public class GlobalTransactionMain {

    public static void main(String[] args) {
        globalTransactionTest();
    }

    private static void globalTransactionTest() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(DataConfig.class);

        MemberGlobalService memberGlobalService = ctx.getBean("memberGlobalService", MemberGlobalService.class);
        Member newMember = new Member("yamdeng12@gmail.com", "1234", "yamdeng11");

        try {
            memberGlobalService.globalInsert(newMember);
//            List<Member> resultList = memberGlobalService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionName = e.getClass().getName();
            log.info("exception name : " + exceptionName);
            if (e instanceof DuplicateKeyException) {
                log.info("handle duplication key");
            }
        }

        ctx.close();
    }


}
