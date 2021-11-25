package com.dionysos.winecellar.domain.winecellar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionysos.winecellar.domain.winecellar.domain.Winecellar;

public interface WinecellarRepository extends JpaRepository<Winecellar, Long> {
}
