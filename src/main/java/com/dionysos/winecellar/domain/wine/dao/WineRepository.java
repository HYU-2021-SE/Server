package com.dionysos.winecellar.domain.wine.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionysos.winecellar.domain.wine.domain.Wine;
import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
    List<Wine> findAllByWinecellar(Winecellar winecellar);
}
