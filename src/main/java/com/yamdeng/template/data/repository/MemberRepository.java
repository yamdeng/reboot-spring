package com.yamdeng.template.data.repository;

import com.yamdeng.template.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}

