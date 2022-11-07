package com.magdy.challenge.tenpo.core.percentage.service;

import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PercentageServiceTest {

    @InjectMocks
    private PercentageService percentageService;

    @Mock
    private HistoryService historyService;

    @Mock
    private PercentageClient percentageClient;

    @Test
    @DisplayName("obtener porcentaje")
    void percentage() {
        Integer percentage = 10;
        Long userId = 10L;
        when(percentageClient.getPercentage()).thenReturn(Optional.of(percentage));

        float results = percentageService.obtainPercentage(userId);

        assertEquals(10, results);
        verify(percentageClient).getPercentage();
    }

}