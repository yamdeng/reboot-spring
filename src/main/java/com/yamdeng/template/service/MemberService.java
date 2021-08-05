package com.yamdeng.template.service;

import java.util.List;

import javax.transaction.Transactional;

import com.yamdeng.template.common.RestServiceInterface;
import com.yamdeng.template.data.repository.MemberRepository;
import com.yamdeng.template.domain.Member;
import com.yamdeng.template.dto.MemberDto;
import com.yamdeng.template.util.TypeConvertUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements RestServiceInterface<Member, MemberDto> {
    
    private final MemberRepository memberRepository;

    // create
    @Override
    public Member create(MemberDto memberDto) {
        Member newMember = TypeConvertUtil.convertType(memberDto, Member.class);
        return memberRepository.save(newMember);
    }

    // update
    @Override
    public Member update(Long id, MemberDto memberDto) {
        Member member = memberRepository.findById(id).get();
        BeanUtils.copyProperties(memberDto, member);
        return memberRepository.save(member);
    }

    // delete
    @Override
    public Long delete(Long id) {
        memberRepository.deleteById(id);
        return id;
    }

    // all list
    @Override
    public List<Member> list() {
        return memberRepository.findAll();
    }

    // detail
    @Override
    public Member getDetail(Long id) {
        return memberRepository.findById(id).get();
    }

}
