package com.dionysos.winecellar.domain.winecellar.dto;

import com.dionysos.winecellar.domain.wine.domain.Wine;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WineDto {
    private Long wineId;
    private String wineName;
    private Integer location;
    private String labelImage;
    private String corkImage;

    public static WineDto from(Wine wine) {
        return new WineDto(wine.getWineId(), wine.getWineName(), wine.getLocation().getLocation(), wine.getLabelImage(),
            wine.getCorkImage());
    }
}
