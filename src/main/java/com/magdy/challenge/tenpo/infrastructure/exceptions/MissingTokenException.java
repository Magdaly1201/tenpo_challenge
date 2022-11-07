package com.magdy.challenge.tenpo.infrastructure.exceptions;

public class MissingTokenException extends CustomException {

    private static final long serialVersionUID = 1L;
    private static final ExceptionCodes exceptionCode = ExceptionCodes.MISSING_TOKEN;

    public MissingTokenException(String cause) {
        super(exceptionCode.getCode(), exceptionCode.getUserMessage(), cause);
    }
}
