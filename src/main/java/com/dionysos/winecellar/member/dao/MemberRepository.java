package com.dionysos.winecellar.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionysos.winecellar.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
