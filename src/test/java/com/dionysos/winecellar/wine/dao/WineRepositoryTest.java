package com.dionysos.winecellar.wine.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dionysos.winecellar.member.dao.MemberRepository;
import com.dionysos.winecellar.member.domain.Member;
import com.dionysos.winecellar.wine.domain.Wine;
import com.dionysos.winecellar.winecellar.dao.WinecellarRepository;
import com.dionysos.winecellar.winecellar.domain.Winecellar;

@DataJpaTest
class WineRepositoryTest {
    @Autowired
    WineRepository wineRepository;
    @Autowired
    WinecellarRepository winecellarRepository;
    @Autowired
    MemberRepository memberRepository;

    Winecellar winecellar;
    Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
            .build();
        memberRepository.save(member);

        winecellar = Winecellar.builder()
            .member(new Member())
            .id(1L)
            .nickName("cellar")
            .build();
        winecellarRepository.save(winecellar);
    }

    @Test
    @DisplayName("와인 등록")
    void save() {
        Wine wine = Wine.builder()
            .wineId(1L)
            .wineName("wine")
            .winecellar(winecellar)
            .build();

        Wine savedWine = wineRepository.save(wine);
        Winecellar savedWinecellar = winecellarRepository.save(winecellar);

        assertThat(savedWine.getWinecellar()).isEqualTo(winecellar);
        assertThat(savedWinecellar.hasWine(savedWine)).isTrue();
    }

}
