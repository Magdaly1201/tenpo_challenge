package com.magdy.challenge.tenpo.adapter.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.sum.model.Sum;
import com.magdy.challenge.tenpo.core.sum.service.SumService;
import org.springframework.data.domain.Page;

public class SumEndpoints {

    private final SumService sumService;
    private final HistoryService historyService;

    public SumEndpoints(SumService sumService, HistoryService historyService) {
        this.sumService = sumService;
        this.historyService = historyService;
    }

    public Sum operationSumValuesAndPercentage(int value1, int value2, Long userId) throws JsonProcessingException {
        return sumService.operationSumValuesAndPercentage(value1, value2, userId);
    }

    public Page<History> getAllByPage(int page, int size) {
        return historyService.getAllByPage(page, size);
    }

}
