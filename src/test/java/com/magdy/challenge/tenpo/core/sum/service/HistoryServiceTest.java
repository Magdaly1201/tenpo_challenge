package com.magdy.challenge.tenpo.core.sum.service;

import com.magdy.challenge.tenpo.core.history.port.HistoryRepository;
import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    @InjectMocks
    private HistoryService historyService;

    @Mock
    private HistoryRepository historyRepository;

    private final LocalDateTime dummyTime = LocalDateTime.now();

    @Test
    @DisplayName("Crear transaccion historica")
    void createTransaction(){

        String type = "OK";
        String userRequest ="userRequestTest";
        String payload="payloadTest";
        String status = "statusTest";

        doNothing().when(historyRepository).createTransaction(any());

        historyService.createTransaction(type,userRequest,payload,status);

        verify(historyRepository).createTransaction(any());

    }

}