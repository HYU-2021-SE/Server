package com.dionysos.winecellar.domain.winecellar.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dionysos.winecellar.domain.member.config.LoginMemberId;
import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;
import com.dionysos.winecellar.domain.winecellar.dto.WinecellarRequestDto;
import com.dionysos.winecellar.domain.winecellar.dto.WinecellarResponseDto;
import com.dionysos.winecellar.domain.winecellar.service.WincellarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WinecellarController {
    private final WincellarService wincellarService;

    @PostMapping("/api/winecellar")
    public ResponseEntity<WinecellarResponseDto> create(@LoginMemberId Long memberId,
        @RequestBody WinecellarRequestDto winecellarRequestDto) {
        Winecellar winecellar = wincellarService.create(memberId, winecellarRequestDto);

        WinecellarResponseDto winecellarResponseDto = WinecellarResponseDto.from(winecellar);
        return ResponseEntity.ok(winecellarResponseDto);
    }
}
