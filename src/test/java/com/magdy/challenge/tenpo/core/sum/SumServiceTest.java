package com.magdy.challenge.tenpo.core.sum;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magdy.challenge.tenpo.core.history.model.Status;
import com.magdy.challenge.tenpo.core.history.model.TypeTransaction;
import com.magdy.challenge.tenpo.core.message.service.MessageService;
import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;
import com.magdy.challenge.tenpo.core.sum.model.Sum;
import com.magdy.challenge.tenpo.core.sum.service.SumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SumServiceTest {

    @InjectMocks
    private SumService sumService;

    @Mock
    private MessageService messageService;

    @Mock
    private PercentageService percentageService;

    @Test
    @DisplayName("operacion de Suma de valores y porcentaje")
    void operationSumValuesAndPercentage() throws JsonProcessingException {
        int value1 = 5;
        int value2 = 5;
        Long userId = 30L;
        float percentage = 10;

        String  payload = "{\n" +
                "  \"value1\" : 5,\n" +
                "  \"value2\" : 5,\n" +
                "  \"sum\" : 10,\n" +
                "  \"percentageSum\" : 1.0,\n" +
                "  \"percentageValue\" : 10.0,\n" +
                "  \"total\" : 11.0\n" +
                "}";
        when(percentageService.obtainPercentage(userId)).thenReturn(10F);
        doNothing().when(messageService).createMessage(anyString(),anyLong(),anyString(),anyString());

        Sum results = sumService.operationSumValuesAndPercentage(value1, value2, userId);

        assertEquals(10, results.getSum());
        assertEquals(percentage, results.getPercentageValue());
        assertEquals(11, results.getTotal());

        assertNotEquals(2, results.getPercentageSum());
    }
}