package com.yamdeng.template.service;

import java.util.List;

import javax.transaction.Transactional;

import com.yamdeng.template.data.repository.MemberRepository;
import com.yamdeng.template.domain.Member;
import com.yamdeng.template.dto.request.MemberDto;
import com.yamdeng.template.util.TypeConvertUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    
    private final MemberRepository memberRepository;

    // create
    public Member create(MemberDto memberDto) {
        Member newMember = TypeConvertUtil.convertType(memberDto, Member.class);
        return memberRepository.save(newMember);
    }

    // update
    public Member update(Long id, MemberDto memberDto) {
        Member member = memberRepository.findById(id).get();
        BeanUtils.copyProperties(memberDto, member);
        return memberRepository.save(member);
    }

    // delete
    public Long delete(Long id) {
        memberRepository.deleteById(id);
        return id;
    }

    // all list
    public List<Member> list() {
        return memberRepository.findAll();
    }

    // detail
    public Member getDetail(Long id) {
        return memberRepository.findById(id).get();
    }


}
