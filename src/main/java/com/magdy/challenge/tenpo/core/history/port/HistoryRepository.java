package com.magdy.challenge.tenpo.core.history.port;

import com.magdy.challenge.tenpo.infrastructure.repository.entity.HistoryEntity;

public interface HistoryRepository{

    void createTransaction(HistoryEntity historyEntity);

    float getLastPercentage();
}
