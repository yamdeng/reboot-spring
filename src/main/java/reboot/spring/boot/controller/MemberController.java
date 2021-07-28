package reboot.spring.boot.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.boot.domain.Member;
import reboot.spring.boot.service.MemberService;

@RequestMapping("/api")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public List<Member> list() {
        return memberService.list();
    }

    @GetMapping("/members/{id}")
    public Member getDetail(@PathVariable Long id) {
        Optional<Member> memberOptional = memberService.getDetail(id);
        return memberOptional.get();
    }

    @PostMapping("/members")
    public Member create(@RequestBody Member member) {
        return memberService.save(member);
    }

    @PutMapping("/members/{id}")
    public Member update(@PathVariable Long id, @RequestBody Member member) {
        member.setId(id);
        return memberService.save(member);
    }

    @DeleteMapping("/members/{id}")
    public Long delete(@PathVariable Long id) {
        memberService.delete(id);
        return id;
    }

}
