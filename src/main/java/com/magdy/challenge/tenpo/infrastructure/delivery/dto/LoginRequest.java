package com.magdy.challenge.tenpo.infrastructure.delivery.dto;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {

    @NotEmpty(message = "no puede ser vacio.")
    private String name;

    @NotEmpty(message = "no puede ser vacio.")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}