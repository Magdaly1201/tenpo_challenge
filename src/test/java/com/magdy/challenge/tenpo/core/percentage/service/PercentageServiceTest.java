package com.magdy.challenge.tenpo.core.percentage.service;

import com.magdy.challenge.tenpo.core.history.model.Status;
import com.magdy.challenge.tenpo.core.history.model.TypeTransaction;
import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.message.service.MessageService;
import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PercentageServiceTest {

    @InjectMocks
    private PercentageService percentageService;

    @Mock
    private HistoryService historyService;

    @Mock
    private MessageService messageService;

    @Mock
    private PercentageClient percentageClient;

    @Test
    @DisplayName("obtener porcentaje")
    void percentage() {
        Integer percentage = 10;
        Long userId = 10L;
        when(percentageClient.getPercentage()).thenReturn(Optional.of(percentage));
        doNothing().when(messageService).createMessage(TypeTransaction.PERCENTAGE, userId, percentage.toString(), Status.OK);

        float results = percentageService.obtainPercentage(userId);

        assertEquals(10, results);
        verify(percentageClient).getPercentage();
    }
}