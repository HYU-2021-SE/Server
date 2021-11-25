package com.dionysos.winecellar.domain.wine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionysos.winecellar.domain.wine.domain.Wine;

public interface WineRepository extends JpaRepository<Wine, Long> {
}
