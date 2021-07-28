package reboot.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.boot.vo.MemberVo;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestApiController {

    private List<MemberVo> list = new ArrayList<>();

    {
        list = new ArrayList<>();
        for (long index=0; index<10; index++) {
            MemberVo memberVo = new MemberVo();
            memberVo.setEmail("yamdeng" + (index+1) + "@gmail.com");
            memberVo.setName("안용성23");
            memberVo.setLoginId("yamdeng" + (index+1));
            memberVo.setId(index + 1);
            list.add(memberVo);
        }
    }

    @GetMapping("/tests")
    public List<MemberVo> members() {
        log.info("members size : " + list.size());
        return list;
    }

    @GetMapping("/tests/{id}")
    public MemberVo getDetail(@PathVariable Long id) {
        Optional<MemberVo> optionalMemberVo = list.stream()
            .filter(memberVo -> memberVo.getId().equals(id)).findAny();
        return optionalMemberVo.get();
    }

    @PostMapping("/tests")
    public ResponseEntity<?> createMember(@RequestBody MemberVo memberVo) {
        MemberVo lastMemberVo = list.get(list.size() - 1);
        Long newId = lastMemberVo.getId() + 1;
        memberVo.setId(newId);
        list.add(memberVo);
        return new ResponseEntity<>(
            memberVo,
            HttpStatus.OK);
    }

    @PutMapping("/tests/{id}")
    public MemberVo updateMember(@PathVariable Long id, @RequestBody MemberVo updateMemberVo) {
        Optional<MemberVo> optionalMemberVo = list.stream()
            .filter(memberVo -> memberVo.getId().equals(id)).findAny();
        MemberVo originalMemberVo = optionalMemberVo.get();
        originalMemberVo.setName(updateMemberVo.getName());
        originalMemberVo.setEmail(updateMemberVo.getEmail());
        originalMemberVo.setLoginId(updateMemberVo.getLoginId());
        return originalMemberVo;
    }

    @DeleteMapping("/tests/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        boolean removed = list.removeIf(memberVo -> memberVo.getId().equals(id));
        return new ResponseEntity<>(
            "delete id : " + id + ", success : " + removed,
            HttpStatus.OK);
    }

}
