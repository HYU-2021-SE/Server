package com.dionysos.winecellar.domain.winecellar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dionysos.winecellar.domain.member.dao.MemberRepository;
import com.dionysos.winecellar.domain.member.domain.Member;
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

    public Winecellar create(Long memberId, WinecellarCreateRequestDto winecellarCreateRequestDto) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid member"));
        Winecellar winecellar = Winecellar.builder()
            .member(member)
            .type(WinecellarType.of(winecellarCreateRequestDto.getSerialNo()))
            .build();
        return winecellarRepository.save(winecellar);
    }

    public List<Winecellar> findAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid member"));
        return winecellarRepository.findAllByMember(member);
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
