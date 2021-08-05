package com.yamdeng.template.controller;

import java.util.List;

import com.yamdeng.template.dto.MemberDto;
import com.yamdeng.template.service.MemberService;
import com.yamdeng.template.util.TypeConvertUtil;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

// @RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberDto> list() {
        return TypeConvertUtil.convertListToDto(memberService.list(), MemberDto.class);
    }

    @GetMapping("{id}")
    public MemberDto getDetail(@PathVariable Long id) {
        return TypeConvertUtil.convertType(memberService.getDetail(id), MemberDto.class);
    }
    
    @PostMapping
    public MemberDto create(@Validated @RequestBody MemberDto memberDto) {
        return TypeConvertUtil.convertType(memberService.create(memberDto), MemberDto.class);
    }

    @PutMapping("{id}")
    public MemberDto update(@PathVariable Long id, @Validated @RequestBody MemberDto memberDto) {
        return TypeConvertUtil.convertType(memberService.update(id, memberDto), MemberDto.class);
    }

    @DeleteMapping("{id}")
    public Long delete(@PathVariable Long id) {
        return memberService.delete(id);
    }

}
