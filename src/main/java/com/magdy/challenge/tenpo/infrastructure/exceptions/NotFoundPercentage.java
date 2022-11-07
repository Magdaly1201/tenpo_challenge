package com.magdy.challenge.tenpo.infrastructure.exceptions;

public class NotFoundPercentage extends CustomException {

    private static final long serialVersionUID = -50510158400280774L;
    private static final ExceptionCodes exceptionCode = ExceptionCodes.NOT_FOUND_PERCENTAGE;

    public NotFoundPercentage() {
        super(exceptionCode.getCode(), exceptionCode.getUserMessage(), exceptionCode.getSystemMessage());
    }

}
