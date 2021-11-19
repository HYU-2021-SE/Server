package com.dionysos.winecellar.winecellar.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dionysos.winecellar.member.dao.MemberRepository;
import com.dionysos.winecellar.member.domain.Member;
import com.dionysos.winecellar.winecellar.domain.Winecellar;

@DataJpaTest
class WinecellarRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    WinecellarRepository winecellarRepository;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
            .memberId(1L)
            .memberName("name")
            .build();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("와인셀러 등록")
    void save() {
        Member member = memberRepository.findById(1L).orElseThrow();
        Winecellar winecellar = Winecellar.builder()
            .id(1L)
            .member(member)
            .build();

        Winecellar savedWinecellar = winecellarRepository.save(winecellar);
        Member savedMember = memberRepository.save(member);

        assertThat(savedWinecellar.getMember()).isEqualTo(savedMember);
        assertThat(savedMember.hasWinecellar(savedWinecellar)).isTrue();
    }
}
