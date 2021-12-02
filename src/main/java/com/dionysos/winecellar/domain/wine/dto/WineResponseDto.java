package com.dionysos.winecellar.domain.wine.dto;

import java.sql.Timestamp;

import com.dionysos.winecellar.domain.wine.domain.Wine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WineResponseDto {
    private Long wineId;
    private Long winecellarId;
    private String wineName;
    private Integer location;
    private String labelImage;
    private Integer vintage;
    private Timestamp purchaseDate;
    private Timestamp producedDate;

    public static WineResponseDto from(Wine wine) {
        return new WineResponseDto(wine.getWineId(), wine.getWinecellar().getWinecellarId(), wine.getWineName(),
            wine.getLocation().getLocation(), wine.getLabelImage(), wine.getVintage(), wine.getPurchaseDate(),
            wine.getProducedDate());
    }
}
