package com.dionysos.winecellar.domain.winecellar.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dionysos.winecellar.domain.member.config.LoginMemberId;
import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;
import com.dionysos.winecellar.domain.winecellar.dto.WinecellarCreateRequestDto;
import com.dionysos.winecellar.domain.winecellar.dto.WinecellarResponseDto;
import com.dionysos.winecellar.domain.winecellar.dto.WinecellarUpdateRequestDto;
import com.dionysos.winecellar.domain.winecellar.service.WincellarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WinecellarController {
    private final WincellarService wincellarService;

    @PostMapping("/api/winecellar")
    public ResponseEntity<WinecellarResponseDto> create(@LoginMemberId Long memberId,
        @RequestBody WinecellarCreateRequestDto winecellarCreateRequestDto) {
        try{
        Winecellar winecellar = wincellarService.create(memberId, winecellarCreateRequestDto);

        WinecellarResponseDto winecellarResponseDto = WinecellarResponseDto.from(winecellar);
            return ResponseEntity.ok(winecellarResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new WinecellarResponseDto());
        }
    }

    @GetMapping("/api/winecellar")
    public ResponseEntity<WinecellarResponseDto> get(@LoginMemberId Long memberId) {
        List<Winecellar> winecellars = wincellarService.findAll(memberId);
        return ResponseEntity.ok(WinecellarResponseDto.from(winecellars.get(0)));
    }

    @PutMapping("/api/winecellar")
    public ResponseEntity<WinecellarResponseDto> update(@LoginMemberId Long memberId,
        @RequestBody WinecellarUpdateRequestDto winecellarUpdateRequestDto) {
        Winecellar winecellar = wincellarService.update(memberId, winecellarUpdateRequestDto);
        return ResponseEntity.ok(WinecellarResponseDto.from(winecellar));
    }
}
