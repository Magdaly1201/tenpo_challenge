package com.magdy.challenge.tenpo.core.sum.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magdy.challenge.tenpo.core.history.model.Status;
import com.magdy.challenge.tenpo.core.history.model.TypeTransaction;
import com.magdy.challenge.tenpo.core.message.service.MessageService;
import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;
import com.magdy.challenge.tenpo.core.sum.model.Sum;

public class SumService {

    public final PercentageService percentageService;
    public final MessageService messageService;


    public SumService(PercentageService percentageService, MessageService messageService) {
        this.percentageService = percentageService;
        this.messageService = messageService;
    }

    public Sum operationSumValuesAndPercentage(int value1, int value2) throws JsonProcessingException {
        Sum sumResponseDTO = new Sum(value1,value2,percentageService.obtainPercentage());
        this.messageService.createMessage(TypeTransaction.OPERATION,"magdaly",sumResponseDTO.json(), Status.OK);
        return sumResponseDTO;
    }
}
