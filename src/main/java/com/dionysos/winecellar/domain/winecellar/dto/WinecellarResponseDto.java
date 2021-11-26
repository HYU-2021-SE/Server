package com.dionysos.winecellar.domain.winecellar.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;
import com.dionysos.winecellar.domain.winecellar.domain.WinecellarType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WinecellarResponseDto {
    private Long winecellarId;
    private WinecellarType type;
    private String nickName;
    private boolean lock;
    private String lockPassword;
    private String lightColor;
    private List<WineDto> wineDtos;

    public static WinecellarResponseDto from(Winecellar winecellar) {
        List<WineDto> wineDtos = winecellar.getWines()
            .stream()
            .map(WineDto::from)
            .collect(Collectors.toList());

        return new WinecellarResponseDto(winecellar.getWinecellarId(), winecellar.getType(),
            winecellar.getNickName(), winecellar.isLock(), winecellar.getLockPassword(), winecellar.getLightColor(),
            wineDtos);
    }
}
