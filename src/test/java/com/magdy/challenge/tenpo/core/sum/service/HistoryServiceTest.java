package com.magdy.challenge.tenpo.core.sum.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.core.history.port.HistoryRepository;
import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    private final LocalDateTime dummyTime = LocalDateTime.now();
    @InjectMocks
    private HistoryService historyService;
    @Mock
    private HistoryRepository historyRepository;

    @Test
    @DisplayName("Crear transaccion historica")
    void createTransaction() {

        String type = "OK";
        Long userId = 30L;
        String payload = "payloadTest";
        String status = "statusTest";

        doNothing().when(historyRepository).createTransaction(any());

        historyService.createTransaction(new History(LocalDateTime.now(), type, payload, userId, status));

        verify(historyRepository).createTransaction(any());

    }

    @Test
    void testValue() {
        ObjectMapper objectMapper = new ObjectMapper();


        String message = "{\"type\":\"OPERATION\"}";
        try {
            HistoryTest history = objectMapper.readValue(message, HistoryTest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testValue1() throws IOException {
        Gson g = new Gson();
        String jsonInString = "{\"type\":\"mkyong\"}";

        HistoryTest s = g.fromJson(jsonInString, HistoryTest.class);
    }

    private class HistoryTest {

        @JsonProperty(value = "type")
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}