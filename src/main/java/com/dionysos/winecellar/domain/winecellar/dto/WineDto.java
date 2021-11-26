package com.dionysos.winecellar.domain.winecellar.dto;

import com.dionysos.winecellar.domain.wine.domain.Wine;
import com.dionysos.winecellar.domain.wine.domain.Wine.Location;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WineDto {
    private Long wineId;
    private String wineName;
    private Location location;
    private String labelImage;

    public static WineDto from(Wine wine) {
        return new WineDto(wine.getWineId(), wine.getWineName(), wine.getLocation(), wine.getLabelImage());
    }
}
