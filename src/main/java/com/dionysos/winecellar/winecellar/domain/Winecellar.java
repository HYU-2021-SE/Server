package com.dionysos.winecellar.winecellar.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dionysos.winecellar.member.domain.Member;
import com.dionysos.winecellar.wine.domain.Wine;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "winecellarId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
@Data
public class Winecellar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long winecellarId;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;
    private String nickName;
    @OneToMany(mappedBy = "winecellar")
    @Builder.Default
    private List<Wine> wines = new ArrayList<>();
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public boolean hasWine(Wine wine) {
        return this.wines.contains(wine);
    }

    public void add(Wine wine) {
        this.wines.add(wine);
    }

    public void setMember(Member member) {
        this.member = member;
        if (!member.hasWinecellar(this)) {
            member.add(this);
        }
    }
}
