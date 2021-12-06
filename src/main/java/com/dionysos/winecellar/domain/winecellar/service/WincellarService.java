package com.dionysos.winecellar.domain.winecellar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dionysos.winecellar.domain.member.dao.MemberRepository;
import com.dionysos.winecellar.domain.member.domain.Member;
import com.dionysos.winecellar.domain.wine.dao.WineRepository;
import com.dionysos.winecellar.domain.wine.domain.Wine;
import com.dionysos.winecellar.domain.winecellar.dao.WinecellarRepository;
import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;
import com.dionysos.winecellar.domain.winecellar.domain.WinecellarType;
import com.dionysos.winecellar.domain.winecellar.dto.WinecellarCreateRequestDto;
import com.dionysos.winecellar.domain.winecellar.dto.WinecellarUpdateRequestDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WincellarService {
    private final MemberRepository memberRepository;
    private final WinecellarRepository winecellarRepository;
    private final WineRepository wineRepository;

    public Winecellar create(Long memberId, WinecellarCreateRequestDto winecellarCreateRequestDto) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid member"));
        Winecellar winecellar = Winecellar.builder()
            .member(member)
            .type(WinecellarType.of(winecellarCreateRequestDto.getSerialNo()))
            .build();
        return winecellarRepository.save(winecellar);
    }

    public Winecellar findAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid member"));
        List<Winecellar> winecellars = winecellarRepository.findAllByMember(member);
;        Winecellar winecellar = winecellars.get(0);
        List<Wine> wines = wineRepository.findAllByWinecellar(winecellar);
        winecellar.setWines(wines);
        return winecellar;
    }

    public Winecellar update(Long memberId, WinecellarUpdateRequestDto winecellarUpdateRequestDto) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid member"));
        Winecellar winecellar = winecellarRepository.findByWinecellarIdAndMember(
            winecellarUpdateRequestDto.getWinecellarId(), member);
        winecellar.update(winecellarUpdateRequestDto);
        return winecellarRepository.save(winecellar);
    }
}
