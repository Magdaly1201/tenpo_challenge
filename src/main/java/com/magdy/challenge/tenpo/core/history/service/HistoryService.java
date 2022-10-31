package com.magdy.challenge.tenpo.core.history.service;

import com.magdy.challenge.tenpo.core.history.port.HistoryRepository;
import com.magdy.challenge.tenpo.infrastructure.repository.entity.HistoryEntity;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;

public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void createTransaction(String type, String userRequest, String payload, String status) {
        historyRepository.createTransaction(new HistoryEntity(LocalDateTime.now(), type, payload, userRequest, status));
    }

    @Cacheable(value = "HISTORY_PERCENTAGE")
    public float getLastPercentage() {
        //TODO add query to the history
        return historyRepository.getLastPercentage();
    }
}
