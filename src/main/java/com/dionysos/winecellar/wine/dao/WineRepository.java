package com.dionysos.winecellar.wine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionysos.winecellar.wine.domain.Wine;

public interface WineRepository extends JpaRepository<Wine, Long> {
}
