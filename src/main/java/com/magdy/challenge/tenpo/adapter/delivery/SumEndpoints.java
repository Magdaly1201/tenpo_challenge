package com.magdy.challenge.tenpo.adapter.delivery;

import com.magdy.challenge.tenpo.core.sum.SumService;

public class SumEndpoints {

    private final SumService sumService;

    public SumEndpoints(SumService sumService) {
        this.sumService = sumService;
    }

    public float operationSumValuesAndPercentage(int value1, int value2) {
        return sumService.operationSumValuesAndPercentage(value1, value2);
    }

}
