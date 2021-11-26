package com.dionysos.winecellar.domain.wine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionysos.winecellar.domain.wine.domain.Wine;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
}
