package com.dionysos.winecellar.domain.wine.dto;

import java.sql.Timestamp;

import lombok.Getter;

@Getter
public class WineCreateRequestDto {
    private Long winecellarId;
    private String wineName;
    private Integer location;
    private String labelImage;
    private Integer vintage;
    private Timestamp purchaseDate;
    private Timestamp producedDate;
}
