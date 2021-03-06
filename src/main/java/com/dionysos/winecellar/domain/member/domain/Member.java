package com.dionysos.winecellar.domain.member.domain;

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

import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "memberId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberName;
    @NotNull
    private String email;
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
