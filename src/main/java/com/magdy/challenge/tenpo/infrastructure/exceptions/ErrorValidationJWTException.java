package com.magdy.challenge.tenpo.infrastructure.exceptions;

public class ErrorValidationJWTException extends CustomException {

    private static final long serialVersionUID = 1L;
    private static final ExceptionCodes exceptionCode = ExceptionCodes.INVALID_TOKEN;

    public ErrorValidationJWTException(String cause) {
        super(exceptionCode.getCode(), exceptionCode.getUserMessage(), cause);
    }
}