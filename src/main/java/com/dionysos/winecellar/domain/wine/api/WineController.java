package com.dionysos.winecellar.domain.wine.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dionysos.winecellar.domain.member.config.LoginMemberId;
import com.dionysos.winecellar.domain.wine.domain.Wine;
import com.dionysos.winecellar.domain.wine.dto.WineCreateRequestDto;
import com.dionysos.winecellar.domain.wine.dto.WineDrankDto;
import com.dionysos.winecellar.domain.wine.dto.WineResponseDto;
import com.dionysos.winecellar.domain.wine.service.WineService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WineController {
    private final WineService wineService;

    @PostMapping("/api/wine")
    public ResponseEntity<WineResponseDto> create(@LoginMemberId Long memberId,
        @RequestBody WineCreateRequestDto wineCreateRequestDto) {
        try {
            Wine wine = wineService.create(memberId, wineCreateRequestDto);
            WineResponseDto wineResponseDto = WineResponseDto.from(wine);
            return ResponseEntity.ok(wineResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new WineResponseDto());
        }
    }

    @GetMapping("/api/wine/{id}")
    public ResponseEntity<WineResponseDto> get(@LoginMemberId Long memberId, @PathVariable("id") Long wineId) {
        Wine wine = wineService.find(wineId);
        WineResponseDto wineResponseDto = WineResponseDto.from(wine);
        return ResponseEntity.ok(wineResponseDto);
    }

    @PutMapping("/api/wine/{id}")
    public ResponseEntity<Void> drank(@LoginMemberId Long memberId, @PathVariable("id") Long wineId,
        @RequestBody WineDrankDto wineDrankDto) {
        wineService.drank(wineId, wineDrankDto);
        return ResponseEntity.ok().build();
    }
}
