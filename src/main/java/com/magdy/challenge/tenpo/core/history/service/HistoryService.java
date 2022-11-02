package com.magdy.challenge.tenpo.core.history.service;

import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.core.history.port.HistoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void createTransaction(String type, String userRequest, String payload, String status) {
        historyRepository.createTransaction(new History(LocalDateTime.now(), type, payload, userRequest, status));
    }

    @Cacheable(value = "HISTORY_PERCENTAGE")
    public float getLastPercentage() {
        return historyRepository.getLastPercentage();
    }

    public Page<History> getAllByPage(int page, int size ){
        return historyRepository.getAllByPage(page, size);
    }

}
