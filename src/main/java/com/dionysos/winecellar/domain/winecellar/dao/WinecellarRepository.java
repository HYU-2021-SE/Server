package com.dionysos.winecellar.domain.winecellar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionysos.winecellar.domain.member.domain.Member;
import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;

@Repository
public interface WinecellarRepository extends JpaRepository<Winecellar, Long> {
    List<Winecellar> findAllByMember(Member member);
}
