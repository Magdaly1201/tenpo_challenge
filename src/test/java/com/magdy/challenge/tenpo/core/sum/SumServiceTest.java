package com.magdy.challenge.tenpo.core.sum;

import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;
import com.magdy.challenge.tenpo.core.sum.service.SumService;
import com.magdy.challenge.tenpo.core.sum.model.Sum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SumServiceTest {

    @InjectMocks
    private SumService sumService;

    @Mock
    private PercentageService percentageService;

    @Test
    @DisplayName("operacion de Suma de valores y porcentaje")
    void operationSumValuesAndPercentage() {
        int value1 = 5;
        int value2 = 5;

        when(percentageService.obtainPercentage()).thenReturn(10F);

        Sum results = sumService.operationSumValuesAndPercentage(value1, value2);

        assertEquals(11, results);
        assertNotEquals(2, results);
    }

}