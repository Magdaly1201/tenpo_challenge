package com.magdy.challenge.tenpo.infrastructure.repository.dao;

import com.magdy.challenge.tenpo.infrastructure.repository.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryDao  extends JpaRepository<HistoryEntity, Long> {

    Optional<HistoryEntity> findOneByStatusAndTypeOrderByIdDesc(String status, String type);
}
