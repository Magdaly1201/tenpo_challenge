package com.magdy.challenge.tenpo.adapter.repository;

import com.magdy.challenge.tenpo.core.history.port.HistoryRepository;
import com.magdy.challenge.tenpo.infrastructure.repository.dao.HistoryDao;
import com.magdy.challenge.tenpo.infrastructure.repository.entity.HistoryEntity;

public class HistoryAdapterRepository implements HistoryRepository {

    private final HistoryDao historyDao;

    public HistoryAdapterRepository(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    @Override
    public void createTransaction(HistoryEntity historyEntity) {
        historyDao.save(historyEntity);
    }

    @Override
    public float getLastPercentage() {
        String percentage = historyDao.findFirstByStatusAndTypeOrderByDateTimeDesc("OK", "percentage").orElseThrow(() -> new RuntimeException("error obteniendo percentage")).getPayload();
        return Float.parseFloat(percentage);
    }
}
