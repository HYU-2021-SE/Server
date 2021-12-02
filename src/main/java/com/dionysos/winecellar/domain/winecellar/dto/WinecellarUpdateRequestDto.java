package com.dionysos.winecellar.domain.winecellar.dto;

import lombok.Getter;

@Getter
public class WinecellarUpdateRequestDto {
    private Long winecellarId;
    private String nickName;
    private boolean lock;
    private String lockPassword;
    private String lightColor;
}
