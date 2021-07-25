package reboot.spring.web.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.bean.vo.MemberVo;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    private List<MemberVo> voList = new ArrayList<>();

    public ApiController() {
        for(long index=0; index<10; index++) {
            MemberVo memberVo = new MemberVo();
            memberVo.setId(index+1);
            memberVo.setEmail("yamdeng" + (index+1) + "@gmail.com");
            memberVo.setName("yamdeng" + (index+1));
            memberVo.setRegDate(LocalDateTime.now());
            memberVo.setRegTime(LocalTime.now());
            memberVo.setUpdateDate(LocalDate.now());
            voList.add(
                memberVo
            );
        }
    }

    @PostMapping("/members")
    public MemberVo member(@RequestBody @Valid MemberVo memberVo, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(errorInfo -> {
                log.info("errorInfo.toString() : " + errorInfo.toString());
            });
            throw new RuntimeException("member create error");
        }
        long id = voList.get(voList.size()-1).getId().longValue() + 1;
        memberVo.setId(id);
        voList.add(memberVo);
        return memberVo;
    }

    @GetMapping("/members")
    public List<MemberVo> list() {
        return voList;
    }

    @GetMapping("/members/{id}")
    public MemberVo detail(@PathVariable("id") Long id) {
        Optional<MemberVo> findMemberVo = voList.stream().filter(info -> info.getId().equals(id)).findAny();
        return findMemberVo.get();
    }

}
