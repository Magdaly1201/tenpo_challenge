package com.magdy.challenge.tenpo.infrastructure.delivery.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginRequest {

    @NotEmpty(message = "no puede ser vacio.")
    @Email
    private String email;

    @NotEmpty(message = "no puede ser vacio.")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}