package com.dionysos.winecellar.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SocialAccessTokenDto {
    //todo set provider
    private final String accessToken;
}
