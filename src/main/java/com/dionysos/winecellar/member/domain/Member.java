package com.dionysos.winecellar.member.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dionysos.winecellar.winecellar.domain.Winecellar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "memberId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberName;
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Winecellar> winecellars = new ArrayList<>();
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public void add(Winecellar winecellar) {
        this.winecellars.add(winecellar);
    }

    public boolean hasWinecellar(Winecellar winecellar) {
        return this.winecellars.contains(winecellar);
    }
}
