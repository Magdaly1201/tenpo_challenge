package com.magdy.challenge.tenpo.core.history.service;

import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.core.history.port.HistoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import static com.magdy.challenge.tenpo.infrastructure.app.config.CacheConfig.HISTORY_PERCENTAGE;

public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void createTransaction(History history) {
        historyRepository.createTransaction(history);
    }

    @Cacheable(value = HISTORY_PERCENTAGE)
    public float getLastPercentage() {
        return historyRepository.getLastPercentage();
    }

    public Page<History> getAllByPage(int page, int size ){
        return historyRepository.getAllByPage(page, size);
    }

}
