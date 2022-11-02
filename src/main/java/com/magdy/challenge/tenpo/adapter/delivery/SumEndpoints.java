package com.magdy.challenge.tenpo.adapter.delivery;

import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.sum.SumService;
import org.springframework.data.domain.Page;

public class SumEndpoints {

    private final SumService sumService;
    private final HistoryService historyService;

    public SumEndpoints(SumService sumService, HistoryService historyService) {
        this.sumService = sumService;
        this.historyService = historyService;
    }

    public float operationSumValuesAndPercentage(int value1, int value2) {
        return sumService.operationSumValuesAndPercentage(value1, value2);
    }

    public Page<History> getAllByPage(int page, int size){
        return historyService.getAllByPage(page, size);
    }

}
