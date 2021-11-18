package com.dionysos.winecellar.wine.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dionysos.winecellar.winecellar.domain.Winecellar;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "wineId")
@NoArgsConstructor
@AllArgsConstructor
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
    private Location location;
    private String labelImage;
    private String corkImage;
    private Timestamp purchaseDate;
    private Timestamp producedDate;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

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
    }
}
