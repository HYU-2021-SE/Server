package com.dionysos.winecellar.domain.winecellar.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dionysos.winecellar.domain.member.domain.Member;
import com.dionysos.winecellar.domain.wine.domain.Wine;
import com.dionysos.winecellar.domain.winecellar.dto.WinecellarUpdateRequestDto;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "winecellarId")
@NoArgsConstructor
@Entity
@Getter
public class Winecellar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long winecellarId;
    private WinecellarType type;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;
    private String nickName;
    @OneToMany(mappedBy = "winecellar")
    private List<Wine> wines = new ArrayList<>();
    private boolean lock;
    private String lockPassword;
    private String lightColor;
    private Integer temperature;
    private Integer humidity;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    private Winecellar(Long id, WinecellarType type, Member member, String nickName, List<Wine> wines, boolean lock,
        String lockPassword, String lightColor, Integer temperature, Integer humidity) {
        this.winecellarId = id;
        this.type = type;
        this.nickName = nickName;
        this.lock = lock;
        this.lockPassword = lockPassword;
        this.lightColor = lightColor;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wines = wines;
        if (Objects.isNull(this.wines)) {
            this.wines = new ArrayList<>();
        }
        setMember(member);
    }

    public void update(WinecellarUpdateRequestDto dto) {
        this.nickName = dto.getNickName();
        this.lock = dto.isLock();
        this.lockPassword = dto.getLockPassword();
        this.lightColor = dto.getLightColor();
        this.humidity = dto.getHumidity();
        this.temperature = dto.getTemperature();
    }

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

    public boolean isOwner(Long memberId) {
        return this.member.getMemberId().equals(memberId);
    }
}
