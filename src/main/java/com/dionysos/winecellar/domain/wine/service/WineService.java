package com.dionysos.winecellar.domain.wine.service;

import org.springframework.stereotype.Service;

import com.dionysos.winecellar.domain.wine.dao.WineRepository;
import com.dionysos.winecellar.domain.wine.domain.Wine;
import com.dionysos.winecellar.domain.wine.dto.WineCreateRequestDto;
import com.dionysos.winecellar.domain.wine.dto.WineDrankDto;
import com.dionysos.winecellar.domain.winecellar.dao.WinecellarRepository;
import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WineService {
    private final WineRepository wineRepository;
    private final WinecellarRepository winecellarRepository;

    public Wine create(Long memberId, WineCreateRequestDto wineCreateRequestDto) {
        Winecellar winecellar = this.winecellarRepository.findById(wineCreateRequestDto.getWinecellarId())
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 와인셀러입니다"));

        if (!winecellar.isOwner(memberId)) {
            throw new IllegalArgumentException("와인셀러의 소유자가 아닙니다.");
        }

        Wine wine = Wine.builder()
            .winecellar(winecellar)
            .wineName(wineCreateRequestDto.getWineName())
            .labelImage(wineCreateRequestDto.getLabelImage())
            .location(Wine.Location.of(wineCreateRequestDto.getLocation()))
            .vintage(wineCreateRequestDto.getVintage())
            .purchaseDate(wineCreateRequestDto.getPurchaseDate())
            .producedDate(wineCreateRequestDto.getProducedDate())
            .build();

        this.winecellarRepository.save(winecellar);
        return this.wineRepository.save(wine);
    }

    public Wine find(Long wineId) {
        return this.wineRepository.findById(wineId)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 와인입니다."));
    }

    public void drank(Long wineId, WineDrankDto wineDrankDto) {
        Wine wine = this.wineRepository.findById(wineId)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 와인입니다."));

        wine.drank(wineDrankDto.getCorkImage());

        wineRepository.save(wine);
    }
}
