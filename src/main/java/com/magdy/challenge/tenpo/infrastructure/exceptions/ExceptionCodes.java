package com.magdy.challenge.tenpo.infrastructure.exceptions;

public enum ExceptionCodes {

    USER_NOT_FOUND(1, "Usuario no encontrada.", "Usuario no encontrada."),
    INVALID_TOKEN(2, "Token invalido.", "Token invalido"),
    MISSING_TOKEN(3, "Request sin token", "Request sin token"),
    EMAIL_USER_ALREADY_EXIST(4, "El email ingresado ya existe para un usuario", "El email ingresado ya existe para un usuario"),
    DATA_INTEGRATION_VIOLATION_EXCEPTION(5, "Ocurrio un error con la BD contacte con el admnistrador", "Ocurrio un error con la BD contacte con el admnistrador"),
    USERNAME_PASSWORD_NO_MATCH(6, "No coincide usuario o password.", "Usuario no encontrado."),
    NOT_FOUND_PERCENTAGE(7, "not se encontro last percentage.", "not se encontro last percentage");


    private int code;
    private String userMessage;
    private String systemMessage;

    ExceptionCodes(int code, String userMessage, String systemMessage) {
        this.code = code;
        this.userMessage = userMessage;
        this.systemMessage = systemMessage;
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
}