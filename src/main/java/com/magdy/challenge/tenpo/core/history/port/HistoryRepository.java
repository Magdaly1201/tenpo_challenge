package com.magdy.challenge.tenpo.core.history.port;

import com.magdy.challenge.tenpo.core.history.model.History;
import org.springframework.data.domain.Page;

public interface HistoryRepository {

    void createTransaction(History history);

    float getLastPercentage();

    Page<History> getAllByPage(int page, int size);
}
