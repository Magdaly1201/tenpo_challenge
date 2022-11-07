package com.magdy.challenge.tenpo.infrastructure.delivery.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserRequestDTO {

    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    //TODO: analizar como debe ser el password
    @Pattern(regexp = "^(?=.*[A-Z]{1})(?=.*[0-9]{2})(?=.*[a-z]).{8,12}")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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