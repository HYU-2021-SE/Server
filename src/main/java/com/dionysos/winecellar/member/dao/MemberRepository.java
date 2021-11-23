package com.dionysos.winecellar.member.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionysos.winecellar.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
