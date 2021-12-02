package com.dionysos.winecellar.domain.wine.domain;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "wineId")
@NoArgsConstructor
@Entity
@Getter
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wineId;
    private String wineName;
    @ManyToOne
    @JoinColumn(name = "winecellar_id")
    @NotNull
    private Winecellar winecellar;
    private Location location = Location.NONE;
    private String labelImage;
    private String corkImage;
    private Integer vintage; // only year
    private Timestamp purchaseDate;
    private Timestamp producedDate;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Wine(Long wineId, String wineName, Winecellar winecellar, Location location, String labelImage,
        String corkImage, Integer vintage, Timestamp purchaseDate, Timestamp producedDate) {
        setWinecellar(winecellar);
        this.wineId = wineId;
        this.wineName = wineName;
        this.location = location;
        this.labelImage = labelImage;
        this.corkImage = corkImage;
        this.vintage = vintage;
        this.purchaseDate = purchaseDate;
        this.producedDate = producedDate;
    }

    public void setWinecellar(Winecellar winecellar) {
        this.winecellar = winecellar;
        if (!winecellar.hasWine(this)) {
            winecellar.add(this);
        }
    }

    public enum Location {
        FIRST(1),
        SECOND(2),
        NONE(0),
        HISTORY(-1);

        private Integer location;

        Location(Integer location) {
            this.location = location;
        }

        public static Location of(Integer location) {
            return Arrays.stream(Location.values())
                .filter(l -> l.getLocation().equals(location))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 선반 위치입니다."));
        }

        public Integer getLocation() {
            return location;
        }
    }
}
