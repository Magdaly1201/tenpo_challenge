package com.magdy.challenge.tenpo.infrastructure.delivery.dto;

public class UserDataResponseLogin {

    private String token;

    public UserDataResponseLogin(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
