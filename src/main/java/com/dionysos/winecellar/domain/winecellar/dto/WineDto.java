package com.dionysos.winecellar.domain.winecellar.dto;

import com.dionysos.winecellar.domain.wine.domain.Wine;
import com.dionysos.winecellar.domain.wine.domain.Wine.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WineDto {
    private Long wineId;
    private String wineName;
    private Location location;
    private String labelImage;

    public static WineDto from(Wine wine) {
        System.out.println(wine.toString());
        return new WineDto(wine.getWineId(), wine.getWineName(), wine.getLocation(), wine.getLabelImage());
    }
}
