package com.magdy.challenge.tenpo.infrastructure.exceptions;

import java.time.LocalDateTime;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String userMessage;
    private String systemMessage;
    private LocalDateTime timestamp;


    public CustomException(int code, String userMessage, String systemMessage) {
        super();
        this.code = code;
        this.userMessage = userMessage;
        this.systemMessage = systemMessage;
        this.timestamp = LocalDateTime.now();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

