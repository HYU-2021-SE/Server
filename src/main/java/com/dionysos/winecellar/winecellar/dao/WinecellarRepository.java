package com.dionysos.winecellar.winecellar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionysos.winecellar.winecellar.domain.Winecellar;

public interface WinecellarRepository extends JpaRepository<Winecellar, Long> {
}
