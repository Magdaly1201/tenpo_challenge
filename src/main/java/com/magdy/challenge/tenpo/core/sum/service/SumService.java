package com.magdy.challenge.tenpo.core.sum.service;

import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;
import com.magdy.challenge.tenpo.core.sum.model.Sum;

public class SumService {

    public final PercentageService percentageService;

    public SumService(PercentageService percentageService) {
        this.percentageService = percentageService;
    }

    public Sum operationSumValuesAndPercentage(int value1, int value2) {
        return new Sum(value1,value2,percentageService.obtainPercentage());
    }

    private float sum(float value1, float value2) {
        return value1 + value2;
    }
}
