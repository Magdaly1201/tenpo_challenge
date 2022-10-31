package com.magdy.challenge.tenpo.core.history.model;
import java.time.LocalDateTime;

public class History {

    private Long id;
    private LocalDateTime dateTime;
    private String type;
    private String payload;
    private String userRequest;
    private String status;

    public History(LocalDateTime dateTime, String type, String payload, String userRequest, String status) {
        this.dateTime = dateTime;
        this.type = type;
        this.payload = payload;
        this.userRequest = userRequest;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(String userRequest) {
        this.userRequest = userRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
