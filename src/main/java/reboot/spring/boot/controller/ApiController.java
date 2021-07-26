package reboot.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.boot.vo.MemberVo;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/members")
    public List<MemberVo> members() {
        List<MemberVo> list = new ArrayList<>();
        for (int index=0; index<10; index++) {
            MemberVo memberVo = new MemberVo();
            memberVo.setEmail("yamdeng" + (index+1) + "@gmail.com");
            memberVo.setName("안용성23");
            memberVo.setLoginId("yamdeng" + (index+1));
            list.add(memberVo);
        }
        log.info("members size : " + list.size());
        return list;
    }

}
